#ifndef LIVRE_H
#define LIVRE_H

#include "Document.h"

class Livre : public Document
{
private:
	string editeur;
	string date;

public:
	Livre(string t, string a, string r, string e, string d);
	Livre(const Document & doc, string e, string d);
	Livre(const Livre & l);
	~Livre();
	void afficher();
	Document * clone();
	friend ostream & operator<<(ostream & out, const Livre & l);

	/* data */
};

#endif