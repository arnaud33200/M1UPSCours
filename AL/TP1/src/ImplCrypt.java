import interfaces.EmbalageGateaux;
import familySpace.Crypter;


public class ImplCrypt extends Crypter {

	@Override
	protected EmbalageGateaux make_encryptMsg() {
		// TODO Auto-generated method stub
		return new EmbalageGateaux() {
			
			@Override
			public String faireEmballage() {
				// TODO Auto-generated method stub
				String req = requires().msg().styleGateaux();
				System.out.println("    > encrypt : " + req);
				req = "|" + req + "|";
				return req;
			}
		};
	}

}
