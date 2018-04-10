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
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
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


public class PnlgraphicalPieChart 
{
	ObjectsExpenseTracker expenseObj;
	
	JFrame frmGraphicalPieChart;
	JLabel lblMain;
	JButton btnMain;
	JButton btnHome;
	
	JButton btnBack;
	JLabel lblDatumVon;
	JButton btnJPG;
	JTextField txtDatumVon;
	JLabel lblDatumBis;
	JTextField txtDatumBis;
	ChartPanel chartPanel;
	JLabel lblMessage;
	
	JFreeChart pieChart;
	
	public JFreeChart getpieChart() {
		return pieChart;
	}



	public void setpieChart(JFreeChart pieChart) {
		this.pieChart = pieChart;
	}

	PnlGraphicalReport objGraph;
	
	public PnlgraphicalPieChart(PnlGraphicalReport objGraph) {
		
		  this.expenseObj = objGraph.getExpenseObj();
		  this.objGraph = objGraph;
		
		  initialize(); 
	      expenseObj.setFrmGraphicalPieChart(frmGraphicalPieChart);
	      
	}
	   
	   
	   
	private void initialize() {
			// TODO Auto-generated method stub
			DefaultComponents expenseTracker = new DefaultComponents();
			frmGraphicalPieChart = expenseTracker.getMainFrame();
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
			
			getCharPane();
			
			btnBack = new JButton("BACK");
			btnBack.setBounds(320, 700, 150, 30);
			btnBack.addActionListener(new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					frmGraphicalPieChart.dispose();
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
				    String fileName = "C:\\h\\PieChart"+UUID.randomUUID()+".jpeg";
				    File lineChartFile = new File( fileName ); 
				    try {
						ChartUtilities.saveChartAsJPEG(lineChartFile ,getpieChart(), width ,height);
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
			
			frmGraphicalPieChart.add(lblMain);
			frmGraphicalPieChart.add(btnMain);
			frmGraphicalPieChart.add(btnHome);
			
			frmGraphicalPieChart.add(lblDatumVon);
			frmGraphicalPieChart.add(txtDatumVon);
			frmGraphicalPieChart.add(lblDatumBis);
			frmGraphicalPieChart.add(txtDatumBis);
			frmGraphicalPieChart.add(btnJPG);
			frmGraphicalPieChart.add(lblMessage);
			frmGraphicalPieChart.getContentPane().add(chartPanel);
			frmGraphicalPieChart.add(btnBack);	
			RefineryUtilities.centerFrameOnScreen(frmGraphicalPieChart);
			setfrmGraphicalPieChart(frmGraphicalPieChart);
	}

		
	private void getCharPane() {
			// TODO Auto-generated method stub
				JFreeChart pieChart = ChartFactory.createPieChart(
		         "Pie Chart Report",           
		         createDataset(),                    
		         true, true, false);
		         
				chartPanel = new ChartPanel( pieChart );        
				chartPanel.setBounds(20, 150, 750, 510);
				setpieChart(pieChart);
	}
		
	private PieDataset  createDataset( ) {
		    
			DefaultPieDataset dataset = new DefaultPieDataset( );  
			ObjectsCategoryAmount objCategoryAmount = new ObjectsCategoryAmount();
			ObjectsCategoryReport obj = new ObjectsCategoryReport();
			Categories categ = new Categories();
			
			obj.setVonDatum(objGraph.getVonDate());
			obj.setBisDatum(objGraph.getBisDate());		
			ArrayList<ObjectsCategoryAmount> arrayCategundAmount = categ.getCategoriesandAmount(obj);
			
			Iterator<ObjectsCategoryAmount> itr = arrayCategundAmount.iterator();
			while (itr.hasNext()) {
				objCategoryAmount  = (ObjectsCategoryAmount) itr.next();
				List<String> selected = objGraph.getLstskipMe().getSelectedValuesList();

				String[] selectedItems = new String[selected.size()];

				for(int i=0; i<selected.size();i++){

					selectedItems[i] = selected.get(i).toString();

				}
				
				if ( ArrayUtils.contains( selectedItems, objCategoryAmount.getCategory() ) ) {
				    // Do some stuff.
				}
				else { 
					dataset.setValue(objCategoryAmount.getCategory(),objCategoryAmount.getAmount());
				}
	        }           

		    return dataset; 
	}

	public JFrame getfrmGraphicalPieChart() {
		return frmGraphicalPieChart;
	}

	public void setfrmGraphicalPieChart(JFrame frmGraphicalPieChart) {
		this.frmGraphicalPieChart = frmGraphicalPieChart;
	}

}