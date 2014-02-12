#include "Livre.h"

Livre::Livre(string t, string a, string r, string e, string d) : 
Document(t,a,r), editeur(e), date(d)
{ }

Livre::Livre(const Livre & l) : 
Document(l), editeur(l.editeur), date(l.date)
{
	cout << "Livre :: Constructor Copy" << endl;
}

Livre::Livre(const Document & doc, string e, string d) : 
Document(doc), editeur(e), date(d)
{

}

Livre::~Livre()
{ }

void Livre::afficher()
{
	Document::afficher();
	cout << "edit : " << editeur << " (" << date << ") ";
}

Document * Livre::clone()
{
	return new Livre(*this);
}

// Livre * Livre::clone()
// {
// 	return new Livre(*this);
// }

int Livre::coutDoc()
{
	return 10;
}

// ostream & operator<<(ostream & out, const Livre & l)
// {
// 	out << (Document)l;
// 	out << "edit : " << l.editeur << " (" << l.date << ") ";
// 	return out;
// }
