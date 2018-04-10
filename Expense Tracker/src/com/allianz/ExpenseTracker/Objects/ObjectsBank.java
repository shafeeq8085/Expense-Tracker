package com.allianz.ExpenseTracker.Objects;

public class ObjectsBank {

	String uuid;
	String date;
	Double amount;
	String type;
	
	String bankName;
	
	String vonDate;
	String bisDate;
	
	public ObjectsBank() {
		
	}
	
	ObjectsBank(Double amount, String type) {
		
		setAmount(amount);
		setType(type);
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

	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
		
	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
}
