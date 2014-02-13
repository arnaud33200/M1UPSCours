package impl;

import interfaces.TypeDeServicePasTerrible;
import CS.ServerComponentType;

public class ServerWithStateImpl extends ServerComponentType implements TypeDeServicePasTerrible{

	private boolean previousRequest = true;
	
	@Override
	protected TypeDeServicePasTerrible make_unServicePasSiMal() {
		// TODO Auto-generated method stub
		return this;
	}

	@Override
	public boolean contrary(boolean b) {
		// TODO Auto-generated method stub
		boolean p = previousRequest;
		
		previousRequest = b;
		return p^b;
	}
	
	

}
