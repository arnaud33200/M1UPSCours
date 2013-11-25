import java.io.*;
import java.lang.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class Exercice
{
    public static void main(String[] args)
    {
        MonitorHoar monit = new MonitorHoar();


        System.out.print("PLAY BALL !\n");

        int n = 10;
        ArrayList<Thread> joueurs = new ArrayList();
        for (int i=0; i<n; ++i)
        {
            int t = (i%2);
            System.out.print( "joueurs num créer : " + t + "\n");
            Thread j = new Joueur(t, i, monit);
            joueurs.add(j);
            joueurs.get(i).start();
        }   

        // while (true); 
    }
}