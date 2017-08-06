package com.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

public class Order {

	@Id
	private int orderId;
	private List<Item> items = new ArrayList<>();
	private String status;
	private String createdBy;
	
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date dateCreated;
	
	public Order() {

	}

	public Order(int orderId, List<Item> items, String status, String createdBy, Date dateCreated) {
		this.orderId = orderId;
		this.items = items;
		this.status = status;
		this.createdBy = createdBy;
		this.dateCreated = dateCreated;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", items=" + items + ", status=" + status + ", createdBy=" + createdBy
				+ ", dateCreated=" + dateCreated + "]";
	}

}
