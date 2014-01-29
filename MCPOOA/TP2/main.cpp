#include <cstdlib>
#include <iostream>

#include "Entree.h"
#include "Tableau.h"
#include "Agenda.h"

using namespace std;

int main(int argc, char *argv[])
{
    Entree e("Jose", 667);
    cout << e << endl;
    Agenda a1;
    a1.ajoutEntree("Jose", 667);
    a1.ajoutEntree("Jose", 444);
    a1.ajoutEntree("Joje", 135);
    a1.ajoutEntree("Carlo", 633);
    a1.ajoutEntree("Mark", 157);
    cout << a1 << endl;
    a1.supprEntree("Jose");
    cout << a1 << endl;
    
    Agenda a2;
    a2.ajoutEntree("a", 1);
    a2.ajoutEntree("b", 2);

    if( a1 == a2 )
        cout << "YEAH";
    else
        cout << "Shit" << endl;

    // Agenda m = a1 + a2;
    // cout << m << endl;
    // system("PAUSE");
    return 0;
}
