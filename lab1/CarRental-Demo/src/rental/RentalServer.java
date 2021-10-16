package rental;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RentalServer {

	private static final String _rentalCompanyName = "Hertz";
	private static Logger logger = Logger.getLogger(RentalServer.class.getName());

	public static void main(String[] args) throws ReservationException, NumberFormatException, Exception {

		// set security manager if non existent
		if(System.getSecurityManager() != null)
			System.setSecurityManager(null);

		// create car rental company
		List<Car> cars = loadData("hertz.csv");
		ICarRentalCompany hertz = new CarRentalCompany(_rentalCompanyName, cars);

		// locate registry
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry();
		} catch(RemoteException e) {
			logger.log(Level.SEVERE, "Could not locate RMI registry.");
			System.exit(-1);
		}

		// register car rental company
		ICarRentalCompany stub;
		try {
			stub = (ICarRentalCompany) UnicastRemoteObject.exportObject(hertz, 0);
			registry.bind(_rentalCompanyName, stub);
			logger.log(Level.INFO, "<{0}> Car Rental Company {0} is registered.", _rentalCompanyName);
		} catch(RemoteException e) {
			logger.log(Level.SEVERE, "<{0}> Could not get stub bound of Car Rental Company {0}.", _rentalCompanyName);
			e.printStackTrace();
			System.exit(-1);
		}
	}

	public static List<Car> loadData(String datafile)
			throws ReservationException, NumberFormatException, IOException {

		List<Car> cars = new LinkedList<Car>();

		int nextuid = 0;

		//open file
		BufferedReader in = new BufferedReader(new FileReader(datafile));
		//while next line exists
		while (in.ready()) {
			//read line
			String line = in.readLine();
			//if comment: skip
			if(line.startsWith("#"))
				continue;
			//tokenize on ,
			StringTokenizer csvReader = new StringTokenizer(line, ",");
			//create new car type from first 5 fields
			CarType type = new CarType(csvReader.nextToken(),
					Integer.parseInt(csvReader.nextToken()),
					Float.parseFloat(csvReader.nextToken()),
					Double.parseDouble(csvReader.nextToken()),
					Boolean.parseBoolean(csvReader.nextToken()));
			System.out.println(type);
			//create N new cars with given type, where N is the 5th field
			for(int i = Integer.parseInt(csvReader.nextToken());i>0;i--){
				cars.add(new Car(nextuid++, type));
			}
		}

		return cars;
	}
}
