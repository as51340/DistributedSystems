package com.leuven.calculator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import com.leuven.calculator.rmi.ICalculator;

/**
 * 
 * @author ansar
 *
 */
public class Main {
	private static final String _calculatorName = "My Calculator";

	public static void main(String [] s) throws RemoteException, NotBoundException
	{
		// set security manager
		if (System.getSecurityManager() != null)
			System.setSecurityManager(null);

		// Lookup calculator from RMI registry
		Registry registry = LocateRegistry.getRegistry();
		ICalculator calculator = (ICalculator)registry.lookup(_calculatorName);
		System.out.println("Calculator name " + _calculatorName + " found from the RMI registry.");
		
		new CalculatorGUI(calculator);
		
	}
}
