package com.fil.RestEnEx1.entities;

import jakarta.persistence.Entity;


public class Order {
	private int orderId;
	private int tableNo;
	private Menu menu;
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
	public Menu getMenu() {
		return menu;
	}
	public void setMenu(Menu menu) {
		this.menu = menu;
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
	public Order(int orderId, int tableNo, Menu menu, String paymentStatus, double orderBill) {
		super();
		this.orderId = orderId;
		this.tableNo = tableNo;
		this.menu = menu;
		this.paymentStatus = paymentStatus;
		this.orderBill = orderBill;
	}
	public Order() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", tableNo=" + tableNo + ", menu=" + menu + ", paymentStatus="
				+ paymentStatus + ", orderBill=" + orderBill + "]";
	}
	
}
