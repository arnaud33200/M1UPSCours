#include "Document.h"
#include "Livre.h"
#include "Article.h"
#include "Bib.h"

#include <cstdlib>
#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
	// cout << endl << "~~~~~~~~~~~~~~ Creation DOC ~~~~~~~~~~~~~" << endl << endl;
	string resum = "ceci est un resum";
	string t = "alchimiste";
	// d.afficher(); cout << endl;
	
	// cout << endl << "~~~~~~~~~~~~~~ Creation Livre Poly ~~~~~~~~~~~~~" << endl << endl;
	Livre test(t, "Paolo COHELO", resum, "Hachette", "1983");
	Document * l = &test;
	// l->afficher(); cout << endl;

	// cout << endl << "~~~~~~~~~~~~~~ Creation Livre Clone Poly ~~~~~~~~~~~~~" << endl << endl;
	Livre test2(*((Livre*)test.clone()));
	l = &test2;
	// l->afficher(); cout << endl;

	// cout << endl << "~~~~~~~~~~~~~~ Creation Article Clone Poly ~~~~~~~~~~~~~" << endl << endl;
	Article a(*(test2.clone()), "Science News", "SupaMag", 45);
	l = &a;
	// l->afficher(); cout << endl;

	Bib biblio;
	// biblio.ajouter(&d);
	biblio.ajouter(&test);
	biblio.ajouter(&test2);
	biblio.ajouter(&a);
	biblio.afficher();

	biblio.recherche("alchimiste")->afficher();

	return 0;
}