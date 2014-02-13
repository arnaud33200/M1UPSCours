import interfaces.FabriqueGateaux;
import FamilyGroup.GroupConnect;


public class ImplGroupConnect extends GroupConnect {

	@Override
	protected FabriqueGateaux make_cassecroute() {
		// TODO Auto-generated method stub
		return new FabriqueGateaux() {
			
			@Override
			public String styleGateaux() {
				// TODO Auto-generated method stub
				String req1 = requires().mother1().styleGateaux();
				String req2 = requires().mother2().styleGateaux();
				String req3 = requires().mother3().styleGateaux();
				
				return (req1 + " + " + req2 + " + " + req3);
			}
		};
	}

}
