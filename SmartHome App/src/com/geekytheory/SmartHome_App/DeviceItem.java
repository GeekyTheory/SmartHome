package com.geekytheory.SmartHome_App;

/**
 * Author: Mario Pérez Esteso 
 * Website: http://geekytheory.com 
 * Mail: mario@geekytheory.com
 */

public class DeviceItem {
	int id;
	String title = null;
	boolean selected = false;

	public DeviceItem(String title, int id, boolean selected) {
		super();
		this.title = title;
		this.id = id;
		this.selected = selected;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isOn() {
		return selected;
	}

	public void setState(boolean selected) {
		this.selected = selected;
	}
}