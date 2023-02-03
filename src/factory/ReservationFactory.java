package factory;

import model.Reservation;

public class ReservationFactory {

	private static ReservationFactory instance;
	
	public static ReservationFactory getInstance() {
		if(instance == null) {
			instance = new ReservationFactory();
		}
		
		return instance;
	}
	
	public Reservation createReservation(int id, String name, String roomType, int roomPrice) {
		return new Reservation(id, name, roomType, roomPrice);
	}
	
}
