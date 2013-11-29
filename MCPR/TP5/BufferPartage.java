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

	public void deposerMessage( String msg )
	{
		lock.lock();
		{
			if( buffer.size() >= maxMessage )
				attendPlaceLibre.await();
			buffer.add(msg);
			attendNouveauMessage.signal();	
		}
		lock.unlock();
	}

	public String retirerMessage()
	{
		String ret;
		lock.lock();
		{
			if( buffer.size() == 0 )
				attendNouveauMessage.await();
			ret = buffer.remove(0);
			attendPlaceLibre.signal();
		}
		lock.unlock();

	}
}