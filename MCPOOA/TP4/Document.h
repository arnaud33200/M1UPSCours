#ifndef DOCUMENT_H
#define DOCUMENT_H

#include <iostream>
#include <cstdlib>
#include <string>

#include "AutoPtr.h"

using namespace std;

class Document
{
	friend class Bib;
	friend class Livre;
private:
	string & titre;
	AutoPtr<string> resume;
	string auteur;

public:
	Document(string t, string a);
	Document(string t, string a, string r);
	Document(const Document & d);
	~Document();
	virtual void afficher();
	Document * clone();

	virtual int coutDoc() = 0;

	friend ostream & operator<<(ostream & out, const Document & a); 
};

#endif	// DOCUMENT_H