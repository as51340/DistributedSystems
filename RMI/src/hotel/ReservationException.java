package hotel;

/**
 * ReservationException for HotelRoomBooking application
 * @author andi
 *
 */
public class ReservationException extends Exception{
	
	private static final long serialVersionUID = 1L;

	public ReservationException(String exception) {
		super(exception);
	}

}
