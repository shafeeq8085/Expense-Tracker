package com.allianz.ExpenseTracker.Objects;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import com.allianz.ExpenseTracker.Panels.PnlExpenseTrackerHome;

public class DefaultComponents {

	JFrame frmExSub;
	public JFrame getMainFrame() {
		frmExSub = new JFrame("Expense Tracker");
		frmExSub.setSize(800, 800);
		frmExSub.setLayout(null);
		frmExSub.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		return(frmExSub);
	}
	
	public JLabel getMainLabel() {
		JLabel lblMain = new JLabel("EXPENSE TRACKER SYSTEM");
		lblMain.setBounds(250, 20, 300, 20);
		lblMain.setHorizontalAlignment(SwingConstants.CENTER);
		lblMain.setFont(new Font("Arial", Font.BOLD, 20));
		return(lblMain);	
	}
	
	public JButton getMainButton() {
		JButton btnMain = new JButton("LOGOUT");
		btnMain.setBounds(690, 20, 90, 25);
		btnMain.setHorizontalAlignment(SwingConstants.CENTER);
		btnMain.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(0);
			}
		});
		return(btnMain);	
	}
	
	public JButton getHomeButton() {
		JButton btnHome = new JButton("HOME");
		btnHome.setBounds(600, 20, 75, 25);
		btnHome.setHorizontalAlignment(SwingConstants.CENTER);
		btnHome.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmExSub.dispose();
				PnlExpenseTrackerHome expenseHome = new PnlExpenseTrackerHome();
				expenseHome.getFrmTrackerHome().setVisible(true);
			}
		});
		return(btnHome);	
	}
}
