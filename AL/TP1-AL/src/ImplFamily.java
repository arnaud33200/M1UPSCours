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

}
