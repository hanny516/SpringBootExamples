package com.model;

public class Item {

	private String sku;
	private int qty;

	public Item() {

	}

	public Item(String sku, int qty) {
		super();
		this.sku = sku;
		this.qty = qty;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}

	@Override
	public String toString() {
		return "Item [sku=" + sku + ", qty=" + qty + "]";
	}


}
