#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/ipc.h>
#include <sys/types.h>

int * cpt;

int main(int argc, char const *argv[])
{
	// list de IPC

// Générer une clé
	key_t key = ftok("/deb/null",1);

// créer une m
	int semid = shmget( IPC_PRIVATE, sizeof(int), IPC_CREAT | 0666 );

	printf("SEMID = %d\n", semid);

	cpt = (int *)shmat(semid, NULL,0);

	*cpt = 0;
	*cpt++;
	printf("INIT :: %d\n", *cpt );
	int i;
	int pid;

	if( (pid = fork()) == 0 ) // fils
	{
		cpt = (int *)shmat(semid, NULL,0);
		for (i = 0; i < 50; ++i)
		{
			*cpt++;
		}
		printf("	** Compteur Fils = %d\n", *cpt );
		shmdt(cpt);
		exit(0);
	}
	else
	{
		cpt = (int *)shmat(semid, NULL,0);
		for (i = 0; i < 50; ++i)
		{
			*cpt--;
		}
			printf("~ Compteur Père = %d\n", *cpt );
			shmdt(cpt);
	}

	shmdt(cpt);
	shmctl(semid, IPC_RMID, NULL);


	return 0;
}
