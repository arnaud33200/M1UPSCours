import familySpace.MotherType;
import FamilyGroup.GroupConnect;
import FamilyGroup.MotherGroup;


public class ImplMotherGroup extends MotherGroup {

	@Override
	protected GroupConnect make_connecter() {
		// TODO Auto-generated method stub
		return new ImplGroupConnect();
	}

	@Override
	protected MotherType make_m1() {
		// TODO Auto-generated method stub
		return new ImplMother();
	}

	@Override
	protected MotherType make_m2() {
		// TODO Auto-generated method stub
		return new ImplMother();
	}

	@Override
	protected MotherType make_m3() {
		// TODO Auto-generated method stub
		return new ImplMother();
	}

}
