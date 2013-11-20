#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/sem.h>
#include <signal.h>

struct sembuf * p;
struct sembuf * v;

int semidCptPompe;
int semidPistolets;
int semidCaisse;
int semidCptZero;
int semidCptAcces;

void initSembuf(int nb);
void eraseSembuf();
void caisse();
void client(int numPompe, int client);

// #########################################################
// #########################################################

int main(int argc, char const *argv[])
{
	if( argc < 2) 
	{
		printf("Use : %s [NbConsomateur]\n", argv[0]);
		exit(1);
	}
	int n = atoi(argv[1]);
	int i;


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// ~~~~~~~~~ Variable partager ~~~~~~~~~~~

// compteur pompe
	semidCptPompe = shmget( ftok("keyVar/pompe",1), (sizeof(int)*2), IPC_CREAT | 0666 );
	int * cpt = shmat(semidCptPompe, NULL, 0);
	cpt[0] = 0; cpt[1] = 0;
	shmdt(cpt);


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
// ~~~~~~~ INIT SEMAPHORE ~~~~~~~~~

	initSembuf(2);
// sémaphore sur accès pompe
	semidPistolets = semget(ftok("keySem/pistolet",1), 2, IPC_CREAT | 0666);
	for (i = 0; i < 2; ++i)
		semctl(semidPistolets, i, SETVAL, 1);
// sémaphore accès au compteur de pompe
	semidCptAcces = semget(ftok("keySem/acces",1), 2, IPC_CREAT | 0666);
	for (i = 0; i < 2; ++i)
		semctl(semidCptAcces, i, SETVAL, 1);
// sémaphore sur remise compteur à zero
	semidCptZero = semget(ftok("keySem/cpt",1), 2, IPC_CREAT | 0666);
	for (i = 0; i < 2; ++i)
		semctl(semidCptZero, i, SETVAL, 0);
// semaphore sur attente payement caisse
	semidCaisse = semget(ftok("keySem/caisse",1), 1, IPC_CREAT | 0666);
	semctl(semidCaisse, 0, SETVAL, 0);

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// CREATION DES CLIENTS

	for (i = 0; i < n; ++i)
	{
		if( fork() == 0)
		{
			client((i%2), i);
			exit(0);
		}
	}

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// CREATION DE LA CAISSE
	pid_t pidCaisse;
	if( (pidCaisse = fork()) == 0)
	{
		caisse();
		exit(0);
	}

	for(i=0; i < n; ++i)
		wait(0);
// job caissier terminé car n'a plus client
	kill(pidCaisse, 9);

	semctl(semidPistolets, 0, IPC_RMID, 0);
	semctl(semidCaisse, 0, IPC_RMID, 0);
	semctl(semidCptZero, 0, IPC_RMID, 0);
	eraseSembuf();

	shmctl(semidCptPompe, IPC_RMID, NULL);

	return 0;
}

// #########################################################
// #########################################################

void initSembuf(int nb)
{
	p = malloc(sizeof(struct sembuf)*nb);
	v = malloc(sizeof(struct sembuf)*nb);
	int i;
	for (i = 0; i < nb; ++i)
	{
		p[i].sem_num = i; p[i].sem_op = -1; p[i].sem_flg = 0;
		v[i].sem_num = i; v[i].sem_op = 1; v[i].sem_flg = 0;
	}
}

void eraseSembuf()
{
	free(p);
	free(v);
}

void caisse()
{
	while( 0 == 0 )
	{
		int * cpts = shmat(semidCptPompe, NULL, 0);
		// think about this borring job
		semop(semidCaisse, &p[0], 1);
		semop(semidCptAcces, &p[1], 1);
		{
			printf("     CAISSIER RECOIT PAYEMENT\n");
			// PAYEMENT RECUT
			cpts[1] = 0;
			semop(semidCptZero, &v[1], 1);
		}
		semop(semidCptAcces, &v[1], 1);
	}
}

void client(int numPompe, int client)
{
	int * cpts = shmat(semidCptPompe, NULL, 0);
	semop(semidPistolets, &p[numPompe], 1);
	{
		semop(semidCptAcces, &p[numPompe], 1);
		{
			if( cpts[numPompe] > 0)
				semop(semidCptZero, &p[numPompe], 1);
			cpts[numPompe] = 10000;
		}
		semop(semidCptAcces, &v[numPompe], 1);
		// CONSO ESSENCE
		// REPOSE PISTOLET
		printf("CLIENT %d - FINI ESSENCE POMPE ( %d )\n", client, numPompe);
	}
	semop(semidPistolets, &v[numPompe], 1);
	
	// PAYEMENT
	if( numPompe == 1 )
		semop(semidCaisse, &v[0], 1);
	else
	{
		semop(semidCptAcces, &p[numPompe], 1);
		{
			cpts[numPompe] = 0;
		}
		semop(semidCptAcces, &v[numPompe], 1);
		semop(semidCptZero, &v[numPompe], 1);
	}
	printf("CLIENT %d - FINI PAYEMENT POMPE ( %d )\n", client, numPompe);

}