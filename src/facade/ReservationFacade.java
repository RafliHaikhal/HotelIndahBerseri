package facade;

import java.util.Scanner;
import java.util.Vector;

import factory.ReservationFactory;
import model.Family;
import model.Regular;
import model.Reservation;
import model.Room;
import model.Royal;

public class ReservationFacade {
	
	Scanner scan = new Scanner(System.in);
	Helper helper = Helper.getInstance();
	
	//Singleton
	private static ReservationFacade instance;
	
	public static ReservationFacade getInstance() {
		if(instance == null) {
			instance = new ReservationFacade();
		}
		
		return instance;
	}
	
	int id = 1;
	
	public void mainMenu(Vector<Reservation> res) {
		helper.clearScreen();
		int option = -1;
		
		do {
			System.out.println("-----------------------");
			System.out.println("| Hotel Indah Berseri |");
			System.out.println("-----------------------");
			System.out.println("");
			System.out.println("1. New reservation");
			System.out.println("2. Prepare reservation");
			System.out.println("3. Manage reservation");
			System.out.println("4. Exit");
			System.out.println("");
			System.out.print(">> ");
			option = scan.nextInt();
			scan.nextLine();
			
			switch (option) {
			case 1:
				newReservation(res);
				break;
			case 2:
				prepareReservation(res);
				break;
			case 3:
				manageReservation(res);
				break;
			case 4:
				System.out.println("Thanks for using our program!");
				System.exit(1);
			}
			
		}while(option != 4);
	}
	
	public Vector<Reservation> newReservation(Vector<Reservation> res) {
		helper.clearScreen();
		String name;
		String roomType;
		int roomPrice = 0;
		
		do {
			System.out.print("Insert name: ");
			name = scan.nextLine();
			if(name.isEmpty()) {
				System.out.println("Name must be filled!\n");
			}
		} while(name.isEmpty());
		
		do {
			System.out.print("Insert room type [Royal | Family | Regular]: ");
			roomType = scan.nextLine();
			
		} while(!roomType.equals("Royal") && !roomType.equals("Family") && !roomType.equals("Regular"));
		
		do {
			System.out.print("Insert room price: ");
			roomPrice = scan.nextInt();
			scan.nextLine();
			if(roomType.equals("Royal") && roomPrice < 4000000) {
				System.out.println("Minimum price of the room is 4.000.000!");
			}
			if(roomType.equals("Family") && roomPrice < 2000000) {
				System.out.println("Minimum price of the room is 2.000.000!");
			}
			if(roomType.equals("Regular") && roomPrice < 1000000) {
				System.out.println("Minimum price of the room is 1.000.000!");
			}
		} while((roomType.equals("Royal") && !(roomPrice >= 4000000)) || (roomType.equals("Family") && !(roomPrice >= 2000000)) || (roomType.equals("Regular") && !(roomPrice >= 1000000)));
		
		ReservationFactory reservationFactory = ReservationFactory.getInstance();
		
		res.add(reservationFactory.createReservation(id, name, roomType, roomPrice));
		
		id++;
		
		helper.clearScreen();
		System.out.println("Reservation has been added");
		System.out.println("");
		helper.pressToContinue();
		
		return res;
		
	}
	
	public Vector<Reservation> prepareReservation(Vector<Reservation> res){
		helper.clearScreen();
		if(res.isEmpty()) {
			System.out.println("There is no reservation.\n");
			helper.pressToContinue();
		}
		else {
			int id = 0;
			String roomType = null;
			System.out.println("===================================================================");
			System.out.printf("| %-4s | %-20s | %-15s | %-15s |\n", "ID", "Reserver Name", "Room Type", "Price");
			System.out.println("|-----------------------------------------------------------------|");
			for (Reservation list : res) {
				System.out.printf("| %-4d | %-20s | %-15s | %-15s |\n", list.getId(), list.getName(), list.getType(), list.getPrice());
				System.out.println("|-----------------------------------------------------------------|");
			}
			System.out.println("===================================================================");
			
			System.out.print("Insert reservation ID to prepare to room: ");
			id = scan.nextInt();
			scan.nextLine();
			helper.threeSpaces();
			
			for (Reservation list : res) {
				if(id == list.getId()) {
					roomType = list.getType();
					if(roomType.equals("Royal")) {
						Room royal = new Royal(roomType);
						royal.prepareRoom();
					}
					if(roomType.equals("Family")) {
						Room family = new Family(roomType);
						family.prepareRoom();
					}
					if(roomType.equals("Regular")) {
						Room regular = new Regular(roomType);
						regular.prepareRoom();
					}
				}
			}
			helper.pressToContinue();
		}
		
		return res;
	}
	
	public Vector<Reservation> manageReservation(Vector<Reservation> res){
		helper.clearScreen();
		int input;
		
		System.out.println("Do you want to upgrade reservation or cancel reservation?");
		System.out.println("");
		System.out.println("Insert 1 for cancel reservation");
		System.out.println("Insert 2 for upgrade reservation");
		System.out.println("");
		System.out.print("Insert here: ");
		input = scan.nextInt();
		scan.nextLine();
		helper.threeSpaces();
		
		switch (input) {
		case 1:
			cancelReservation(res);
			break;
		case 2:
			upgradeReservation(res);
			break;
		}
		
		return res;
	}
	
	public Vector<Reservation> cancelReservation(Vector<Reservation> res){
		helper.clearScreen();
		int id = 0;
		String name;
		String roomType;
		int price;
		System.out.println("===================================================================");
		System.out.printf("| %-4s | %-20s | %-15s | %-15s |\n", "ID", "Reserver Name", "Room Type", "Price");
		System.out.println("|-----------------------------------------------------------------|");
		for (Reservation list : res) {
			System.out.printf("| %-4d | %-20s | %-15s | %-15s |\n", list.getId(), list.getName(), list.getType(), list.getPrice());
			System.out.println("|-----------------------------------------------------------------|");
		}
		System.out.println("===================================================================");
		
		System.out.print("Insert reservation ID to cancel the reservation: ");
		id = scan.nextInt();
		scan.nextLine();
		
		for (Reservation list2 : res) {
			if(id == list2.getId()) {
				name = list2.getName();
				roomType = list2.getType();
				price = list2.getPrice();
				
				printCancelation(name, roomType, price);
				
				res.remove(list2);
				break;
			}
		}
		
		System.out.println("Reservation successfully deleted.");
		helper.pressToContinue();
		
		return res;
	}
	
	public Vector<Reservation> upgradeReservation(Vector<Reservation> res){
		helper.clearScreen();
		int id = 0;
		String name;
		String newRoomType;
		int newPrice;
		System.out.println("===================================================================");
		System.out.printf("| %-4s | %-20s | %-15s | %-15s |\n", "ID", "Reserver Name", "Room Type", "Price");
		System.out.println("|-----------------------------------------------------------------|");
		for (Reservation list : res) {
			System.out.printf("| %-4d | %-20s | %-15s | %-15s |\n", list.getId(), list.getName(), list.getType(), list.getPrice());
			System.out.println("|-----------------------------------------------------------------|");
		}
		System.out.println("===================================================================");
		
		System.out.print("Insert reservation ID to upgrade the reservation: ");
		id = scan.nextInt();
		scan.nextLine();
		
		for (Reservation list2 : res) {
			if(id == list2.getId()) {
				
				do {
					System.out.print("Insert new room type:");
					newRoomType = scan.nextLine();
				} while (!newRoomType.equals("Royal") && !newRoomType.equals("Family") && !newRoomType.equals("Regular"));
				list2.setType(newRoomType);
				
				do {
					System.out.print("Insert new price:");
					newPrice = scan.nextInt();
					scan.nextLine();
					if(newRoomType.equals("Royal") && newPrice < 4000000) {
						System.out.println("Minimum price of the room is 4.000.000!");
					}
					if(newRoomType.equals("Family") && newPrice < 2000000) {
						System.out.println("Minimum price of the room is 2.000.000!");
					}
					if(newRoomType.equals("Regular") && newPrice < 1000000) {
						System.out.println("Minimum price of the room is 1.000.000!");
					}
				} while ((newRoomType.equals("Royal") && !(newPrice >= 4000000)) || (newRoomType.equals("Family") && !(newPrice >= 2000000)) || (newRoomType.equals("Regular") && !(newPrice >= 1000000)));
				list2.setPrice(newPrice);
				
				name = list2.getName();
				newRoomType = list2.getType();
				newPrice = list2.getPrice();
				
				printUpgrade(name, newRoomType, newPrice);
			}
		}
		
		System.out.println("Reservation successfully upgraded.");
		helper.pressToContinue();
		
		return res;
	}
	
	public void printCancelation(String name, String roomType, int price) {
		
		System.out.print("Sending email");
		helper.delaying();
		
		helper.threeSpaces();
		System.out.printf("%-40s\n", "=======================================");
		System.out.printf("To: %s@mail.com\n", name);
		System.out.println("From: Hotel Indah Berseri");
		System.out.println("");
		System.out.println("Your Reservation with the details:");
		System.out.printf("Reserver Name: %s\n", name);
		System.out.printf("Room Type: %s\n", roomType);
		System.out.printf("Price: %d\n", price);
		System.out.println("");
		System.out.println("Successfully canceled. Thanks for using");
		System.out.println("Our Services.");
		helper.threeSpaces();
		System.out.println("Hotel Indah Berseri");
		System.out.println("");
		System.out.printf("%-40s\n", "=======================================");
		helper.threeSpaces();
	}
	
public void printUpgrade(String name, String roomType, int price) {
		
		System.out.print("Sending email");
		helper.delaying();
		
		helper.threeSpaces();
		System.out.printf("%-40s\n", "======================================");
		System.out.printf("To: %-15s\n", name);
		System.out.println("From: Hotel Indah Berseri");
		System.out.println("");
		System.out.println("Your Reservation successfully upgraded.");
		System.out.println("The upgraded reservation details:");
		System.out.printf("Reserver Name: %s\n", name);
		System.out.printf("Room Type: %s\n", roomType);
		System.out.printf("Price: %d\n", price);
		System.out.println("");
		System.out.println("Thanks for using our sevices.");
		helper.threeSpaces();
		System.out.println("Hotel Indah Berseri");
		System.out.println("");
		System.out.printf("%-40s\n", "======================================");
		helper.threeSpaces();
	}
	
}
