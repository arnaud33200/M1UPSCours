#include "Entree.h"

Entree::Entree( string n, int t): nom(n), tel(t)
{ }

ostream & operator<<( ostream & out, Entree & e )
{
        out << e.nom << " / " << e.tel;
        return out;
}
