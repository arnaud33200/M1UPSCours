#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/sem.h>

#define N 100;
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

	
// // ~~~~~~~ INIT SEMAPHORE ~~~~~~~~~
	int semidWrite = semget(ftok("keySem/keymes",1), n, IPC_CREAT | 0666);
	for (i = 0; i < n; ++i)
	{
		semctl(semidWrite, i, SETVAL, 1);
	}
	initSembuf(n);
	int semidCount = semget(ftok("keySem/keymem",1), 1, IPC_CREAT | 0666);
	semctl(semidCount, 0, SETVAL, 1);
	int semidProd = semget(ftok("keySem/keypro",1), 1, IPC_CREAT | 0666);
	semctl(semidProd, 0, SETVAL, n);
	int semidCons = semget(ftok("keySem/keycon",1), 1, IPC_CREAT | 0666);
	semctl(semidCons, 0, SETVAL, 0);

// CREATION DES PRODUCTEUR
	for (i = 0; i < nbProd; ++i)
	{
		if( fork() == 0)
		{
			Message * m = shmat(semidMessage, NULL,0);
			int * nb = shmat(semidNb, NULL, 0);
			int j;
			for (j = 0; j < n; ++j)
			{
				semop(semidProd, &p[0], 1);

		// ~~~~ Write message
				semop(semidWrite, &p[j], 1);
				// printf("DEBUT\n");
				sprintf(m[j].value,"%d:Message%d", i, j);
				printf("PROD %d - %s\n", i, m[j].value );
				// m[j].value = "TOTO";
				semop(semidWrite, &v[j], 1);

				semop(semidCount, &p[0], 1);
				*nb = *nb + 1;
				semop(semidCount, &v[0], 1);
				semop(semidCons, &v[0], 1);
			}
			shmdt(m);
			shmdt(nb);
			exit(0);
		}
	}

	for (i = 0; i < nbCons; ++i)
	{
		if( fork() == 0)
		{
			Message * m = shmat(semidMessage, NULL,0);
			int * nb = shmat(semidNb, NULL, 0);
			int j;
			for (j = 0; j < n; ++j)
			{
				semop(semidCons, &p[0], 1);

				printf("CONS %d - %s\n", i, m[j].value );
				semop(semidCount, &p[0], 1);
				*nb = *nb - 1;
				semop(semidCount, &v[0], 1);
				semop(semidProd, &v[0], 1);
			}
			shmdt(m);
			shmdt(nb);
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

	for (i = 0; i < n; ++i)
	{
		printf("%s\n", msg[i].value);
	}
}

void eraseMessages(Message * msg, int n)
{
	int i;
	for (i = 0; i < n; ++i)
		free(msg[i].value);
	free(msg);
}