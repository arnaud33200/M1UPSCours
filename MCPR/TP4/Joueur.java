import java.io.*;
import java.lang.*;
import java.util.*;

public class Joueur extends Thread
{
    public static boolean partiEnCours;
    public int type;
    public int num;
    public MonitorHoar monit;

    public Joueur( int type, int num, MonitorHoar monit )
    {
        this.partiEnCours = true;
        this.type = type;
        this.num = num;
        this.monit = monit;
    }

    public void run()
    {
        // System.out.print("-> PING");
        while (partiEnCours)
        {
            monit.accederTable(type);
            jouerCoup(this.type, this.num);
            waitMoment();
            monit.libererTable();
        }
    }

    public static void jouerCoup(int type, int num)
    {
        if( type == 0 )
            System.out.print( num + "-> PING\n");
        else
            System.out.print( num + "       PONG  <-\n");
    }

    public static void waitMoment()
    {
        try { Thread.sleep(500); }
        catch( Exception e){ }
    }
}