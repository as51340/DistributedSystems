package staff;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.util.Set;

import hotel.BookingDetail;
import hotel.BookingManager;
import hotel.IBookingManager;

public class BookingClient extends AbstractScriptedSimpleTest{
	
	public static final String _defaultHotel = "Westin";
	public static IBookingManager booking;
	
	/***************
	 * CONSTRUCTOR *
	 ***************/
	public BookingClient() {
		try {
			//Look up the registered remote instance
			BookingManager bm = BookingManager.getInstance(); // if you need to use it, here no need for it.	
			Registry registry = LocateRegistry.getRegistry();
			booking = (IBookingManager) registry.lookup(_defaultHotel);
			System.out.println("Hotel found");
			
			
		} catch (NotBoundException ex) {
			System.err.println("Could not hotel with given name.");
		} catch (RemoteException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	@Override
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) throws RemoteException {
		return booking.isRoomAvailable(roomNumber, date);
	}

	@Override
	public void addBooking(BookingDetail bookingDetail) throws Exception {
		try {
		booking.addBooking(bookingDetail);
		} catch (Exception e) {
			System.out.println("Room not available in those dates.");
		}
	}

	@Override
	public Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException {
		return booking.getAvailableRooms(date);
	}

	@Override
	public Set<Integer> getAllRooms() throws RemoteException {
		return booking.getAllRooms();
	}
}
