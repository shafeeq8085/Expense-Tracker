package com.allianz.ExpenseTracker.Reports;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import org.jfree.ui.RefineryUtilities;

import com.allianz.ExpenseTracker.Objects.DefaultComponents;
import com.allianz.ExpenseTracker.Objects.ObjectsExpenseTracker;
import com.allianz.ExpenseTracker.Steurung.Categories;

public class PnlGraphicalReport {

	ObjectsExpenseTracker expenseObj;
	Categories category = new Categories();
	private static PnlGraphicalReport instance = new PnlGraphicalReport();
	
	JFrame frmGrReportHome;
	JLabel lblMain;
	JButton btnMain;
	JButton btnHome;

	JLabel lblDatumVon;
	JTextField txtDatumVon;
	JLabel lblDatumBis;
	JTextField txtDatumBis;
	
	JLabel lblSkipMe;
	
	JList<String> lstskipMe;
	DefaultListModel<String> JLM1;
	JScrollPane JSP1;
	
	JButton btnBarChart;
	JButton btnPieChart;
	JButton btnLineChart;
	JButton btn3DChart;
	
	JButton btnBack;
	
	String vonDate, bisDate;
	
	PnlGraphicalReport() {
		
	}
	
	public void PnlGraphicalReport1(ObjectsExpenseTracker expenseObj, String vonDate, String bisDate) {
		
		setExpenseObj(expenseObj);
		setVonDate(vonDate);
		setBisDate(bisDate);
		
		initialize();
		expenseObj.setfrmGrReportHome(frmGrReportHome);
		
	}

	private void initialize() {
		// TODO Auto-generated method stub
		
		DefaultComponents expenseTracker = new DefaultComponents();
		frmGrReportHome = expenseTracker.getMainFrame();
		lblMain = expenseTracker.getMainLabel();
		btnMain = expenseTracker.getMainButton();
		btnHome = expenseTracker.getHomeButton();
		
		createComponents();
		addToFrame();
		setfrmGrReportHome(frmGrReportHome);
	}

	private void createComponents() {
		// TODO Auto-generated method stub
		
		DefaultComponents expenseTracker = new DefaultComponents();
		frmGrReportHome = expenseTracker.getMainFrame();
		lblMain = expenseTracker.getMainLabel();
		btnMain = expenseTracker.getMainButton();
		
		lblDatumVon = new JLabel("Date From:");
		lblDatumVon.setBounds(150, 70, 100, 25);
		txtDatumVon = new JTextField();
		txtDatumVon.setBounds(275, 70, 80, 25);	
		txtDatumVon.setText(vonDate);
		
		lblDatumBis = new JLabel("Date Till:");
		lblDatumBis.setBounds(450, 70, 100, 25);
		txtDatumBis = new JTextField();
		txtDatumBis.setBounds(575, 70, 80, 25);
		txtDatumBis.setText(bisDate);
		
		lblSkipMe = new JLabel("Skip Me");
		lblSkipMe.setBounds(50, 130, 100, 25);
		
		JLM1 = new DefaultListModel<String>();
        
		String[] categ = null;
		categ = category.getCategoryList();
		
		int [] index = new int[] {0,0,0}; 
		int i = 0,j=0;
		for(String s: categ) {
			JLM1.addElement(s);
			if(s.equals("Rent") || s.equals("India") || s.equals("Loan")) {
				index[i] = j;
				i++;
			}
			j++;
		}
		
//		JLM1.addElement("hi1");
//		JLM1.addElement("hi2");
//		JLM1.addElement("hi3");
//		JLM1.addElement("hi4");
		
		lstskipMe = new JList<String>(JLM1);
		lstskipMe.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		
		lstskipMe.setSelectedIndices(index);
		lstskipMe.setVisibleRowCount(3);
		
		setLstskipMe(lstskipMe);
		
		JSP1 = new JScrollPane(lstskipMe);
		JSP1.setBounds(50, 155, 200, 60);
		
		btnBarChart = new JButton("Bar Chart");
		btnBarChart.setBounds(300, 130, 200, 100);
		btnBarChart.setFont(new Font("Arial", Font.BOLD, 20));
		btnBarChart.setFocusPainted(false);
		btnBarChart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVonDate(txtDatumVon.getText());
				setBisDate(txtDatumBis.getText());
				PnlgraphicalBarChart barChart = new PnlgraphicalBarChart(getInstance());
				barChart.getfrmGraphicalBarChart().setVisible(true);
				frmGrReportHome.setVisible(false);
			}
		});
		
		btnPieChart = new JButton("Pie Chart");
		btnPieChart.setBounds(300, 280, 200, 100);
		btnPieChart.setFont(new Font("Arial", Font.BOLD, 20));
		btnPieChart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				setVonDate(txtDatumVon.getText());
				setBisDate(txtDatumBis.getText());
				PnlgraphicalPieChart pieChart = new PnlgraphicalPieChart(getInstance());
				pieChart.getfrmGraphicalPieChart().setVisible(true);
				frmGrReportHome.setVisible(false);
			}
		});
		
		btnLineChart = new JButton("Line Chart");
		btnLineChart.setBounds(300, 430, 200, 100);
		btnLineChart.setFont(new Font("Arial", Font.BOLD, 20));
		btnLineChart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVonDate(txtDatumVon.getText());
				setBisDate(txtDatumBis.getText());
				PnlgraphicalLineChart lineChart = new PnlgraphicalLineChart(getInstance());
				lineChart.getfrmGraphicalLineChart().setVisible(true);
				frmGrReportHome.setVisible(false);
			}
			
		});
		
		btn3DChart = new JButton("3D Chart");
		btn3DChart.setBounds(300, 580, 200, 100);
		btn3DChart.setFont(new Font("Arial", Font.BOLD, 20));
		btn3DChart.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				setVonDate(txtDatumVon.getText());
				setBisDate(txtDatumBis.getText());
				Pnlgraphical3DChart threeDChart = new Pnlgraphical3DChart(getInstance());
				threeDChart.getfrmGraphical3DChart().setVisible(true);
				frmGrReportHome.setVisible(false);
			}
		});
		
		btnBack = new JButton("BACK");
		btnBack.setBounds(325, 720, 150, 30);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmGrReportHome.dispose();
				expenseObj.getFrmReport().setVisible(true);
				
			}
		});
		
		
	}

	private void addToFrame() {
		// TODO Auto-generated method stub
		
		frmGrReportHome.add(lblMain);
		frmGrReportHome.add(btnMain);
		frmGrReportHome.add(btnHome);
		
		frmGrReportHome.add(lblDatumVon);
		frmGrReportHome.add(txtDatumVon);
		frmGrReportHome.add(lblDatumBis);
		frmGrReportHome.add(txtDatumBis);
		frmGrReportHome.add(lblSkipMe);
		frmGrReportHome.add(JSP1);
		frmGrReportHome.add(btnBarChart);
		frmGrReportHome.add(btnPieChart);
		frmGrReportHome.add(btnLineChart);
		frmGrReportHome.add(btn3DChart);
		frmGrReportHome.add(btnBack);
		RefineryUtilities.centerFrameOnScreen(frmGrReportHome);
	}

	public JFrame getfrmGrReportHome() {
		return frmGrReportHome;
	}

	public void setfrmGrReportHome(JFrame frmGrReportHome) {
		this.frmGrReportHome = frmGrReportHome;
	}

	public ObjectsExpenseTracker getExpenseObj() {
		return expenseObj;
	}

	public void setExpenseObj(ObjectsExpenseTracker expenseObj) {
		this.expenseObj = expenseObj;
	}

	public JList<String> getLstskipMe() {
		return lstskipMe;
	}

	public void setLstskipMe(JList<String> lstskipMe) {
		this.lstskipMe = lstskipMe;
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

	public static PnlGraphicalReport getInstance() {
		return instance;
	}

	public void setInstance(PnlGraphicalReport instance) {
		PnlGraphicalReport.instance = instance;
	}
	
}
