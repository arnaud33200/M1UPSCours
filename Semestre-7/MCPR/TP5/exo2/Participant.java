import java.lang.*;

public class Participant extends Thread {

	public int num;
	public Barriere bar;

	public Participant(int num, Barriere bar) {
		this.num = num;
		this.bar = bar;
	}

	public void run()
	{
		bar.demanderRDV(num);
		bar.prendreRDV(num);
	}
}