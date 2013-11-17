#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/sem.h>

#define N 10;
#define T 2;

struct sembuf * p;
struct sembuf * v;

typedef struct _Message
{
	int type;
	char * value;
} Message;

void initSembuf(int nb);
void eraseSembuf();
void initMessages(Message * msg, int value, int n);
void eraseMessages(Message * msg, int n);

// #########################################################
// #########################################################

int main(int argc, char const *argv[])
{
	if( argc < 3) 
	{
		printf("Use : %s [NbProducteurs] [NbConsomateur]\n", argv[0]);
		exit(1);
	}
	int nbProd = atoi(argv[1]);
	int nbCons = atoi(argv[2]);
	int nbType = T;
	int n = N;
	int i;


// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// ~~~~~~~~~ Variable partager ~~~~~~~~~~~

// tableau message
	int semidMessage = shmget( ftok("keyVar/message",1), (sizeof(Message) * n), IPC_CREAT | 0666 );
	int semidValue = shmget( ftok("keyVar/value",1), (sizeof(char) * n * 32), IPC_CREAT | 0666 );
	Message * msgs = shmat(semidMessage, NULL,0);
	initMessages(msgs, semidValue, n);
	shmdt(msgs);
// nombre message écris
	int semidNb = shmget( ftok("keyVar/nb",1), sizeof(int), IPC_CREAT | 0666 );
	int * nbMessage = shmat(semidNb, NULL, 0);
	*nbMessage = 0;
	shmdt(nbMessage);
// indice écriture
	int semidIE = shmget( ftok("keyVar/ie",1), sizeof(int), IPC_CREAT | 0666 );
	int * indice = shmat(semidIE, NULL, 0);
	*indice = 0;
	shmdt(indice);
// indice lecture
	int semidIL = shmget( ftok("keyVar/il",1), sizeof(int), IPC_CREAT | 0666 );
	indice = shmat(semidIL, NULL, 0);
	*indice = 0;
	shmdt(indice);

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~	
// ~~~~~~~ INIT SEMAPHORE ~~~~~~~~~

// sémaphore sur écriture boite message
	int semidWrite = semget(ftok("keySem/keymes",1), n, IPC_CREAT | 0666);
	for (i = 0; i < n; ++i)
		semctl(semidWrite, i, SETVAL, 1);
	initSembuf(n);
// sémaphore sur des variables
	int semidCount = semget(ftok("keySem/keymem",1), 1, IPC_CREAT | 0666);
	semctl(semidCount, 0, SETVAL, 1);
	int semidSIE = semget(ftok("keySem/ie",1), 1, IPC_CREAT | 0666);
	semctl(semidSIE, 0, SETVAL, 1);
	int semidSIL = semget(ftok("keySem/il",1), 1, IPC_CREAT | 0666);
	semctl(semidSIL, 0, SETVAL, 1);
// sémaphore sur producteur & consomateur
	int semidProd = semget(ftok("keySem/keypro",1), 1, IPC_CREAT | 0666);
	semctl(semidProd, 0, SETVAL, n);
	int semidCons = semget(ftok("keySem/keycon",1), 1, IPC_CREAT | 0666);
	semctl(semidCons, 0, SETVAL, 0);

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// CREATION DES PRODUCTEUR

	for (i = 0; i < nbProd; ++i)
	{
		if( fork() == 0)
		{
			Message * m = shmat(semidMessage, NULL,0);

			int * nb = shmat(semidNb, NULL, 0);
			int * ie = shmat(semidIE, NULL, 0);
			int j;
			// for (j = 0; j < n; ++j)
			// {
				semop(semidProd, &p[0], 1);

		// ~~~~ Write message && incremente indice
				semop(semidSIE, &p[0], 1);
				int ind = *ie;
				semop(semidWrite, &p[ind], 1);
				sprintf(m[ind].value,"%d:Message%d", i, ind);
				printf("PROD %d - %s\n", i, m[ind].value );
				*ie = ind + 1;
				semop(semidSIE, &v[0], 1);
				semop(semidWrite, &v[ind], 1);

			// incrementation du nombre de message
				semop(semidCount, &p[0], 1);
				*nb = *nb + 1;
				semop(semidCount, &v[0], 1);
				semop(semidCons, &v[0], 1);
			// }
			shmdt(m);
			shmdt(nb);
			shmdt(ie);
			exit(0);
		}
	}

// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
// CREATION DES CONSOMATEURS

	for (i = 0; i < nbCons; ++i)
	{
		if( fork() == 0)
		{
			Message * m = shmat(semidMessage, NULL,0);
			int * nb = shmat(semidNb, NULL, 0);
			int * il = shmat(semidIL, NULL, 0);
			
			int j;

			// for (j = 0; j < n; ++j)
			// {
				semop(semidCons, &p[0], 1);

				semop(semidSIL, &p[0], 1);
				int indice = *il;
				printf("CONS %d - %s\n", i, m[indice].value );
				*il = indice +1;
				semop(semidSIL, &v[0], 1);

				semop(semidCount, &p[0], 1);
				*nb = *nb - 1;
				semop(semidCount, &v[0], 1);
				semop(semidProd, &v[0], 1);
			// }
			shmdt(m);
			shmdt(nb);
			shmdt(il);
			exit(0);
		}
	}

	for(i=0; i < nbCons+nbProd; ++i)
		wait(0);

	semctl(semidWrite, 0, IPC_RMID, 0);
	semctl(semidCount, 0, IPC_RMID, 0);
	semctl(semidProd, 0, IPC_RMID, 0);
	semctl(semidCons, 0, IPC_RMID, 0);
	eraseSembuf();

	shmctl(semidMessage, IPC_RMID, NULL);
	shmctl(semidValue, IPC_RMID, NULL);

	// shmdt(semidNb);
	shmctl(semidNb, IPC_RMID, NULL);
	shmctl(semidIE, IPC_RMID, NULL);
	shmctl(semidIL, IPC_RMID, NULL);

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

void initMessages(Message * msg, int value, int n)
{
	char * valuees = shmat(value, NULL,0);
	int i;
	for (i = 0; i < n; ++i)
	{
		msg[i].value = valuees + (32*i);
		sprintf(msg[i].value, "message%d", i);
		msg[i].type = 0;
		// printf("alloc\n");
	}

	// for (i = 0; i < n; ++i)
	// {
	// 	printf("%s\n", msg[i].value);
	// }
}

void eraseMessages(Message * msg, int n)
{
	int i;
	for (i = 0; i < n; ++i)
		free(msg[i].value);
	free(msg);
}