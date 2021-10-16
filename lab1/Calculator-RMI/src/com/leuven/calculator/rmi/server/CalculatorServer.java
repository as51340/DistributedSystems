package com.leuven.calculator.rmi.server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.leuven.calculator.rmi.ICalculator;
import com.leuven.calculator.rmi.impl.Calculator;

/**
 * 
 * @author ansar
 *
 */
public class CalculatorServer {
	private static final String _calculatorName = "My Calculator";
	private static Logger logger = Logger.getLogger(CalculatorServer.class.getName());

	public static void main(String[] args) throws RemoteException{
		// set security manager if non existent
		if(System.getSecurityManager() != null)
			System.setSecurityManager(null);

		ICalculator calulator = new Calculator();

		// locate registry
		Registry registry = null;
		try {
			registry = LocateRegistry.getRegistry();
		} catch(RemoteException e) {
			logger.log(Level.SEVERE, "Could not locate RMI registry.");
			System.exit(-1);
		}

		// register calculator
		ICalculator stub;
		try {
			stub = (ICalculator) UnicastRemoteObject.exportObject(calulator, 0);
			registry.rebind(_calculatorName, stub);
			logger.log(Level.INFO, "<{0}> is registered in the RMI registry.", _calculatorName);
			logger.log(Level.INFO, "<{0}> is ready to serve requests.", _calculatorName);
		} catch(RemoteException e) {
			logger.log(Level.SEVERE, "<{0}> Could not get stub bound in the RMI registry.", _calculatorName);
			e.printStackTrace();
			System.exit(-1);
		}
	}
}
