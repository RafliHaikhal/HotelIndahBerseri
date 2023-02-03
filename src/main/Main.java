package main;

import java.util.Vector;

import facade.Helper;
import facade.ReservationFacade;
import factory.ReservationFactory;
import model.Reservation;

public class Main {

	ReservationFacade reservationFacade = ReservationFacade.getInstance();
	Helper helper = Helper.getInstance();
	ReservationFactory reservationFactory = ReservationFactory.getInstance();
	
	Vector<Reservation> vRes = new Vector<>();
	
	public Main() {
		reservationFacade.mainMenu(vRes);
	}

	public static void main(String[] args) {
		new Main();

	}

}
