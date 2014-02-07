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
	cout << "Document :: Constructor Copy" << endl;
}

Document::~Document()
{
	delete resume;
}

void Document::afficher()
{
	cout << titre << " - " << auteur << " [ " << (*resume) << " ] ";
}

Document * Document::clone()
{
	return new Document(*this);
}

ostream & operator<<(ostream & out, const Document & a)
{
	out << a.titre << " - " << a.auteur << " [ " << (*a.resume) << " ] ";
	return out;
}