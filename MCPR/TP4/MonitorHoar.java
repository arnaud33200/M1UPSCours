import java.io.*;
import java.lang.*;
import java.util.*;

public class MonitorHoar
{
	private int auTourDe;
	private int nbUtilisateur;
	final private Condition attenteTour0;
	final private Condition attenteTour1;

	public MonitorHoar()
	{
		auTourDe = 0;
		nbUtilisateur = 0;
		attenteTour0 = new AbstractQueuedSynchronizer.ConditionObject();
		attenteTour1 = new AbstractQueuedSynchronizer.ConditionObject();
	}

	public void accederTable(int num)
	{
		lock.lock();

		if( nbUtilisateur > 0 || auTourDe != num)
		{
			if( num == 0) attenteTour0.await();
			else attenteTour1.await();
		}
		else
			nbUtilisateur = 1;

	}

	public void libererTable()
	{
		auTourDe = (auTourDe +1 )%2;
		nbUtilisateur = 0;
		if( num == 0) attenteTour0.signal();
		else attenteTour1.signal();
	}
}