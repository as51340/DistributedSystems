package com.leuven.calculator;

import com.leuven.calculator.rmi.ICalculator;

/**
 * 
 * @author ansar
 *
 */
public class Executor {

	private ICalculator calculator;

	public Executor(ICalculator calculator) {
		this.calculator = calculator;
	}
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public double add(double num1, double num2){
		double result=0.0;
		try {
			result = calculator.add(num1, num2);
		}catch(Exception exp) {
			exp.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public double sub(double num1, double num2) {
		double result=0.0;
		try {
			result = calculator.sub(num1, num2);
		}catch(Exception exp) {
			exp.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public double mul(double num1, double num2) {
		double result=0.0;
		try {
			result = calculator.mul(num1, num2);
		}catch(Exception exp) {
			exp.printStackTrace();
		}
		return result;
	}
	/**
	 * 
	 * @param num1
	 * @param num2
	 * @return
	 */
	public double div(double num1, double num2) {
		double result=0.0;
		try {
			result = calculator.div(num1, num2);
		}catch(Exception exp) {
			exp.printStackTrace();
		}
		return result;
	}
}
