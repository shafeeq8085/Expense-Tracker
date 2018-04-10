package com.allianz.ExpenseTracker.Steurung;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import com.allianz.ExpenseTracker.DBO.PerformDataBaseOperation;
import com.allianz.ExpenseTracker.Exceptions.expenseTrackerExceptions;
import com.allianz.ExpenseTracker.Objects.ObjectsDBFeedback;
import com.allianz.ExpenseTracker.Objects.objItemundAmount;

public class SteurungExpenseentry {

	expenseTrackerExceptions expexception = new expenseTrackerExceptions();
	LinkedHashMap<String,Integer> expense = new LinkedHashMap<String,Integer>();
	ArrayList<objItemundAmount> addItem = new ArrayList<objItemundAmount>();
	ObjectsDBFeedback feedBack = new ObjectsDBFeedback();
	
	Double sum = (double) 0;

	String date;
	int countRows=1;
	
	public SteurungExpenseentry() {
	
	}

	@SuppressWarnings("rawtypes")
	public ObjectsDBFeedback doAdd(ArrayList expenseArray) {
		
		PerformDataBaseOperation addDB = new PerformDataBaseOperation(); 
		feedBack = addDB.addToDB(expenseArray);
		return(feedBack);
	}
	
}
