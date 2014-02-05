import interfaces.FabriqueGateaux;
import familySpace.MotherType;


public class ImplMother extends MotherType {

	@Override
	protected FabriqueGateaux make_faitplaiz() {
		// TODO Auto-generated method stub
		return new FabriqueGateaux() {
			
			@Override
			public String styleGateaux() {
				// TODO Auto-generated method stub
				return "Cookies Chocolat";
			}
		};
	}

}
