package com.allianz.ExpenseTracker.Objects;

import javax.swing.JEditorPane;
import javax.swing.JFrame;

public class ObjectsExpenseReport {

	JEditorPane editor;
	int posX, posY;
	JFrame frmReport;
	String vonDate;
	String bisDate;
	
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
	objItemundAmount obj;
	
	public objItemundAmount getObj() {
		return obj;
	}
	public void setObj(objItemundAmount obj) {
		this.obj = obj;
	}
	public JEditorPane getEditor() {
		return editor;
	}
	public void setEditor(JEditorPane editor) {
		this.editor = editor;
	}
	public int getPosX() {
		return posX;
	}
	public void setPosX(int posX) {
		this.posX = posX;
	}
	public int getPosY() {
		return posY;
	}
	public void setPosY(int posY) {
		this.posY = posY;
	}
	public JFrame getFrmReport() {
		return frmReport;
	}
	public void setFrmReport(JFrame frmReport) {
		this.frmReport = frmReport;
	}
	
}
