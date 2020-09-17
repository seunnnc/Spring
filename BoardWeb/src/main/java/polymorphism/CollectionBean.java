package polymorphism;

import java.util.*;

public class CollectionBean {
	private List<String> addressList;

	public void setAddressList(List<String> addressList) {
		this.addressList = addressList;
	}
	
	public void printAddress() {
		for(String addr : addressList) {
			System.out.println("addr : " + addr);
		}
	}
}
