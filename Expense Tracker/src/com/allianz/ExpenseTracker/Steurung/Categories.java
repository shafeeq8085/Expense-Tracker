package com.allianz.ExpenseTracker.Steurung;

import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.xml.parsers.ParserConfigurationException;

import org.jdom2.input.SAXBuilder;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import com.allianz.ExpenseTracker.DBO.PerformDataBaseOperation;
import com.allianz.ExpenseTracker.Exceptions.ExpenseTrackerConfigurationExceptions;
import com.allianz.ExpenseTracker.Objects.ObjectsBank;
import com.allianz.ExpenseTracker.Objects.ObjectsCategoryAmount;
import com.allianz.ExpenseTracker.Objects.ObjectsCategoryReport;
import com.allianz.ExpenseTracker.Objects.ObjectsPnlExpenseReport;
import com.allianz.ExpenseTracker.Objects.objItemundAmount;

public class Categories {
	
	ObjectsCategoryReport obj = new ObjectsCategoryReport();
	
	ArrayList<ObjectsCategoryAmount> objAmountundCateg = new ArrayList<>();
	ArrayList<ObjectsCategoryAmount> objAmountundDate = new ArrayList<>();
	
	int x=0,y=0,width=0,height=0;
	String bold, category;
	
	public ArrayList<JLabel> categoriesTextF = new ArrayList<>();
	public ArrayList<JLabel> categoriesTextExp = new ArrayList<>();
	public ArrayList<JLabel> categoriesTextDiff = new ArrayList<>();
	
	public ArrayList<JLabel> categoriesLabel = new ArrayList<>();
	
	public ArrayList<JLabel> bankTextF = new ArrayList<>();
	public ArrayList<JLabel> bankLabel = new ArrayList<>();
	
	HashMap<String, Double> mapCategories = new HashMap<String, Double>();
	HashMap<String, String> mapUsageFlag = new HashMap<String, String>();  
	
	String bankName;
	String[] categoryList;
	
	protected static String configPath;
	
	public Categories() {
		try {
			getCategories();
		} catch (ExpenseTrackerConfigurationExceptions e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void getCategories() throws ExpenseTrackerConfigurationExceptions, ParserConfigurationException {
		
//		String category[]={"Telecom & Net","Grocery","Schooling","Travel Tickets","Trips","Clothing","External Food","Sport","Electricity","Household","Rent","Bank","Others","Medical","Insurance","Loan","India"};
		
		ArrayList<String> listCategory = new ArrayList<String>();
		
		configPath = "./config/ExpCategorisedAmount.xml";
		
		Document document = null;
		
		File configFile = new File(configPath);

		SAXBuilder sBuilder = new SAXBuilder();
		try {
			document = sBuilder.build(configFile);
		} catch (JDOMException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Element categ = document.getRootElement();
		List<Element> categList = categ.getChildren("Category");
		for(int j=0;j<categList.size();j++){
			Element eachcateg = categList.get(j);
			Element name = eachcateg.getChild("name");
			String nameStr = name.getText();
			Element amount = eachcateg.getChild("amount");
			String amountstr = amount.getText();
			Element usage = eachcateg.getChild("usage");
			String usageFlag = usage.getText(); 
			Double amountdbl = Double.valueOf(amountstr);
			listCategory.add(nameStr);
			mapCategories.put(nameStr,amountdbl);
			mapUsageFlag.put(nameStr,usageFlag);
			
		}
		
		categoryList = listCategory.toArray(new String[listCategory.size()]);
		setCategoryList(categoryList);
		setMapCategories(mapCategories);
		setMapUsageFlag(mapUsageFlag);
		
		
	}
	
		
	public void getCategoriesComponents(ObjectsCategoryReport obj) throws InstantiationException, IllegalAccessException {
		
		this.x=obj.getX();
		this.y=obj.getY();
		this.width=obj.getWidth();
		this.height=obj.getHeight();
		this.bold = obj.getBold();
		
		String category[] = null;
		category = getCategoryList();
		Map<String, Double> amountExp = getMapCategories();
		Map<String, String> usageFlag = getMapUsageFlag();
		
		Double amount, expAmount, diffAmount, totalAmount = (double) 0,totalAmountExp = (double) 0, totalAmountDiff = (double) 0;
		
		for (String s: category) {
			
			this.category = s;
			JLabel lblCategoriess = new JLabel(s.toUpperCase());
			if(bold.equals("Y")) {
				lblCategoriess.setFont(new Font("Arial", Font.BOLD, 16));
			}
			lblCategoriess.setBounds(x, y, width, height);
			categoriesLabel.add(lblCategoriess);
			
			JLabel txtCategories = new JLabel();
			txtCategories.setBounds(x+150, y, width, height);
			
			amount = getCategoriesWiseAmount(obj);
			totalAmount = totalAmount + amount;
			
			txtCategories.setText(String.valueOf(amount));
			txtCategories.setFont(new Font("Arial", Font.BOLD, 16));
			
			JLabel txtCategories1 = new JLabel();
			txtCategories1.setBounds(x+300, y, width, height);
			
			expAmount = (Double) amountExp.get(s);
			float factor = getFactorNegative(obj);
			
			float expSavingAmount = (float) (factor * expAmount);
			expAmount = (double) expSavingAmount;
			expAmount = round(expAmount,2);
			
			totalAmountExp = totalAmountExp + expAmount;
			
			txtCategories1.setText(String.valueOf(expAmount));
			txtCategories1.setFont(new Font("Arial", Font.BOLD, 16));
			
			diffAmount = expAmount - amount;
			diffAmount = round(diffAmount,2);

			JLabel txtCategories2 = new JLabel();
			txtCategories2.setBounds(x+450, y, width, height);
			txtCategories2.setText(String.valueOf(diffAmount));
			txtCategories2.setFont(new Font("Arial", Font.BOLD, 16));
			
			if (diffAmount < 0) {
				txtCategories2.setBackground(Color.RED);
			} else {
				if(usageFlag.get(s).equals("N")){
					txtCategories2.setBackground(Color.GREEN);
				} else {
					txtCategories2.setBackground(Color.YELLOW);
				}
			}
			
			txtCategories2.setOpaque(true);
			
			categoriesTextF.add(txtCategories);
			categoriesTextExp.add(txtCategories1);
			categoriesTextDiff.add(txtCategories2);
			
			y = y + 25;
	    }
		
		JLabel lblCategoriess = new JLabel("TOTAL");
		lblCategoriess.setFont(new Font("Arial", Font.BOLD, 16));
		lblCategoriess.setBounds(x, y+10, width, height);
		categoriesLabel.add(lblCategoriess);
		
		totalAmount = round(totalAmount,2);
		JLabel txtCategories = new JLabel();
		txtCategories.setBounds(x+150, y+10, width, height);
		txtCategories.setText(String.valueOf(totalAmount));
		txtCategories.setFont(new Font("Arial", Font.BOLD, 16));
		categoriesTextF.add(txtCategories);
		
		totalAmountExp = round(totalAmountExp,2);
		JLabel txtCategories1 = new JLabel();
		txtCategories1.setBounds(x+300, y+10, width, height);
		txtCategories1.setText(String.valueOf(totalAmountExp));
		txtCategories1.setFont(new Font("Arial", Font.BOLD, 16));
		categoriesTextExp.add(txtCategories1);
		
		totalAmountDiff = totalAmountExp - totalAmount;
		totalAmountDiff = round(totalAmountDiff,2);
		JLabel txtCategories2 = new JLabel();
		txtCategories2.setBounds(x+450, y+10, width, height);
		txtCategories2.setText(String.valueOf(totalAmountDiff));
		txtCategories2.setFont(new Font("Arial", Font.BOLD, 16));
		categoriesTextDiff.add(txtCategories2);
		
		if (totalAmountDiff < 0) {
			txtCategories2.setBackground(Color.RED);
		} else {
			txtCategories2.setBackground(Color.GREEN);
		}
		txtCategories2.setOpaque(true);
		
		setCategoriesLabel(categoriesLabel);
		setCategoriesTextF(categoriesTextF);
		setCategoriesTextExp(categoriesTextExp);
		setCategoriesTextDiff(categoriesTextDiff);
	}

	private Double getCategoriesWiseAmount(ObjectsCategoryReport obj) {
		// TODO Auto-generated method stub
		
		Double amount = (double) 0;
		ObjectsPnlExpenseReport dates = new ObjectsPnlExpenseReport();
		objItemundAmount objamount = new objItemundAmount();
		dates.setFrmDate(obj.getVonDatum());
		dates.setToDate(obj.getBisDatum());
		
		PerformDataBaseOperation readDB = new PerformDataBaseOperation();
		ArrayList<objItemundAmount> eintrag = readDB.ReadExpenseDataBaseExcel(dates);
		
		Iterator<objItemundAmount> itr = eintrag.iterator();
        
        while (itr.hasNext()) {
        	objamount  = (objItemundAmount) itr.next();
        	if (objamount.getItem().equals(category)) {
        		amount = amount + objamount.getAmount();
        	}
        }
        
        return round(amount,2);
//        return amount;
		
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public ArrayList<ObjectsCategoryAmount> getCategoriesandAmount(ObjectsCategoryReport obj) {
		// TODO Auto-generated method stub
		
		Double amount = (double) 0;
		ObjectsPnlExpenseReport dates = new ObjectsPnlExpenseReport();
		objItemundAmount objItemAmount = new objItemundAmount();
		dates.setFrmDate(obj.getVonDatum());
		dates.setToDate(obj.getBisDatum());
		
		PerformDataBaseOperation readDB = new PerformDataBaseOperation();
		ArrayList<objItemundAmount> eintrag = readDB.ReadExpenseDataBaseExcel(dates);
        
		String category[] = null;
		category = getCategoryList();
		
		
		for (String s: category) {
			
			amount = (double) 0;
			ObjectsCategoryAmount objCategoryAmount = new ObjectsCategoryAmount();
			Iterator<objItemundAmount> itr = eintrag.iterator();
			while (itr.hasNext()) {
				
				objItemAmount  = (objItemundAmount) itr.next();
	        	if (objItemAmount.getItem().equals(s)) {
	        		amount = amount + objItemAmount.getAmount();
	        	}
	        }
			
			objCategoryAmount.setAmount(amount);
			objCategoryAmount.setCategory(s);
			objAmountundCateg.add(objCategoryAmount);
		}
        
        
        return objAmountundCateg;
		
	}
	
	public ArrayList<ObjectsCategoryAmount> getMonthsandAmount(ObjectsCategoryReport obj) {
		// TODO Auto-generated method stub
		
		Double amount = (double) 0;
		ObjectsPnlExpenseReport dates = new ObjectsPnlExpenseReport();
		objItemundAmount objItemAmount = new objItemundAmount();
		dates.setFrmDate(obj.getVonDatum());
		dates.setToDate(obj.getBisDatum());
		
		PerformDataBaseOperation readDB = new PerformDataBaseOperation();
		ArrayList<objItemundAmount> eintrag = readDB.ReadExpenseDataBaseExcel(dates);
        
		String category[] = null;
		category = getMonthList(obj.getVonDatum(),obj.getBisDatum());
		
		
		for (String s: category) {
			
			amount = (double) 0;
			ObjectsCategoryAmount objCategoryAmount = new ObjectsCategoryAmount();
			Iterator<objItemundAmount> itr = eintrag.iterator();
			while (itr.hasNext()) {
				
				objItemAmount  = (objItemundAmount) itr.next();
	        	if (objItemAmount.getDate().substring(0, 7).equals(s)) {
	        		amount = amount + objItemAmount.getAmount();
	        	}
	        }
			
			objCategoryAmount.setAmount(amount);
			objCategoryAmount.setCategory(s);
			objAmountundCateg.add(objCategoryAmount);
		}
        
        
        return objAmountundCateg;
		
	}
	
	private String[] getMonthList(String vonDate, String bisDate) {
		// TODO Auto-generated method stub
		ArrayList<String> listCategory = new ArrayList<String>();
		
		 Calendar cal1 = new GregorianCalendar();
	     Calendar cal2 = new GregorianCalendar();

	     SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

	     Date date = null;
	     
		 try {
			date = sdf.parse(vonDate);
			cal1.setTime(date);
			date = (Date) sdf.parse(bisDate);
			cal2.setTime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		 while (cal1.before(cal2)) {
	            // add one month to date per loop
	            String date1 =     sdf.format(cal1.getTime()).toUpperCase();
	            listCategory.add(date1.substring(0, 7));
	            cal1.add(Calendar.MONTH, 1);
        }

		return listCategory.toArray(new String[listCategory.size()]);
		
	}

	
	public ArrayList<ObjectsCategoryAmount> getAmountAndDate(ObjectsCategoryReport obj) {
		// TODO Auto-generated method stub
		
		ObjectsPnlExpenseReport dates = new ObjectsPnlExpenseReport();
		objItemundAmount objItemAmount = new objItemundAmount();
		dates.setFrmDate(obj.getVonDatum());
		dates.setToDate(obj.getBisDatum());
		
		PerformDataBaseOperation readDB = new PerformDataBaseOperation();
		ArrayList<objItemundAmount> eintrag = readDB.ReadExpenseDataBaseExcel(dates);
        
		Iterator<objItemundAmount> itr = eintrag.iterator();
		
		String currentDate, prevDate = null;
		
		String firstEntry = "Y", writeFlag = "Y";
		
		Double amount = (double) 0, prevAmount = (double) 0;
		while (itr.hasNext()) {
			
			objItemAmount  = (objItemundAmount) itr.next();
			currentDate    = objItemAmount.getDate();
			
			ObjectsCategoryAmount objCategoryAmount = new ObjectsCategoryAmount();
			
			if (objItemAmount.getItem().equals(obj.getSelectedCateg())) {
			
				if(currentDate.equals(prevDate)) {
					amount = amount + objItemAmount.getAmount();
				}
				else {
					amount = objItemAmount.getAmount();
					if(firstEntry.equals("Y")) {
						firstEntry = "N";
					} else {
						objCategoryAmount.setAmount(prevAmount);
						objCategoryAmount.setDate(prevDate);
				       	objAmountundDate.add(objCategoryAmount);
				       	writeFlag = "Y";
					}
					
				}
				
				prevAmount = amount;
				prevDate   = currentDate;
				writeFlag = "N";
			}
	    }
		
		if (writeFlag.equals("N")) {
			ObjectsCategoryAmount objCategoryAmount = new ObjectsCategoryAmount();
			objCategoryAmount.setAmount(prevAmount);
			objCategoryAmount.setDate(prevDate);
	       	objAmountundDate.add(objCategoryAmount);
		}
        return objAmountundDate;
		
	}

	public void getBankComponents(ObjectsCategoryReport obj) throws InstantiationException, IllegalAccessException {
		
		this.x=obj.getX();
		this.y=obj.getY();
		this.width=obj.getWidth();
		this.height=obj.getHeight();
		this.bold = obj.getBold();
		
		PerformDataBaseOperation bankNameDB = new PerformDataBaseOperation();
		ArrayList<String > bankName = bankNameDB.readBankNameDB();
		
		Double savingTotalAmount = (double) 0;
		
		for (String s: bankName) {
			
			this.bankName = s;
			JLabel lblBankName = new JLabel("SAVINGS IN "+s.toUpperCase());
			if(bold.equals("Y")) {
				lblBankName.setFont(new Font("Arial", Font.BOLD, 16));
			}
			lblBankName.setBounds(x, y, width, height);
			bankLabel.add(lblBankName);
			
			JLabel txtBankAmount = new JLabel();
			txtBankAmount.setBounds(x+300, y, width, height);
			
			SteurungBankTransactions steurung = new SteurungBankTransactions();

			ObjectsBank objBank = new ObjectsBank();
			objBank.setBankName(this.bankName);
			objBank.setVonDate(obj.getVonDatum());
			objBank.setBisDate(obj.getBisDatum());
			
			Double creditAmount = steurung.getCreditAmount(objBank);
			Double debitAmount = steurung.getDebitAmount(objBank);
			Double savingAmount = creditAmount - debitAmount;
			
			savingAmount = round(savingAmount,2);
			txtBankAmount.setText(String.valueOf(savingAmount));
			txtBankAmount.setFont(new Font("Arial", Font.BOLD, 16));
			bankTextF.add(txtBankAmount);
			
			savingTotalAmount = savingTotalAmount + savingAmount;
			y = y + 50;
	    }
	
		JLabel lblBankName = new JLabel("TOTAL SAVINGS");
		lblBankName.setFont(new Font("Arial", Font.BOLD, 16));
		lblBankName.setBounds(x, y, width, height);
		bankLabel.add(lblBankName);
		
		savingTotalAmount = round(savingTotalAmount,2);
		JLabel txtBankAmount = new JLabel();
		txtBankAmount.setBounds(x+300, y, width, height);
		txtBankAmount.setText(String.valueOf(savingTotalAmount));
		txtBankAmount.setFont(new Font("Arial", Font.BOLD, 16));
		
		float factor = getFactorPositive(obj);
		float monthlySavingAmount = 1100;
		
		float expSavingAmount = factor * monthlySavingAmount;
		Double expAmount = (double) expSavingAmount;
		expAmount = round(expAmount,2);
		
		if (savingTotalAmount < expAmount) {
			txtBankAmount.setBackground(Color.RED);
		} else {
			txtBankAmount.setBackground(Color.GREEN);
		}
		txtBankAmount.setOpaque(true);
		
		bankTextF.add(txtBankAmount);
		
		y = y + 50;
		
		lblBankName = new JLabel("PER MONTH SAVINGS");
		lblBankName.setFont(new Font("Arial", Font.BOLD, 16));
		lblBankName.setBounds(x, y, width, height);
		bankLabel.add(lblBankName);
		
		savingTotalAmount = savingTotalAmount / factor;
		
		savingTotalAmount = round(savingTotalAmount,2);
		txtBankAmount = new JLabel();
		txtBankAmount.setBounds(x+300, y, width, height);
		txtBankAmount.setText(String.valueOf(savingTotalAmount));
		txtBankAmount.setFont(new Font("Arial", Font.BOLD, 16));
		if (savingTotalAmount < monthlySavingAmount) {
			txtBankAmount.setBackground(Color.RED);
		} else {
			txtBankAmount.setBackground(Color.GREEN);
		}
		txtBankAmount.setOpaque(true);
		bankTextF.add(txtBankAmount);
		
		y = y + 50;
		
		lblBankName = new JLabel("SAVINGS EXPECTED");
		lblBankName.setFont(new Font("Arial", Font.BOLD, 16));
		lblBankName.setBounds(x, y, width, height);
		bankLabel.add(lblBankName);
		
		txtBankAmount = new JLabel();
		txtBankAmount.setBounds(x+300, y, width, height);
		txtBankAmount.setText(String.valueOf(expAmount));
		txtBankAmount.setFont(new Font("Arial", Font.BOLD, 16));
		bankTextF.add(txtBankAmount);
		
		setBankLabel(bankLabel);
		setBankTextF(bankTextF);
	}

	private int getDays(ObjectsCategoryReport obj2) {
		// TODO Auto-generated method stub
		
		int days = 0;
		try{
			Calendar cal1 = new GregorianCalendar();
		     Calendar cal2 = new GregorianCalendar();

		     SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");

		     Date date;
		     
			 date = sdf.parse(obj2.getVonDatum());
			 cal1.setTime(date);
			 date = (Date) sdf.parse(obj2.getBisDatum());
			 cal2.setTime(date);
			 days = daysBetween(cal1.getTime(),cal2.getTime()); 

		} catch (Exception e) {
	        e.printStackTrace();
	    }
		 	    
		return days;
	}
	
	public float getFactorPositive(ObjectsCategoryReport obj) {
		
		int days = getDays(obj);
		float monthDays=30;
		
		float factor = days/monthDays;
		if (factor < 1) {
			
			factor = 1 + factor;
		}
		
		return factor;
		
	}
	
	public float getFactorNegative(ObjectsCategoryReport obj) {
		
		int days = getDays(obj);
		float monthDays=30;
		
		float factor = days/monthDays;
		
		return factor;
		
	}

	public int daysBetween(java.util.Date date, java.util.Date date2){
        return (int)( (date2.getTime() - date.getTime()) / (1000 * 60 * 60 * 24));
	}
	
	public ArrayList<JLabel> getCategoriesTextF() {
		return categoriesTextF;
	}

	
	public void setCategoriesTextF(ArrayList<JLabel> categoriesTextF) {
		this.categoriesTextF = categoriesTextF;
	}

	public ArrayList<JLabel> getCategoriesLabel() {
		return categoriesLabel;
	}

	public void setCategoriesLabel(ArrayList<JLabel> categoriesLabel) {
		this.categoriesLabel = categoriesLabel;
	}

	public ArrayList<JLabel> getBankTextF() {
		return bankTextF;
	}

	public void setBankTextF(ArrayList<JLabel> bankTextF) {
		this.bankTextF = bankTextF;
	}

	public ArrayList<JLabel> getBankLabel() {
		return bankLabel;
	}

	public void setBankLabel(ArrayList<JLabel> bankLabel) {
		this.bankLabel = bankLabel;
	}

	public ArrayList<JLabel> getCategoriesTextExp() {
		return categoriesTextExp;
	}

	public void setCategoriesTextExp(ArrayList<JLabel> categoriesTextExp) {
		this.categoriesTextExp = categoriesTextExp;
	}

	public ArrayList<JLabel> getCategoriesTextDiff() {
		return categoriesTextDiff;
	}

	public void setCategoriesTextDiff(ArrayList<JLabel> categoriesTextDiff) {
		this.categoriesTextDiff = categoriesTextDiff;
	}

	public HashMap<String, Double> getMapCategories() {
		return mapCategories;
	}

	public void setMapCategories(HashMap<String, Double> mapCategories) {
		this.mapCategories = mapCategories;
	}

	public String[] getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(String[] categoryList) {
		this.categoryList = categoryList;
	}

	public HashMap<String, String> getMapUsageFlag() {
		return mapUsageFlag;
	}

	public void setMapUsageFlag(HashMap<String, String> mapUsageFlag) {
		this.mapUsageFlag = mapUsageFlag;
	}

	
}
