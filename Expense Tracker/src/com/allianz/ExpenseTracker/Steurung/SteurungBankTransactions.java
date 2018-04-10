package com.allianz.ExpenseTracker.Steurung;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.channels.OverlappingFileLockException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.allianz.ExpenseTracker.DBO.PerformDataBaseOperation;
import com.allianz.ExpenseTracker.Exceptions.expenseTrackerExceptions;
import com.allianz.ExpenseTracker.Objects.ObjectsBank;

public class SteurungBankTransactions {

	expenseTrackerExceptions expexception = new expenseTrackerExceptions();
	ObjectsBank obj = new ObjectsBank();
	
	String date;
	String type;
	
	File dbFile;
	
	public expenseTrackerExceptions doCredit(Double amount, String bankName) {
		
		dbFile = new File("./res/ExpenseTracker.xlsx");
		int cellnum=0;
		type = "C";
		expexception = doTransaction(amount,cellnum,type,bankName);
		return(expexception);
	}
	
	public expenseTrackerExceptions doDebit(Double amount, String bankName) {
		
		dbFile = new File("./res/ExpenseTracker.xlsx");
		int cellnum=0;
		type = "D";
		expexception = doTransaction(amount,cellnum,type,bankName);
		return(expexception);
	}


	public expenseTrackerExceptions doTransaction(Double amount, int cellnum, String type, String bankName) {
		
		
		FileInputStream file;
		int i=1,countRows;
		try {
			file = new FileInputStream(dbFile);
			XSSFWorkbook workbook = new XSSFWorkbook(file);
			XSSFSheet sheet = workbook.getSheet("Bank");
			
			int a = sheet.getLastRowNum();
			a++;
			countRows = a;
        	
		    //This data needs to be written (Object[])
		    Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
		    getDate();
		    
		    String uuid = UUID.randomUUID().toString();
		    data.put(i, new Object[]{uuid,date,amount,type,bankName});				
			
		    //Iterate over data and write to sheet
		    Set<Integer> keyset = data.keySet();
	
		    int rownum = countRows;
		    for (Integer key : keyset) 
		    {
		        //create a row of excelsheet
		        Row row = sheet.createRow(rownum++);
	
		        //get object array of prerticuler key
		        Object[] objArr = data.get(key);
	
	
		        for (Object obj : objArr) 
		        {
		            Cell cell = row.createCell(cellnum++);
		            if (obj instanceof String) 
		            {
		                cell.setCellValue((String) obj);
		            }
		            else if (obj instanceof Double) 
		            {
		                cell.setCellValue((Double) obj);
		            }
		            else if (obj instanceof Integer) 
		            {
		                cell.setCellValue((Integer) obj);
		            }
		        }
		    }
		        //Write the workbook in file system
		    FileOutputStream out = new FileOutputStream(dbFile);
		    workbook.write(out);
		    out.close();
		}
		catch (Exception e)
	    {
			expexception.setErrFlag("Y");
			if (e instanceof RuntimeException) {
				
				expexception.setErrMsg("Exception during processing of data");
			}
			else if(e instanceof FileNotFoundException) {
				
				expexception.setErrMsg("File not found: ./res/ExpenseTracker.xlsx");
			}
			else if(e instanceof OverlappingFileLockException) {
				
				expexception.setErrMsg("File is open: ./res/ExpenseTracker.xlsx");
			}
			else {
				expexception.setErrMsg("Unhandled exception occured. Please contact system administrator.");
			}
			e.printStackTrace();
	    }
		return(expexception);
	}
	
	public void getDate() {
		// TODO Auto-generated method stub
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		date = dtf.format(localDate);
	}
	
	public Double getCreditAmount(ObjectsBank obj) {
		// TODO Auto-generated method stub
		PerformDataBaseOperation getFromDB = new PerformDataBaseOperation();
		Double amount = getFromDB.readBankDB("C",obj);
		return amount;
	}
	
	public Double getDebitAmount(ObjectsBank obj) {
		// TODO Auto-generated method stub
		PerformDataBaseOperation getFromDB = new PerformDataBaseOperation();
		Double amount = getFromDB.readBankDB("D",obj);
		return amount;
	}
}
