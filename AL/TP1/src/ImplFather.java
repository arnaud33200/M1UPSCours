import interfaces.CoupsMarteaux;
import familySpace.FatherType;


public class ImplFather extends FatherType {

	@Override
	protected CoupsMarteaux make_bricole() {
		// TODO Auto-generated method stub
		return new CoupsMarteaux() {
			
			@Override
			public String taperCloue(int n) {
				// TODO Auto-generated method stub
				String req = "Le père mange un ";
				req += requires().cassecroute().styleGateaux() + " et produit : ";
				
				if( n == 0 ) return req + "rien";
				String rtn = "";
				for (int i = 0; i < n; i++) {
					rtn += "Un Coup ! ";
				}
				return req + rtn;
			}
		};
	}

}
