#include "Agenda.h"

Agenda::Agenda()
{
	entries = new Tableau();
}

Agenda::Agenda(const Agenda & a)
{
	entries = new Tableau(*(a.entries));
}

Agenda::~Agenda()
{
	delete entries;
}

Agenda Agenda::operator+(const Agenda & a)
{
	delete this->entries;
	Tableau * t = new Tableau(*(this->entries) + *(a.entries));
	Agenda na;
	na.entries = t;
	return na;
}

void Agenda::ajoutEntree( string n, int t)
{
	entries->ajoutEntree(n,t);
}

void Agenda::supprEntree( string n, int t)
{
	entries->supprEntree(n,t);
}

void Agenda::supprEntree( string n )
{
	entries->supprEntree(n);
}

ostream & operator<<(ostream & out, Agenda & a)
{
	out << "~~~~~~~~~~~~~~~~~~" << endl;
	out << "AGENDA" << endl << "~~~~~~~~~~~~~~~~~~" << endl << *(a.entries);
	return out;
}

Agenda & Agenda::operator=(const Agenda & a)
{
	delete this->entries;
	this->entries = new Tableau(*(a.entries));
	return (*this);
	
}

Agenda & Agenda::operator+=(const Agenda & a)
{
	delete this->entries;
	this->entries = new Tableau(*(this->entries) + *(a.entries));
	return (*this);
}
/*
Agenda & Agenda::operator+=(const string & nom, const int & num)
{
	this->entries->ajoutEntree( nom, num);
	return (*this);
}
*/

Entree & Agenda::operator[](const string & n)
{
	return this->entries->find(n);
}

void operator-=(const Agenda & a, const string & n)
{
	a.entries->supprEntree( n );
}
void operator+=(const Agenda & a, const Entree & e)
{
	a.entries->ajoutEntree(e);
}

bool operator==(const Agenda & a1, const Agenda & a2)
{
	return ( (*a1.entries) == (*a2.entries) );
}

bool operator/(const Agenda & a, const string & nom)
{
	
}

void Agenda::operator()(const char & n)
{
	(*entries)(n);
}

