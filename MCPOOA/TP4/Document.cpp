#include "Document.h"

Document::Document(string t, string a) : 
titre(t), auteur(a), resume(new string(t));
{
	cout << "Document[" << titre << "] :: Constructor Parameters" << endl;
}

Document::Document(string t, string a, string r) :
titre(t), auteur(a)
{
	resume = new string(r);
	cout << "Document[" << t << "] :: Constructor Parameters" << endl;
}

Document::Document(const Document & d) :
titre(d.titre), auteur(d.auteur)
{
	resume = new string(*(d.resume));
	cout << "Document[" << titre << "] :: Constructor Copy" << endl;
}

// Document * Document::clone()
// {
// 	return new Document(*this);
// }

Document::~Document()
{
	// cout << "Document[" << titre << "] :: Destructor" << endl;
}

void Document::afficher()
{
	cout << titre << " - " << auteur << " [ " << (*resume) << " ] ";
}

ostream & operator<<(ostream & out, const Document & a)
{
	out << a.titre << " - " << a.auteur << " [ " << (*a.resume) << " ] ";
	return out;
}