package impl;

import CS.BigComponentType;
import CS.ClientComponentType;
import CS.ServerComponentType;

public class OtherBigComponentImpl extends BigComponentType {

	@Override
	protected ClientComponentType make_client() {
		// TODO Auto-generated method stub
		return new ClientImpl();
	}

	@Override
	protected ServerComponentType make_server() {
		// TODO Auto-generated method stub
		return new ServerWithStateImpl();
	}

}
