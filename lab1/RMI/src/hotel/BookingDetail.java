package hotel;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Encapsulates all details about available rooms
 * @author andi
 *
 */
public class BookingDetail implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String guest;
	private Integer roomNumber;
	private LocalDate date;

	public BookingDetail(String guest, Integer roomNumber, LocalDate date) {
		this.guest = guest;
		this.roomNumber = roomNumber;
		this.date = date;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getGuest() {
		return guest;
	}

	public void setGuest(String guest) {
		this.guest = guest;
	}

	public Integer getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(Integer roomNumber) {
		this.roomNumber = roomNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(date, guest, roomNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BookingDetail other = (BookingDetail) obj;
		return Objects.equals(date, other.date) && Objects.equals(guest, other.guest)
				&& Objects.equals(roomNumber, other.roomNumber);
	}	
}
