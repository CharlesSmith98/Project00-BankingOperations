package com.revature.menus;

import java.util.ArrayList;

public abstract class Menu {

	private ArrayList<MenuItem> menuItems;
	private String prompt;
	
	public Menu() {
		this.menuItems = null;
		this.prompt = null;
	}
	
	public Menu(ArrayList<MenuItem> items, String prompt) {
		this();
		this.menuItems = items;
		this.prompt = prompt;
	}
	
	public ArrayList<MenuItem> getMenuItems() {
		return this.menuItems;
	}
	
	public String getPrompt() {
		return this.prompt;
	}
	
	public abstract void processSelection(String sel);
	
}
