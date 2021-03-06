
class Main
{
	public static void main(String[] args)
	{
		if( args.length != 2)
		{
			System.out.print("usage : Main [NB PROD] [NB CONS]\n");
			System.exit(1);
		}
		BufferPartage buf = new BufferPartage();
		int nbProd = Integer.parseInt(args[0]);
		int nbCons = Integer.parseInt(args[1]);

		for (int i=0; i<nbProd; ++i)
		{
			Producteur p = new Producteur(i, buf);
			p.start();
		}

		for (int i=0; i<nbCons; ++i)
		{
			Consomateur c = new Consomateur(i, buf);
			c.start();
		}
	}	
}