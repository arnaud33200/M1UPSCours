package impl;

import interfaces.TypeDeServicePasTerrible;
import CS.ServerComponentType;

public class ServerImpl extends ServerComponentType {

	@Override
	protected TypeDeServicePasTerrible make_unServicePasSiMal() {
		// TODO Auto-generated method stub
		return new TypeDeServicePasTerrible() {
			
			@Override
			public boolean contrary(boolean b) {
				// TODO Auto-generated method stub
				return !b;
			}
		};
	}

}
