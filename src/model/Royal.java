package model;

public class Royal extends Room{

	public Royal(String type) {
		super("Royal");
	}

	@Override
	public void addPrivateJaccuzzi() {
		System.out.print("Preparing private jaccuzzi");
		helper.delaying();
	}

	@Override
	public void addPrivatePool() {
		System.out.print("Preparing private pool");
		helper.delaying();
	}

	@Override
	public void addBreakfast() {
		System.out.print("Including free breakfast for 4 people");
		helper.delaying();
	}

	@Override
	public void addRequestMenu() {
		System.out.print("Including request menu for each meal services");
		helper.delaying();
	}

	@Override
	public void addMiniFridge() {
		
	}

}
