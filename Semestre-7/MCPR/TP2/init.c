#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/shm.h>
#include <sys/ipc.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <sys/sem.h>

#define N 100;

int main(int argc, char const *argv[])
{
	int i;
	int nbFils = 30;

	int semid = semget(ftok("./key",1), nbFils, IPC_CREAT | 0666);
	for (i = 0; i < nbFils; ++i)
	{
		semctl(semid, i, SETVAL, 0);
	}
	return 0;
}
