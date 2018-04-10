package com.allianz.ExpenseTracker.Reports;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import org.jfree.ui.RefineryUtilities;

import com.allianz.ExpenseTracker.Objects.DefaultComponents;
import com.allianz.ExpenseTracker.Objects.ObjectsCategoryReport;
import com.allianz.ExpenseTracker.Objects.ObjectsPnlExpenseReport;
import com.allianz.ExpenseTracker.Panels.PnlExpenseTrackerHome;
import com.allianz.ExpenseTracker.Steurung.Categories;

public class PnlStatiticsReport {
	
	JFrame frmStatiticsReport;
	JLabel lblMain;
	JButton btnMain;
	JButton btnHome;
	
	JLabel lblDatumVon;
	JLabel lblDatumBis;
	
	JTextField txtDatumVon;
	JTextField txtDatumBis;
	
	JButton btnSuchen;
	
	JLabel lblBankBalance;
	JTextField txtBankBalance;
	JLabel lblLoan;
	JTextField txtLoan;
	JLabel lblToIndia;
	JTextField txtToIndia;
	JLabel lblTotal;
	JTextField txtTotal;
	
	JLabel lblExpTotal;
	
	JTextField txtExpTotal;
	
	JLabel lblDifference;
	JTextField txtDifference;
	
	JButton btnBack;
	JButton btnPMView;
	
	JLabel lblMessage;

	ArrayList<JLabel> categCompoLabel;
	ArrayList<JLabel> categCompoText;
	
	ArrayList<JLabel> bankCompoLabel;
	ArrayList<JLabel> bankCompoText;
	
	String vonDate;
	String bisDate;
	
	PnlStatiticsReport pnlstats;
	
	public PnlStatiticsReport() {
		
		ObjectsPnlExpenseReport dates = new ObjectsPnlExpenseReport();
		this.vonDate = "2016/10/01";
		this.bisDate = dates.toDate;
		
		initialize();
		
	}
	
	PnlStatiticsReport(String vonDate, String bisDate) {
		
		this.vonDate = vonDate;
		this.bisDate = bisDate;
		
		initialize();
	}
	
	private void initialize() {
		// TODO Auto-generated method stub
		
		DefaultComponents expenseTracker = new DefaultComponents();
		frmStatiticsReport = expenseTracker.getMainFrame();
		lblMain = expenseTracker.getMainLabel();
		btnMain = expenseTracker.getMainButton();
		btnHome = expenseTracker.getHomeButton();
		
		createComponents();
		addToFrame();
		setFrmStatiticsReport(frmStatiticsReport);
		
	}

	private void createComponents() {
		
		lblDatumVon = new JLabel("Date From:");
		lblDatumVon.setBounds(50, 75, 100, 25);
		txtDatumVon = new JTextField();
		txtDatumVon.setBounds(150, 75, 80, 25);	
		txtDatumVon.setText(vonDate);
//		txtDatumVon.setEnabled(false);
		
		lblDatumBis = new JLabel("Date Till:");
		lblDatumBis.setBounds(275, 75, 100, 25);
		txtDatumBis = new JTextField();
		txtDatumBis.setBounds(400, 75, 80, 25);
		txtDatumBis.setText(bisDate);
//		txtDatumBis.setEnabled(false);

		btnSuchen = new JButton("CHECK");
		btnSuchen.setBounds(550, 75, 100, 25);
		btnSuchen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmStatiticsReport.dispose();
				PnlStatiticsReport report = new PnlStatiticsReport(txtDatumVon.getText(),txtDatumBis.getText());
				report.getFrmStatiticsReport().setVisible(true);
			}
		});
	
		Categories categ = new Categories();
		ObjectsCategoryReport obj = new ObjectsCategoryReport();
		obj.setX(150);
		obj.setY(150);
		obj.setWidth(200);
		obj.setHeight(25);
		obj.setBold("N");
		obj.setVonDatum(vonDate);
		obj.setBisDatum(bisDate);		
		try {
			categ.getBankComponents(obj);
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		bankCompoLabel = categ.getBankLabel();
		bankCompoText = categ.getBankTextF();
		
		btnPMView = new JButton("MONTHLY REPORT");
		btnPMView.setBounds(150, 550, 150, 50);
		btnPMView.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVonDate(txtDatumVon.getText());
				setBisDate(txtDatumBis.getText());
				frmStatiticsReport.setVisible(false);
				PnlMonthlygraphical3DChart threeDChart = new PnlMonthlygraphical3DChart(vonDate, bisDate);
				threeDChart.setFrmStatiticsReport(frmStatiticsReport);
				threeDChart.getfrmMonthlyGraphical3DChart().setVisible(true);
				
			}
		});
		
		
		
		btnBack = new JButton("BACK");
		btnBack.setBounds(300, 650, 150, 50);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmStatiticsReport.dispose();
				PnlExpenseTrackerHome expenseHome = new PnlExpenseTrackerHome();
				expenseHome.getFrmTrackerHome().setVisible(true);
			}
		});

		
		lblMessage = new JLabel();
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setVerticalAlignment(SwingConstants.CENTER);
		lblMessage.setBounds(100, 700, 600, 40);
		lblMessage.setText("");
	}

	private void addToFrame() {
		// TODO Auto-generated method stub
		
		frmStatiticsReport.add(lblMain);
		frmStatiticsReport.add(btnMain);
		frmStatiticsReport.add(btnHome);
		
		frmStatiticsReport.add(lblDatumVon);
		frmStatiticsReport.add(txtDatumVon);
		frmStatiticsReport.add(lblDatumBis);
		frmStatiticsReport.add(txtDatumBis);
		frmStatiticsReport.add(btnSuchen);
		for(JLabel bank : bankCompoLabel) {
			
			frmStatiticsReport.add(bank);
			
		}
		
		for(JLabel amount : bankCompoText) {
			
			frmStatiticsReport.add(amount);
			
		}
		
		frmStatiticsReport.add(btnBack);
		frmStatiticsReport.add(btnPMView);
		frmStatiticsReport.add(lblMessage);
		frmStatiticsReport.getRootPane().setDefaultButton(btnSuchen);
		RefineryUtilities.centerFrameOnScreen(frmStatiticsReport);
	}

	public JFrame getFrmStatiticsReport() {
		return frmStatiticsReport;
	}

	public void setFrmStatiticsReport(JFrame frmStatiticsReport) {
		this.frmStatiticsReport = frmStatiticsReport;
	}

	public String getVonDate() {
		return vonDate;
	}

	public void setVonDate(String vonDate) {
		this.vonDate = vonDate;
	}

	public String getBisDate() {
		return bisDate;
	}

	public void setBisDate(String bisDate) {
		this.bisDate = bisDate;
	}
}
