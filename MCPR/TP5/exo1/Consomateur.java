import java.lang.*;
import java.util.*;

public class Consomateur extends Thread {

	private int num;
	private BufferPartage buff;

	public Consomateur(int num, BufferPartage buff) {
		this.buff = buff;
		this.num = num;
	}

	public void run()
	{
		for (int i=0; i<10; ++i)
		{
			Message msg = buff.retirerMessage(i%3);
			System.out.print("	retrait " + msg + "\n");
		}

	}
}