package com.allianz.ExpenseTracker.Reports;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.SwingUtilities;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.Position;
import javax.swing.text.StyleConstants;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import org.apache.commons.lang.StringUtils;
import org.apache.regexp.RE;
import org.jdom.Attribute;
import org.jfree.ui.RefineryUtilities;

import com.allianz.ExpenseTracker.Objects.DefaultComponents;
import com.allianz.ExpenseTracker.Objects.ObjectsExpenseReport;
import com.allianz.ExpenseTracker.Objects.ObjectsExpenseTracker;
import com.allianz.ExpenseTracker.Objects.ObjectsPnlExpenseReport;
import com.allianz.ExpenseTracker.Objects.objItemundAmount;
import com.allianz.ExpenseTracker.Panels.PnlAddExpense;
import com.allianz.ExpenseTracker.Panels.PnlExpenseTrackerHome;
import com.allianz.ExpenseTracker.Panels.PnlItemDetailsAnzeigen;
import com.allianz.ExpenseTracker.Steurung.Categories;
import com.allianz.ExpenseTracker.Steurung.SteurungDetailsAnzeigen;
import com.allianz.ExpenseTracker.Steurung.SteurungExpenseReport;

public class pnlExpenseReport {

	objItemundAmount obj;
	pnlExpenseReport report;
	ObjectsExpenseReport objReport = new ObjectsExpenseReport();
	ObjectsExpenseTracker expenseObj = new ObjectsExpenseTracker();
	
	JFrame frmReport;
	JPanel pnlReport;
	JLabel lblMain;
	JButton btnMain;
	JButton btnHome;
	
	JLabel lblDatumVon;
	JLabel lblDatumBis;
	JLabel lblOr;
	JLabel lblMonth;
	JLabel lblYear;
	
	JTextField txtDatumVon;
	JTextField txtDatumBis;
	
	JButton btnSuchen;
	
	JButton btnCategoryReport;
	
	JButton btnGraphicalReport;
	
	JButton btnAddExpense;
	
	JComboBox<?> cmpCategory;
	JButton btnShow;
	
	JEditorPane edHTMLHeaderPane;
	JEditorPane edHTMLPane;
	JScrollPane scrHTMLPane, cScpHtml;
	
	JButton btnBack;
	
	String date;
	private String rgbBackSelected = null;
	private String rgbFrontSelected = null;
	private String rgbBack = null;
	private String rgbFront = null;
	
	protected Element suchergebnisRoot;
	List<Attribute> mAttributeVerlaufEintrag = new ArrayList<Attribute>();
	
	SteurungExpenseReport steurungExpRep = new SteurungExpenseReport();
	SteurungDetailsAnzeigen steurungDetAnz = new SteurungDetailsAnzeigen();
	ObjectsPnlExpenseReport dates = new ObjectsPnlExpenseReport();
	Categories category = new Categories();
	
	JEditorPane editor;
	int posX, posY;
	
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

	public pnlExpenseReport() {
		
		dates.setFlagShow("N");
		initialize();
	}

	public pnlExpenseReport(String frmDate, String toDate, String strFlagShow) {
		
		dates = new ObjectsPnlExpenseReport(frmDate, toDate);
		dates.setFlagShow(strFlagShow);
		initialize();
	}
	
	public void pnlExpenseReportSuchen() {

		initialize();
	}
	
	private void initialize() {
		// TODO Auto-generated method stub
		DefaultComponents expenseTracker = new DefaultComponents();
		frmReport = expenseTracker.getMainFrame();
		lblMain = expenseTracker.getMainLabel();
		btnMain = expenseTracker.getMainButton();
		btnHome = expenseTracker.getHomeButton();
		
		try {
			createComponents();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		addToFrame();
		setFrmReport(frmReport);
		expenseObj.setFrmReport(frmReport);
		
	}

	public JFrame getFrmReport() {
		return frmReport;
	}

	public void setFrmReport(JFrame frmReport) {
		this.frmReport = frmReport;
	}
	
	private void createComponents() throws IOException {
		
		// TODO Auto-generated method stub
		lblDatumVon = new JLabel("Date From:");
		lblDatumVon.setBounds(50, 75, 100, 25);
		txtDatumVon = new JTextField();
		txtDatumVon.setBounds(150, 75, 80, 25);	
		txtDatumVon.setText(dates.frmDate);
		
		lblDatumBis = new JLabel("Date Till:");
		lblDatumBis.setBounds(275, 75, 100, 25);
		txtDatumBis = new JTextField();
		txtDatumBis.setBounds(400, 75, 80, 25);
		txtDatumBis.setText(dates.toDate);
		
		btnSuchen = new JButton("SEARCH");
		btnSuchen.setBounds(550, 75, 100, 25);
		btnSuchen.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmReport.dispose();
				dates = new ObjectsPnlExpenseReport(txtDatumVon.getText(),txtDatumBis.getText());
				dates.setFlagShow("N");
				pnlExpenseReportSuchen();
				getFrmReport().setVisible(true);
			}
		});
		
		btnCategoryReport = new JButton("Category Report");
		btnCategoryReport.setBounds(50, 125, 150, 30);
		btnCategoryReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				PnlCategoryReport report = null;
				try {
					report = new PnlCategoryReport(expenseObj,txtDatumVon.getText(),txtDatumBis.getText());
				} catch (InstantiationException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IllegalAccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				report.getfrmCategoryReport().setVisible(true);
				frmReport.setVisible(false);
			}
		});
		
		btnGraphicalReport = new JButton("Graphical Report");
		btnGraphicalReport.setBounds(250, 125, 150, 30);
		btnGraphicalReport.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PnlGraphicalReport barReport = new PnlGraphicalReport();
				barReport.setInstance(barReport);
				barReport.PnlGraphicalReport1(expenseObj,txtDatumVon.getText(),txtDatumBis.getText());
				barReport.getfrmGrReportHome().setVisible(true);
				frmReport.setVisible(false);
			}
		});
		
		btnAddExpense = new JButton("Add Expense");
		btnAddExpense.setBounds(450, 125, 150, 30);
		btnAddExpense.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PnlAddExpense expense = new PnlAddExpense();
				expense.getFrmExpense().setVisible(true);
				frmReport.dispose();
			}
		});
		
		cmpCategory = new JComboBox<Object>(category.getCategoryList());
		cmpCategory.setBounds(50, 165, 225, 25);
		if (dates.getFlagShow().equals("Y")) {
			cmpCategory.setSelectedItem(dates.getCategShow());
		}
		
		
		btnShow = new JButton("Show");
		btnShow.setBounds(300, 165, 100, 25);
		btnShow.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				dates.setCategShow(cmpCategory.getSelectedItem().toString());
				dates.setFlagShow("Y");
				frmReport.dispose();
				pnlExpenseReportSuchen();
				getFrmReport().setVisible(true);
			}
		});
		
			
		edHTMLPane = new JEditorPane();
		edHTMLPane.setEditable(false);
		edHTMLPane.setContentType("text/html; charset=UTF-8");
		initializeMouseListener();
		
		edHTMLHeaderPane = new JEditorPane();
		edHTMLHeaderPane.setName("HtmlHeaderPane");
		edHTMLHeaderPane.setEditable(false);
		edHTMLHeaderPane.setContentType("text/html; charset=UTF-8");
		
		cScpHtml = new JScrollPane(edHTMLPane);
        cScpHtml.setName("Scrollpane");
        cScpHtml.setHorizontalScrollBarPolicy(javax.swing.JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        cScpHtml.setVerticalScrollBarPolicy(javax.swing.JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        cScpHtml.setViewportView(edHTMLPane);
        
        JViewport viewportHeader = new JViewport();
        viewportHeader.setView(edHTMLHeaderPane);
        
        cScpHtml.setColumnHeaderView(viewportHeader);
        cScpHtml.getViewport().setBackground(edHTMLHeaderPane.getBackground());        
        
        edHTMLHeaderPane.setText(steurungExpRep.initializeHTMLHeader());
        edHTMLPane.setText(steurungExpRep.initializeHTML(dates));
		
        cScpHtml.setBounds(20, 200, 750, 510);	
		
		SwingUtilities.invokeLater(new Runnable()
		{
		    public void run()
		    {
		    	cScpHtml.getViewport().setViewPosition( new Point(0, 0) );
		    }
		});
		
		btnBack = new JButton("BACK");
		btnBack.setBounds(300, 720, 150, 30);
		btnBack.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				frmReport.dispose();
				PnlExpenseTrackerHome expenseHome = new PnlExpenseTrackerHome();
				expenseHome.getFrmTrackerHome().setVisible(true);
			}
		});
		
		edHTMLPane.addHyperlinkListener(new HyperlinkListener() {
			
			@Override
			public void hyperlinkUpdate(HyperlinkEvent e) {
				// TODO Auto-generated method stub
				if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
					String id = (String) e.getSourceElement().getAttributes()
							.getAttribute(HTML.Attribute.ID);
					if (id != null && id.startsWith("#posItem")) {
						doItemDetailsAnzeigen(id);
					}
				}
			}

			
		});
		
	}
	
	private void doItemDetailsAnzeigen(String id) {
		// TODO Auto-generated method stub
		String sindex = id.substring(8);
		int index = Integer.parseInt(sindex);
		if (steurungExpRep.getListOfTransactions().size() < index) {
			
		} else {
			obj = steurungExpRep.getListOfTransactions().get(index-1);
			createObjReport();
			PnlItemDetailsAnzeigen details = new PnlItemDetailsAnzeigen(objReport,"N");
			details.getFrmItemDetails().setVisible(true);
		}	
	}
	
	private void addToFrame() {
		// TODO Auto-generated method stub
	
		frmReport.add(lblMain);
		frmReport.add(btnMain);
		frmReport.add(btnHome);
		
		frmReport.add(lblDatumVon);
		frmReport.add(txtDatumVon);
		frmReport.add(lblDatumBis);
		frmReport.add(txtDatumBis);
		frmReport.add(btnSuchen);
		frmReport.add(btnCategoryReport);
		frmReport.add(btnGraphicalReport);
		frmReport.add(btnAddExpense);
		frmReport.add(cmpCategory);
		frmReport.add(btnShow);
		frmReport.add(cScpHtml);
		
		frmReport.add(btnBack);
		RefineryUtilities.centerFrameOnScreen(frmReport);
		
	}

	private void initializeMouseListener() {
		edHTMLPane.addMouseListener(new java.awt.event.MouseAdapter() {

			public void mouseReleased(java.awt.event.MouseEvent e) {
				mouseReleasedHtmlHeaderPane(e);
			}
		});
	}	
	protected void mouseReleasedHtmlHeaderPane(MouseEvent e) {
		
		if (e.getButton() == 1 && e.getClickCount() == 1) {
			Position.Bias bias[] = new Position.Bias[1];

			int pos = ((JEditorPane) e.getSource()).getUI().viewToModel(
					(JEditorPane) e.getSource(), new Point(e.getX(), e.getY()),
					bias);

			HTMLDocument htmlDoc = (HTMLDocument) ((JEditorPane) e.getSource())
					.getDocument();

			Element elem = htmlDoc.getCharacterElement(pos);
			Object imageLoc = null;

			if (elem != null
					&& elem.getAttributes().getAttribute(
							StyleConstants.NameAttribute) == HTML.Tag.IMG)
				imageLoc = elem.getAttributes().getAttribute(HTML.Attribute.ID);

			// Ein zusammengehöriges Tabellenelement kann aus mehreren TR
			// bestehen:
			// deshalb erstmal zurück bis eine ID auftaucht und von da an wieder
			// vorwärts bis zur nächsten ID E140572
			while (elem != null
					&& elem.getAttributes().getAttribute(
							StyleConstants.NameAttribute) != HTML.Tag.TR) {
				elem = elem.getParentElement();
				
			}
				
			
			if (elem == null)
				return;
			
			Element elemParent = elem.getParentElement();
			
			Object desel = null;
			Element deselElem = null;
					
			for (int i = 0; i < elemParent.getElementCount(); i++) {
				
				if (elemParent.getElement(i).getAttributes().getAttribute(
					HTML.Attribute.ID) != null
					&& elemParent.getElement(i).getAttributes()
						.getAttribute(HTML.Attribute.BGCOLOR) != null 
					&& elemParent.getElement(i).getAttributes()
						.getAttribute(HTML.Attribute.BGCOLOR)
						.equals("#" + getRgbBackSelected())) {
					desel = elemParent.getElement(i).getAttributes()
						.getAttribute(HTML.Attribute.ID);
					deselElem = elemParent.getElement(i);
				}
				if (desel != null && deselElem != null && deselElem != elem) {
					toggleRowSelection(e, deselElem, desel);
					desel = null;
					deselElem = null;
				}
				
			}
			
			Object loc = elem.getAttributes().getAttribute(HTML.Attribute.ID);

			if (imageLoc != null) {
//				doExpandCollapseEntry(e, elem, imageLoc);

				// Elem neu ermitteln, weil geändert
				elem = htmlDoc.getCharacterElement(pos);

				while (elem != null
						&& elem.getAttributes().getAttribute(
								StyleConstants.NameAttribute) != HTML.Tag.TR)
					elem = elem.getParentElement();
			}

			// Erste TR zu der angeklickten TR-Gruppe finden und diese markieren
			if (loc == null || ((String) loc).startsWith("#posSubTr")) {
				elemParent = elem.getParentElement();
				int idxNewElementToSelect = -1;
				for (int i = 0; i < elemParent.getElementCount(); i++) {
					if (elemParent.getElement(i).getAttributes().getAttribute(
							HTML.Attribute.ID) != null
							&& ((String) elemParent.getElement(i)
									.getAttributes().getAttribute(
											HTML.Attribute.ID))
									.startsWith("#posTr")) {
						loc = elemParent.getElement(i).getAttributes()
								.getAttribute(HTML.Attribute.ID);
						idxNewElementToSelect = i;
					}
					if (elemParent.getElement(i).equals(elem))
						i = elemParent.getElementCount();
				}
				if (idxNewElementToSelect > -1)
					elem = elemParent.getElement(idxNewElementToSelect);
			}

			if (loc == null)
				return;

			toggleRowSelection(e, elem, loc);
		}
		
		if (e.getButton() == 3 && e.getClickCount() == 1) {
			
			JEditorPane editor = (JEditorPane) e.getSource();
			
			setEditor(editor);
			setPosX(e.getX());
			setPosY(e.getY());
			
			Position.Bias bias[] = new Position.Bias[1];

			int pos = ((JEditorPane) e.getSource()).getUI().viewToModel(
					(JEditorPane) e.getSource(), new Point(e.getX(), e.getY()),
					bias);

			HTMLDocument htmlDoc = (HTMLDocument) ((JEditorPane) e.getSource())
					.getDocument();
			
			Element elem = htmlDoc.getCharacterElement(pos);
            AttributeSet a = (AttributeSet) elem.getAttributes();
            String id = (String) a.getAttribute(HTML.Attribute.ID);
//			obj = steurungDetAnz.getTheDetails(steurungExpRep,id);
            String sindex = id.substring(8);
    		int index = Integer.parseInt(sindex);
    		if (steurungExpRep.getListOfTransactions().size()>index-1){
    			obj = steurungExpRep.getListOfTransactions().get(index-1);
    			createObjReport();
    			new PumExpenseTrackerReport(objReport);
    		}
            
//			for (int i = 0; i < elem.getElementCount(); i++) {
//				if ((elem != null) && ((String) elem.getElement(i).getAttributes().getAttribute(
//						HTML.Attribute.ID)).startsWith("#posTr")) {
//			System.out.println(elem.getAttributes().getAttribute(
//					StyleConstants.NameAttribute));
//					elem = elem.getParentElement();
//					Object loc = elem.getElement(i).getAttributes()
//							.getAttribute(HTML.Attribute.ID);
//			System.out.println(elem);


//		}

//			}
							
		}

	}	
	
	private void createObjReport() {
		// TODO Auto-generated method stub
		objReport.setFrmReport(frmReport);
		objReport.setEditor(editor);
		objReport.setPosX(getPosX());
		objReport.setPosY(getPosY());
		objReport.setObj(obj);
		objReport.setVonDate(txtDatumVon.getText());
		objReport.setBisDate(txtDatumBis.getText());
	}

	private void toggleRowSelection(MouseEvent e, Element elem, Object loc) {

		HTMLDocument htmlDoc = (HTMLDocument) ((JEditorPane) e.getSource())
				.getDocument();
		String htmlTxt = (String) ((JEditorPane) e.getSource()).getText();

		boolean setAltBgColor = getOddElement((String) loc);

		int startIdx = htmlTxt.substring(0, htmlTxt.indexOf((String) loc))
				.lastIndexOf("<");
		int endIdx = htmlTxt.indexOf("</tr>", startIdx);
		String textTr = htmlTxt.substring(startIdx, endIdx);

		Object bgColor = elem.getAttributes().getAttribute(
				HTML.Attribute.BGCOLOR);

		String bgColorNewTxt = "";
		String fgColorNewTxt = "";

		if (bgColor != null && bgColor.equals("#" + getRgbBackSelected())) {
			if (!setAltBgColor)
				bgColorNewTxt = " bgcolor=\"#" + getRgbBack() + "\"";
			else
				bgColorNewTxt = " bgcolor=\"#" + getRgbBackAlternate() + "\"";

			fgColorNewTxt = " color=\"#" + getRgbFront() + "\"";
			textTr = StringUtils.replace(textTr, "\"yellow\"", "\"blue\"");
//			removeSelectedList((String) loc);

		} else {
			bgColorNewTxt = " bgcolor=\"#" + getRgbBackSelected() + "\"";
			fgColorNewTxt = " color=\"#" + getRgbFrontSelected() + "\"";
			textTr = StringUtils.replace(textTr, "\"blue\"", "\"yellow\"");
//			addSelList((String) loc);

		}

		textTr = textTr.substring(0, textTr.indexOf(">")) + bgColorNewTxt
				+ fgColorNewTxt + textTr.substring(textTr.indexOf(">"))
				+ "</tr>";

		try {
			try {
				htmlDoc.setOuterHTML(elem, textTr);
				// System.out.println("content= " +
				// getPanel().getHtmlPane().getText()); //Steph Test
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} catch (BadLocationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// System.out.println("content= " + getPanel().getHtmlPane().getText());
		// //Steph Test
	}
	
	private String getStringRGBfromColor(Color mBgCol) {
		String r = StringUtils.leftPad(Integer.toHexString(mBgCol.getRed()), 2,
				"0");
		String g = StringUtils.leftPad(Integer.toHexString(mBgCol.getGreen()),
				2, "0");
		String b = StringUtils.leftPad(Integer.toHexString(mBgCol.getBlue()),
				2, "0");

		String rgb = r + g + b;
		return rgb;
	}
	
	private String getRgbBackSelected() {
		if (rgbBackSelected == null)
			setRgbBackSelected(getStringRGBfromColor(edHTMLPane.getSelectionColor()));
		return rgbBackSelected;
	}
	
	private void setRgbBackSelected(String rgbBackSelected) {
		this.rgbBackSelected = rgbBackSelected;
	}
	
	private boolean getOddElement(String loc) {
		RE re = new RE("#posTr(\\d+)");
		if (re.match(loc)) {
			String sindex = loc.substring(re.getParenStart(1), re
					.getParenEnd(1));
			int index = Integer.parseInt(sindex);
			return index % 2 == 0;
		}
		return false;
	}
	
	private String getRgbBack() {
		if (rgbBack == null)
			setRgbBack(getStringRGBfromColor(edHTMLPane.getBackground()));
		return rgbBack;
	}

	private void setRgbBack(String rgbBack) {
		this.rgbBack = rgbBack;
	}
	
	private String getRgbBackAlternate() {
		return "eeeeee";
	}
	
	private String getRgbFront() {
		if (rgbFront == null)
			setRgbFront(getStringRGBfromColor(edHTMLPane.getForeground()));
		return rgbFront;
	}

	private void setRgbFront(String rgbFront) {
		this.rgbFront = rgbFront;
	}

	private String getRgbFrontSelected() {
		if (rgbFrontSelected == null)
			setRgbFrontSelected(getStringRGBfromColor(edHTMLPane.getSelectedTextColor()));
		return rgbFrontSelected;
	}

	private void setRgbFrontSelected(String rgbFrontSelected) {
		this.rgbFrontSelected = rgbFrontSelected;
	}
	
}
