#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/wait.h>

#define N 100000;

int main(int argc, char const *argv[])
{
	// list de IPC
	int n = N;
// Générer une clé
	key_t key = ftok("/deb/null",1);

// créer une m
	int semidcpt = shmget( key, sizeof(int), IPC_CREAT | 0666 );
	int semidshare = shmget( key, sizeof(int), IPC_CREAT | 0666 );

	printf("SEMIDcpt = %d\n", semidcpt);
	printf("SEMIShare = %d\n", semidshare);

	int * init = shmat(semidcpt, NULL,0);

	*init = 0;
	printf("INIT :: %d\n", *init );
	int i;
	int pid1;
	int pid2;

	if( (pid1 = fork()) == 0 ) // fils
	{
		printf("########### FILS 1 ###########\n");
		int *cptSon = shmat(semidcpt, NULL,0);
		for (i = 0; i < n; ++i)
		{
			(*cptSon) += 1;
		}
			printf("  ** Compteur final Fils = %d\n", *cptSon );
		shmdt(cptSon);
		int * val = shmat(semidshare, NULL, 0);
		(*val) = 15;
		exit(0);
	}
	
	if( (pid1 = fork()) == 0 ) // fils
	{
		printf("########### FILS 2 ###########\n");
		int * cptFather = shmat(semidcpt, NULL,0);
		for (i = 0; i < n; ++i)
		{
			(*cptFather) -= 1;
		}
			printf("~ Compteur final Père = %d\n", *cptFather );
		int * val = shmat(semidshare, NULL, 0);
		printf("VAL = %d\n", *val );
		shmdt(cptFather);
		exit(0);
	}

	wait(0);
	wait(0);
	printf("########### FIN ###########\n");
	printf("FINAL :: %d\n", *init );

	int * val = shmat(semidshare, NULL, 0);
		printf("VAL = %d\n", *val );
	shmdt(init);
	shmctl(semidcpt, IPC_RMID, NULL);


	return 0;
}
