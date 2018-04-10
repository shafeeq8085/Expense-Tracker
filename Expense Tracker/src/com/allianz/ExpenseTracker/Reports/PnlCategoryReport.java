package com.allianz.ExpenseTracker.Reports;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.jfree.ui.RefineryUtilities;

import com.allianz.ExpenseTracker.Exceptions.expenseTrackerExceptions;
import com.allianz.ExpenseTracker.Objects.DefaultComponents;
import com.allianz.ExpenseTracker.Objects.ObjectsCategoryReport;
import com.allianz.ExpenseTracker.Objects.ObjectsDBFeedback;
import com.allianz.ExpenseTracker.Objects.ObjectsExpenseTracker;
import com.allianz.ExpenseTracker.Objects.objItemundAmount;
import com.allianz.ExpenseTracker.Steurung.Categories;

public class PnlCategoryReport {

		expenseTrackerExceptions expexception = new expenseTrackerExceptions();
		Categories category = new Categories();
		ObjectsDBFeedback feedBack = new ObjectsDBFeedback();
		
		ObjectsExpenseTracker expenseObj;
		
		JFrame frmCategoryReport;
		JLabel lblMain;
		JButton btnMain;
		JButton btnHome;
		
		JLabel lblCashInd;
		
		JLabel lblDatumVon;
		JTextField txtDatumVon;
		
		JLabel lblDatumBis;
		JTextField txtDatumBis;
		
		JLabel lblCategory;
		JLabel lblAmount;
		JLabel lblAmountExp;
		JLabel lblAmountDiff;
		
		JLabel lblCategoryName;
		JTextField txtAmount;
		JTextField txtAmountExp;
		
		ArrayList<JLabel> categCompoLabel;
		ArrayList<JLabel> categCompoText;
		ArrayList<JLabel> categCompoTextExp;
		ArrayList<JLabel> categCompoTextDiff;
		
		
		JButton btnBack;
		
		String vonDate;
		String bisDate;
		
		ArrayList<objItemundAmount> addItem = new ArrayList<objItemundAmount>();
		String erroFlag ="N";
		
		PnlCategoryReport(ObjectsExpenseTracker expenseObj,String vonDate, String bisDate) throws InstantiationException, IllegalAccessException {
		
			this.expenseObj = expenseObj;
			this.vonDate = vonDate;
			this.bisDate = bisDate;
			
			DefaultComponents expenseTracker = new DefaultComponents();
			frmCategoryReport = expenseTracker.getMainFrame();
			lblMain = expenseTracker.getMainLabel();
			btnMain = expenseTracker.getMainButton();
			btnHome = expenseTracker.getHomeButton();
			
			addComponents();
			addToFrame();
			expenseObj.setFrmCategoryReport(frmCategoryReport);
			
			
		}

		private void addComponents() throws InstantiationException, IllegalAccessException {
			// TODO Auto-generated method stub
			
			lblDatumVon = new JLabel("Date From:");
			lblDatumVon.setBounds(150, 70, 100, 25);
			txtDatumVon = new JTextField();
			txtDatumVon.setBounds(275, 70, 80, 25);	
			txtDatumVon.setText(vonDate);
			txtDatumVon.setEnabled(false);
			
			lblDatumBis = new JLabel("Date Till:");
			lblDatumBis.setBounds(450, 70, 100, 25);
			txtDatumBis = new JTextField();
			txtDatumBis.setBounds(575, 70, 80, 25);
			txtDatumBis.setText(bisDate);
			txtDatumBis.setEnabled(false);
			
			lblCategory = new JLabel("CATEGORY");
			lblCategory.setBounds(150, 110, 125, 25);
			lblCategory.setFont(new Font("Arial", Font.BOLD, 16));
			
			lblAmount = new JLabel("AMOUNT ACT");
			lblAmount.setBounds(300, 110, 125, 25);
			lblAmount.setFont(new Font("Arial", Font.BOLD, 16));
			
			lblAmountExp = new JLabel("AMOUNT EXP");
			lblAmountExp.setBounds(450, 110, 125, 25);
			lblAmountExp.setFont(new Font("Arial", Font.BOLD, 16));
			
			lblAmountDiff = new JLabel("AMOUNT DIF");
			lblAmountDiff.setBounds(600, 110, 125, 25);
			lblAmountDiff.setFont(new Font("Arial", Font.BOLD, 16));
			
			Categories categ = new Categories();
			ObjectsCategoryReport obj = new ObjectsCategoryReport();
			obj.setX(150);
			obj.setY(150);
			obj.setWidth(125);
			obj.setHeight(25);
			obj.setBold("N");
			obj.setVonDatum(vonDate);
			obj.setBisDatum(bisDate);		
			categ.getCategoriesComponents(obj);
			categCompoLabel = categ.getCategoriesLabel();
			categCompoText = categ.getCategoriesTextF();
			categCompoTextExp = categ.getCategoriesTextExp();
			categCompoTextDiff = categ.getCategoriesTextDiff();
			
			
			btnBack = new JButton("BACK");
			btnBack.setBounds(390, 650, 80, 30);
			btnBack.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frmCategoryReport.dispose();
					expenseObj.getFrmReport().setVisible(true);
				}
			});

		}
		
		
		private void addToFrame() {
			// TODO Auto-generated method stub
			frmCategoryReport.add(lblMain);
			frmCategoryReport.add(btnMain);
			frmCategoryReport.add(btnHome);
			
			frmCategoryReport.add(lblDatumVon);
			frmCategoryReport.add(txtDatumVon);
			frmCategoryReport.add(lblDatumBis);
			frmCategoryReport.add(txtDatumBis);
			
			frmCategoryReport.add(lblCategory);
			frmCategoryReport.add(lblAmount);
			frmCategoryReport.add(lblAmountExp);
			frmCategoryReport.add(lblAmountDiff);
			
			for(JLabel categ : categCompoLabel) {
				
				frmCategoryReport.add(categ);
				
			}
			
			for(JLabel categ : categCompoText) {
				
				frmCategoryReport.add(categ);
				
			}
			
			for(JLabel categ : categCompoTextExp) {
				
				frmCategoryReport.add(categ);
				
			}
			
			for(JLabel categ : categCompoTextDiff) {
				
				frmCategoryReport.add(categ);
				
			}
			
			frmCategoryReport.add(btnBack);
			
			RefineryUtilities.centerFrameOnScreen(frmCategoryReport);
			setfrmCategoryReport(frmCategoryReport);
		}
		
		public JFrame getfrmCategoryReport() {
			return frmCategoryReport;
		}

		public void setfrmCategoryReport(JFrame frmCategoryReport) {
			this.frmCategoryReport = frmCategoryReport;
		}

}

