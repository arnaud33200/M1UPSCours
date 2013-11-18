import java.io.*;
import java.lang.*;
import java.util.*;

public class Joueur extends Thread
{
    public static boolean partiEnCours;
    public int type;
    public int num;

    public Joueur( int type, int num )
    {
        this.partiEnCours = true;
        this.type = type;
        this.num = num;
    }

    public void run()
    {
        // System.out.print("-> PING");
        while (partiEnCours)
        {
            jouerCoup(this.type, this.num);
            waitMoment();
        }
    }

    public static void jouerCoup(int type, int num)
    {
        if( type == 0 )
            System.out.print( num + " -> PING\n");
        else
            System.out.print( num + " <- PONG\n");
    }

    public static void waitMoment()
    {
        try { Thread.sleep(1000); }
        catch( Exception e){ }
    }
}