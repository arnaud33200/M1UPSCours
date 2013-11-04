#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/sem.h>

#define N 10;
struct sembuf * p;
struct sembuf * v;

void initSembuf(int nb);
void eraseSembuf();

int main(int argc, char const *argv[])
{
	if( argc < 3) 
	{
		printf("Use : %s [NbProducteurs] [NbConsomateur]\n", argv[0]);
		exit(1);
	}
	int nbProd = atoi(argv[1]);
	int nbCons = atoi(argv[2]);

// nombre de message 
	int n = N;
	int i;
	int nbFils = 20;
	int pidFils[nbFils];

// ~~~~~~~~~ Variable partager ~~~~~~~~~~~

// tableau message
	int semidMessage = shmget( ftok("keyVar/message",1), (sizeof(char) * 5 * n), IPC_CREAT | 0666 );
// nombre message
	int semidNb = shmget( ftok("keyVar/nb",1), sizeof(int), IPC_CREAT | 0666 );
// indice dernier message
	int semidInd = shmget( ftok("keyVar/ind",1), sizeof(int), IPC_CREAT | 0666 );
	
// // ~~~~~~~ INIT SEMAPHORE ~~~~~~~~~
// 	// variable partager pour le sémaphore
// 	int semid = semget(ftok("key/keymes",1), 1, IPC_CREAT | 0666);
// 	for (i = 0; i < nbFils; ++i)
// 	{
// 		semctl(semid, i, SETVAL, 0);
// 	}
// 	// Operation des sémaphore : P & V
// 	initSembuf(nbFils);
// 	// struct sembuf p; p.sem_num = 0; p.sem_op = -1; p.sem_flg = 0;
// 	// struct sembuf v; v.sem_num = 0; v.sem_op = 1; v.sem_flg = 0;

// // ~~~~~~~~~ LANCEMENT DES NBFILS PAR LE PÈRE ~~~~~~~~~~
	
// 	for (i = 0; i < nbFils; ++i)
// 	{
// 		if( (pidFils[i] = fork()) == 0 ) // fils
// 		{
// 			int nb = i+1;
// 			int j;
// 			for (j = 0; j < n; ++j)
// 			{
// 				semop(semid, &p[i], 1);
// 				printf("Fils %d Message\n", nb);
// 				semop(semid, &v[(i+1)%nbFils], 1);
// 			}
// 			exit(0);
// 		}
// 	}

// 	semop(semid, &v[0], 1);
// 	for (i = 0; i < nbFils; ++i)
// 	{
// 		wait(0);
// 	}
	
// 	printf("########### FIN ###########\n");

// 	semctl(semid, 0, IPC_RMID, 0);
// 	eraseSembuf();
	shmdt(semidMessage);
	shmdt(semidNb);
	shmdt(semidInd);
	shmctl(semidMessage, IPC_RMID, NULL);
	shmctl(semidNb, IPC_RMID, NULL);
	shmctl(semidInd, IPC_RMID, NULL);
	return 0;
}

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