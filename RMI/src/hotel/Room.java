package hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Room has reference to roomNumber and also to other bookings - guests that booked room on specific night.
 * @author andi
 *
 */
public class Room {

	private Integer roomNumber;
	private List<BookingDetail> bookings; // we could speed up our search method by not using list but rather some sorted object

	public Room(Integer roomNumber) {
		this.roomNumber = roomNumber;
		bookings = new ArrayList<BookingDetail>();
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	public List<BookingDetail> getBookings() {
		return bookings;
	}

	public void setBookings(List<BookingDetail> bookings) {
		this.bookings = bookings;
	}
}