import java.io.*;
import java.lang.*;
import java.util.concurrent.locks.*;

public class MonitorHoar
{
	static ReentrantLock lock;
	private int auTourDe;
	private static int nbUtilisateur;
	static private Condition attenteTour0;
	static private Condition attenteTour1;

	public MonitorHoar()
	{
		auTourDe = 0;
		nbUtilisateur = 0;
		lock = new ReentrantLock();
		attenteTour0 = lock.newCondition();
		attenteTour1 = lock.newCondition();
	}

	public void accederTable(int num)
	{
		lock.lock();

		try
		{
			if( nbUtilisateur != 0 )
			{
				if( num == 0) attenteTour0.await();
				else attenteTour1.await();
			}
			else
				auTourDe = num;
		}
		catch (Exception e) {}

			nbUtilisateur = 1;

		lock.unlock();
	}

	public void libererTable()
	{
		lock.lock();

		if (auTourDe == 0) auTourDe = 1;
		else auTourDe = 0;
		if( auTourDe == 0) attenteTour0.signal();
		else attenteTour1.signal();
		// nbUtilisateur = 0;

		lock.unlock();
	}
}