package staff;

import java.time.LocalDate;
import java.util.Set;

import hotel.BookingDetail;
import hotel.BookingManager;

public class BookingClient extends AbstractScriptedSimpleTest {

	private BookingManager bm = null;
	
	/**
	 * I want that in separate class which can make use of multiple clients.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		BookingClient client = new BookingClient();
		client.run();
	}

	/***************
	 * CONSTRUCTOR *
	 ***************/
	public BookingClient() {
		try {
			//Look up the registered remote instance
			bm = new BookingManager(); // I think this is wrong because this would mean that every client has its own
			// BookingManager. Utilize singleton for multithreading => not very simple
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}

	@Override
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		//Implement this method
		// Call booking manager
		return true;
	}

	@Override
	public void addBooking(BookingDetail bookingDetail) throws Exception {
		//Implement this method
	}

	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) {
		//Implement this method
		return null;
	}

	@Override
	public Set<Integer> getAllRooms() {
		return bm.getAllRooms();
	}
}
