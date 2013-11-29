import java.lang.*;
import java.util.*;

public class Producteur extends Thread {

	BuffferPartage buff;

	public Producteur(BuffferPartage buff) {
		this.buff = buff;
	}

	public void run() {
		String msg = "Message";
		buff.deposerMessage(msg);
	}
}