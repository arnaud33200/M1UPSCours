package impl;

import interfaces.TypeDeServiceSuperChouette;
import CS.ClientComponentType;

public class ClientImpl extends ClientComponentType {

	@Override
	protected TypeDeServiceSuperChouette make_unServiceSuperChouette() {
		// TODO Auto-generated method stub
		return new TypeDeServiceSuperChouette(){

			@Override
			public boolean compute(boolean b) {
				// TODO Auto-generated method stub
				return requires().unServiceBasiqueBofBof().contrary(b);
//				return unServiceBasiqueBofBof().contrary(b);
			}
			
		};
	}

}
