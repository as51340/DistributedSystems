package hotel;

import java.time.LocalDate;

public class BookingDetail {

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
}
