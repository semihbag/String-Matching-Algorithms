import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class GraphWindow {
	public JScrollPane scrollPane4;
	public Object[][] data4;

	public GraphWindow(Object[][] data4) {
		this.data4 = data4;
	}
	
	public void setGraph() {
		int dl = data4.length;
        JPanel big = new JPanel(new GridLayout((dl/4), 1));

        String labels[] = {" BRUTE FORCE", "    HORSPOLL", " BOYER MOORE"};
        
        for (int i = 0; i < dl; i+=4) {
          	int values[] = new int[3];
        	values[0] = (int)data4[i][3];
        	values[1] = (int)data4[i+1][3];
        	values[2] = (int)data4[i+2][3];
        	AlgoGraph ag1 = new AlgoGraph(values, labels, "COMPARISON NUMBERES");
        	ag1.setBackground(Color.lightGray);
        	int values2[] = new int[3];
        	values2[0] = (int)(long)data4[i][7];
        	values2[1] = (int)(long)data4[i+1][7];
        	values2[2] = (int)(long)data4[i+2][7];
        	        	
        	AlgoGraph ag2 = new AlgoGraph(values2, labels, "EXECUTION TIME");
        	
        	JPanel panel1 = new JPanel(new GridLayout(0, 2));
        	panel1.add(ag1);
        	panel1.add(ag2);
        	
        	big.add(panel1);
        }
        JScrollPane scrollPane_4 = new JScrollPane(big);
        this.scrollPane4 = scrollPane_4;
	}
	
	
	// This is inner class. We will this later to create our special graphs
	public class AlgoGraph extends JPanel {
	    private int[] values;
	    private String[] labels;
	    private String title;
	    
	    public AlgoGraph(int[] values, String[] labels, String title) {
	        this.values = values;
	        this.labels = labels;
	        this.title = title;

	    }
	    
	    @Override
	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);
	        
	        // set font. do it bold
	        Font font = g.getFont();
	        font = font.deriveFont(Font.BOLD, 16);
	        g.setFont(font);
	        
	        int maxValue = getMaxValue();
	       
	        // set x and y axis
	        g.setColor(Color.BLACK);
	        g.drawLine(50, getHeight() - 50, getWidth() - 50, getHeight() - 50);
	        g.drawLine(50, getHeight() - 50, 50, 50);

	        // set title
	        int titleWidth = g.getFontMetrics().stringWidth(title);
	        g.drawString(title, getWidth() / 2 - titleWidth / 2, 30);
	        
	        // set labels
	        int x = 80;
	        for (int i = 0; i < labels.length; i++) {
	            g.drawString(labels[i], x, getHeight() - 30);
	            x += (getWidth() - 150) / labels.length;
	        }
	       
	        // set columns
	        int x1 = 80;
	        int columnWidth = (getWidth() - 150) / values.length;
	        for (int i = 0; i < values.length; i++) {
	            int columnHeight = (values[i] * (getHeight() - 100)) / maxValue;
	            int y2 = getHeight() - 50 - columnHeight;
	            g.setColor((i==0)? Color.pink : (i==1)? Color.cyan : Color.orange);
	            g.fillRect(x1, y2, columnWidth, columnHeight);
	            g.setColor(Color.BLACK);
	            g.drawRect(x1, y2, columnWidth, columnHeight);
	            x1 += columnWidth;
	        }
	    }
	    
	    private int getMaxValue() {
	        int maxValue = -1;
	        for (int value : values) {
	            if (value > maxValue) {
	                maxValue = value;
	            }
	        }
	        return (maxValue == 0)? -1 : maxValue;
	    }
	    
	    @Override
	    public Dimension getPreferredSize() {
	        return new Dimension(400, 300);
	    }
	}
}
