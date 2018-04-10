package com.allianz.ExpenseTracker.Objects;

import java.util.HashMap;

public class ObjectsCategoryReport {
	
	int x, y, width, height;
	String vonDatum, bisDatum, bold, selectedCateg;

	HashMap<String, Double> map = new HashMap<String, Double>(); 
	
	public HashMap<String, Double> getMap() {
		return map;
	}
	public void setMap(HashMap<String, Double> map) {
		this.map = map;
	}
	public String getBold() {
		return bold;
	}
	public void setBold(String bold) {
		this.bold = bold;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public String getVonDatum() {
		return vonDatum;
	}
	public void setVonDatum(String vonDatum) {
		this.vonDatum = vonDatum;
	}
	public String getBisDatum() {
		return bisDatum;
	}
	public void setBisDatum(String bisDatum) {
		this.bisDatum = bisDatum;
	}
	public String getSelectedCateg() {
		return selectedCateg;
	}
	public void setSelectedCateg(String selectedCateg) {
		this.selectedCateg = selectedCateg;
	}
	
	
}
