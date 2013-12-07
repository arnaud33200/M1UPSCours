public class Main {

	public static void main(String[] args) {

		if( args.length != 1)
		{
			System.out.print("usage : Main [NB PART] \n");
			System.exit(1);
		}
		Barriere bar = new Barriere();
		int nbPart = Integer.parseInt(args[0]);

		for (int i=0; i<nbPart; ++i) {
			Participant p = new Participant(i, bar);
			p.start();
		}
	}
}

// feuillad@irit.com