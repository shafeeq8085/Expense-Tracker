package com.allianz.ExpenseTracker.Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.text.NumberFormat;
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
import com.allianz.ExpenseTracker.Objects.ObjectsBank;
import com.allianz.ExpenseTracker.Objects.ObjectsDBFeedback;
import com.allianz.ExpenseTracker.Objects.ObjectsExpenseTracker;
import com.allianz.ExpenseTracker.Reports.pnlBankDetailReport;
import com.allianz.ExpenseTracker.Reports.pnlExpenseReport;
import com.allianz.ExpenseTracker.Steurung.SteurungBankTransactions;

public class pnlBankTransaction {

	expenseTrackerExceptions expexception;
	ObjectsExpenseTracker expenseObj;
	
	JFrame frmBank;
	JLabel lblMain;
	JButton btnMain;
	JButton btnHome;
	
	JLabel lblBankSel;
	JComboBox<?> cmbBankSel;
	JLabel lblCredit;
	JLabel lblDebit;
	JLabel lblAmount1;
	JLabel lblAmount2;
	JLabel lblNewBank;
	JLabel lblBankName;
	JButton btnCredit;
	JButton btnDebit;
	JTextField txtCredit;
	JTextField txtDebit;
	JTextField txtAddBank;
	JButton btnAddBank;
	JButton btnReport;
	JButton btnDetailReport;
	JButton btnBack;
	
	JLabel lblMessage;
	
	Double amount;
	
	public pnlBankTransaction(ObjectsExpenseTracker expenseObj) {
		
		this.expenseObj = expenseObj;
		initialize();
		
	}

	private void initialize() {
		// TODO Auto-generated method stub
		
		DefaultComponents expenseTracker = new DefaultComponents();
		frmBank = expenseTracker.getMainFrame();
		lblMain = expenseTracker.getMainLabel();
		btnMain = expenseTracker.getMainButton();
		btnHome = expenseTracker.getHomeButton();
		
		createComponents();
		addToFrame();
		setFrmBank(frmBank);
		expenseObj.setFrmBank(frmBank);
		
	}


	public JFrame getFrmBank() {
		return frmBank;
	}

	public void setFrmBank(JFrame frmBank) {
		this.frmBank = frmBank;
	}

	private void createComponents() {
		// TODO Auto-generated method stub
		
		lblBankSel = new JLabel("SELECT THE BANK");
		lblBankSel.setBounds(150, 100, 200, 25);
		
		PerformDataBaseOperation bankNameDB = new PerformDataBaseOperation();
		ArrayList<String > bankName = bankNameDB.readBankNameDB();
		
		cmbBankSel = new JComboBox<Object>(bankName.toArray());
		cmbBankSel.setBounds(450, 100, 200, 25);
		
		lblCredit = new JLabel("CREDIT TO BANK");
		lblCredit.setBounds(300, 180, 300, 25);
		lblCredit.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblAmount1 = new JLabel("AMOUNT");
		lblAmount1.setBounds(150, 210, 200, 25);
		
		txtCredit = new JTextField();
		txtCredit.setBounds(150, 230, 200, 25);
		
		btnCredit = new JButton("ADD");
		btnCredit.setBounds(450, 230, 100, 25);
		btnCredit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtCredit.getText().contains(",")){
					JOptionPane.showMessageDialog(frmBank, "Invalid character found in Amount field ','");
				} else {
					SteurungBankTransactions steurungBank = new SteurungBankTransactions();
					expexception = steurungBank.doCredit(Double.valueOf(txtCredit.getText()),cmbBankSel.getSelectedItem().toString());
					NumberFormat format = new DecimalFormat("#.##");
					if (expexception.getErrFlag() == "N") {
						JOptionPane.showMessageDialog(frmBank, "Amount "+ format.format(Double.valueOf(txtCredit.getText())) + " Creditted to " + cmbBankSel.getSelectedItem().toString() + " Succesfully");
						txtCredit.setText(null);
					} else {
						JOptionPane.showMessageDialog(frmBank, expexception.getErrMsg());
					}
				}
				
			}
		});
		
		lblDebit = new JLabel("DEBIT FROM BANK");
		lblDebit.setBounds(300, 300, 300, 25);
		lblDebit.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblAmount2 = new JLabel("AMOUNT");
		lblAmount2.setBounds(150, 330, 200, 25);
		
		txtDebit = new JTextField();
		txtDebit.setBounds(150, 350, 200, 25);
		
		btnDebit = new JButton("ADD");
		btnDebit.setBounds(450, 350, 100, 25);
		btnDebit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(txtDebit.getText().contains(",")){
					JOptionPane.showMessageDialog(frmBank, "Invalid character found in Amount field ','");
				} else {
					SteurungBankTransactions steurungBank = new SteurungBankTransactions();
					amount = Double.valueOf(txtDebit.getText());
					expexception = steurungBank.doDebit(amount,cmbBankSel.getSelectedItem().toString());
					NumberFormat format = new DecimalFormat("#.##");
					if (expexception.getErrFlag() == "N") {
						JOptionPane.showMessageDialog(frmBank, "Amount " + format.format(Double.valueOf(txtDebit.getText())) +" Debitted From " + cmbBankSel.getSelectedItem().toString() + " Succesfully");
						txtDebit.setText(null);					
					} else {
						JOptionPane.showMessageDialog(frmBank, expexception.getErrMsg());
					}
				}
				
			}
		});
		
		lblNewBank = new JLabel("ADD A NEW BANK");
		lblNewBank.setBounds(300, 400, 300, 25);
		lblNewBank.setFont(new Font("Arial", Font.BOLD, 16));
		
		lblBankName = new JLabel("BANK NAME");
		lblBankName.setBounds(150, 430, 200, 25);
		
		txtAddBank = new JTextField();
		txtAddBank.setBounds(150, 450, 200, 25);
		
		btnAddBank = new JButton("ADD BANK");
		btnAddBank.setBounds(450, 450, 100, 25);
		btnAddBank.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int output = JOptionPane.showConfirmDialog(frmBank
						, txtAddBank.getText()+" name will be added to the Bank DataBase. Please confirm."
						,"Bank DataBase"
						,JOptionPane.YES_NO_OPTION);
				if(output == JOptionPane.YES_OPTION) {
					ObjectsDBFeedback feedback = new ObjectsDBFeedback();
					PerformDataBaseOperation bankNameDB = new PerformDataBaseOperation();
					feedback = bankNameDB.addToBankDB(txtAddBank.getText());
					if (feedback.getExpexception().getErrFlag() == "N") {
						JOptionPane.showMessageDialog(frmBank,"Bank Name added Succesfully.");
						frmBank.dispose();
						PnlExpenseTrackerHome expenseHome = new PnlExpenseTrackerHome();
						expenseHome.getFrmTrackerHome().setVisible(true);
					} else {
						JOptionPane.showMessageDialog(frmBank, expexception.getErrMsg());
					}
				} 				
			}
		});
		
		btnReport = new JButton("REPORT");
		btnReport.setBounds(150, 600, 150, 50);
		btnReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getTheReport(e);
			}
		});
		
		btnDetailReport = new JButton("Detailed Report");
		btnDetailReport.setBounds(350, 600, 150, 50);
		btnDetailReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				getTheDetailedReport(e);
			}
		});
		
		btnBack = new JButton("BACK");
		btnBack.setBounds(550, 600, 150, 50);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmBank.dispose();
				PnlExpenseTrackerHome expenseHome = new PnlExpenseTrackerHome();
				expenseHome.getFrmTrackerHome().setVisible(true);
			}
		});
		
		lblMessage = new JLabel();
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setVerticalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(100, 700, 600, 40);
	}
	
	private void addToFrame() {
		// TODO Auto-generated method stub
		
		frmBank.add(lblMain);
		frmBank.add(btnMain);
		frmBank.add(btnHome);
		
		frmBank.add(lblBankSel);
		frmBank.add(cmbBankSel);
		frmBank.add(lblCredit);
		frmBank.add(lblAmount1);
		frmBank.add(txtCredit);
		frmBank.add(btnCredit);
		frmBank.add(lblDebit);
		frmBank.add(lblAmount2);
		frmBank.add(txtDebit);
		frmBank.add(btnDebit);
		frmBank.add(txtAddBank);
		frmBank.add(btnAddBank);
		frmBank.add(lblBankName);
		frmBank.add(lblNewBank);
		frmBank.add(btnReport);
		frmBank.add(btnDetailReport);
		frmBank.add(btnBack);
		frmBank.add(lblMessage);
		RefineryUtilities.centerFrameOnScreen(frmBank);
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
		    	  frmBank.dispose();
		        }
	      };
	      
	   
	      mySwingWorker.execute();
	      lblMessage.setText("Bank Report is getting loaded, Please Wiat...");
	      lblMessage.setFont(new Font("Arial", Font.BOLD, 18));
	      lblMessage.setForeground(Color.GREEN);
	      JProgressBar progressBar = new JProgressBar();
	      progressBar.setIndeterminate(true);
	      progressBar.setBounds(300, 550, 150, 50);
	      frmBank.add(progressBar, BorderLayout.CENTER);
	      frmBank.revalidate();
	      
	}

	private void getTheDetailedReport(ActionEvent e) {

		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
	         @Override
	         public Void doInBackground() throws Exception {

	        	 pnlBankDetailReport report = new pnlBankDetailReport();
	        	 report.getFrmReport().setVisible(true);
	             return null;
	         }
	         
		      @Override
		        protected void done() {
		    	  frmBank.dispose();
		        }
	      };
	      
	   
	      mySwingWorker.execute();
	      lblMessage.setText("Expense Report is getting loaded, Please Wait...");
	      lblMessage.setFont(new Font("Arial", Font.BOLD, 18));
	      lblMessage.setForeground(Color.GREEN);
	      JProgressBar progressBar = new JProgressBar();
	      progressBar.setIndeterminate(true);
	      progressBar.setBounds(100, 700, 600, 40);
	      frmBank.add(progressBar, BorderLayout.CENTER);
	      frmBank.revalidate();
	      
	}
	
}
