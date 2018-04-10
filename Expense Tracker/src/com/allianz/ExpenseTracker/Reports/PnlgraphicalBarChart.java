package com.allianz.ExpenseTracker.Reports;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
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
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
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


public class PnlgraphicalBarChart 
{
	ObjectsExpenseTracker expenseObj;
	
	JFrame frmGraphicalBarChart;
	JLabel lblMain;
	JButton btnMain;
	JButton btnHome;
	
	JButton btnBack;
	JLabel lblDatumVon;
	JTextField txtDatumVon;
	JLabel lblDatumBis;
	JTextField txtDatumBis;
	ChartPanel chartPanel;
	JButton btnJPG;
	JLabel lblMessage;
	
	JFreeChart barChart;
	
	public JFreeChart getBarChart() {
		return barChart;
	}



	public void setBarChart(JFreeChart barChart) {
		this.barChart = barChart;
	}

	PnlGraphicalReport objgraph;
	

	
	public PnlgraphicalBarChart(PnlGraphicalReport obj) {
		
		  
		  this.objgraph = obj;
		  expenseObj = obj.getExpenseObj();
		  
		  initialize(); 
	      expenseObj.setFrmGraphicalBarChart(frmGraphicalBarChart);
	      
	}
	   
	   
	   
	private void initialize() {
			// TODO Auto-generated method stub
			DefaultComponents expenseTracker = new DefaultComponents();
			frmGraphicalBarChart = expenseTracker.getMainFrame();
			lblMain = expenseTracker.getMainLabel();
			btnMain = expenseTracker.getMainButton();
			btnHome = expenseTracker.getHomeButton();
			
			lblDatumVon = new JLabel("Date From:");
			lblDatumVon.setBounds(150, 70, 100, 25);
			txtDatumVon = new JTextField();
			txtDatumVon.setBounds(275, 70, 80, 25);	
			txtDatumVon.setText(objgraph.getVonDate());
			txtDatumVon.setEnabled(false);
			
			lblDatumBis = new JLabel("Date Till:");
			lblDatumBis.setBounds(450, 70, 100, 25);
			txtDatumBis = new JTextField();
			txtDatumBis.setBounds(575, 70, 80, 25);
			txtDatumBis.setText(objgraph.getBisDate());
			txtDatumBis.setEnabled(false);
			
			getCharPane();
			
			btnBack = new JButton("BACK");
			btnBack.setBounds(320, 700, 150, 30);
			btnBack.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frmGraphicalBarChart.dispose();
					expenseObj.getfrmGrReportHome().setVisible(true);
					
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
				    String fileName = "C:\\h\\BarChart"+UUID.randomUUID()+".jpeg";
				    File lineChartFile = new File( fileName ); 
				    try {
						ChartUtilities.saveChartAsJPEG(lineChartFile ,getBarChart(), width ,height);
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
			
			
			frmGraphicalBarChart.add(lblMain);
			frmGraphicalBarChart.add(btnMain);
			frmGraphicalBarChart.add(btnHome);
			
			frmGraphicalBarChart.add(lblDatumVon);
			frmGraphicalBarChart.add(txtDatumVon);
			frmGraphicalBarChart.add(lblDatumBis);
			frmGraphicalBarChart.add(txtDatumBis);
			frmGraphicalBarChart.add(btnJPG);
			frmGraphicalBarChart.add(lblMessage);
			frmGraphicalBarChart.getContentPane().add(chartPanel);
			frmGraphicalBarChart.add(btnBack);	
			RefineryUtilities.centerFrameOnScreen(frmGraphicalBarChart);
			setfrmGraphicalBarChart(frmGraphicalBarChart);
	}

		
	private void getCharPane() {
			// TODO Auto-generated method stub
				JFreeChart barChart = ChartFactory.createBarChart(
		         "Bar Chart Report",           
		         "Category",            
		         "Amount",            
		         createDataset(),          
		         PlotOrientation.VERTICAL,           
		         true, true, false);
				 
				 final CategoryPlot plot = barChart.getCategoryPlot();
		         final BarRenderer renderer = (BarRenderer) plot.getRenderer();
		         final GradientPaint gradient =
		            new GradientPaint( 0.0f, 0.0f, Color.RED.brighter(),
		                               0.0f, 0.0f, Color.WHITE );
		         renderer.setSeriesPaint(0, gradient);
		         
				chartPanel = new ChartPanel( barChart );        
				chartPanel.setBounds(20, 150, 750, 510);
				setBarChart(barChart);
	}
		
	private CategoryDataset createDataset( ) {
		    
			final DefaultCategoryDataset dataset = 
				      new DefaultCategoryDataset( );  
			
			ObjectsCategoryAmount objCategoryAmount = new ObjectsCategoryAmount();
			ObjectsCategoryReport obj = new ObjectsCategoryReport();
			Categories categ = new Categories();
			
			obj.setVonDatum(objgraph.getVonDate());
			obj.setBisDatum(objgraph.getBisDate());		
			ArrayList<ObjectsCategoryAmount> arrayCategundAmount = categ.getCategoriesandAmount(obj);
			
			Iterator<ObjectsCategoryAmount> itr = arrayCategundAmount.iterator();
			while (itr.hasNext()) {
				
				objCategoryAmount  = (ObjectsCategoryAmount) itr.next();
				
				List<String> selected = objgraph.getLstskipMe().getSelectedValuesList();

				String[] selectedItems = new String[selected.size()];

				for(int i=0; i<selected.size();i++){

					selectedItems[i] = selected.get(i).toString();

				}
				
				if ( ArrayUtils.contains( selectedItems, objCategoryAmount.getCategory() ) ) {
				    // Do some stuff.
				}
				else { 
					dataset.addValue(objCategoryAmount.getAmount(), objCategoryAmount.getCategory(),"Expense");
				}
				
	        }           

		    return dataset; 
	}

	public JFrame getfrmGraphicalBarChart() {
		return frmGraphicalBarChart;
	}

	public void setfrmGraphicalBarChart(JFrame frmGraphicalBarChart) {
		this.frmGraphicalBarChart = frmGraphicalBarChart;
	}

}