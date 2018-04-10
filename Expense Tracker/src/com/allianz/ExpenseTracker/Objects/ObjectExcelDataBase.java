package com.allianz.ExpenseTracker.Objects;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ObjectExcelDataBase {

	FileInputStream file;
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	public XSSFSheet getSheet() {
		return sheet;
	}
	public void setSheet(XSSFSheet sheet) {
		this.sheet = sheet;
	}
	public FileInputStream getFile() {
		return file;
	}
	public void setFile(FileInputStream file) {
		this.file = file;
	}
	public XSSFWorkbook getWorkbook() {
		return workbook;
	}
	public void setWorkbook(XSSFWorkbook workbook) {
		this.workbook = workbook;
	}
	
	
}
