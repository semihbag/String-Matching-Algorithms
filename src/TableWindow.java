import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class TableWindow {
	public JScrollPane scrollPane1;
	public JScrollPane scrollPane2;
	public JScrollPane scrollPane3;
	public JScrollPane scrollPane4;
	public Object[][] data1;
	public Object[][] data2;
	public Object[][] data3;
	int[][] maxAndMin;
	public int size;
	
	public TableWindow(int size, int[][] maxAndMin, Object[][] data1, Object[][] data2, Object[][] data3) {
		this.size = size;
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.maxAndMin = maxAndMin;
	}
	
	public void setTableWindow() throws Exception{
		
		
		/// PREPARE THE GENERAL OUTPUT TABLE ///
        String[] columnNames1 = {"INPUT NAME", "ALGORITHM TYPE", "PATTERN", "COMP NUMBER","MATCH NUMBER","START TIME","FINISH TIME","TIME SPENT IN MS"};
        
        JTable table1 = new JTable(data1, columnNames1);
        table1.getColumnModel().getColumn(0).setPreferredWidth(30);
        table1.setEnabled(false);
        table1.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
               java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
               Color color;
               if (row%4 < 3) {
            	   color = (column == 0)? Color.cyan : Color.white;
            	   if (column == 3) {             
                	   if ((int)value == maxAndMin[row/4][0]) 
                		   color = Color.red;
                	   if ((int)value == maxAndMin[row/4][1]) 
                		   color = new Color(0, 255, 51);
            	   }
               }
               else {
                   color = Color.LIGHT_GRAY;
               }
               c.setBackground(color);
               return c;
            }
        });
        
        
     
 		/// PREPARE THE HORSPOLL SHIFT TABLE ///
        String[] columnNames2 = new String[size];
        columnNames2[0] = "INPUT NAME";
        for (int i = 1; i < columnNames2.length; i++) {
        	columnNames2[i] = "" + (i);
        }
        
        JTable table2 = new JTable(data2, columnNames2);
        table2.setEnabled(false);
        table2.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
               java.awt.Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
               Color color;
               if (row%3 < 2) {
            	   color = (column == 0)? Color.cyan : (row%3 == 0)? Color.yellow : Color.white;
            	   color = (value == null)? Color.white : color;
               }
               else {
                   color = Color.LIGHT_GRAY;
               }
               c.setBackground(color);
               return c;
            }
        });
        
        
        
 		/// PREPARE THE BOYER MOORE SHIFT TABLE ///        
        String[] columnNames3 = new String[size];
        columnNames3[0] = "INPUT NAME";
        for (int i = 1; i < columnNames3.length; i++) {
        	columnNames3[i] = "" + (i);
        }
        
        JTable table3 = new JTable(data3, columnNames2);
        table3.setEnabled(false);
        table3.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public java.awt.Component getTableCellRendererComponent(JTable table3, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
               java.awt.Component c = super.getTableCellRendererComponent(table3, value, isSelected, hasFocus, row, column);
               Color color = Color.white;
               if (row%5 < 4) {
            	  if (column == 0) {
            		  color = Color.cyan;
            	  }
            	  else {
            		  if (row%5 == 2)
                		  color = Color.yellow;
                	  if (row%5 == 0)
                		  color = Color.orange;
            	  }
            	 
            	   color = (value == null)? Color.white : color;

               }
               else {
                   color = Color.LIGHT_GRAY;
               }
               c.setBackground(color);
               return c;
            }
        });
        
        
        JTableHeader header1 = table1.getTableHeader();
        header1.setReorderingAllowed(false);
        JPanel panel1 = new JPanel(); 
        panel1.add(table1);
        JScrollPane scrollPane_1 = new JScrollPane(table1, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_1.setColumnHeaderView(header1);

        JTableHeader header2 = table2.getTableHeader();
        header2.setReorderingAllowed(false);
        JPanel panel2 = new JPanel();
        panel2.add(table2);
        JScrollPane scrollPane_2 = new JScrollPane(panel2, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_2.setColumnHeaderView(header2);

        JTableHeader header3 = table3.getTableHeader();
        header3.setReorderingAllowed(false);
        JPanel panel3 = new JPanel(); 
        panel3.add(table3);
        JScrollPane scrollPane_3 = new JScrollPane(panel3, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane_3.setColumnHeaderView(header3);

        
        this.scrollPane1 = scrollPane_1;
        this.scrollPane2 = scrollPane_2;
        this.scrollPane3 = scrollPane_3;
	}
}
