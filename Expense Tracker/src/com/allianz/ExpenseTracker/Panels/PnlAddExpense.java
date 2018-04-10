package com.allianz.ExpenseTracker.Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import org.jfree.ui.RefineryUtilities;

import com.allianz.ExpenseTracker.DBO.PerformDataBaseOperation;
import com.allianz.ExpenseTracker.Exceptions.expenseTrackerExceptions;
import com.allianz.ExpenseTracker.Objects.DefaultComponents;
import com.allianz.ExpenseTracker.Objects.ObjectsDBFeedback;
import com.allianz.ExpenseTracker.Objects.ObjectsExpenseTracker;
import com.allianz.ExpenseTracker.Objects.objItemundAmount;
import com.allianz.ExpenseTracker.Reports.pnlExpenseReport;
import com.allianz.ExpenseTracker.Steurung.Categories;
import com.allianz.ExpenseTracker.Steurung.SteurungExpenseentry;

public class PnlAddExpense {

	expenseTrackerExceptions expexception = new expenseTrackerExceptions();
	Categories category = new Categories();
	ObjectsDBFeedback feedBack = new ObjectsDBFeedback();
	ObjectsExpenseTracker expenseObj = new ObjectsExpenseTracker();
	
	JFrame frmExpense;
	JLabel lblMain;
	JButton btnMain;
	JButton btnHome;
	
	JLabel lblCategory;
	JLabel lblAmount;
	JLabel lblCashInd;
	
	JComboBox<?> cmpCategory1;
	JTextField txtAmount1;
	JComboBox<?> cmpCategory2;
	JTextField txtAmount2;
	JComboBox<?> cmpCategory3;
	JTextField txtAmount3;
	JComboBox<?> cmpCategory4;
	JTextField txtAmount4;
	JComboBox<?> cmpCategory5;
	JTextField txtAmount5;
	JComboBox<?> cmpCategory6;
	JTextField txtAmount6;
	JComboBox<?> cmpCategory7;
	JTextField txtAmount7;
	JComboBox<?> cmpCategory8;
	JTextField txtAmount8;
	JComboBox<?> cmpCategory9;
	JTextField txtAmount9;
	JComboBox<?> cmpCategory10;
	JTextField txtAmount10;
	
	JComboBox<?> cmpCashInd1;
	JComboBox<?> cmpCashInd2;
	JComboBox<?> cmpCashInd3;
	JComboBox<?> cmpCashInd4;
	JComboBox<?> cmpCashInd5;
	JComboBox<?> cmpCashInd6;
	JComboBox<?> cmpCashInd7;
	JComboBox<?> cmpCashInd8;
	JComboBox<?> cmpCashInd9;
	JComboBox<?> cmpCashInd10;
	
	JOptionPane oppAddExpense;
	
	JButton btnAdd;
	JButton btnCancel;
	JButton btnClear;
	JButton btnReport;
	
	JLabel lblMessage;
	
	ArrayList<objItemundAmount> addItem = new ArrayList<objItemundAmount>();
	String erroFlag ="N";
	
	public PnlAddExpense() {
	
		DefaultComponents expenseTracker = new DefaultComponents();
		frmExpense = expenseTracker.getMainFrame();
		lblMain = expenseTracker.getMainLabel();
		btnMain = expenseTracker.getMainButton();
		btnHome = expenseTracker.getHomeButton();
		
		addComponents();
		addToFrame();
		expenseObj.setFrmExpense(frmExpense);
		
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		lblCategory = new JLabel("CATEGORY");
		lblCategory.setBounds(100, 80, 300, 25);
		
		cmpCategory1 = new JComboBox<Object>(category.getCategoryList());
		cmpCategory1.setBounds(100, 100, 300, 25);
		cmpCategory2 = new JComboBox<Object>(category.getCategoryList());
		cmpCategory2.setBounds(100, 150, 300, 25);
		cmpCategory3 = new JComboBox<Object>(category.getCategoryList());
		cmpCategory3.setBounds(100, 200, 300, 25);
		cmpCategory4 = new JComboBox<Object>(category.getCategoryList());
		cmpCategory4.setBounds(100, 250, 300, 25);
		cmpCategory5 = new JComboBox<Object>(category.getCategoryList());
		cmpCategory5.setBounds(100, 300, 300, 25);
		cmpCategory6 = new JComboBox<Object>(category.getCategoryList());
		cmpCategory6.setBounds(100, 350, 300, 25);
		cmpCategory7 = new JComboBox<Object>(category.getCategoryList());
		cmpCategory7.setBounds(100, 400, 300, 25);
		cmpCategory8 = new JComboBox<Object>(category.getCategoryList());
		cmpCategory8.setBounds(100, 450, 300, 25);
		cmpCategory9 = new JComboBox<Object>(category.getCategoryList());
		cmpCategory9.setBounds(100, 500, 300, 25);
		cmpCategory10 = new JComboBox<Object>(category.getCategoryList());
		cmpCategory10.setBounds(100, 550, 300, 25);
		
		
		lblAmount = new JLabel("AMOUNT");
		lblAmount.setBounds(500, 80, 150, 25);
		
		txtAmount1 = new JTextField();
		txtAmount1.setBounds(500, 100, 150, 25);
		txtAmount2 = new JTextField();
		txtAmount2.setBounds(500, 150, 150, 25);
		txtAmount3 = new JTextField();
		txtAmount3.setBounds(500, 200, 150, 25);
		txtAmount4 = new JTextField();
		txtAmount4.setBounds(500, 250, 150, 25);
		txtAmount5 = new JTextField();
		txtAmount5.setBounds(500, 300, 150, 25);
		txtAmount6 = new JTextField();
		txtAmount6.setBounds(500, 350, 150, 25);
		txtAmount7 = new JTextField();
		txtAmount7.setBounds(500, 400, 150, 25);
		txtAmount8 = new JTextField();
		txtAmount8.setBounds(500, 450, 150, 25);
		txtAmount9 = new JTextField();
		txtAmount9.setBounds(500, 500, 150, 25);
		txtAmount10 = new JTextField();
		txtAmount10.setBounds(500, 550, 150, 25);
		
		
		lblCashInd = new JLabel("BANK");
		lblCashInd.setBounds(670, 80, 100, 25);
		
		PerformDataBaseOperation bankNameDB = new PerformDataBaseOperation();
		ArrayList<String > bankName = bankNameDB.readBankNameDB();
		
		cmpCashInd1 = new JComboBox<Object>(bankName.toArray());
		cmpCashInd1.setBounds(670, 100, 90, 25);
		cmpCashInd2 = new JComboBox<Object>(bankName.toArray());
		cmpCashInd2.setBounds(670, 150, 90, 25);
		cmpCashInd3 = new JComboBox<Object>(bankName.toArray());
		cmpCashInd3.setBounds(670, 200, 90, 25);
		cmpCashInd4 = new JComboBox<Object>(bankName.toArray());
		cmpCashInd4.setBounds(670, 250, 90, 25);
		cmpCashInd5 = new JComboBox<Object>(bankName.toArray());
		cmpCashInd5.setBounds(670, 300, 90, 25);
		cmpCashInd6 = new JComboBox<Object>(bankName.toArray());
		cmpCashInd6.setBounds(670, 350, 90, 25);
		cmpCashInd7 = new JComboBox<Object>(bankName.toArray());
		cmpCashInd7.setBounds(670, 400, 90, 25);
		cmpCashInd8 = new JComboBox<Object>(bankName.toArray());
		cmpCashInd8.setBounds(670, 450, 90, 25);
		cmpCashInd9 = new JComboBox<Object>(bankName.toArray());
		cmpCashInd9.setBounds(670, 500, 90, 25);
		cmpCashInd10 = new JComboBox<Object>(bankName.toArray());
		cmpCashInd10.setBounds(670, 550, 90, 25);
		
		

		
		btnAdd = new JButton("ADD");
		btnAdd.setBounds(300, 650, 80, 30);
		btnAdd.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getExpenses();
				if (erroFlag == "N") {
					int output = JOptionPane.showConfirmDialog(frmExpense
									, "Entries will be added to Expense Tracker. Please confirm."
									,"Expense Tracker System"
									,JOptionPane.YES_NO_OPTION);
					if(output == JOptionPane.YES_OPTION) {
							SteurungExpenseentry strExpense = new SteurungExpenseentry();
							feedBack = strExpense.doAdd(addItem);
							if (expexception.getErrFlag() == "N") {
								JOptionPane.showMessageDialog(frmExpense,"Expense Updated Succesfully. Total amount spend is "+feedBack.getSum());
								frmExpense.dispose();
								PnlExpenseTrackerHome expenseHome = new PnlExpenseTrackerHome();
								expenseHome.getFrmTrackerHome().setVisible(true);
							} else {
								JOptionPane.showMessageDialog(frmExpense, expexception.getErrMsg());
							}
							
					}
				}
			}


		});
		btnCancel = new JButton("BACK");
		btnCancel.setBounds(390, 650, 80, 30);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmExpense.dispose();
				PnlExpenseTrackerHome expenseHome = new PnlExpenseTrackerHome();
				expenseHome.getFrmTrackerHome().setVisible(true);
			}
		});
		btnClear = new JButton("CLEAR");
		btnClear.setBounds(480, 650, 80, 30);
		btnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				clearFields();
				
			}
		});
		
		btnReport = new JButton("Report");
		btnReport.setBounds(570, 650, 80, 30);
		btnReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getTheReport(e);
				
			}
		});
		
		lblMessage = new JLabel();
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setVerticalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(100, 700, 600, 40);


	}
	
	private void clearFields() {
		// TODO Auto-generated method stub
		txtAmount1.setText(null);
		txtAmount2.setText(null);
		txtAmount3.setText(null);
		txtAmount4.setText(null);
		txtAmount5.setText(null);
		txtAmount6.setText(null);
		txtAmount7.setText(null);
		txtAmount8.setText(null);
		txtAmount9.setText(null);
		txtAmount10.setText(null);
		
		cmpCategory1.setSelectedIndex(0);
		cmpCategory2.setSelectedIndex(0);
		cmpCategory3.setSelectedIndex(0);
		cmpCategory4.setSelectedIndex(0);
		cmpCategory5.setSelectedIndex(0);
		cmpCategory6.setSelectedIndex(0);
		cmpCategory7.setSelectedIndex(0);
		cmpCategory8.setSelectedIndex(0);
		cmpCategory9.setSelectedIndex(0);
		cmpCategory10.setSelectedIndex(0);
		
		cmpCashInd1.setSelectedIndex(0);
		cmpCashInd2.setSelectedIndex(0);
		cmpCashInd2.setSelectedIndex(0);
		cmpCashInd4.setSelectedIndex(0);
		cmpCashInd5.setSelectedIndex(0);
		cmpCashInd6.setSelectedIndex(0);
		cmpCashInd7.setSelectedIndex(0);
		cmpCashInd8.setSelectedIndex(0);
		cmpCashInd9.setSelectedIndex(0);
		cmpCashInd10.setSelectedIndex(0);
		
	}
	
	private void getExpenses() {
		// TODO Auto-generated method stub
		erroFlag = "N";
		String amount;
		try {
			if (!txtAmount1.getText().equals("")) {
				System.out.println(txtAmount1.getText());
					amount = txtAmount1.getText().replace(",", ".");
					addToArray(cmpCategory1.getSelectedItem().toString(), Double.valueOf(amount),cmpCashInd1.getSelectedItem().toString());
			}
			if (!txtAmount2.getText().equals("")) {
				amount = txtAmount2.getText().replace(",", ".");
				addToArray(cmpCategory2.getSelectedItem().toString(), Double.valueOf(amount),cmpCashInd2.getSelectedItem().toString());
			}
			if (!txtAmount3.getText().equals("")) {
				amount = txtAmount3.getText().replace(",", ".");
				addToArray(cmpCategory3.getSelectedItem().toString(), Double.valueOf(amount),cmpCashInd3.getSelectedItem().toString());
			}
			if (!txtAmount4.getText().equals("")) {
				amount = txtAmount4.getText().replace(",", ".");
				addToArray(cmpCategory4.getSelectedItem().toString(), Double.valueOf(amount),cmpCashInd4.getSelectedItem().toString());
			}
			if (!txtAmount5.getText().equals("")) {
				amount = txtAmount5.getText().replace(",", ".");
				addToArray(cmpCategory5.getSelectedItem().toString(), Double.valueOf(amount),cmpCashInd5.getSelectedItem().toString());
			}
			if (!txtAmount6.getText().equals("")) {
				amount = txtAmount6.getText().replace(",", ".");
				addToArray(cmpCategory6.getSelectedItem().toString(), Double.valueOf(amount),cmpCashInd6.getSelectedItem().toString());
			}
			if (!txtAmount7.getText().equals("")) {
				amount = txtAmount7.getText().replace(",", ".");
				addToArray(cmpCategory7.getSelectedItem().toString(), Double.valueOf(amount),cmpCashInd7.getSelectedItem().toString());
			}
			if (!txtAmount8.getText().equals("")) {
				amount = txtAmount8.getText().replace(",", ".");
				addToArray(cmpCategory8.getSelectedItem().toString(), Double.valueOf(amount),cmpCashInd8.getSelectedItem().toString());
			}
			if (!txtAmount9.getText().equals("")) {
				amount = txtAmount9.getText().replace(",", ".");
				addToArray(cmpCategory9.getSelectedItem().toString(), Double.valueOf(amount),cmpCashInd9.getSelectedItem().toString());
			}
			if (!txtAmount10.getText().equals("")) {
				amount = txtAmount10.getText().replace(",", ".");
				addToArray(cmpCategory10.getSelectedItem().toString(), Double.valueOf(amount),cmpCashInd10.getSelectedItem().toString());
			}
		}
		catch (Exception e) {
			
			e.printStackTrace();
			erroFlag = "Y";
			JOptionPane.showMessageDialog(frmExpense,"Something went wrong, please check the Amount value!");
						
		}		
	}
	
	
	private void addToArray(String item, Double amount, String cashInd) {
		// TODO Auto-generated method stub
		
		objItemundAmount addObj = new objItemundAmount(item, amount, cashInd);
		addItem.add(addObj);
		
	}

	private void addToFrame() {
		// TODO Auto-generated method stub
		frmExpense.add(lblMain);
		frmExpense.add(btnMain);
		frmExpense.add(btnHome);
		
		frmExpense.add(lblCategory);
		frmExpense.add(lblAmount);
		frmExpense.add(lblCashInd);
		frmExpense.add(cmpCategory1);
		frmExpense.add(txtAmount1);
		frmExpense.add(cmpCashInd1);
		frmExpense.add(cmpCategory2);
		frmExpense.add(txtAmount2);
		frmExpense.add(cmpCashInd2);
		frmExpense.add(cmpCategory3);
		frmExpense.add(txtAmount3);
		frmExpense.add(cmpCashInd3);
		frmExpense.add(cmpCategory4);
		frmExpense.add(txtAmount4);
		frmExpense.add(cmpCashInd4);
		frmExpense.add(cmpCategory5);
		frmExpense.add(txtAmount5);
		frmExpense.add(cmpCashInd5);
		frmExpense.add(cmpCategory6);
		frmExpense.add(txtAmount6);
		frmExpense.add(cmpCashInd6);
		frmExpense.add(cmpCategory7);
		frmExpense.add(txtAmount7);
		frmExpense.add(cmpCashInd7);
		frmExpense.add(cmpCategory8);
		frmExpense.add(txtAmount8);
		frmExpense.add(cmpCashInd8);
		frmExpense.add(cmpCategory9);
		frmExpense.add(txtAmount9);
		frmExpense.add(cmpCashInd9);
		frmExpense.add(cmpCategory10);
		frmExpense.add(txtAmount10);
		frmExpense.add(cmpCashInd10);
		
		frmExpense.add(btnAdd);
		frmExpense.add(btnCancel);
		frmExpense.add(btnClear);
		frmExpense.add(btnReport);
		
		frmExpense.add(lblMessage);
		
		RefineryUtilities.centerFrameOnScreen(frmExpense);
		setFrmExpense(frmExpense);
	}
	
	private void getTheReport(ActionEvent e) {

		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
	         @Override
	         public Void doInBackground() throws Exception {

	        	 pnlExpenseReport report = new pnlExpenseReport();
	        	 report.getFrmReport().setVisible(true);
	             return null;
	         }
	         
		      @Override
		        protected void done() {
		    	  frmExpense.dispose();
		        }
	      };
	      
	   
	      mySwingWorker.execute();
	      lblMessage.setText("Expense Report is getting loaded, Please Wait...");
	      lblMessage.setFont(new Font("Arial", Font.BOLD, 18));
	      lblMessage.setForeground(Color.GREEN);
	      JProgressBar progressBar = new JProgressBar();
	      progressBar.setIndeterminate(true);
	      progressBar.setBounds(100, 700, 600, 40);
	      frmExpense.add(progressBar, BorderLayout.CENTER);
	      frmExpense.revalidate();
	      
	}
	
	public JFrame getFrmExpense() {
		return frmExpense;
	}

	public void setFrmExpense(JFrame frmExpense) {
		this.frmExpense = frmExpense;
	}

}
