package com.revature.menus;

import java.util.ArrayList;

public class TryAgainMenu extends Menu {

	private boolean inputValid;
	private char selection;
	
	public TryAgainMenu() {
		this.inputValid = false;
		this.selection = 0;
		this.setPrompt("Would you like to try again?");
		MenuItem yesItem = new MenuItem(1, "Yes");
		MenuItem noItem = new MenuItem(2, "No");
		ArrayList<MenuItem> items = new ArrayList<>();
		items.add(yesItem);
		items.add(noItem);
		this.setMenuItems(items);
	}
	
	public boolean isInputValid() {
		return inputValid;
	}

	public void setInputValid(boolean inputValid) {
		this.inputValid = inputValid;
	}

	public char getSelection() {
		return selection;
	}

	public void setSelection(char selection) {
		this.selection = selection;
	}

	@Override
	public void processSelection(int sel) {
		switch(sel) {
		case 1:
			selection = 'Y';
			break;
		case 2:
			selection = 'N';
			break;
		default:
			selection = ' ';
			break;
		}
	}

}
