import java.io.*;
import java.lang.*;
import java.util.*;

public class Exercice
{
    public static void main(String[] args)
    {
        System.out.print("PLAY BALL !\n");

        int n = 10;
        ArrayList<Thread> joueurs = new ArrayList();
        for (int i=0; i<n; ++i)
        {
            int t = (i%2);
            Thread j = new Joueur(t, i);
            joueurs.add(j);
            joueurs.get(i).start();
        }   

        // while (true); 
    }
}