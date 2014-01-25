#ifndef TABLEAU_H
#define TABLEAU_H

#include <iostream>
#include <string>
#include <vector>

#include "Entree.h"

using namespace std;

class Tableau
{
      friend class Agenda;
      private:
              vector<Entree> values;
              
      public:
             Tableau();
             ~Tableau();
             Tableau( const Tableau & t);
             void ajoutEntree( string n, int t);
             void ajoutEntree(const Entree & e);
             void supprEntree( string n, int t);
             void supprEntree( string n );
             Entree & find(const string & n);
             
             Tableau operator+(const Tableau & t);
             friend ostream & operator<<( ostream & out, Tableau & t);
             friend bool operator==(const Tableau & t1, const Tableau & t2);
          
};

#endif
