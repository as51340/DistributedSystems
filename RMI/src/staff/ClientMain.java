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
		//singleUserTest();
		//multipleUserTest();
		//multipleUserTest2();
		multipleUserTest3();
	}
	
	
	/**
	 * Test for single user access
	 * @throws Exception
	 */
	private static void singleUserTest() throws Exception{
		BookingClient client = new BookingClient();
		client.run();
	}
	
	private static void multipleUserTest3() throws Exception {
		BookingClient client1 = new BookingClient();
		BookingClient client2 = new BookingClient();
		String[] names = {"Client1", "Client2"};
		LocalDate today = LocalDate.now();
		for(int i = 0; i < 2; i++) {
			final int j = i;
			new Thread(() -> {
				try {
					client1.addBooking(new BookingDetail(names[j], 101, today));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}).start();
		}
	}
	
	private static void multipleUserTest2() throws Exception {
		BookingClient client1 = new BookingClient();
		BookingClient client2 = new BookingClient();
		BookingClient client3 = new BookingClient();
		BookingClient client4 = new BookingClient();
		
		LocalDate today = LocalDate.now();
		LocalDate tomorrow = today.plusDays(1);
		LocalDate yesterday = today.minusDays(1);
		LocalDate nextWeek = today.plusDays(7);
		
		new Thread(() -> {
			try {
				client1.addBooking(new BookingDetail("Client1", 101, today));
			} catch(Exception ex) {
				
			}
		}).start();
		
		new Thread(() -> {
			try {
				client1.addBooking(new BookingDetail("Client1", 101, tomorrow));
			} catch(Exception ex) {
				
			}
		}).start();
		
		new Thread(() -> {
			try {
				client1.addBooking(new BookingDetail("Client1", 101, nextWeek));
			} catch(Exception ex) {
				
			}
		}).start();


		new Thread(() -> {
			try {
				client2.addBooking(new BookingDetail("Client2", 203, today));
			} catch(Exception ex) {
				
			}
		}).start();
		
		
	}
	
	private static void multipleUserTest() throws Exception {
		BookingClient client1 = new BookingClient();
		BookingClient client2 = new BookingClient();
		
		LocalDate today = LocalDate.now();
		
		new Thread(() -> {
			try {
				printRooms(client1.getAvailableRooms(today), "Client1");
				// Reservation room 101 
				BookingDetail bd1 = new BookingDetail("Client1", 101, today);
				client1.addBooking(bd1);
				printRooms(client1.getAvailableRooms(today), "Client1");
				
				// Reservation room 102 
				BookingDetail bd2 = new BookingDetail("Client1", 102, today);				
				client1.addBooking(bd2);
				printRooms(client1.getAvailableRooms(today), "Client1");
				
				// Reservation room 201 
				BookingDetail bd3 = new BookingDetail("Client1", 201, today);
				client1.addBooking(bd3);
				printRooms(client1.getAvailableRooms(today), "Client1");
				
				// Reservation room 203 
				BookingDetail bd4 = new BookingDetail("Client1", 203, today);
				client1.addBooking(bd4);
				printRooms(client1.getAvailableRooms(today), "Client1");
			
			} catch(Exception ex) {
				
			}
		}).start();
		
		new Thread(() -> {
			try {
				printRooms(client1.getAvailableRooms(today), "Client2");
				
				// Reservation room 101
				BookingDetail bd1 = new BookingDetail("Client2", 101, today);
				client2.addBooking(bd1);
				printRooms(client1.getAvailableRooms(today), "Client2");
				
				// Reservation room 102 
				BookingDetail bd2 = new BookingDetail("Client2", 102, today);
				client2.addBooking(bd2);
				printRooms(client1.getAvailableRooms(today), "Client2");
				
				// Reservation room 201 
				BookingDetail bd3 = new BookingDetail("Client2", 201, today);
				client2.addBooking(bd3);
				printRooms(client1.getAvailableRooms(today), "Client2");
				
				// Reservation room 203 
				BookingDetail bd4 = new BookingDetail("Client2", 203, today);
				client2.addBooking(bd4);
				printRooms(client1.getAvailableRooms(today), "Client2");
	
			} catch(Exception ex) {
				
			}
		}).start();
		
			
	}
	
	/**
	 * Print all rooms, actually room numbers.
	 * @param rooms
	 */
	private static void printRooms(Set<Integer> rooms, String executor) {
		System.out.print(executor + " obtains rooms: ");
		if(rooms.isEmpty()) {
			System.out.print("void");
		} else {
			for(Integer room: rooms) {
				System.out.print(room + " ");
			}
		}
		System.out.println();
		System.out.println();
	}

}
