package facade;

import java.util.Scanner;

public class Helper {

	Scanner scan = new Scanner(System.in);
	
	private static Helper instance;
	
	public static Helper getInstance() {
		if(instance == null) {
			instance = new Helper();
		}
		return instance;
	}	
	
	public void pressToContinue() {
		System.out.println("Press enter to continue...");
		scan.nextLine();
	}
	
	public void delaying() {
		for (int i = 0; i < 4; i++) {
			System.out.print(".");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("");
	}
	
	public void threeSpaces() {
		System.out.println("");
		System.out.println("");
		System.out.println("");
	}
	
	public void clearScreen() {
		for (int i = 0; i < 45 ; i++) {
			System.out.println("");
		}
	}

}
