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
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import org.jfree.ui.RefineryUtilities;

import com.allianz.ExpenseTracker.Steurung.SteurungBankTransactions;
import com.allianz.ExpenseTracker.DBO.PerformDataBaseOperation;
import com.allianz.ExpenseTracker.Objects.DefaultComponents;
import com.allianz.ExpenseTracker.Objects.ObjectsBank;
import com.allianz.ExpenseTracker.Objects.ObjectsExpenseTracker;

public class PnlBankReport {

	SteurungBankTransactions steurung = new SteurungBankTransactions();
	ObjectsBank obj = new ObjectsBank();
	ObjectsExpenseTracker expenseObj = new ObjectsExpenseTracker();
	
	JFrame frmBankReport;
	JLabel lblMain;
	JButton btnMain;
	JButton btnHome;
	
	JLabel lblBankSel;
	JComboBox<?> cmbBankSel;
	JButton btnSearch;
		
	JLabel lblCredit;
	JLabel lblDebit;
	JLabel lblTotal;
	
	JLabel lblAmount1;
	JLabel lblAmount2;
	JLabel lblAmount3;
	
	JTextField txtCredit;
	JTextField txtDebit;
	JTextField txtTotal;
	
	JLabel lblMessage;
	
	JButton btnBack;
	
	
	PnlBankReport(ObjectsExpenseTracker expenseObj, ObjectsBank obj) {
		
		this.expenseObj = expenseObj;
		this.obj = obj;
		initialize();
		
	}

	private void initialize() {
		// TODO Auto-generated method stub
		DefaultComponents expenseTracker = new DefaultComponents();
		frmBankReport = expenseTracker.getMainFrame();
		lblMain = expenseTracker.getMainLabel();
		btnMain = expenseTracker.getMainButton();
		btnHome = expenseTracker.getHomeButton();
		
		createComponents();
		addToFrame();
		setFrmBankReport(frmBankReport);
		expenseObj.setFrmBankReport(frmBankReport);
		
	}

	public JFrame getFrmBankReport() {
		return frmBankReport;
	}

	public void setFrmBankReport(JFrame frmBankReport) {
		this.frmBankReport = frmBankReport;
	}

	
	private void createComponents() {
		// TODO Auto-generated method stub
		
		lblBankSel = new JLabel("SELECT THE BANK");
		lblBankSel.setBounds(150, 100, 200, 25);
		
		PerformDataBaseOperation bankNameDB = new PerformDataBaseOperation();
		ArrayList<String > bankName = bankNameDB.readBankNameDB();
		cmbBankSel = new JComboBox<Object>(bankName.toArray());
		cmbBankSel.setBounds(375, 100, 100, 25);
		cmbBankSel.setSelectedItem(obj.getBankName());

		btnSearch = new JButton("SEARCH");
		btnSearch.setBounds(500, 100, 100, 25);
		btnSearch.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				getTheReport(e);
				
			}
		});
		
		lblCredit = new JLabel("CREDIT IN " + obj.getBankName().toUpperCase());
		lblCredit.setBounds(250, 180, 500, 25);
		lblCredit.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblAmount1 = new JLabel("Amount");
		lblAmount1.setBounds(250, 230, 100, 25);
		
		txtCredit = new JTextField();
		txtCredit.setBounds(370, 230, 100, 25);
		Double creditAmount = steurung.getCreditAmount(obj);
		txtCredit.setText(String.valueOf(creditAmount));
		
		
		lblDebit = new JLabel("DEBIT FROM " + obj.getBankName().toUpperCase());
		lblDebit.setBounds(250, 300, 500, 25);
		lblDebit.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblAmount2 = new JLabel("Amount");
		lblAmount2.setBounds(250, 350, 100, 25);
		
		txtDebit = new JTextField();
		txtDebit.setBounds(370, 350, 100, 25);
		Double debitAmount = steurung.getDebitAmount(obj);
		txtDebit.setText(String.valueOf(debitAmount));
		
		lblTotal = new JLabel("TOTAL SAVINGS IN " + obj.getBankName().toUpperCase());
		lblTotal.setBounds(250, 450, 500, 25);
		lblTotal.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblAmount3 = new JLabel("Amount");
		lblAmount3.setBounds(250, 500, 100, 25);
		
		Double total = creditAmount - debitAmount; 
		
		txtTotal = new JTextField();
		txtTotal.setBounds(370, 500, 100, 25);
		txtTotal.setText(String.valueOf(round(total,2)));
		
		btnBack = new JButton("BACK");
		btnBack.setBounds(300, 650, 150, 50);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				frmBankReport.dispose();
				pnlBankTransaction bank = new pnlBankTransaction(expenseObj);
				bank.getFrmBank().setVisible(true);			
				
			}
		});
		
		lblMessage = new JLabel();
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setVerticalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(100, 700, 600, 40);
	}

	private void addToFrame() {
		// TODO Auto-generated method stub
		frmBankReport.add(lblMain);
		frmBankReport.add(btnMain);
		frmBankReport.add(btnHome);
		frmBankReport.add(lblBankSel);
		frmBankReport.add(cmbBankSel);
		frmBankReport.add(btnSearch);
		frmBankReport.add(lblCredit);
		frmBankReport.add(lblAmount1);
		frmBankReport.add(txtCredit);
		frmBankReport.add(lblDebit);
		frmBankReport.add(lblAmount2);
		frmBankReport.add(txtDebit);
		frmBankReport.add(lblTotal);
		frmBankReport.add(lblAmount3);
		frmBankReport.add(txtTotal);
		frmBankReport.add(btnBack);
		frmBankReport.add(lblMessage);
		RefineryUtilities.centerFrameOnScreen(frmBankReport);
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	private void getTheReport(ActionEvent e) {
		// TODO Auto-generated method stub

		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
	         @Override
	         public Void doInBackground() throws Exception {

	            // mimic some long-running process here...
	        	 ObjectsBank obj = new ObjectsBank();
	        	 obj.setBankName(cmbBankSel.getSelectedItem().toString());
	        	 obj.setVonDate("");
	        	 obj.setBisDate("");
	        	 PnlBankReport report = new PnlBankReport(expenseObj,obj);
	        	 report.getFrmBankReport().setVisible(true);
	             return null;
	         }
	         
		      @Override
		        protected void done() {
		    	  frmBankReport.dispose();
		        }
	      };
	      
	   
	      mySwingWorker.execute();
	      lblMessage.setText("Bank Report is getting loaded, Please Wiat...");
	      lblMessage.setFont(new Font("Arial", Font.BOLD, 18));
	      lblMessage.setForeground(Color.GREEN);
	      JProgressBar progressBar = new JProgressBar();
	      progressBar.setIndeterminate(true);
	      progressBar.setBounds(300, 550, 150, 50);
	      frmBankReport.add(progressBar, BorderLayout.CENTER);
	      frmBankReport.revalidate();
	      
	}
	
	
}
