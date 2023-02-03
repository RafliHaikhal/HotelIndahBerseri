package model;

import facade.Helper;

public abstract class Room {
	
	Helper helper = Helper.getInstance();
	
	protected String type;
	
	public Room(String type) {
		super();
		this.type = type;
	}
	
	public abstract void addPrivateJaccuzzi();
	public abstract void addPrivatePool();
	public abstract void addBreakfast();
	public abstract void addRequestMenu();
	public abstract void addMiniFridge();
	
	public void prepareRoom() {
		System.out.println("");
		addPrivateJaccuzzi();
		addPrivatePool();
		addBreakfast();
		addRequestMenu();
		addMiniFridge();
		System.out.println("");
	}
}
