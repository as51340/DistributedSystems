package staff;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

import hotel.BookingDetail;

public abstract class AbstractScriptedSimpleTest {

	private final LocalDate today = LocalDate.now();

	/**
	 * Return true if there is no booking for the given room on the date,
	 * otherwise false
	 */
	protected abstract boolean isRoomAvailable(Integer room, LocalDate date) throws Exception;

	/**
	 * Add a booking for the given guest in the given room on the given
	 * date. If the room is not available, throw a suitable Exception.
	 */
	protected abstract void addBooking(BookingDetail bookingDetail) throws Exception;

	/**
	 * Return a list of all the available room numbers for the given date
	 */
	protected abstract Set<Integer> getAvailableRooms(LocalDate date) throws Exception;

	/**
	 * Return a list of all the room numbers
	 */
	protected abstract Set<Integer> getAllRooms() throws Exception;

	public void run() throws Exception {

		//Print all rooms of the hotelq
		printAllRooms();

		isRoomAvailable(101, today); //true
		BookingDetail bd1 = new BookingDetail("Ansar", 101, today);
		addBooking(bd1);//booking success

		//Check available rooms after the first booking
		System.out.println("Printing the list of available rooms after the first booking\n");
		Integer[] expectedRoomsAfterFirstBooking = {102, 201, 203};
		checkAvailableRoomsOutput(3, expectedRoomsAfterFirstBooking);

		isRoomAvailable(102, today); //true
		BookingDetail bd2 = new BookingDetail("Smith", 102, today);
		addBooking(bd2);//booking success

		//Check available rooms after the second booking
		System.out.println("Printing the list of available rooms after the second booking\n");
		Integer[] expectedRoomsAfterSecondBooking = {201, 203};
		checkAvailableRoomsOutput(2, expectedRoomsAfterSecondBooking);

		isRoomAvailable(102, today); //false
		BookingDetail bd3 = new BookingDetail("Dimitri", 102, today);
		addBooking(bd3);//booking failure

		//Check available rooms after the booking failure
		System.out.println("Printing the list of available rooms after the third booking failure\n");
		Integer[] expectedRoomsAfterBookingFailure = {201, 203};
		checkAvailableRoomsOutput(2, expectedRoomsAfterBookingFailure);
	}

	private void checkAvailableRoomsOutput(int expectedSize, Integer[] expectedAvailableRooms) throws Exception {
		Set<Integer> availableRooms = getAvailableRooms(today);
		if (availableRooms != null && availableRooms.size() == expectedSize) {
			if (availableRooms.containsAll(Arrays.asList(expectedAvailableRooms))) {
				System.out.println("List of available rooms (room ID) " + getAvailableRooms(today) + " [CORRECT]\n");
			} else {
				System.out.println("List of available rooms (room ID) " + getAvailableRooms(today) + " [INCORRECT]\n");
			}
		} else {
			System.out.println("List of available rooms (room ID) " + getAvailableRooms(today) + " [INCORRECT]\n");
		}
	}

	private void printAllRooms() throws Exception {
		System.out.println("List of rooms (room ID) in the hotel " + getAllRooms() + "\n");
	}
}