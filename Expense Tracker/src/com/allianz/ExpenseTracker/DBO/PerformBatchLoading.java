package com.allianz.ExpenseTracker.DBO;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.OverlappingFileLockException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.UUID;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.allianz.ExpenseTracker.Exceptions.expenseTrackerExceptions;
import com.allianz.ExpenseTracker.Objects.ObjectExcelDataBase;
import com.allianz.ExpenseTracker.Objects.ObjectsBank;
import com.allianz.ExpenseTracker.Objects.ObjectsDBFeedback;
import com.allianz.ExpenseTracker.Objects.ObjectsPnlExpenseReport;
import com.allianz.ExpenseTracker.Objects.objItemundAmount;

public class PerformBatchLoading {
	
	ArrayList<objItemundAmount> addItem = new ArrayList<objItemundAmount>();
	ObjectExcelDataBase excel = new ObjectExcelDataBase();
	expenseTrackerExceptions expexception = new expenseTrackerExceptions();
	ObjectsDBFeedback feedBack = new ObjectsDBFeedback();
	ObjectsPnlExpenseReport dates;
	ObjectsBank obj = new ObjectsBank();
	
	Double sum = (double) 0;
	String date;
	int countRows=1;
	
	public PerformBatchLoading() {
		
	}
	
	public ArrayList<objItemundAmount> LoadExpenseDataBaseExcel() {
		// TODO Auto-generated method stub
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String reportDate = dtf.format(localDate);
		
		ArrayList<objItemundAmount> eintrag = new ArrayList<>();

		Integer siNo = 0,cashIndold = 0;
		String cashInd = null;
		String item = null;
		Double amount = null;
		String date = null;
		
		try {
			ObjectExcelDataBase excelOld = getExpenseOldSheet();
			
			Iterator<Row> rows = excelOld.getSheet().rowIterator(); 
			Iterator<Cell> cells = null; 
			int a=0;
			while (rows.hasNext() && a < 350) { 
				a++;
				Row row = rows.next();

				if(!(0 == row.getRowNum())) {
					
					siNo++;
					cells = row.cellIterator(); 	 
					while (cells.hasNext()) { 
						Cell cell = cells.next();
						if (2 == cell.getColumnIndex()) {
							amount = cell.getNumericCellValue();
						} else if (3 == cell.getColumnIndex()) {
							item = cell.getStringCellValue();
						} else if (4 == cell.getColumnIndex()) {
							date = cell.getStringCellValue();
						}
						else if (5 == cell.getColumnIndex()) {
							cashIndold = (int) cell.getNumericCellValue();
							break;
						} 
					}
					
					if (cashIndold == 1) {
						cashInd = "Deutsche Bank";
					} else if (cashIndold == 0) {
						cashInd = "Commerz Bank";
					}
					
					if(date.equals("November")) {
						
						reportDate = "2016/11/01";
					} else if(date.equals("December")) {
						
						reportDate = "2016/12/01";
					} else if(date.equals("January")) {
						
						reportDate = "2017/01/01";
					} else if(date.equals("February")) {
						
						reportDate = "2017/02/01";
					} else if(date.equals("March")) {
						
						reportDate = "2017/03/01";
					} else if(date.equals("April")) {
						
						reportDate = "2017/04/01";
					} else if(date.equals("May")) {
						
						reportDate = "2017/05/01";
					} else if(date.equals("June")) {
						
						reportDate = "2017/06/01";
					} else if(date.equals("July")) {
						
						reportDate = "2017/07/01";
					}
					

					objItemundAmount addObj = new objItemundAmount(reportDate, item, amount, cashInd);
					addItem.add(addObj);
				}
				
		 	} 
		} catch (Exception e) {
	        e.printStackTrace();
	    }
		
		addToDB(addItem);	
		return eintrag;
		
	}
	
	public String updateExpenseDataBaseExcel(objItemundAmount obj) {
		
		try {
			ObjectExcelDataBase excel = getExpenseSheet();
            Cell cell = null;

            int rowNum = findRow(excel.getSheet(),obj.getUuid());
            //Update the value of cell
            cell = excel.getSheet().getRow(rowNum).getCell(0);
            cell.setCellValue(obj.getUuid());
            
            cell = excel.getSheet().getRow(rowNum).getCell(1);
            cell.setCellValue(obj.getDate());
            
            cell = excel.getSheet().getRow(rowNum).getCell(2);
            cell.setCellValue(obj.getItem());
            
            cell = excel.getSheet().getRow(rowNum).getCell(3);
            cell.setCellValue(String.valueOf(obj.getAmount()));
            
            cell = excel.getSheet().getRow(rowNum).getCell(4);
            cell.setCellValue(obj.getCashInd());

            excel.getFile().close();

            FileOutputStream outFile =new FileOutputStream(new File("C:\\h\\ExpenseTracker.xlsx"));
            excel.getWorkbook().write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

		return null;
		
	}
	
    public String deleteExpenseDataBaseExcel(objItemundAmount obj) {
		
		try {
			
			ObjectExcelDataBase excel = getExpenseSheet();

            //Delete the value of cell
            int rowNo = findRow(excel.getSheet(),obj.getUuid());
            
            XSSFRow removingRow=excel.getSheet().getRow(rowNo);
            if(removingRow!=null){
            	excel.getSheet().removeRow(removingRow);
            }

            excel.getFile().close();

            FileOutputStream outFile =new FileOutputStream(new File("C:\\h\\ExpenseTracker.xlsx"));
            excel.getWorkbook().write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

		return null;
		
	}
    
    public Double readBankDB(String mode, ObjectsBank obj) {
    	
    	Double amount = (double) 0;
		Double cellAmount = (double) 0;
		String type = null,bankName = null;
    	try {
			
    		ObjectExcelDataBase excel = getBankSheet();
			Iterator<Row> rows = excel.getSheet().rowIterator(); 
			Iterator<Cell> cells = null; 
			while (rows.hasNext()) { 
				
				Row row = rows.next();
				if(!(0 == row.getRowNum())) {
					cells = row.cellIterator(); 	 
					while (cells.hasNext()) { 
						Cell cell = cells.next();
						if (2 == cell.getColumnIndex()) {
							cellAmount = cell.getNumericCellValue();
						} else if (3 == cell.getColumnIndex()) {
							type = cell.getStringCellValue();
						} 
						else if (4 == cell.getColumnIndex()) {
							bankName = cell.getStringCellValue();
						} 
					}
					
					if (mode.equals(type) && (obj.getBankName().equals(bankName))) {
						amount = amount + cellAmount;
					}
					
				}
			}	
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
		return amount;
    	
    }
    
    private static int findRow(XSSFSheet sheet, String cellContent){
        /*
         *  This is the method to find the row number
         */

        int rowNum = 0; 

        for(Row row : sheet) {
            for(Cell cell : row) {

                if (cell.getCellType() == Cell.CELL_TYPE_STRING){

                    if(cell.getRichStringCellValue().getString ().equals(cellContent)){

                            rowNum = row.getRowNum();
                            return rowNum;  
                    }
                }
            }
        }               
        return rowNum;
    }
    
    
    
    @SuppressWarnings("rawtypes")
	public ObjectsDBFeedback addToDB(ArrayList expenseArray) {
		
    	String date = getDate();
		int i=0;
		String amount;
		
		try {
			ObjectExcelDataBase excel = getExpenseSheet();
			
			int a = excel.getSheet().getLastRowNum();
			a++;
			countRows = a;
			
			
		    //This data needs to be written (Object[])
		    Map<Integer, Object[]> data = new TreeMap<Integer, Object[]>();
		    Iterator it = expenseArray.iterator();
			while(it.hasNext()) {
				i++;
				a++;
				String uuid = UUID.randomUUID().toString();
				objItemundAmount abcd = (objItemundAmount) it.next();
				NumberFormat format = new DecimalFormat("#.##");
				sum = sum + abcd.getAmount();
				amount = format.format(abcd.getAmount());
				if(!abcd.getDate().equals("")) {
					date = abcd.getDate();
				}
				data.put(i, new Object[]{uuid,date,abcd.getItem(), amount, abcd.getCashInd()});				
			}
		    
			setSum(sum);
		    //Iterate over data and write to sheet
		    Set<Integer> keyset = data.keySet();
	
		    int rownum = countRows;
		    for (Integer key : keyset) 
		    {
		        //create a row of excelsheet
		        Row row = excel.getSheet().createRow(rownum++);
	
		        //get object array of prerticuler key
		        Object[] objArr = data.get(key);
	
		        int cellnum = 0;
	
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
		    FileOutputStream out = new FileOutputStream(new File("C:\\h\\ExpenseTracker.xlsx"));
		    excel.getWorkbook().write(out);
		    out.close();
		}
		catch (Exception e)
	    {
			expexception.setErrFlag("Y");
			if (e instanceof RuntimeException) {
				
				expexception.setErrMsg("Exception during processing of data");
			}
			else if(e instanceof FileNotFoundException) {
				
				expexception.setErrMsg("File not found: C:\\h\\ExpenseTracker.xlsx");
			}
			else if(e instanceof OverlappingFileLockException) {
				
				expexception.setErrMsg("File is open: C:\\h\\ExpenseTracker.xlsx");
			}
			else {
				expexception.setErrMsg("Unhandled exception occured. Please contact system administrator.");
			}
			e.printStackTrace();
	    }
		
		feedBack.setExpexception(expexception);
		feedBack.setSum(sum);
		return(feedBack);
	}

	@SuppressWarnings("rawtypes")
	public ArrayList readBankNameDB() {
		// TODO Auto-generated method stub
		
		ArrayList<String> bankName=new ArrayList<String>();
		
		try {
			ObjectExcelDataBase excel = getBankNameSheet();

	        //Iterate through each rows one by one
	        Iterator<Row> rowIterator = excel.getSheet().iterator();
	        while (rowIterator.hasNext())
	        {
	            Row row = rowIterator.next();
	            if(!(0 == row.getRowNum())) {
		            //For each row, iterate through all the columns
		            Iterator<Cell> cellIterator = row.cellIterator();

		            while (cellIterator.hasNext()) 
		            {
		                Cell cell = cellIterator.next();
		                //Check the cell type and format accordingly
		                bankName.add(cell.getStringCellValue());
		            }
	            }
	        }
	        excel.getFile().close();
	        
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		return bankName;
	}
	
	public ObjectsDBFeedback addToBankDB(String bankName) {
		
		try {
			ObjectExcelDataBase excel = getBankNameSheet();
			
			int a = excel.getSheet().getLastRowNum();
			a++;
			Row row = excel.getSheet().createRow(a);
	        Cell cell = row.createCell(0);
	        cell.setCellValue(bankName);
	        
	        //Write the workbook in file system
		    FileOutputStream out = new FileOutputStream(new File("C:\\h\\ExpenseTracker.xlsx"));
		    excel.getWorkbook().write(out);
		    out.close();
		}
		catch (Exception e)
	    {
			expexception.setErrFlag("Y");
			if (e instanceof RuntimeException) {
				
				expexception.setErrMsg("Exception during processing of data");
			}
			else if(e instanceof FileNotFoundException) {
				
				expexception.setErrMsg("File not found: C:\\h\\ExpenseTracker.xlsx");
			}
			else if(e instanceof OverlappingFileLockException) {
				
				expexception.setErrMsg("File is open: C:\\h\\ExpenseTracker.xlsx");
			}
			else {
				expexception.setErrMsg("Unhandled exception occured. Please contact system administrator.");
			}
			e.printStackTrace();
	    }
		
		feedBack.setExpexception(expexception);
		return(feedBack);
	}

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public String getDate() {
		// TODO Auto-generated method stub
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		String date = dtf.format(localDate);
		return date;
	}
	
	public ObjectExcelDataBase getExpenseSheet() throws IOException {
    	
    	try {
    		
    		FileInputStream file = new FileInputStream(new File("C:\\h\\ExpenseTracker.xlsx"));
    		XSSFWorkbook workbook = new XSSFWorkbook(file);
    		XSSFSheet sheet = workbook.getSheet("Expense");
    		excel.setFile(file);
			excel.setWorkbook(workbook);
			excel.setSheet(sheet);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excel;
		
    }
    
	public ObjectExcelDataBase getExpenseOldSheet() throws IOException {
    	
    	try {
    		
    		FileInputStream file = new FileInputStream(new File("C:\\h\\ExpenseTrackerOld.xlsx"));
    		XSSFWorkbook workbook = new XSSFWorkbook(file);
    		XSSFSheet sheet = workbook.getSheet("Expense");
    		excel.setFile(file);
			excel.setWorkbook(workbook);
			excel.setSheet(sheet);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excel;
		
    }
    public ObjectExcelDataBase getBankSheet() throws IOException {
    	
    	
    	try {
    		FileInputStream file = new FileInputStream(new File("C:\\h\\ExpenseTracker.xlsx"));
    		XSSFWorkbook workbook = new XSSFWorkbook(file);
    		XSSFSheet sheet = workbook.getSheet("Bank");
    		excel.setFile(file);
			excel.setWorkbook(workbook);
			excel.setSheet(sheet);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excel;
		
    }
    
    public ObjectExcelDataBase getBankNameSheet() throws IOException {
    	
    	
    	try {
    		FileInputStream file = new FileInputStream(new File("C:\\h\\ExpenseTracker.xlsx"));
    		XSSFWorkbook workbook = new XSSFWorkbook(file);
    		XSSFSheet sheet = workbook.getSheet("Bank Name");
    		excel.setFile(file);
			excel.setWorkbook(workbook);
			excel.setSheet(sheet);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return excel;
		
    }
}
