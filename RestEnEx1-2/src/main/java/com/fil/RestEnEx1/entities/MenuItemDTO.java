package com.fil.RestEnEx1.entities;

public class MenuItemDTO {
	
	private String itemName;
    private float itemPrice;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public float getItemPrice() {
		return itemPrice;
	}
	public void setItemPrice(float itemPrice) {
		this.itemPrice = itemPrice;
	}
	public MenuItemDTO(String itemName, float itemPrice) {
		super();
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}
	public MenuItemDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "MenuItemDTO [itemName=" + itemName + ", itemPrice=" + itemPrice + "]";
	}
    
    
    

}
