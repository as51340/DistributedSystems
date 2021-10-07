package rental;

import java.rmi.Remote;
import java.rmi.RemoteException;

import java.util.Date;
import java.util.Set;

/**
 * Interface for remote car rental companies.
 *
 * @author	Ansar Rafique
 *
 * Date: 19-sep.-2021
 *
 */
public interface ICarRentalCompany extends Remote {

	/**
	 * @return	Returns the name of this car rental company.
	 * @throws 	RemoteException	
	 */
	public String getName() throws RemoteException;

	/**
	 * Get the list of car types that are available within the given period.
	 *
	 * @param   	start
	 *          	Start date of the period.
	 * @param   	end
	 *          	End date of the period.
	 * @return	Returns the list of all car types that are free in the given period.
	 * @throws 	RemoteException	
	 */
	public Set<CarType> getFreeCarTypes(Date start, Date end) throws RemoteException;

	/**
	 * Check if a car of the given car type is available in the given period.
	 *
	 * @param 	carTypeName
	 * 		Name of the car type.
	 * @param   	start
	 *          	Start date of the period.
	 * @param   	end
	 *          	End date of the period.
	 * @return	True, if a car of the given car type is available in the given period.
	 *          	Otherwise false.
	 * @throws	IllegalArgumentException
	 * 		Given car type does not exist.
	 * @throws 	RemoteException
	 */
	public boolean isFree(String carTypeName, Date start, Date end) throws RemoteException;
}
