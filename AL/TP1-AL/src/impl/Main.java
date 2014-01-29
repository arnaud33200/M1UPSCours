package impl;

import CS.BigComponentType;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BigComponentType.Component root = (new BigComponentImpl()).newComponent();
//		root.start();
		System.out.println(root.unServiceQuiDechire().compute(false));
		System.out.println(root.unServiceQuiDechire().compute(true));
		System.out.println(root.unServiceQuiDechire().compute(false));
		
		BigComponentType.Component root2 = (new OtherBigComponentImpl()).newComponent();
//		root2.start();
		System.out.println(root2.unServiceQuiDechire().compute(false));
		System.out.println(root2.unServiceQuiDechire().compute(true));
		System.out.println(root2.unServiceQuiDechire().compute(false));
		
		
	}

}
