import familySpace.Crypter;
import familySpace.Decrypter;
import familySpace.Family;
import familySpace.FatherType;
import familySpace.MotherType;


public class ImplFamily extends Family {

	@Override
	protected FatherType make_father() {
		// TODO Auto-generated method stub
		return new ImplFather();
	}

	@Override
	protected MotherType make_mother() {
		// TODO Auto-generated method stub
		return new ImplMother();
	}

	@Override
	protected Decrypter make_deballe() {
		// TODO Auto-generated method stub
		return new ImplDecrypt();
	}

	@Override
	protected Crypter make_emballe() {
		// TODO Auto-generated method stub
		return new ImplCrypt();
	}

}
