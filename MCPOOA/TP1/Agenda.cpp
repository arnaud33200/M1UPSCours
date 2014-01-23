#include "Agenda.h"

Agenda::Agenda(): entries()
{}

Agenda::Agenda(const Agenda & a): entries(a.entries)
{}

Agenda::~Agenda()
{}

Agenda Agenda::operator+(const Agenda & a)
{
	Tableau t = this->entries + a.entries;
	Agenda na;
	na.entries = t;
	return na;
}

void Agenda::ajoutEntree( string n, int t)
{
	entries.ajoutEntree(n,t);
}

void Agenda::supprEntree( string n, int t)
{
	entries.supprEntree(n,t);
}

void Agenda::supprEntree( string n )
{
	entries.supprEntree(n);
}

ostream & operator<<(ostream & out, Agenda & a)
{
	out << "~~~~~~~~~~~~~~~~~~" << endl;
	out << "AGENDA" << endl << "~~~~~~~~~~~~~~~~~~" << endl << a.entries;
	return out;
}