package com.allianz.ExpenseTracker.Panels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import org.jfree.ui.RefineryUtilities;

import com.allianz.ExpenseTracker.DBO.PerformDataBaseOperation;
import com.allianz.ExpenseTracker.Objects.DefaultComponents;
import com.allianz.ExpenseTracker.Objects.ObjectsExpenseReport;
import com.allianz.ExpenseTracker.Objects.ObjectsExpenseTracker;
import com.allianz.ExpenseTracker.Objects.objItemundAmount;
import com.allianz.ExpenseTracker.Reports.pnlBankDetailReport;
import com.allianz.ExpenseTracker.Reports.pnlExpenseReport;
import com.allianz.ExpenseTracker.Steurung.Categories;

public class PnlBankItemDetailsAnzeigen {

	objItemundAmount obj;
	Categories category = new Categories();
	ObjectsExpenseReport objReport = new ObjectsExpenseReport();
	ObjectsExpenseTracker expenseObj = new ObjectsExpenseTracker();
	
	JFrame frmItemDetails;
	JLabel lblMain;
	JLabel lblSiNo;
	JLabel lblDate;
	JLabel lblItem;
	JLabel lblAmount;
	JLabel lblCashInd;
	JLabel lblComment;
	
	public JFrame getFrmItemDetails() {
		return frmItemDetails;
	}


	public void setFrmItemDetails(JFrame frmItemDetails) {
		this.frmItemDetails = frmItemDetails;
	}


	JTextField txtSiNo;
	JTextField txtDate;
	JComboBox<?> cmpItem;
	JTextField txtAmount;
	JComboBox<?> cmpCashInd;
	JTextField txtComment;

	JButton btnBack;
	JButton btnMain;
	JButton btnHome;
	JButton btnSave;
	
	String frmDate;
	String toDate;
	String id,edit;
	
	PnlBankItemDetailsAnzeigen() {
		
	}
	
	public PnlBankItemDetailsAnzeigen(ObjectsExpenseReport objReport, String edit) {
		
		this.objReport = objReport;
		this.obj = objReport.getObj();
		this.edit = edit;
		initialize();
		expenseObj.setFrmItemDetails(frmItemDetails);
		
		
	}
	

	private void initialize() {
		
		frmItemDetails = new JFrame("Expense Tracker");
		frmItemDetails.setSize(800, 450);
		frmItemDetails.setLayout(null);
		frmItemDetails.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		DefaultComponents expenseTracker = new DefaultComponents();
		lblMain = expenseTracker.getMainLabel();
		btnMain = expenseTracker.getMainButton();
		btnHome = expenseTracker.getHomeButton();
		
		lblSiNo = new JLabel("SI NO:");
		lblSiNo.setBounds(250, 150, 100, 25);
		
		lblDate = new JLabel("DATE:");
		lblDate.setBounds(250, 175, 100, 25);
		
		lblItem = new JLabel("BANK:");
		lblItem.setBounds(250, 200, 100, 25);
		
		lblAmount = new JLabel("AMOUNT:");
		lblAmount.setBounds(250, 225, 100, 25);
		
		lblCashInd = new JLabel("TYPE:");
		lblCashInd.setBounds(250, 250, 100, 25);
		
		lblComment = new JLabel("COMMENTS:");
		lblComment.setBounds(250, 275, 100, 25);
		
		
		txtSiNo = new JTextField();
		txtSiNo.setBounds(400, 150, 150, 25);
		
		txtDate = new JTextField();
		txtDate.setBounds(400, 175, 150, 25);
		
		
		PerformDataBaseOperation bankNameDB = new PerformDataBaseOperation();
		ArrayList<String > bankName = bankNameDB.readBankNameDB();
		
		cmpItem = new JComboBox<Object>(bankName.toArray());
		cmpItem.setBounds(400, 200, 150, 25);
		
		txtAmount = new JTextField();
		txtAmount.setBounds(400, 225, 150, 25);
		
		ArrayList<String > typeInd = new ArrayList<>();
		typeInd.add("Credit");
		typeInd.add("Debit");
				
		cmpCashInd = new JComboBox<Object>(typeInd.toArray());
		cmpCashInd.setBounds(400, 250, 150, 25);
		
		txtComment = new JTextField();
		txtComment.setBounds(400, 275, 150, 25);
		if (edit.equals("Y") || edit.equals("D")) {
			
			if(edit.equals("Y"))  {
				btnSave = new JButton("SAVE");
			} else {
				btnSave = new JButton("DELETE");
			}
			
			btnSave.setBounds(330, 330, 100, 30);
			btnSave.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					 
					
					int response = JOptionPane.showConfirmDialog(frmItemDetails, "Do you want to continue?", "Confirm",
					        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					    if (response == JOptionPane.NO_OPTION) {
					    } else if (response == JOptionPane.YES_OPTION) {
					    	frmItemDetails.dispose();
					    	if (edit.equals("Y")) {
					    		PerformDataBaseOperation updDB = new PerformDataBaseOperation();
						    	fillData();
						    	updDB.updateBankDataBaseExcel(obj);
					    	} else {
					    		PerformDataBaseOperation dltDB = new PerformDataBaseOperation();
					    		dltDB.deleteBankDataBaseExcel(obj);
					    	}
					    	
					    	objReport.getFrmReport().dispose();
					    	pnlBankDetailReport report = new pnlBankDetailReport(objReport.getVonDate(),objReport.getBisDate(),"N");
							report.getFrmReport().setVisible(true);
					      
					    } else if (response == JOptionPane.CLOSED_OPTION) {
					      System.out.println("JOptionPane closed");
					    }

				}
			});
						
			btnBack = new JButton("BACK");
			btnBack.setBounds(450, 330, 100, 30);
			btnBack.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frmItemDetails.dispose();
				}
			});
			
		} else {
			
			disableTheFields();
			btnBack = new JButton("BACK");
			btnBack.setBounds(330, 330, 150, 30);
			btnBack.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frmItemDetails.dispose();
				}
			});
		}
		
		
		fillUpTheData();
		addToFrame();
		
	}


	private void disableTheFields() {
		// TODO Auto-generated method stub
		txtSiNo.setEditable(false);
		txtDate.setEditable(false);
		cmpItem.setEditable(false);
		txtAmount.setEditable(false);
		cmpCashInd.setEditable(false);
		
	}


	private void fillUpTheData() {
		// TODO Auto-generated method stub
		
		txtSiNo.setText(String.valueOf(obj.getSiNo()));
		txtSiNo.setEditable(false);
		txtDate.setText(String.valueOf(obj.getDate()));
		cmpItem.setSelectedItem(obj.getItem());
		txtAmount.setText(String.valueOf(obj.getAmount()));
		cmpCashInd.setSelectedItem(obj.getCashInd());
		txtComment.setText(obj.getComments());
		
	}


	private void addToFrame() {
		// TODO Auto-generated method stub
		
		frmItemDetails.add(lblMain);
		frmItemDetails.add(lblSiNo);
		frmItemDetails.add(lblDate);
		frmItemDetails.add(lblItem);
		frmItemDetails.add(lblAmount);
		frmItemDetails.add(lblCashInd);
		frmItemDetails.add(lblComment);
		frmItemDetails.add(txtSiNo);
		frmItemDetails.add(txtDate);
		frmItemDetails.add(cmpItem);
		frmItemDetails.add(txtAmount);
		frmItemDetails.add(cmpCashInd);
		frmItemDetails.add(txtComment);
		frmItemDetails.add(btnBack);
		frmItemDetails.add(btnMain);
		frmItemDetails.add(btnHome);
		if (edit.equals("Y") || edit.equals("D")){ 
			frmItemDetails.add(btnSave);
		}
		
		RefineryUtilities.centerFrameOnScreen(frmItemDetails);
		setFrmItemDetails(frmItemDetails);		
		
	}
	
	private void fillData() {
		// TODO Auto-generated method stub
		obj.setItem(cmpItem.getSelectedItem().toString());
		obj.setDate(txtDate.getText());
		obj.setAmount(Double.valueOf(txtAmount.getText()));
		obj.setCashInd(cmpCashInd.getSelectedItem().toString());
		System.out.println(txtComment.getText());
		obj.setComments(txtComment.getText());
		
	}
	
}

