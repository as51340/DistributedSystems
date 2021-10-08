package staff;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;

import hotel.BookingDetail;
import hotel.BookingManager;

/**
 * Offers two different methods depending if you want to allow single user access or multiple access.
 * @author andi
 *
 */
public class ClientMain {

	public static void main(String[] args) throws Exception {
		singleUserTest();
	}
	
	
	/**
	 * Test for single user access
	 * @throws Exception
	 */
	private static void singleUserTest() throws Exception{
		BookingClient client = new BookingClient();
		client.run();
	}
	
//	private static void multipleUserTest() throws Exception,  {
//		BookingClient client1 = new BookingClient();
//		BookingClient client2 = new BookingClient();
//		
//		LocalDate today = LocalDate.now();
//		
//		new Thread(() -> {
//			System.out.println("***Client1 obtainS all rooms***");
//			printRooms(client1.getAllRooms());
//			System.out.println("***Client1 obtains all available rooms***");
//			printRooms(client1.getAvailableRooms(today));
//			// Reservation room 101 
//			System.out.println("***Client1 tries to reserve room 101***");
//			BookingDetail bd1 = new BookingDetail("KU Leuven", 101, today);
//			try {
//				client1.addBooking(bd1);
//			} catch (Exception e) {
//				System.out.println("***Client1 couldn't reserve room 101***");
//			}
//			System.out.println("***Client1 obtains all available rooms***");
//			printRooms(client1.getAvailableRooms(today));
//			
//			// Reservation room 102 
//			System.out.println("***Client1 tries to reserve room 102***");
//			BookingDetail bd2 = new BookingDetail("KU Leuven", 102, today);
//			try {
//				client1.addBooking(bd2);
//			} catch (Exception e) {
//				System.out.println("***Client1 couldn't reserve room 102***");
//			}
//			System.out.println("***Client1 obtains all available rooms***");
//			printRooms(client1.getAvailableRooms(today));
//			
//			// Reservation room 201 
//			System.out.println("***Client1 tries to reserve room 201***");
//			BookingDetail bd3 = new BookingDetail("KU Leuven", 201, today);
//			try {
//				client1.addBooking(bd3);
//			} catch (Exception e) {
//				System.out.println("***Client1 couldn't reserve room 201***");
//			}
//			System.out.println("***Client1 obtains all available rooms***");
//			printRooms(client1.getAvailableRooms(today));
//			
//			// Reservation room 203 
//			System.out.println("***Client1 tries to reserve room 203***");
//			BookingDetail bd4 = new BookingDetail("KU Leuven", 203, today);
//			try {
//				client1.addBooking(bd4);
//			} catch (Exception e) {
//				System.out.println("***Client1 couldn't reserve room 203***");
//			}
//			System.out.println("***Client1 obtains all available rooms***");
//			printRooms(client1.getAvailableRooms(today));
//		}).start();
//		
//		new Thread(() -> {
//			System.out.println("***Client2 obtains all available rooms***");
//			printRooms(client2.getAvailableRooms(today));
//			
//			// Reservation room 101
//			System.out.println("***Client2 tries to reserve room 101***");
//			BookingDetail bd1 = new BookingDetail("KU Leuven", 101, today);
//			try {
//				client2.addBooking(bd1);
//			} catch (Exception e) {
//				System.out.println("***Client2 couldn't reserve room 101***");
//			}
//			System.out.println("***Client2 obtains all available rooms***");
//			printRooms(client2.getAvailableRooms(today));
//			
//			// Reservation room 102 
//			System.out.println("***Client2 tries to reserve room 102***");
//			BookingDetail bd2 = new BookingDetail("KU Leuven", 102, today);
//			try {
//				client2.addBooking(bd2);
//			} catch (Exception e) {
//				System.out.println("***Client2 couldn't reserve room 102***");
//			}
//			System.out.println("***Client2 obtains all available rooms***");
//			printRooms(client2.getAvailableRooms(today));
//			
//			// Reservation room 201 
//			System.out.println("***Client2 tries to reserve room 201***");
//			BookingDetail bd3 = new BookingDetail("KU Leuven", 201, today);
//			try {
//				client2.addBooking(bd3);
//			} catch (Exception e) {
//				System.out.println("***Client2 couldn't reserve room 201***");
//			}
//			System.out.println("***Client2 obtains all available rooms***");
//			printRooms(client2.getAvailableRooms(today));
//			
//			// Reservation room 203 
//			System.out.println("***Client2 tries to reserve room 203***");
//			BookingDetail bd4 = new BookingDetail("KU Leuven", 203, today);
//			try {
//				client2.addBooking(bd4);
//			} catch (Exception e) {
//				System.out.println("***Client2 couldn't reserve room 203***");
//			}
//			System.out.println("***Client2 obtains all available rooms***");
//			printRooms(client2.getAvailableRooms(today));
//		}).start();
//		
//	}
	
	/**
	 * Print all rooms, actually room numbers.
	 * @param rooms
	 */
	private static void printRooms(Set<Integer> rooms) {
		for(Integer room: rooms) {
			System.out.print(room + " ");
		}
	}

}
