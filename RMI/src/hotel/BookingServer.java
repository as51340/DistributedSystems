package hotel;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.rmi.AlreadyBoundException;

public class BookingServer {

	private static final String _hotelname = "Westin";
	private static Logger logger = Logger.getLogger(BookingServer.class.getName());

	public static void main(String[] args) throws ReservationException, NumberFormatException, Exception {
		
		if(System.getSecurityManager() != null) {
			System.setSecurityManager(null);
		}

		
		IBookingManager bookingManager = BookingManager.getInstance();

		// locate registry
		Registry registry = null;

		try {
			registry = LocateRegistry.getRegistry();
		} catch (RemoteException e) {
			logger.log(Level.SEVERE, "Could not locate RMI registry.");
			System.exit(-1);
		}

		// register car rental company
		IBookingManager stub;

		try {
			stub = (IBookingManager) UnicastRemoteObject.exportObject(bookingManager, 0);
			registry.bind(_hotelname, stub);
			logger.log(Level.INFO, "<{0}> Car Rental Company {0} is registered.", _hotelname);
		} catch (RemoteException e1) {
			logger.log(Level.SEVERE, "<{0}> Could not get stub bound of Car Rental Company {0}.", _hotelname);
			e1.printStackTrace();
			System.exit(-1);
		} catch (AlreadyBoundException e) {
			e.printStackTrace();
		}

	}
}
