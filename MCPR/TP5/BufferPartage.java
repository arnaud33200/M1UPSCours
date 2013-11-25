import java.lang.*;
import java.util.*;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.*;

public class BufferPartage 
{
	private static ArrayList<String> buffer;
	private static int maxMessage;

	private static ReentrantLock lock;
	private static Condition attendPlaceLibre;
	private static Condition attendNouveauMessage;

	public BufferPartage()
	{
		buffer = new ArrayList();

		maxMessage = 0;


		lock = new ReentrantLock();
		attendPlaceLibre = lock.newCondition();
		attendNouveauMessage = lock.newCondition();
	}

	public void ecrireMessage( String msg )
	{
		lock.lock();
		{
			if( buffer.lentgh() >= maxMessage )
				attendPlaceLibre.await();
			buffer.add(msg);
			attendNouveauMessage.signal();
		}
		lock.unlock();
	}

	public String retirerMessage()
	{
		lock.lock();
		{
			if( buffer.lentgh() == 0 )
				attendNouveauMessage.await();
			buffer.add(msg);
			attendPlaceLibre.signal();
		}
		lock.unlock();

	}
}