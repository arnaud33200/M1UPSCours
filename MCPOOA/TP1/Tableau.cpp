#include "Tableau.h"

Tableau::Tableau()
{      
}

Tableau::~Tableau()
{
}
                  
Tableau::Tableau( const Tableau & t) : values(t.values)
{
                  
}

void Tableau::ajoutEntree( string n, int t)
{
     Entree e(n,t);
     values.push_back(e);
}

void Tableau::supprEntree( string n, int t)
{
     for ( vector<Entree>::iterator it = values.begin(); it != values.end(); ++it )
     {

         if( n.compare(it->nom) == 0  && it->tel == t )
             values.erase(it);
     }
}
void Tableau::supprEntree( string n )
{
     for ( vector<Entree>::iterator it = values.begin(); it != values.end(); ++it )
     {
        cout << "name : " << it->nom << endl;
         if( n.compare(it->nom) == 0 )
             values.erase(it);
     }
}

Tableau Tableau::operator+(const Tableau & t)
{
        Tableau tr(t);
        for ( vector<Entree>::iterator it = values.begin(); it != values.end(); ++it )
     {
         tr.values.push_back(*it);
     }
     return tr;       
}

ostream & operator<<( ostream & out, Tableau & t)
{
        for ( vector<Entree>::iterator it = t.values.begin(); it != t.values.end(); ++it )
            out << " - " << *it << endl;
        return out; 
}
