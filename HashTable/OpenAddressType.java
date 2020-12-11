public enum OpenAddressType{
		linear,double_hash,quadratic;
	public OpenAddressType type;
	/*
	 * @param the openaddresstype of hashing
	 * set the type of hashing
	 */
	public void setType(OpenAddressType x) {
		switch(x) {
		case linear:
			x=OpenAddressType.linear;
			break;
		case quadratic:
			x=OpenAddressType.quadratic;
			break;
		case double_hash:
			x=OpenAddressType.double_hash;
			break;		
		}


	}

}
