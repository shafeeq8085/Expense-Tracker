package com.allianz.ExpenseTracker.Objects;

import com.allianz.ExpenseTracker.Exceptions.expenseTrackerExceptions;

public class ObjectsDBFeedback {

	expenseTrackerExceptions expexception;
	Double sum = (double) 0;
	public expenseTrackerExceptions getExpexception() {
		return expexception;
	}
	public void setExpexception(expenseTrackerExceptions expexception) {
		this.expexception = expexception;
	}
	public Double getSum() {
		return sum;
	}
	public void setSum(Double sum) {
		this.sum = sum;
	}
	
}
