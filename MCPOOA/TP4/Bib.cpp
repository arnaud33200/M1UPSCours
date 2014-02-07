#include "Bib.h"

Bib::Bib()
{

}

Bib::~Bib()
{
	// for (list<Document *>::iterator i = collect.begin(); i != collect.end(); ++i)
	// {
	// 	Document * d = *i;
	// 	delete d;
	// }
}

void Bib::ajouter(Document * d)
{
	// Document * n = new Document(*d);
	collect.insert(collect.end(), d);
}

Document * Bib::recherche(string t)
{
	Document * rtn = NULL;
	for (list<Document *>::iterator i = collect.begin(); i != collect.end(); ++i)
	{
		Document * d = *i;
		if ( t.compare(d->titre) == 0 )
			return d;
	}
	return rtn;
}	

void Bib::afficher()
{
	cout << "|______ BIB ________|" << endl;
	for (list<Document *>::iterator i = collect.begin(); i != collect.end(); ++i)
	{
		cout << "| ";
		Document * d = *i;
		d->afficher();
		cout << endl;
	}
}
