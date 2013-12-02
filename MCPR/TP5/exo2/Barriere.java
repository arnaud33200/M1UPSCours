import java.lang.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class Barriere {

	private int cpt;
	private static ReentrantLock lock;
	private static Condition attendProchainRdv;
	private static Condition attendAutres;

	public Barriere()
	{
		cpt = 0;
		lock = new ReentrantLock();
		attendProchainRdv = lock.newCondition();
		attendAutres = lock.newCondition();
	}

	public void prendreRdv(int num)
	{
		lock.lock();
		try {
			cpt++;
			if( cpt > 10 )
			{
				System.out.print( num + " - Attend prochain rdv \n");
				attendProchainRdv.await();
			}

			else if( cpt >= 10 )	// tout le monde est là
				attendAutres.signalAll();
			else
				attendAutres.await();

			System.out.print("	" + num + " - rdv terminé \n");

			cpt--;
			if( cpt <= 0 )
				attendProchainRdv.signalAll();
		}
		catch( Exception e){}

		lock.unlock();
	}

}