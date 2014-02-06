import familySpace.Family;
import ArnaudSpace.Compopo;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("HELLO è.é");
		
// EXO 1 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		Compopo.Component c = (new ImplInterfaceTypeArnaud()).newComponent();
		c.fourniture1().coucou();
		c.fourniture2().laBiz(3);
// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~

// EXO 2 ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
		
		Family.Component huxtable = (new ImplFamily()).newComponent();
		System.out.println(huxtable.service().taperCloue(3));
		System.out.println(huxtable.service().taperCloue(0));
	}

}
