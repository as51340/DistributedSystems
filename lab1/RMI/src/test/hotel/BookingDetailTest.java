package test.hotel;

import java.time.LocalDate;

import hotel.BookingDetail;

public class BookingDetailTest {

	public static void main(String[] args) {
		BookingDetail bd1 = new BookingDetail("KU Leuven", 201, LocalDate.now());
		BookingDetail bd2 = new BookingDetail("KU Leuvenajo", 202, LocalDate.now());
		System.out.println("BookingDetail1 = BookingDetail2 " + bd1.equals(bd2));  // it should be false
		
		BookingDetail bd3 = new BookingDetail("KU Leuven", 201, LocalDate.now());
		BookingDetail bd4 = new BookingDetail("KU Leuven", 201, LocalDate.now());
		System.out.println("BookingDetail3 = BookingDetail4 " + bd3.equals(bd4)); // it should be true
		
	}

}
