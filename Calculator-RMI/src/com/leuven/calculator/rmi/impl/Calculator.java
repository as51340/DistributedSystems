package com.leuven.calculator.rmi.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import com.leuven.calculator.rmi.ICalculator;

/**
 * 
 * @author ansar
 *
 */
public class Calculator implements ICalculator {

	@Override
	public double add(double num1, double num2) throws RemoteException {
		return num1+num2;
	}

	@Override
	public double sub(double num1, double num2) throws RemoteException {
		return num1-num2;
	}

	@Override
	public double mul(double num1, double num2) throws RemoteException {
		return num1*num2;
	}

	@Override
	public double div(double num1, double num2) throws RemoteException {
		return num1/num2;
	}
}
