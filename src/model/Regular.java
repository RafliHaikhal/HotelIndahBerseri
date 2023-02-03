package model;

public class Regular extends Room{

	public Regular(String type) {
		super("Regular");
	}

	@Override
	public void addPrivateJaccuzzi() {
		
	}

	@Override
	public void addPrivatePool() {
		
	}

	@Override
	public void addBreakfast() {
		System.out.print("Including free breakfast for 2 people");
		helper.delaying();
	}

	@Override
	public void addRequestMenu() {
		
	}

	@Override
	public void addMiniFridge() {
		System.out.print("Preparing mini fridge");
		helper.delaying();
	}

}
