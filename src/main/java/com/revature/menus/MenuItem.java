package com.revature.menus;

public class MenuItem {

	private int id;
	private String desc;
	
	public MenuItem() {
		this.id = 0;
		this.desc = null;
	}
	
	public MenuItem(int id, String desc) {
		this();
		this.id = id;
		this.desc = desc;
	}
	
	@Override
	public String toString() {
		return id + " --- " + desc;
	}
	
}
