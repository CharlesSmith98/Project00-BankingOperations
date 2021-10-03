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
	
	public void setMenuItems(ArrayList<MenuItem> items) {
		this.menuItems = items;
	}
	
	public String getPrompt() {
		return this.prompt;
	}
	
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	
	public void display() {
		System.out.println(this.prompt+"\n");
		for (MenuItem i : this.menuItems) {
			System.out.println(i);
		}
		System.out.print("\n>>");
	}
	
}
