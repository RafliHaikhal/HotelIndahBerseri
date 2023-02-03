package model;

public class Family extends Room{

	public Family(String type) {
		super("Family");
	}

	@Override
	public void addPrivateJaccuzzi() {
		System.out.print("Preparing private jaccuzzi");
		helper.delaying();
	}

	@Override
	public void addPrivatePool() {
		
	}

	@Override
	public void addBreakfast() {
		System.out.print("Including free breakfast for 3 people");
		helper.delaying();
	}

	@Override
	public void addRequestMenu() {
		System.out.print("Including request menu for dinner services");
		helper.delaying();
	}

	@Override
	public void addMiniFridge() {
		
	}

}
