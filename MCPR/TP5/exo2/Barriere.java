import java.lang.*;
import java.util.*;
import java.util.concurrent.locks.*;

public class Barriere {

	private static int cpt;
	private static int cptatta;
	private static int cptrdv;
	private static int cptTotal;
	private static ReentrantLock lock;
	private static Condition attendProchainRdv;
	private static Condition attendAutres;

	public Barriere()
	{
		cpt = 0;
		cptatta = 0;
		cptrdv = 0;
		cptTotal = 0;
		lock = new ReentrantLock();
		attendProchainRdv = lock.newCondition();
		attendAutres = lock.newCondition();
	}

	public void demanderRDV(int num)
	{
		lock.lock();
		try {
			if( cpt >= 5 )
			{
				cptatta++;
				System.out.print( num + " - Attend prochain rdv \n");
				attendProchainRdv.await();
				cptatta--;
			}
			cpt++;
		}
		catch( Exception e){}

		lock.unlock();
	}

	public void prendreRDV(int num)
	{
		lock.lock();
		try {
				cptrdv++;
				if( cpt < 5 )
					attendAutres.await();
				else
					attendAutres.signalAll();
				cpt--;
				cptrdv--;
				attendProchainRdv.signal();
				cptTotal++;
			System.out.print("	" + num + " - rdv terminé ( " + cptrdv + " / " + cptatta + " = " + cptTotal + " ) \n");
			
			attendProchainRdv.signal();
		}
		catch( Exception e){}
		lock.unlock();
	}

}