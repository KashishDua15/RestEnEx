package com.fil.RestEnEx1.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


public class Order {

	private int orderId;
	private int tableNo;
	private MenuItem menuItem;
	private String paymentStatus;
	private double orderBill;
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public int getTableNo() {
		return tableNo;
	}
	public void setTableNo(int tableNo) {
		this.tableNo = tableNo;
	}
	public MenuItem getMenuItem() {
		return menuItem;
	}
	public void setMenuItem(MenuItem menuItem) {
		this.menuItem = menuItem;
	}
	public String getPaymentStatus() {
		return paymentStatus;
	}
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}
	public double getOrderBill() {
		return orderBill;
	}
	public void setOrderBill(double orderBill) {
		this.orderBill = orderBill;
	}
	public Order(int orderId, int tableNo, MenuItem menuItem, String paymentStatus, double orderBill) {
		super();
		this.orderId = orderId;
		this.tableNo = tableNo;
		this.menuItem = menuItem;
		this.paymentStatus = paymentStatus;
		this.orderBill = orderBill;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", tableNo=" + tableNo + ", menuItem=" + menuItem + ", paymentStatus="
				+ paymentStatus + ", orderBill=" + orderBill + "]";
	}

	
}
