#ifndef ENTREE_H
#define ENTREE_H

#include <iostream>

using namespace std;

class Entree 
{
      friend class Tableau;
      private :
              string nom;
              int tel;
      public :
             Entree( string n, int t);
             friend ostream & operator<<( ostream & out, Entree & e );
             friend bool operator==( Entree & e1, Entree & e2);
             friend bool operator!=( Entree & e1, Entree & e2);
};

#endif 
