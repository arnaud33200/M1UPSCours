#include "Document.h"

Document::Document(string t, string a) : 
titre(t), auteur(a)
{
	resume = new string("");
}

Document::Document(string t, string a, string r) :
titre(t), auteur(a)
{
	resume = new string(r);
}

Document::Document(const Document & d) :
titre(d.titre), auteur(d.auteur)
{
	resume = new string((*d.resume));
}

Document::~Document()
{
	delete resume;
}

ostream & operator<<(ostream & out, const Document & a)
{
	out << a.titre << " - " << a.auteur << " [ " << (*a.resume) << " ] ";
	return out;
}