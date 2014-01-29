#include "Entree.h"

Entree::Entree( string n, int t): nom(n), tel(t)
{ }

ostream & operator<<( ostream & out, Entree & e )
{
        out << e.nom << " / " << e.tel;
        return out;
}

bool operator==( Entree & e1, Entree & e2)
{
	return ( e1.nom.compare(e2.nom)  == 0 && e2.tel == e1.tel );
}

bool operator!=( Entree & e1, Entree & e2)
{
	return ( e1.nom.compare(e2.nom)  != 0 || e2.tel != e1.tel );
}
