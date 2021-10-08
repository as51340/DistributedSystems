package hotel;

import java.rmi.RemoteException;

/**
 * ReservationException for HotelRoomBooking application
 * @author andi
 *
 */
public class ReservationException extends RemoteException{
	
	private static final long serialVersionUID = 1L;

	public ReservationException(String exception) {
		super(exception);
	}

}
