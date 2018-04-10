package com.allianz.ExpenseTracker.Exceptions;

@SuppressWarnings("serial")
public class ExpenseTrackerConfigurationExceptions extends Exception {

	/**
	 * 
	 */
	public ExpenseTrackerConfigurationExceptions() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ExpenseTrackerConfigurationExceptions(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public ExpenseTrackerConfigurationExceptions(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ExpenseTrackerConfigurationExceptions(Throwable cause) {
		super(cause);
	}
	
	

}
