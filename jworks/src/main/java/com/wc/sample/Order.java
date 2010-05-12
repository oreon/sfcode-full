package com.wc.sample;

public class Order {
	
	int qty;
	String productName;
	
	public Order(int qty, String productName) {
		super();
		this.qty = qty;
		this.productName = productName;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}

}
