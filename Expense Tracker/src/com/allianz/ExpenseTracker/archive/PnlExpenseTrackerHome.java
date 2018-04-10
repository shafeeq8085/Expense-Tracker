package com.allianz.ExpenseTracker.archive;

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
import com.allianz.ExpenseTracker.Panels.PnlAddExpense;
import com.allianz.ExpenseTracker.Panels.pnlBankTransaction;
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
		
		RefineryUtilities.centerFrameOnScreen(frmTrackerHome);
	}

	public JFrame getFrmTrackerHome() {
		return frmTrackerHome;
	}

	public void setFrmTrackerHome(JFrame frmTrackerHome) {
		this.frmTrackerHome = frmTrackerHome;
	}

	private void createComponents() {
		// TODO Auto-generated method stub
		
//		ImageIcon iconExpense = createImageIcon("addexpense.png","Java");
//		ImageIcon iconBank = createImageIcon("Bank.png","Java");
//		ImageIcon iconReport = createImageIcon("expenseReport.png","Java");
//		ImageIcon iconStatitics = createImageIcon("statitics.png","Java");
		
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
//		btnExpense.setIcon(resizeIcon(iconExpense));
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
//		btnBank.setIcon(resizeIcon(iconBank));
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
//		btnReport.setIcon(resizeIcon(iconReport));
		btnReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				btnExpense.setEnabled(false);
//				btnBank.setEnabled(false);
//				btnReport.setEnabled(false);
//				btnMReport.setEnabled(false);
//				frmTrackerHome.dispose();
				getTheReport(e);
//				pnlExpenseReport report = new pnlExpenseReport();
////			    frmTrackerHome.dispose();
//			    report.getFrmReport().setVisible(true);
			    
			}
			
		});
		
		btnMReport = new JButton("Statitics");
		btnMReport.setBounds(300, 550, 200, 100);
		btnMReport.setFont(new Font("Arial", Font.BOLD, 20));
//		btnMReport.setIcon(resizeIcon(iconStatitics));
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
	
	public String getRandomText()  {
		
		String [] msg = {"HI","HELLO","HALLO","SERVUS","HELL","DESP","HAPPY","AVARAADHAM","TOLL","NICE"};
		Random random = new Random();
		int a = random.nextInt(msg.length);
		
		return msg[a];
	}	
	
	public void actionPerformed(ActionEvent e){
        if(e.getSource()==close){
            System.exit(0);
        }
    }
	
////	private ImageIcon createImageIcon(String path, 
////		      String description) {
////		      java.net.URL imgURL = getClass().getClassLoader().getResource(path);
////		      if (imgURL != null) {
////		         return new ImageIcon(imgURL, description);
////		      } else {            
////		         System.err.println("Couldn't find file: " + path);
////		         return null;
////		      }
////	}
////	
//	private static Icon resizeIcon(ImageIcon icon) {
//	    Image img = icon.getImage();  
//	    Image resizedImage = img.getScaledInstance(230, 100,  java.awt.Image.SCALE_SMOOTH);  
//	    return new ImageIcon(resizedImage);
//	}
//	
	private void getTheReport(ActionEvent e) {
		// TODO Auto-generated method stub

		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
	         @Override
	         public Void doInBackground() throws Exception {

	            // mimic some long-running process here...
	        	 
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
		// TODO Auto-generated method stub

		SwingWorker<Void, Void> mySwingWorker = new SwingWorker<Void, Void>(){
	         @Override
	         public Void doInBackground() throws Exception {

	            // mimic some long-running process here...
	        	 
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
