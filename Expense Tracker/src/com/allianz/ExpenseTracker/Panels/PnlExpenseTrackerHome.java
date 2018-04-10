package com.allianz.ExpenseTracker.Panels;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;

import org.jfree.ui.RefineryUtilities;

import com.allianz.ExpenseTracker.Objects.DefaultComponents;
import com.allianz.ExpenseTracker.Objects.ObjectsExpenseTracker;
import com.allianz.ExpenseTracker.Reports.PnlStatiticsReport;
import com.allianz.ExpenseTracker.Reports.pnlExpenseReport;

public class PnlExpenseTrackerHome {

	ObjectsExpenseTracker expenseObj = new ObjectsExpenseTracker();
	
	JFrame frmTrackerHome;
	JLabel lblMain;
	JButton btnMain;
	JButton btnHome;
	
	JButton btnExpense;
	JButton btnBank;
	JButton btnReport;
	JButton btnMReport;
	
	JMenuBar bar;
    JMenu file, register;
    JMenuItem close, search;
    
	JLabel lblMessage;
		
	public PnlExpenseTrackerHome() {
		
		initialize();
	}

	private void initialize() {
		// TODO Auto-generated method stub
		
		DefaultComponents expenseTracker = new DefaultComponents();
		frmTrackerHome = expenseTracker.getMainFrame();
		lblMain = expenseTracker.getMainLabel();
		btnMain = expenseTracker.getMainButton();
		btnHome = expenseTracker.getHomeButton();
		
		createComponents();
		addToFrame();
		setFrmTrackerHome(frmTrackerHome);
		expenseObj.setFrmTrackerHome(frmTrackerHome);
		
	}

	public JFrame getFrmTrackerHome() {
		return frmTrackerHome;
	}

	public void setFrmTrackerHome(JFrame frmTrackerHome) {
		this.frmTrackerHome = frmTrackerHome;
	}

	private void createComponents() {
		
		bar= new JMenuBar();
        file= new JMenu("File");
        register= new JMenu("Search");
        
        close= new JMenuItem("Close");
        close.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		
        search= new JMenuItem("Request Query");
        search.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});

        //Keyboard Shortcut
        register.setMnemonic(KeyEvent.VK_S);
        file.setMnemonic(KeyEvent.VK_F);
        search.setMnemonic(KeyEvent.VK_R);
        
        bar.add(file);
        bar.add(register);
        file.add(close);
        register.add(search);
        
		btnExpense = new JButton("ADD EXPENSE");
		btnExpense.setBounds(300, 100, 200, 100);
		btnExpense.setFont(new Font("Arial", Font.BOLD, 20));
		btnExpense.setFocusPainted(false);
		btnExpense.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PnlAddExpense expense = new PnlAddExpense();
				expense.getFrmExpense().setVisible(true);
				frmTrackerHome.dispose();
			}
		});
		
		btnBank = new JButton("BANK");
		btnBank.setBounds(300, 250, 200, 100);
		btnBank.setFont(new Font("Arial", Font.BOLD, 20));
		btnBank.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pnlBankTransaction bank = new pnlBankTransaction(expenseObj);
				bank.getFrmBank().setVisible(true);
				frmTrackerHome.dispose();
			}
		});
		
		btnReport = new JButton("VIEW REPORT");
		btnReport.setBounds(300, 400, 200, 100);
		btnReport.setFont(new Font("Arial", Font.BOLD, 20));
		btnReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				getTheReport(e);
			}
			
		});
		
		btnMReport = new JButton("Statitics");
		btnMReport.setBounds(300, 550, 200, 100);
		btnMReport.setFont(new Font("Arial", Font.BOLD, 20));
		btnMReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
    			getTheStatiticsReport(e);
			}
		});
		
		lblMessage = new JLabel();
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setVerticalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(100, 700, 600, 40);
		lblMessage.setText(getRandomText());
		
		
	}
	
	private void addToFrame() {
		// TODO Auto-generated method stub
		
		frmTrackerHome.add(lblMain);
		frmTrackerHome.add(btnMain);
		frmTrackerHome.add(btnHome);
		
		frmTrackerHome.add(btnExpense);
		frmTrackerHome.add(btnBank);
		frmTrackerHome.add(btnReport);
		frmTrackerHome.add(btnMReport);
		frmTrackerHome.add(lblMessage);
		
//		frmTrackerHome.add(bar);
		
		RefineryUtilities.centerFrameOnScreen(frmTrackerHome);
	}
	
	public String getRandomText()  {
		
		String [] msg = {"HI","HELLO","HALLO","SERVUS","HELL","DESP","HAPPY","AVARAADHAM","TOLL","NICE"};
		Random random = new Random();
		int a = random.nextInt(msg.length);
		
		return msg[a];
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
		    	  frmTrackerHome.dispose();
		        }
	      };
	      
	   
	      mySwingWorker.execute();
	      lblMessage.setText("Expense Report is getting loaded, Please Wait...");
	      lblMessage.setFont(new Font("Arial", Font.BOLD, 18));
	      lblMessage.setForeground(Color.GREEN);
	      JProgressBar progressBar = new JProgressBar();
	      progressBar.setIndeterminate(true);
	      progressBar.setBounds(100, 700, 600, 40);
	      frmTrackerHome.add(progressBar, BorderLayout.CENTER);
	      frmTrackerHome.revalidate();
	      
	}
	
	private void getTheStatiticsReport(ActionEvent e) {

		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
	         @Override
	         public Void doInBackground() throws Exception {

				PnlStatiticsReport report = new PnlStatiticsReport();
				report.getFrmStatiticsReport().setVisible(true);
				return null;
	         }
	         
		      @Override
		        protected void done() {
		    	  frmTrackerHome.dispose();
		        }
	      };
	      
	   
	      mySwingWorker.execute();
	      lblMessage.setText("Statitics Report is getting loaded, Please Wait...");
	      lblMessage.setFont(new Font("Arial", Font.BOLD, 18));
	      lblMessage.setForeground(Color.GREEN);
	      JProgressBar progressBar = new JProgressBar();
	      progressBar.setIndeterminate(true);
	      progressBar.setBounds(100, 700, 600, 40);
	      frmTrackerHome.add(progressBar, BorderLayout.CENTER);
	      frmTrackerHome.revalidate();
	      
	}

}
