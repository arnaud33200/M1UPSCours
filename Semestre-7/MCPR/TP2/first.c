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
	
	int n = N;
	int i;
	int nbFils = 30;
	int pidFils[nbFils];

	// list de IPC
	
// ~~~~~~~ INIT SEMAPHORE ~~~~~~~~~
	// variable partager pour le sémaphore
	int semid = semget(ftok("./key",1), nbFils, IPC_CREAT | 0666);
	// Operation des sémaphore : P & V
	initSembuf(nbFils);

	semop(semid, &v[0], 1);
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