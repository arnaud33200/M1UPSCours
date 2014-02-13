import interfaces.InterfaceTypeArnaud1;
import interfaces.InterfaceTypeArnaud2;
import ArnaudSpace.Compopo;


public class ImplInterfaceTypeArnaud extends Compopo {

	@Override
	protected InterfaceTypeArnaud1 make_fourniture1() {
		// TODO Auto-generated method stub
		return new InterfaceTypeArnaud1() {
			
			@Override
			public void coucou() {
				System.out.println("Coucou l'ami\n");
			}
		};
	}

	@Override
	protected InterfaceTypeArnaud2 make_fourniture2() {
		// TODO Auto-generated method stub
		return new InterfaceTypeArnaud2() {
			
			@Override
			public void laBiz(int n) {
				// TODO Auto-generated method stub
				System.out.println("Vous avez demandé " + n + " BISOUS\n");
				for (int i = 0; i < n; i++) {
					System.out.println("- KISS <3\n");
				}
			}
		};
	}

}
