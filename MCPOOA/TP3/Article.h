#ifndef ARTICLE_H
#define ARTICLE_H

#include "Document.h"

class Article : public Document
{
private:
	string revue;
	string editeur;
	int numero;

public:
	Article(string t, string a, string r, string re, string e, int n);
	Article(const Document & doc, string re, string e, int n);
	Article(const Article & a);
	~Article();

	void afficher();

	/* data */
};

#endif