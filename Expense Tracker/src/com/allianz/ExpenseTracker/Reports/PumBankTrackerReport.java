package com.allianz.ExpenseTracker.Reports;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import com.allianz.ExpenseTracker.Objects.ObjectsExpenseReport;
import com.allianz.ExpenseTracker.Panels.PnlBankItemDetailsAnzeigen;
import com.allianz.ExpenseTracker.Panels.PnlItemDetailsAnzeigen;

@SuppressWarnings("serial")
public class PumBankTrackerReport extends JPopupMenu {
	
	ObjectsExpenseReport objReport = new ObjectsExpenseReport();
	
	JMenuItem item1;
	JMenuItem item2;
	
	public PumBankTrackerReport() {
		
	}
	public PumBankTrackerReport(ObjectsExpenseReport objReport) {
		
		this.objReport = objReport;
		
        item1 = new JMenuItem("Edit                ");
        item1.setEnabled(true);
        item1.setFont(new Font("Arial", Font.BOLD, 12));
        item1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PnlBankItemDetailsAnzeigen details = new PnlBankItemDetailsAnzeigen(objReport,"Y");
				details.getFrmItemDetails().setVisible(true);
			}
		});

        add(item1);

        item2 = new JMenuItem("Delete              ");
        item2.setEnabled(true);
        item2.setFont(new Font("Arial", Font.BOLD, 12));
        item2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PnlBankItemDetailsAnzeigen details = new PnlBankItemDetailsAnzeigen(objReport,"D");
				details.getFrmItemDetails().setVisible(true);
			}
		});

        add(item2);
        
        show(objReport.getEditor(), objReport.getPosX(), objReport.getPosY());

	}

}
