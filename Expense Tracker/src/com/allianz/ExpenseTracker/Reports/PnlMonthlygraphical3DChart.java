package com.allianz.ExpenseTracker.Reports;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.ArrayUtils;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart; 
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset; 
import org.jfree.data.category.DefaultCategoryDataset; 
import org.jfree.ui.RefineryUtilities;

import com.allianz.ExpenseTracker.Objects.DefaultComponents;
import com.allianz.ExpenseTracker.Objects.ObjectsCategoryAmount;
import com.allianz.ExpenseTracker.Objects.ObjectsCategoryReport;
import com.allianz.ExpenseTracker.Objects.ObjectsExpenseTracker;
import com.allianz.ExpenseTracker.Steurung.Categories;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class PnlMonthlygraphical3DChart 
{

	private static PnlStatiticsReport instance;
	
	ObjectsExpenseTracker expenseObj;
	
	JFrame frmStatiticsReport;
	
	JFrame frmMonthlyGraphical3DChart;
	JLabel lblMain;
	JButton btnMain;
	JButton btnHome;
	
	JButton btnJPG;
	JButton btnBack;
	JLabel lblDatumVon;
	JTextField txtDatumVon;
	JLabel lblDatumBis;
	JTextField txtDatumBis;
	ChartPanel chartPanel;
	JLabel lblMessage; 
	
	JFreeChart threeDChart;
	
	PnlGraphicalReport objGraph;
	
	PnlStatiticsReport objstatitics;
	
	public String vonDate;
	public String bisDate;

	
	public PnlMonthlygraphical3DChart(String vonDate, String bisDate) {
		
		
		  this.vonDate = vonDate;
		  this.bisDate = bisDate;
		  
		  initialize(); 
	      
	}
	   
	   
	   
	private void initialize() {
			// TODO Auto-generated method stub
			DefaultComponents expenseTracker = new DefaultComponents();
			frmMonthlyGraphical3DChart = expenseTracker.getMainFrame();
			lblMain = expenseTracker.getMainLabel();
			btnMain = expenseTracker.getMainButton();
			btnHome = expenseTracker.getHomeButton();
			
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
			
			getCharPane();
			
			btnBack = new JButton("BACK");
			btnBack.setBounds(320, 700, 150, 30);
			btnBack.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frmMonthlyGraphical3DChart.dispose();
					getFrmStatiticsReport().setVisible(true);
					
				}
			});

			btnJPG = new JButton("Get As Jpeg");
			btnJPG.setBounds(600, 120, 150, 25);
			btnJPG.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int width = 640;    /* Width of the image */
				    int height = 480;   /* Height of the image */ 
				    String fileName = "C:\\h\\3DChart"+UUID.randomUUID()+".jpeg";
				    File lineChartFile = new File( fileName ); 
				    try {
						ChartUtilities.saveChartAsJPEG(lineChartFile ,getthreeDChart(), width ,height);
					} catch (IOException ex) {
						// TODO Auto-generated catch block
						ex.printStackTrace();
					}
				    lblMessage.setText("Line Chart stored in location C:\\h");
				    lblMessage.setFont(new Font("Arial", Font.BOLD, 16));
					
				}
			});
			
			
			lblMessage = new JLabel();
			lblMessage.setBounds(275, 735, 500, 30);
			
			frmMonthlyGraphical3DChart.add(lblMain);
			frmMonthlyGraphical3DChart.add(btnMain);
			frmMonthlyGraphical3DChart.add(btnHome);
			
			frmMonthlyGraphical3DChart.add(lblDatumVon);
			frmMonthlyGraphical3DChart.add(txtDatumVon);
			frmMonthlyGraphical3DChart.add(lblDatumBis);
			frmMonthlyGraphical3DChart.add(txtDatumBis);
			frmMonthlyGraphical3DChart.add(btnJPG);
			frmMonthlyGraphical3DChart.add(lblMessage);
			frmMonthlyGraphical3DChart.getContentPane().add(chartPanel);
			frmMonthlyGraphical3DChart.add(btnBack);	
			RefineryUtilities.centerFrameOnScreen(frmMonthlyGraphical3DChart);
			setfrmMonthlyGraphical3DChart(frmMonthlyGraphical3DChart);
	}

		
	private void getCharPane() {
			// TODO Auto-generated method stub
				JFreeChart threeDChart = ChartFactory.createBarChart3D(
		         "3D Chart Report",           
		         "Category",            
		         "Amount",            
		         createDataset(),          
		         PlotOrientation.VERTICAL,           
		         true, true, false);
		         
				chartPanel = new ChartPanel( threeDChart );        
				chartPanel.setBounds(20, 150, 750, 510);
				setthreeDChart(threeDChart);
	}
		
	private CategoryDataset createDataset( ) {
		    
			final DefaultCategoryDataset dataset = 
				      new DefaultCategoryDataset( );  
			
			ObjectsCategoryAmount objCategoryAmount = new ObjectsCategoryAmount();
			ObjectsCategoryReport obj = new ObjectsCategoryReport();
			Categories categ = new Categories();
			
			obj.setVonDatum(vonDate);
			obj.setBisDatum(bisDate);		
			ArrayList<ObjectsCategoryAmount> arrayCategundAmount = categ.getMonthsandAmount(obj);
			
			Iterator<ObjectsCategoryAmount> itr = arrayCategundAmount.iterator();
			while (itr.hasNext()) {
				objCategoryAmount  = (ObjectsCategoryAmount) itr.next();
				dataset.addValue(objCategoryAmount.getAmount(), objCategoryAmount.getCategory(),"Expense");
	        }           

		    return dataset; 
	}

	public JFrame getfrmMonthlyGraphical3DChart() {
		return frmMonthlyGraphical3DChart;
	}

	public void setfrmMonthlyGraphical3DChart(JFrame frmMonthlyGraphical3DChart) {
		this.frmMonthlyGraphical3DChart = frmMonthlyGraphical3DChart;
	}



	public JFreeChart getthreeDChart() {
		return threeDChart;
	}



	public void setthreeDChart(JFreeChart threeDChart) {
		this.threeDChart = threeDChart;
	}

	public JFrame getFrmStatiticsReport() {
		return frmStatiticsReport;
	}

	public void setFrmStatiticsReport(JFrame frmStatiticsReport) {
		this.frmStatiticsReport = frmStatiticsReport;
	}
	
}