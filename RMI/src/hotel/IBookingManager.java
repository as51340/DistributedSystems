package hotel;

import java.time.LocalDate;
import java.util.Set;

/**
 * Interface for possible other hotel booking managers.
 * TODO pretty much everything about it.
 * @author andi
 *
 */
public interface IBookingManager {
	
	/**
	 * @return all rooms in system
	 */
	Set<Integer> getAllRooms();
	
	/**
	 * Checks if room with {@link roomNumber} is available on specific date
	 * @param roomNumber
	 * @param date
	 * @return
	 */
	boolean isRoomAvailable(Integer roomNumber, LocalDate date) throws ReservationException;
	
	/**
	 * Adds booking for specific room or throws exception if room is not actually available on that date 
	 * @param bookingDetail
	 * @throws ReservationException
	 */
	void addBooking(BookingDetail bookingDetail) throws ReservationException;
	
	/**
	 * @param date
	 * @return all available rooms for {@link date}
	 * @throws ReservationException
	 */
	Set<Integer> getAvailableRooms(LocalDate date) throws ReservationException;
	

}
