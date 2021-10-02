package com.revature.menus;

public class MenuItem {

	private String id;
	private String desc;
	
	public MenuItem() {
		this.id = null;
		this.desc = null;
	}
	
	public MenuItem(String id, String desc) {
		this();
		this.id = id;
		this.desc = desc;
	}
	
}
