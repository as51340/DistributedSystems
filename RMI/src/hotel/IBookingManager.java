package hotel;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Set;

/**
 * Interface for possible other hotel booking managers.
 * TODO pretty much everything about it.
 * @author andi
 *
 */
public interface IBookingManager extends Remote {
	
	/**
	 * @return all rooms in system
	 */
	public Set<Integer> getAllRooms() throws RemoteException;
	
	/**
	 * Checks if room with {@link roomNumber} is available on specific date
	 * @param roomNumber
	 * @param date
	 * @return
	 */

	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) throws RemoteException;

	
	/**
	 * Adds booking for specific room or throws exception if room is not actually available on that date 
	 * @param bookingDetail
	 * @throws ReservationException
	 */

	public void addBooking(BookingDetail bookingDetail) throws RemoteException;

	
	/**
	 * @param date
	 * @return all available rooms for {@link date}
	 * @throws ReservationException
	 */
	public Set<Integer> getAvailableRooms(LocalDate date) throws RemoteException;

}
