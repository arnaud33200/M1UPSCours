#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/sem.h>

#define N 100000;

int main(int argc, char const *argv[])
{
	
	int n = N;
	int nbFils = 10;
	int pidFils[nbFils];

	// list de IPC
	
// ~~~~~~~ INIT SEMAPHORE ~~~~~~~~~
	// variable partager pour le sémaphore
	int semid = semget(IPC_PRIVATE, 1, IPC_CREAT | 0666);
	semctl(semid, 0, SETVAL, 1);
	// Operation des sémaphore : P & V
	struct sembuf p; p.sem_num = 0; p.sem_op = -1; p.sem_flg = 0;
	struct sembuf v; v.sem_num = 0; v.sem_op = 1; v.sem_flg = 0;

// ~~~~~~~~~ LANCEMENT DES NBFILS PAR LE PÈRE ~~~~~~~~~~
	int i;
	for (i = 0; i < nbFils; ++i)
	{
		if( (pidFils[i] = fork()) == 0 ) // fils
		{
			int nb = i+1;
			int j;
			for (j = 0; j < n; ++j)
			{
				// semop(semid, &p, 1);
				// semop(semid, &v, 1);
				printf("Fils %d Message\n");
			}
			exit(0);
		}
	}
	for (int i = 0; i < nbFils; ++i)
	{
		wait(pidFils[i]);
	}
	
	printf("########### FIN ###########\n");

	semctl(semid, 0, IPC_RMID, 0);



	return 0;
}
