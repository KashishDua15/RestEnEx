package com.fil.RestEnEx1.entities;

import jakarta.persistence.Entity;


public class Menu {
	private String starters;
	private  String mainCourse;
	private String dessert;
	private String beverages;
	public String getStarters() {
		return starters;
	}
	public void setStarters(String starters) {
		this.starters = starters;
	}
	public String getMainCourse() {
		return mainCourse;
	}
	public void setMainCourse(String mainCourse) {
		this.mainCourse = mainCourse;
	}
	public String getDessert() {
		return dessert;
	}
	public void setDessert(String dessert) {
		this.dessert = dessert;
	}
	public String getBeverages() {
		return beverages;
	}
	public void setBeverages(String beverages) {
		this.beverages = beverages;
	}
	public Menu(String starters, String mainCourse, String dessert, String beverages) {
		super();
		this.starters = starters;
		this.mainCourse = mainCourse;
		this.dessert = dessert;
		this.beverages = beverages;
	}
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Menu [starters=" + starters + ", mainCourse=" + mainCourse + ", dessert=" + dessert + ", beverages="
				+ beverages + "]";
	}
	
}
