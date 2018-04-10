package com.allianz.ExpenseTracker.Steurung;

import com.allianz.ExpenseTracker.Objects.objItemundAmount;

public class SteurungDetailsAnzeigen {

	objItemundAmount obj;
	
	public SteurungDetailsAnzeigen() {
		
	}

	public objItemundAmount getTheDetails(SteurungExpenseReport steurungExpRep, String id) {
		// TODO Auto-generated method stub
		
		obj = new objItemundAmount();
		
		String sindex = id.substring(8);
		int index = Integer.parseInt(sindex);
		
obj = steurungExpRep.getListOfTransactions().get(index);
//        ArrayList<objItemundAmount> eintrag = listOfTransactions.get(index);
//        obj = eintrag.get(index-1);
        setObj(obj);
        
        return obj;
	}

	public objItemundAmount getObj() {
		return obj;
	}

	public void setObj(objItemundAmount obj) {
		this.obj = obj;
	}
}
