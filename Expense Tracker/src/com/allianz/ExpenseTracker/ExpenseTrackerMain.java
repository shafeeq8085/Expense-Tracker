package com.allianz.ExpenseTracker;

import javax.swing.UIManager;

import com.allianz.ExpenseTracker.Panels.PnlExpenseTrackerMain;

public class ExpenseTrackerMain {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try 
	    { 
	        UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel"); 
	    } 
	    catch(Exception e){ 
	    }
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	PnlExpenseTrackerMain expenseTracker = new PnlExpenseTrackerMain();
        		expenseTracker.initialize();
        		expenseTracker.getFrmExMain().setVisible(true);
            }
        });

		
	}

}
