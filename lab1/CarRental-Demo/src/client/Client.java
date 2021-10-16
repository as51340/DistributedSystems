package client;

import rental.ICarRentalCompany;
import rental.CarType;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import java.util.Scanner;

/**
 * Car rental company client for demo: print all free car types in the given
 * period.
 * 
 * @author Ansar Rafique
 * 
 *         Date: 19-sep.-2021
 * 
 */
public class Client {

	public static final String _defaultRentalCompanyName = "Hertz";
	public static final String _defaultBeginDate = "07/10/2011";
	public static final String _defaultEndDate = "09/10/2011";

	public static void main(String[] args) {

		// set security manager
		if (System.getSecurityManager() != null)
			System.setSecurityManager(null);

		// check arguments
		if (args.length == 0) {
			args = new String[] { _defaultRentalCompanyName, _defaultBeginDate, _defaultEndDate };
		} else if (args.length != 3) {
			System.err.println("This program requires 3 arguments: "
					+ "car rental company name - begin date (dd/mm/yyyy) - end date (dd/mm/yyyy).");
			System.exit(0);
		}

		// Run car rental company client
		Client client = new Client(args);
		client.run();
	}

	/***************
	 * CONSTRUCTOR *
	 ***************/

	/**
	 * Initialize a new car rental company client with given arguments.
	 * 
	 * @param args The arguments for the given client.
	 */
	public Client(String[] args) {
		rentalCompanyName = args[0];

		Calendar c = Calendar.getInstance();

		Scanner s = new Scanner(args[1]).useDelimiter("/");
		int day = s.nextInt();
		int month = s.nextInt() - 1;
		int year = s.nextInt();

		// set begin date for booking
		c.set(year, month, day);
		begin = c.getTime();

		s = new Scanner(args[2]).useDelimiter("/");
		day = s.nextInt();
		month = s.nextInt() - 1;
		year = s.nextInt();

		// set end date for booking
		c.set(year, month, day);
		end = c.getTime();
	}

	/*************
	 * ARGUMENTS *
	 *************/

	private String rentalCompanyName;
	private Date begin;
	private Date end;

	/*******
	 * RUN *
	 *******/

	public void run() {
		try {
			// get car rental company
			Registry registry = LocateRegistry.getRegistry();
			ICarRentalCompany rental = (ICarRentalCompany) registry.lookup(rentalCompanyName);
			System.out.println("Car Rental Company " + rental.getName() + " found.");

			// Print all free car types in the given period.
			Set<CarType> carTypes = rental.getFreeCarTypes(begin, end);
			System.out.println("List of all free car types in the period " + begin + " - " + end + ": ");
			for (CarType carType : carTypes)
				System.out.println("\t" + carType.toString());

		} catch (NotBoundException ex) {
			System.err.println("Could not find car rental company with given name.");
		} catch (RemoteException ex) {
			System.err.println(ex.getMessage());
		}
	}


	/*public void run() { 
		try { 
			// get car rental company 
			ICarRentalCompany rental = (ICarRentalCompany) Naming.lookup("///"+rentalCompanyName);
			System.out.println("Car Rental Company "+rental.getName()+" found.");

			// Print all free car types in the given period.
			Set<CarType> carTypes = rental.getFreeCarTypes(begin, end);
			System.out.println("List of all free car types in the period " + begin + " - " + end + ": ");
			for (CarType carType : carTypes)
				System.out.println("\t" + carType.toString());

		} catch(NotBoundException ex) {
			System.err.println("Could not find car rental company with given name."); 
		}catch(MalformedURLException ex) {
			System.err.println("Malformed URL: "+ex.getMessage());
		} catch(RemoteException ex) { 
			System.err.println(ex.getMessage()); 
		} 
	}*/

}
