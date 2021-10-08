package hotel;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Rooms are stored in array but we support converting array into set.
 * I would like to handle all multithreading in this class. 
 * We don't want to have lock on all rooms rather just on one. Synchronized block on every room should suffice for this problem.
 * Problem is: how to make one thread for one client not for every method => possible solution: client is actually
 * a thread or runnable. I would prefer to make it Thread(extend Thread) because it's not easy to write what
 * actually runnable is.
 * 
 * @author andi
 *
 */
public class BookingManager implements IBookingManager{

	private Room[] rooms;

	public BookingManager() {
		this.rooms = initializeRooms();
	}

	public Set<Integer> getAllRooms() {
		Set<Integer> allRooms = new HashSet<Integer>();
		Iterable<Room> roomIterator = Arrays.asList(rooms);
		for (Room room : roomIterator) {
			allRooms.add(room.getRoomNumber());
		}
		return allRooms;
	}
	
	/**
	 * Added functionality for checking if room is available.
	 * @param roomNumber
	 * @param date
	 * @return
	 */
	public boolean isRoomAvailable(Integer roomNumber, LocalDate date) {
		for(Room room: this.rooms) {
			if(room.getRoomNumber() != roomNumber) continue; // check if this that room
			List<BookingDetail> roomBookings = room.getBookings();
			for(BookingDetail bd: roomBookings) {
				if(bd.getDate().equals(date) == true) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Adds booking if room is available otherwise throws {@linkplain ReservationException}
	 * @param bookingDetail
	 * @throws ReservationException 
	 */
	public void addBooking(BookingDetail bookingDetail) throws ReservationException {
		// We can write this in more efficient way by not using isRoomAvailable because when room is available
		// then we need to iterate again over our solution. Still better then situation with getAvailableRooms
		if(isRoomAvailable(bookingDetail.getRoomNumber(), bookingDetail.getDate())) {
			for(Room room: this.rooms) {
				if(room.getRoomNumber() == bookingDetail.getRoomNumber()) { // this is our room
					room.getBookings().add(bookingDetail);
				}
			}
		} else {
			throw new ReservationException("Room " + bookingDetail.getRoomNumber() + " cannot be booked for " + bookingDetail.getDate().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "");
		}
	}
	
	/**
	 * We could somehow use getOneRomm but the problem is that we cannot retrieve our room in O(1) but in O(n)
	 * @param date
	 * @return
	 */
	public Set<Integer> getAvailableRooms(LocalDate date) {
		Set<Integer> availableRoomsNumber = new TreeSet<Integer>(); // keeps rooms in sorted way
		for(Room room: this.rooms) { // iterate over every room
			List<BookingDetail> roomBookings = room.getBookings();
			boolean roomAvailable = true;
			for(BookingDetail bd: roomBookings) { // check all their bookings
				if(bd.getDate().equals(date) == true) {
					roomAvailable = false;
					break;
				}
			}
			if(roomAvailable) {
				availableRoomsNumber.add(room.getRoomNumber());
			}
		}
		return availableRoomsNumber;
	}

	private static Room[] initializeRooms() {
		Room[] rooms = new Room[4];
		rooms[0] = new Room(101);
		rooms[1] = new Room(102);
		rooms[2] = new Room(201);
		rooms[3] = new Room(203);
		return rooms;
	}
}
