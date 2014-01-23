#ifndef AGENDA_H
#define AGENDA_H

#include <cstdlib>
#include <iostream>

#include "Tableau.h"

using namespace std;

class Agenda
{
private:
	Tableau entries;
public:
	Agenda();
	Agenda(const Agenda & a);
	~Agenda();
	Agenda operator+(const Agenda & a);
	void ajoutEntree( string n, int t);
	void supprEntree( string n, int t);
	void supprEntree( string n );

	friend ostream & operator<<(ostream & out, Agenda & a);

	/* data */
};

#endif

