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

void initSembuf(int nb);
void eraseSembuf();

int main(int argc, char const *argv[])
{
	int nb = atoi(argv[1]);
	int n = N;
	int i;
	int nbFils = 30;
	int pidFils[nbFils];

	// list de IPC
	
// ~~~~~~~ INIT SEMAPHORE ~~~~~~~~~
	// variable partager pour le sémaphore
	int semid = semget(ftok("./key",1), nbFils, IPC_CREAT | 0666);
	printf("fils %d get semid %d\n", nb, semid);
	// Operation des sémaphore : P & V
	initSembuf(nbFils);


// ~~~~~~~~~ LANCEMENT DES NBFILS PAR LE PÈRE ~~~~~~~~~~

	for (i = 0; i < n; ++i)
	{
		semop(semid, &p[nb], 1);
		printf("Fils %d Message\n", nb);
		semop(semid, &v[(nb+1)%nbFils], 1);
	}

	printf("########### FIN ###########\n");

	eraseSembuf();
	if( nb == nbFils-1)
		semctl(semid, 0, IPC_RMID, 0);
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