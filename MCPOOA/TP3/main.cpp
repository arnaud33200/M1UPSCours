#include "Document.h"
#include "Livre.h"
#include "Article.h"

#include <cstdlib>
#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
	cout << endl << "~~~~~~~~~~~~~~ Creation DOC ~~~~~~~~~~~~~" << endl << endl;
	string resum = "ceci est un resum";
	string t = "alchimiste";
	Document d(t, "Paolo COHELO", resum);
	d.afficher(); cout << endl;
	
	cout << endl << "~~~~~~~~~~~~~~ Creation Livre Poly ~~~~~~~~~~~~~" << endl << endl;
	Livre test(d, "Hachette", "1983");
	Document * l = &test;
	l->afficher(); cout << endl;

	cout << endl << "~~~~~~~~~~~~~~ Creation Livre Clone Poly ~~~~~~~~~~~~~" << endl << endl;
	Livre test2(*((Livre*)test.clone()));
	l = &test2;
	l->afficher(); cout << endl;

	cout << endl << "~~~~~~~~~~~~~~ Creation Article Clone Poly ~~~~~~~~~~~~~" << endl << endl;
	Article a(*(d.clone()), "Science News", "SupaMag", 45);
	l = &a;
	l->afficher(); cout << endl;

	return 0;
}