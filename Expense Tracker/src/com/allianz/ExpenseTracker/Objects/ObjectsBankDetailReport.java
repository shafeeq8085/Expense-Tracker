package com.allianz.ExpenseTracker.Objects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ObjectsBankDetailReport {

	String date; 
	public String frmDate;
	public String toDate;
	
	public String flagShow;
	
	public String categShow;
	
	String month;
	
	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public ObjectsBankDetailReport() {
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
//		date = dtf.format(localDate.minusDays(30));
		date = dtf.format(localDate.withDayOfMonth(1));
		setFrmDate(date);
		date = dtf.format(localDate);
		setToDate(date);
		DateTimeFormatter dtf1 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		month = dtf1.format(localDate);
		setMonth(month);
	}

	public ObjectsBankDetailReport(String frmDate, String toDate) {
		
		setFrmDate(frmDate);
		setToDate(toDate);
	}
	
	public ObjectsBankDetailReport(String frmDate, String toDate, String flagShow, String categShow) {
		
		setFrmDate(frmDate);
		setToDate(toDate);
		setFlagShow(flagShow);
		setCategShow(categShow);
		
	}
	
	public String getFrmDate() {
		return frmDate;
	}
	public void setFrmDate(String frmDate) {
		this.frmDate = frmDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}

	public String getFlagShow() {
		return flagShow;
	}

	public void setFlagShow(String flagShow) {
		this.flagShow = flagShow;
	}

	public String getCategShow() {
		return categShow;
	}

	public void setCategShow(String categShow) {
		this.categShow = categShow;
	}
	
	
}
