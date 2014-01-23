import java.lang.*;
import java.util.*;
// import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.*;

public class BufferPartage 
{
	private static ArrayList<Message> buffer;
	private static int maxMessage;

	private static ReentrantLock lock;
	private static Condition attendPlaceLibre;
	private static Condition[] attendNouveauMessage;

	public BufferPartage()
	{
		buffer = new ArrayList();
		maxMessage = 10;

		lock = new ReentrantLock();
		attendPlaceLibre = lock.newCondition();
		attendNouveauMessage = new Condition[3];
		for ( Condition c : attendNouveauMessage ) {
			c = lock.newCondition();
		}
	}

	public void deposerMessage( Message msg )
	{
		lock.lock();
		try
		{
			if( buffer.size() >= maxMessage )
				attendPlaceLibre.await();
			// msg = (buffer.size()-1) + " - " + msg;
			buffer.add(msg);
			System.out.print("depot : " + msg.toString() + "\n");
			attendNouveauMessage[msg.type].signal();
		}
		catch( Exception e) {}	
		lock.unlock();
	}

	public Message retirerMessage(int type)
	{
		Message ret = new Message();
		lock.lock();
		try 
		{
			if( buffer.size() == 0 )
				attendNouveauMessage[type].await();
			ret = buffer.remove(0);
			attendPlaceLibre.signal();
		}
		catch (Exception e){ }
		lock.unlock();
		return ret;
	}
}