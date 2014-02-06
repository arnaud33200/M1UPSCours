#include "Document.h"
#include "Livre.h"

#include <cstdlib>
#include <iostream>

using namespace std;

int main(int argc, char const *argv[])
{
	string t = "alchimiste";
	Document d(t, "Paolo COHELO");
	cout << d << endl;
	Livre test(d, "Hachette", "1983");
	Document * l = &test;
	cout << *l << endl;

	return 0;
}