#include "Article.h"

Article::Article(string t, string a, string r, string re, string e, int n) :
Document(t,a,r), revue(re), editeur(e), numero(n)
{

}

Article::Article(const Document & doc, string re, string e, int n) : 
Document(doc), revue(re), editeur(e), numero(n)
{

}

Article::Article(const Article & a) : 
Document(a), revue(a.revue), editeur(a.editeur), numero(a.numero)
{

}

Article::~Article()
{

}

void Article::afficher()
{
	Document::afficher();
	cout << "revue : " << revue << "N°" << numero << " (" << editeur << ") ";
}