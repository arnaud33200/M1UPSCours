#ifndef BIB_H
#define BIB_H

#include <list>
#include "Document.h"

using namespace std;

class Bib
{
private:
	list<Document *> collect;

public:
	Bib();
	~Bib();
	void ajouter(Document * d);
	Document * recherche(string t);
	void afficher();

};

#endif