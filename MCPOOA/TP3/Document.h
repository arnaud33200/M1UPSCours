#ifndef DOCUMENT_H
#define DOCUMENT_H

#include <iostream>
#include <cstdlib>
#include <string>

using namespace std;

class Document
{
private:
	string & titre;
	string * resume;
	string auteur;

public:
	Document(string t, string a);
	Document(string t, string a, string r);
	Document(const Document & d);
	~Document();

	virtual friend ostream & operator<<(ostream & out, const Document & a); 

};

#endif	// DOCUMENT_H