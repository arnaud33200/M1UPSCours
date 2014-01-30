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

void Tableau::ajoutEntree(const Entree & e)
{
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

Entree & Tableau::find(const string & n)
{
 for ( vector<Entree>::iterator it = values.begin(); it != values.end(); ++it )
 {
   if( n.compare(it->nom) == 0 )
     return (*it);
 }
}

Tableau Tableau::operator+(const Tableau & t)
{
  Tableau tr(t);
  for ( vector<Entree>::iterator it = values.begin(); it != values.end(); ++it )
  {
    Entree e(*it);
    tr.values.push_back(e);
  }
  return tr;       
}

ostream & operator<<( ostream & out, Tableau & t)
{
  for ( vector<Entree>::iterator it = t.values.begin(); it != t.values.end(); ++it )
    out << " - " << *it << endl;
  return out; 
}

bool operator==(Tableau & t1, Tableau & t2)
{
 if( t1.values.size() != t2.values.size() ) return false;
 bool rtn = true;
 for ( vector<Entree>::iterator it = t1.values.begin(); it != t1.values.end() && rtn; ++it )
 {
   for ( vector<Entree>::iterator jt = t2.values.begin(); jt != t2.values.end() && rtn; ++jt )
   {
     if( (*it) != (*jt) ) rtn = false;
   }
 }
 return rtn;
}

void Tableau::operator()(const char & n)
{
 for ( vector<Entree>::iterator it = values.begin(); it != values.end(); ++it )
 {
   if( (*it).nom[0] == n )
     cout << "- " << (*it).nom << endl;
 }
}