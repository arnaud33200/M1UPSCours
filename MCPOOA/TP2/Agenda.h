#ifndef AGENDA_H
#define AGENDA_H

#include <cstdlib>
#include <iostream>

#include "Tableau.h"

using namespace std;

class Agenda
{
private:
	Tableau * entries;
public:
	Agenda();
	Agenda(const Agenda & a);
	~Agenda();
	
	void ajoutEntree( string n, int t);
	void supprEntree( string n, int t);
	void supprEntree( string n );

	friend ostream & operator<<(ostream & out, Agenda & a);
	Agenda operator+(const Agenda & a);
	Agenda & operator=(const Agenda & a);
	Agenda & operator+=(const Agenda & a);
	Entree & operator[](const string & n);
	friend void operator-=(const Agenda & a, const string & n);
	friend void operator+=(const Agenda & a, const Entree & e);
	friend bool operator==(const Agenda & a1, const Agenda & a2);
	friend bool operator/(const Agenda & a, const string & nom);
	void operator()(const char & n);

	/* data */
};

#endif
