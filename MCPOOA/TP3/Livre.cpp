#include "Livre.h"

Livre::Livre(string t, string a, string r, string e, string d) : 
Document(t,a,r), editeur(e), date(d)
{ }

Livre::Livre(const Livre & l) : 
Document(l), editeur(l.editeur), date(l.date)
{ }

Livre::Livre(const Document & doc, string e, string d) : 
Document(doc), editeur(e), date(d)
{

}

Livre::~Livre()
{ }


ostream & operator<<(ostream & out, const Livre & l)
{
	out << (Document)l;
	out << "edit : " << l.editeur << " (" << l.date << ") ";
	return out;
}
