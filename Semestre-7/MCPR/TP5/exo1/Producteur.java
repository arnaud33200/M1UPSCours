import java.lang.*;
import java.util.*;

public class Producteur extends Thread {

	private int num;
	private BufferPartage buff;

	public Producteur(int num, BufferPartage buff)
	{
		this.buff = buff;
		this.num = num;
	}

	public void run()
	{
		for (int i=0; i<10; ++i)
		{
			Message msg = new Message(i%3, "Message " + num);
			buff.deposerMessage(msg);
			
		}
	}
}