package com.leuven.calculator.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * 
 * @author ansar
 *
 */
public interface ICalculator extends Remote {
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 * @throws RemoteException
	 */
	public double add(double num1, double num2) throws RemoteException;
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 * @throws RemoteException
	 */
	public double sub(double num1, double num2) throws RemoteException;
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 * @throws RemoteException
	 */
	public double mul(double num1, double num2) throws RemoteException;
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 * @throws RemoteException
	 */
	public double div(double num1, double num2) throws RemoteException;

}
