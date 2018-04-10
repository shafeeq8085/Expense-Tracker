package com.allianz.ExpenseTracker.Reports;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.UUID;

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


public class PnlgraphicalLineChart 
{
	ObjectsExpenseTracker expenseObj; 
	
	JFrame frmGraphicalLineChart;
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
	
	JFreeChart lineCharts;
	PnlGraphicalReport objGraph;	

	
	public PnlgraphicalLineChart(PnlGraphicalReport objGraph) {
		
		  this.expenseObj = objGraph.getExpenseObj();
		  this.objGraph = objGraph;
		
		  initialize(); 
	      expenseObj.setFrmGraphicalLineChart(frmGraphicalLineChart);
	      
	}
	   
	   
	   
	private void initialize() {
			// TODO Auto-generated method stub
			DefaultComponents expenseTracker = new DefaultComponents();
			frmGraphicalLineChart = expenseTracker.getMainFrame();
			lblMain = expenseTracker.getMainLabel();
			btnMain = expenseTracker.getMainButton();
			btnHome = expenseTracker.getHomeButton();
			
			lblDatumVon = new JLabel("Date From:");
			lblDatumVon.setBounds(150, 70, 100, 25);
			txtDatumVon = new JTextField();
			txtDatumVon.setBounds(275, 70, 80, 25);	
			txtDatumVon.setText(objGraph.getVonDate());
			txtDatumVon.setEnabled(false);
			
			lblDatumBis = new JLabel("Date Till:");
			lblDatumBis.setBounds(450, 70, 100, 25);
			txtDatumBis = new JTextField();
			txtDatumBis.setBounds(575, 70, 80, 25);
			txtDatumBis.setText(objGraph.getBisDate());
			txtDatumBis.setEnabled(false);
			
			btnJPG = new JButton("Get As Jpeg");
			btnJPG.setBounds(600, 120, 150, 25);
			btnJPG.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					int width = 640;    /* Width of the image */
				    int height = 480;   /* Height of the image */ 
				    String fileName = "C:\\h\\LineChart"+UUID.randomUUID()+".jpeg";
				    File lineChartFile = new File( fileName ); 
				    try {
						ChartUtilities.saveChartAsJPEG(lineChartFile ,getLineCharts(), width ,height);
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
			
			getCharPane();
			
			btnBack = new JButton("BACK");
			btnBack.setBounds(320, 700, 150, 30);
			btnBack.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frmGraphicalLineChart.dispose();
					expenseObj.getfrmGrReportHome().setVisible(true);
					
				}
			});
		
			frmGraphicalLineChart.add(lblMain);
			frmGraphicalLineChart.add(btnMain);
			frmGraphicalLineChart.add(btnHome);
			
			frmGraphicalLineChart.add(lblDatumVon);
			frmGraphicalLineChart.add(txtDatumVon);
			frmGraphicalLineChart.add(lblDatumBis);
			frmGraphicalLineChart.add(txtDatumBis);
			frmGraphicalLineChart.add(btnJPG);
			frmGraphicalLineChart.getContentPane().add(chartPanel);
			frmGraphicalLineChart.add(btnBack);	
			frmGraphicalLineChart.add(lblMessage);
			RefineryUtilities.centerFrameOnScreen(frmGraphicalLineChart);
			setfrmGraphicalLineChart(frmGraphicalLineChart);
	}

		
	private void getCharPane() {
			// TODO Auto-generated method stub
				JFreeChart lineCharts = ChartFactory.createLineChart(
		         "Line Chart Report",           
		         "Category",            
		         "Amount",            
		         createDataset(),          
		         PlotOrientation.VERTICAL,           
		         true, true, false);
		         
				chartPanel = new ChartPanel( lineCharts );        
				chartPanel.setBounds(20, 150, 750, 510);
				setLineCharts(lineCharts);
				
	}
		
	private CategoryDataset createDataset( ) {
		    
			final DefaultCategoryDataset dataset = 
				      new DefaultCategoryDataset( );  
			
			ObjectsCategoryAmount objAmountDate = new ObjectsCategoryAmount();
			ObjectsCategoryReport obj = new ObjectsCategoryReport();
			Categories categ = new Categories();
			
			obj.setVonDatum(objGraph.getVonDate());
			obj.setBisDatum(objGraph.getBisDate());	
			obj.setSelectedCateg(objGraph.getLstskipMe().getSelectedValue().toString());
			ArrayList<ObjectsCategoryAmount> arrayobjAmountDate = categ.getAmountAndDate(obj);
			
			Iterator<ObjectsCategoryAmount> itr = arrayobjAmountDate.iterator();
			while (itr.hasNext()) {
				objAmountDate  = (ObjectsCategoryAmount) itr.next();
				String selectedItem = objGraph.getLstskipMe().getSelectedValue().toString();
				System.out.println(objAmountDate.getAmount());
				System.out.println(objAmountDate.getDate());
				
				dataset.addValue(objAmountDate.getAmount(), selectedItem, objAmountDate.getDate());
	        }           

		    return dataset; 
	}

	public JFrame getfrmGraphicalLineChart() {
		return frmGraphicalLineChart;
	}

	public void setfrmGraphicalLineChart(JFrame frmGraphicalLineChart) {
		this.frmGraphicalLineChart = frmGraphicalLineChart;
	}



	public JFreeChart getLineCharts() {
		return lineCharts;
	}



	public void setLineCharts(JFreeChart lineCharts) {
		this.lineCharts = lineCharts;
	}

	
}