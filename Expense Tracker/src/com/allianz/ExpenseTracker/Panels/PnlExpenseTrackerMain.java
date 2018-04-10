package com.allianz.ExpenseTracker.Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import org.jfree.ui.RefineryUtilities;

import com.allianz.ExpenseTracker.Exceptions.ExpenseTrackerConfigurationExceptions;
import com.allianz.ExpenseTracker.Objects.ObjectsExpenseTracker;
import com.allianz.ExpenseTracker.Util.ExpenseTrackerConfiguration;

public class PnlExpenseTrackerMain {

	ObjectsExpenseTracker expenseObj = new ObjectsExpenseTracker();
	
	JFrame frmExMain;
	
	JLabel lblMain;
	JLabel lblUserName;
	JLabel lblPassword;
	
	JTextField txtUserName;
	JPasswordField txtPassword;
	
	JButton btnLogin;
	JButton btnCancel;
	JButton btnClear;
	
	JLabel lblMessage;
	
	public PnlExpenseTrackerMain() {
	}
	
	public void initialize() {
		// TODO Auto-generated method stub
		createFrameMain();
		addComponents();
		expenseObj.setFrmExMain(frmExMain);
		
	}
	
	public JFrame getFrmExMain() {
		return frmExMain;
	}

	public void setFrmExMain(JFrame frmExMain) {
		this.frmExMain = frmExMain;
	}

	public void createFrameMain() {
		
		if (frmExMain == null) {
			frmExMain = new JFrame("Expense Tracker");
			frmExMain.setSize(400, 300);
			frmExMain.setLayout(null);
			frmExMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		setFrmExMain(frmExMain);
		
	}

	private void addComponents() {
		// TODO Auto-generated method stub
		getMainLabel();
		getUserSecurityWindow();
	}

	private void getUserSecurityWindow() {
		// TODO Auto-generated method stub
		lblUserName = new JLabel("USER NAME");
		lblUserName.setBounds(20, 75, 80, 30);
		
		lblPassword = new JLabel("PASSWORD");
		lblPassword.setBounds(20, 120, 80, 30);
		
		txtUserName = new JTextField();
		txtUserName.setBounds(120, 75, 120, 30);
		txtUserName.setText("OFIBURC");
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(120, 120, 120, 30);
		txtPassword.setText("ec1234");
		
		btnLogin = new JButton("LOGIN");
		btnLogin.setBounds(120, 160, 80, 30);
		btnLogin.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				boolean validUser = validateInout(txtUserName.getText().toUpperCase(),txtPassword.getPassword());
				if (validUser==true){
					frmExMain.dispose();
					PnlExpenseTrackerHome expenseHome = new PnlExpenseTrackerHome();
					expenseHome.getFrmTrackerHome().setVisible(true);
				 	
			    } else lblMessage.setText("User Name or Password is incorrect"+txtUserName.getText()+txtPassword.getPassword().toString());
			}

		});
		
		btnCancel = new JButton("CLEAR");
		btnCancel.setBounds(205, 160, 80, 30);
		btnCancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txtUserName.setText(null);
				txtPassword.setText(null);
			}
		});
		
		btnClear = new JButton("CLOSE");
		btnClear.setBounds(290, 160, 80, 30);
		btnClear.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);				
			}
		});
		
		lblMessage = new JLabel();
		lblMessage.setBounds(120, 200, 250, 30);
		
		addToFrame();
		
		
	}

	private void addToFrame() {
		// TODO Auto-generated method stub
		frmExMain.add(lblMain);
		frmExMain.add(lblUserName);
		frmExMain.add(lblPassword);
		frmExMain.add(txtUserName);
		frmExMain.add(txtPassword);
		frmExMain.add(btnLogin);
		frmExMain.add(btnCancel);
		frmExMain.add(btnClear);
		frmExMain.add(lblMessage);
		frmExMain.getRootPane().setDefaultButton(btnLogin);
		RefineryUtilities.centerFrameOnScreen(frmExMain);
	}

	public JLabel getMainLabel() {
		// TODO Auto-generated method stub
		lblMain = new JLabel("Expense Tracker System");
		lblMain.setBounds(120, 20, 200, 25);
		return(lblMain);
	}

	public JLabel getLblMain() {
		return lblMain;
	}
	
	private boolean validateInout(String text, char[] userPassword) {
		// TODO Auto-generated method stub
		
		String pw = new String(userPassword);
		boolean a = false;
		
		Properties p = null;
		
		try {
			p = ExpenseTrackerConfiguration.getProperties();
		} catch (ExpenseTrackerConfigurationExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String userName = p.getProperty("User");
		String passWord = p.getProperty("Password");
		if (userName.toUpperCase().equals(text) && passWord.equals(pw)) {
			a = true;
		}
		
		
		return a; 
	}


	public void setLblMain(JLabel lblMain) {
		this.lblMain = lblMain;
	}

	
}
