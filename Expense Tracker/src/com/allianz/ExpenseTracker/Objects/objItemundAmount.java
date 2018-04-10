package com.allianz.ExpenseTracker.Objects;

public class objItemundAmount {

	private Double amount;
	private String item;
	private String cashInd;
	private Integer siNo;
	private String date;
	private String comments;
	private String uuid;
	
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
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

	public String getCashInd() {
		return cashInd;
	}

	public Integer getSiNo() {
		return siNo;
	}

	public void setSiNo(Integer siNo) {
		this.siNo = siNo;
	}

	public void setCashInd(String cashInd) {
		this.cashInd = cashInd;
	}
	
	public objItemundAmount() {
		
	}

	public objItemundAmount(String item, Double amount, String cashInd) {
		setAmount(amount);
		setItem(item);
		setCashInd(cashInd);
	}
	
	public objItemundAmount(Integer siNo, String uuid, String date, String item, Double amount, String cashInd, String comments) {
		setSiNo(siNo);
		setUuid(uuid);
		setDate(date);
		setAmount(amount);
		setItem(item);
		setCashInd(cashInd);
		setComments(comments);
	}
	
	public objItemundAmount(String date, String item, Double amount, String cashInd) {
		setSiNo(siNo);
		setUuid(uuid);
		setDate(date);
		setAmount(amount);
		setItem(item);
		setCashInd(cashInd);
	}
	
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	
	@Override
	public String toString() {
	    return getDate()+getItem()+getAmount();
	}

}
