package com.fil.RestEnEx1.entities;

import java.util.List;

public class Menu {
	private List<MenuItem> starters;
	private List<MenuItem> maincourse;
	private List<MenuItem> deserts;
	public List<MenuItem> getStarters() {
		return starters;
	}
	public void setStarters(List<MenuItem> starters) {
		this.starters = starters;
	}
	public List<MenuItem> getMaincourse() {
		return maincourse;
	}
	public void setMaincourse(List<MenuItem> maincourse) {
		this.maincourse = maincourse;
	}
	public List<MenuItem> getDeserts() {
		return deserts;
	}
	public void setDeserts(List<MenuItem> deserts) {
		this.deserts = deserts;
	}
	public Menu(List<MenuItem> starters, List<MenuItem> maincourse, List<MenuItem> deserts) {
		super();
		this.starters = starters;
		this.maincourse = maincourse;
		this.deserts = deserts;
	}
	
	

}
