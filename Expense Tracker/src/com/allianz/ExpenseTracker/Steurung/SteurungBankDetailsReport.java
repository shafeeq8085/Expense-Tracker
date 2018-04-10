package com.allianz.ExpenseTracker.Steurung;

import java.io.StringReader;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.allianz.ExpenseTracker.DBO.PerformDataBaseOperation;
import com.allianz.ExpenseTracker.Objects.ObjectsBankDetailReport;
import com.allianz.ExpenseTracker.Objects.ObjectsPnlExpenseReport;
import com.allianz.ExpenseTracker.Objects.objItemundAmount;
import com.allianz.ExpenseTracker.Reports.pnlExpenseReport; 


public class SteurungBankDetailsReport {
	
	ObjectsBankDetailReport dates;
	String date;
	ArrayList<objItemundAmount> eintrag;
	Double totalCreditAmount = (double) 0;
	Double totalDebitAmount = (double) 0;
	Double totalInvAmount = (double) 0;
	
	protected Element suchergebnisRoot;
	List<Attribute> mAttributeVerlaufEintrag = new ArrayList<Attribute>();
	List<objItemundAmount> listOfTransactions;
	
	public String getFromDate() {
		// TODO Auto-generated method stub
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		date = dtf.format(localDate.minusDays(30));
		return date;
	}
	
	public String getToDate() {
		// TODO Auto-generated method stub
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDate localDate = LocalDate.now();
		date = dtf.format(localDate);
		return date;
	}
	
	public Document createResourceDoc() {
		Element root = new Element("korrespondenzverlauf");
		Element resourceElement = new Element("resource");			
		root.addContent(resourceElement);
		return new Document(root);
	}
	
	public String initializeHTMLHeader() {
		String html = null;
		try {
			System.setProperty("java.net.useSystemProxies", "true");
			Document ergebnis = createResourceDoc();
			StringWriter sw = new StringWriter();
			XMLOutputter xout = new XMLOutputter();
			xout.output(ergebnis, sw);
			Source xmlSource = new StreamSource(new StringReader(sw.toString()));
			Source xsltSource = new StreamSource(
					pnlExpenseReport.class
							.getResourceAsStream("/cocos_korrev_header2.xslt"));
			TransformerFactory transFact = TransformerFactory.newInstance();
			Transformer trans = transFact.newTransformer(xsltSource);
			StringWriter swHtml = new StringWriter();
			trans.transform(xmlSource, new StreamResult(swHtml));
			html = swHtml.toString();
			html = html.replace("<META http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">", "");

		} catch (final Throwable e) {
			System.out.println("ERROR");
			e.printStackTrace(); 
		}   
		
		return html;

	
	}
	
	
	public String initializeHTML(ObjectsBankDetailReport dates) {
		
		this.dates = dates;
		String html = null;
		try {
			Document ergebnis = createXMLDoc();
			StringWriter sw = new StringWriter();
			XMLOutputter xout = new XMLOutputter();
			xout.output(ergebnis, sw);
			Source xmlSource = new StreamSource(new StringReader(sw.toString()));
			Source xsltSource = new StreamSource(
					pnlExpenseReport.class
							.getResourceAsStream("/cocos_korrev2.xslt"));
			TransformerFactory transFact = TransformerFactory.newInstance();
			Transformer trans = transFact.newTransformer(xsltSource);
			StringWriter swHtml = new StringWriter();
			trans.transform(xmlSource, new StreamResult(swHtml));
			html = swHtml.toString();
			html = html.replace("<META http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">", "");

		} catch (final Throwable e) {
			System.out.println("ERROR 2");
			e.printStackTrace(); 
		}
		return html;   

	
	}
	
	
	private Document createXMLDoc() {
		
		try {
			objItemundAmount obj = new objItemundAmount();
			PerformDataBaseOperation readDB = new PerformDataBaseOperation();
			
			suchergebnisRoot        = new Element("korrespondenzverlauf");
			
	        Element resourceElement = new Element("resource");
	
			suchergebnisRoot.addContent(resourceElement);
			
			if (dates.getFlagShow().equals("N")) {
				eintrag = readDB.ReadBankDataBaseExcel(dates);
				setEintrag(eintrag);
			}
						
	        Iterator<objItemundAmount> itr = eintrag.iterator();
	        
	        listOfTransactions = new ArrayList<objItemundAmount>();
	        
	        totalCreditAmount = (double) 0;
	        totalDebitAmount = (double) 0;
	        
	        while (itr.hasNext()) {
	        	obj  = (objItemundAmount) itr.next();
	        	Element eintragElement = new Element("eintrag");
	        	mAttributeVerlaufEintrag = getEintragAttribute(obj);
	        	if (0 != mAttributeVerlaufEintrag.size()) {
	        		eintragElement.setAttributes(mAttributeVerlaufEintrag);
		            suchergebnisRoot.addContent(eintragElement);
		            
	        	}
	        }     
	        
	        Element eintragElement = new Element("eintrag");
	        mAttributeVerlaufEintrag = getFinalCreditAttribute(obj);
	        eintragElement.setAttributes(mAttributeVerlaufEintrag);
	        suchergebnisRoot.addContent(eintragElement);
	        
	        eintragElement = new Element("eintrag");
	        mAttributeVerlaufEintrag = getFinalDebitAttribute(obj);
	        eintragElement.setAttributes(mAttributeVerlaufEintrag);
	        suchergebnisRoot.addContent(eintragElement);
	        
	        eintragElement = new Element("eintrag");
	        mAttributeVerlaufEintrag = getFinalTotalAttribute(obj);
	        eintragElement.setAttributes(mAttributeVerlaufEintrag);
	        suchergebnisRoot.addContent(eintragElement);
	        
	        
	        setListOfTransactions(listOfTransactions);
			
				
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new Document(suchergebnisRoot);	
	}
	

	private List<Attribute> getEintragAttribute(objItemundAmount obj) {
		// TODO Auto-generated method stub
		
		List<Attribute> mAttributeVerlaufEintrag = new ArrayList<Attribute>();
		
		if (dates.getFlagShow().equals("Y")) {
			if (dates.getCategShow().equals(obj.getItem())){
				
				if (obj.getCashInd().equals("Credit")){
					totalCreditAmount = totalCreditAmount + obj.getAmount();
				} else if(obj.getCashInd().equals("Debit")) {
					totalDebitAmount = totalDebitAmount + obj.getAmount();
				}
				
				
				mAttributeVerlaufEintrag.add(new Attribute("sino", String.valueOf(obj.getSiNo())));
		        mAttributeVerlaufEintrag.add(new Attribute("datum", obj.getDate()));
		  
		        mAttributeVerlaufEintrag.add(new Attribute("bank", obj.getItem()));
		        
		        mAttributeVerlaufEintrag.add(new Attribute("amount", String.valueOf(obj.getAmount())) );
		        
		        mAttributeVerlaufEintrag.add(new Attribute("type", obj.getCashInd()));
		        
		        listOfTransactions.add(obj);
		  
			}
			      
		} else {
			
			if (obj.getCashInd().equals("Credit")){
				totalCreditAmount = totalCreditAmount + obj.getAmount();
			} else if(obj.getCashInd().equals("Debit")) {
				totalDebitAmount = totalDebitAmount + obj.getAmount();
			}
			
			mAttributeVerlaufEintrag.add(new Attribute("sino", String.valueOf(obj.getSiNo())));
	        mAttributeVerlaufEintrag.add(new Attribute("datum", obj.getDate()));
	  
	        mAttributeVerlaufEintrag.add(new Attribute("bank", obj.getItem()));
	        
	        mAttributeVerlaufEintrag.add(new Attribute("amount", String.valueOf(obj.getAmount())) );
	        
	        mAttributeVerlaufEintrag.add(new Attribute("type", obj.getCashInd()));
	        
	        listOfTransactions.add(obj);
	  
		}
		
		return mAttributeVerlaufEintrag;
	}
	
	private List<Attribute> getFinalCreditAttribute(objItemundAmount obj) {
		// TODO Auto-generated method stub
		
		List<Attribute> mAttributeVerlaufEintrag = new ArrayList<Attribute>();
		
						
				totalCreditAmount = round(totalCreditAmount,2);
				
				mAttributeVerlaufEintrag.add(new Attribute("sino", " "));
		        mAttributeVerlaufEintrag.add(new Attribute("datum", " "));
		  
		        mAttributeVerlaufEintrag.add(new Attribute("bank", "Credit"));
		        
		        mAttributeVerlaufEintrag.add(new Attribute("amount", String.valueOf(totalCreditAmount)) );
		        
		        mAttributeVerlaufEintrag.add(new Attribute("type", " "));
		        
		return mAttributeVerlaufEintrag;
	}
	
	private List<Attribute> getFinalDebitAttribute(objItemundAmount obj) {
		// TODO Auto-generated method stub
		
		List<Attribute> mAttributeVerlaufEintrag = new ArrayList<Attribute>();
		
						
				totalDebitAmount = round(totalDebitAmount,2);
				
		        mAttributeVerlaufEintrag.add(new Attribute("sino", " "));
		        mAttributeVerlaufEintrag.add(new Attribute("datum", " "));
		  
		        mAttributeVerlaufEintrag.add(new Attribute("bank", "Debit"));
		        
		        mAttributeVerlaufEintrag.add(new Attribute("amount", String.valueOf(totalDebitAmount)) );
		        
		        mAttributeVerlaufEintrag.add(new Attribute("type", " "));
		        
		return mAttributeVerlaufEintrag;
	}
	
	private List<Attribute> getFinalTotalAttribute(objItemundAmount obj) {
		// TODO Auto-generated method stub
		
		List<Attribute> mAttributeVerlaufEintrag = new ArrayList<Attribute>();
		
				totalInvAmount = totalCreditAmount - totalDebitAmount;
				totalInvAmount = round(totalInvAmount,2);
				
		        mAttributeVerlaufEintrag.add(new Attribute("sino", " "));
		        mAttributeVerlaufEintrag.add(new Attribute("datum", " "));
		  
		        mAttributeVerlaufEintrag.add(new Attribute("bank", "Investment"));
		        
		        mAttributeVerlaufEintrag.add(new Attribute("amount", String.valueOf(totalInvAmount)) );
		        
		        mAttributeVerlaufEintrag.add(new Attribute("type", " "));
		        
		return mAttributeVerlaufEintrag;
	}

	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}
	
	public List<objItemundAmount> getListOfTransactions() {
		return listOfTransactions;
	}

	public void setListOfTransactions(List<objItemundAmount> listOfTransactions) {
		this.listOfTransactions = listOfTransactions;
	}

	public ArrayList<objItemundAmount> getEintrag() {
		return eintrag;
	}

	public void setEintrag(ArrayList<objItemundAmount> eintrag) {
		this.eintrag = eintrag;
	}


	
}
