import interfaces.FabriqueGateaux;
import familySpace.Decrypter;


public class ImplDecrypt extends Decrypter {

	@Override
	protected FabriqueGateaux make_msg() {
		// TODO Auto-generated method stub
		return new FabriqueGateaux() {
			
			@Override
			public String styleGateaux() {
				// TODO Auto-generated method stub
				String req = requires().encryptMsg().faireEmballage();
				System.out.println("    > decrypt : " + req);
				req = req.substring(1, req.length()-1);
				return req;
			}
		};
	}

}
