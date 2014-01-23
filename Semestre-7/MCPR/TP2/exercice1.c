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
	// list de IPC
	int n = N;
// Générer une clé
	// key_t key = ftok("./key",1);

// créer une m
	int semidcpt = shmget( ftok("./key",1), sizeof(int), IPC_CREAT | 0666 );
	// int semidshare = shmget( ftok("./key",2), sizeof(int), IPC_CREAT | 0666 );
	
// ~~~~~~~ INIT SEMAPHORE ~~~~~~~~~
// variable partager pour le sémaphore
	int semid = semget(IPC_PRIVATE, 1, IPC_CREAT | 0666);
	semctl(semid, 0, SETVAL, 1);

	struct sembuf p; p.sem_num = 0; p.sem_op = -1; p.sem_flg = 0;
	struct sembuf v; v.sem_num = 0; v.sem_op = 1; v.sem_flg = 0;


printf("############### INIT #############\n");
	printf("SEMIDcpt = %d\n", semidcpt);
	printf("SEMIDsem = %d\n", semid);

	int * init = shmat(semidcpt, NULL,0);
	*init = 0;
	printf("INIT :: %d\n", *init );

	int i;
	int pid;

	if( (pid = fork()) == 0 ) // fils
	{
		printf("########### FILS 1 ###########\n");
		int *cptSon = shmat(semidcpt, NULL,0);
		for (i = 0; i < n; ++i)
		{
			semop(semid, &p, 1);
			(*cptSon) += 1;
			semop(semid, &v, 1);
		}
		printf("  ** Compteur final Fils = %d\n", *cptSon );
		shmdt(cptSon);
		exit(0);
	}
	else
	{
		printf("########### PERE ###########\n");
		int * cptFather = shmat(semidcpt, NULL,0);
		for (i = 0; i < n; ++i)
		{
			semop(semid, &p, 1);
			(*cptFather) -= 1;
			semop(semid, &v, 1);
		}
		printf("~ Compteur final Père = %d\n", *cptFather );
		shmdt(cptFather);
	}

	wait(0);
	printf("########### FIN ###########\n");
	printf("FINAL :: %d\n", *init );
	shmdt(init);
	shmctl(semidcpt, IPC_RMID, NULL);

	semctl(semid, 0, IPC_RMID, 0);



	return 0;
}
