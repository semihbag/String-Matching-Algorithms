import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

public class Main {

	public static void main(String[] args) {

		try {
			List<Object[]> process = new ArrayList<>();
			
			// the files and patterns. any numbers of case can be added to process like this. we nees just input output and pattern
			File input1 = new File("input.html");
			File yeni = new File("yeni.html");
			File yeni2 = new File("yeni2.html");
			File yeni3 = new File("yeni3.html");
			File bitString = new File("bitString.html");
			File bitString2 = new File("bitString2.html");
			File bitString3 = new File("bitString3.html");

			
			
			File output1 = new File("output1.html");
			String pattern1 = "AT_THAT";  
			process.add(new Object[]{pattern1, input1, output1, });
								
			File output2 = new File("output2.html");
			String pattern2 = "bold";
			process.add(new Object[]{pattern2, yeni, output2, });

			File output3 = new File("output3.html");
			String pattern3 = "corresponding";
			process.add(new Object[]{pattern3, yeni, output3, });

			File output4 = new File("output4.html");
			String pattern4 = "the";
			process.add(new Object[]{pattern4, yeni2, output4, });

			File output5 = new File("output5.html");
			String pattern5 = "fiz";
			process.add(new Object[]{pattern5, yeni2, output5, });
			
			File output6 = new File("output6.html");
			String pattern6 = "a";
			process.add(new Object[]{pattern6, yeni3, output6, });
			
			File output7 = new File("output7.html");
			String pattern7 = "~~";
			process.add(new Object[]{pattern7, yeni3, output7, });
			
			File output8 = new File("output8.html");
			String pattern8 = "~~~~~~~~~~~";
			process.add(new Object[]{pattern8, yeni3, output8, });
			
			File output9 = new File("output9.html");
			String pattern9 = "a2b34";
			process.add(new Object[]{pattern9, bitString, output9, });
			
			File output10 = new File("output10.html");
			String pattern10 = "GFAHFLFLHFHLFHHFGJLLGJLGJ";
			process.add(new Object[]{pattern10, bitString, output10, });
			
			File output11 = new File("output11.html");
			String pattern11 = "0";
			process.add(new Object[]{pattern11, bitString, output11, });
			
			File output12 = new File("output12.html");
			String pattern12 = "1";
			process.add(new Object[]{pattern12, bitString, output12, });
		
			File output13 = new File("output13.html");
			String pattern13 = "1";
			process.add(new Object[]{pattern13, bitString2, output13, });
			
			File output14 = new File("output14.html");
			String pattern14 = "0";
			process.add(new Object[]{pattern14, bitString2, output14, });
			
			File output15 = new File("output15.html");
			String pattern15 = "111110";
			process.add(new Object[]{pattern15, bitString2, output15, });
			
			File output16 = new File("output16.html");
			String pattern16 = "111011";
			process.add(new Object[]{pattern16, bitString2, output16, });
			
			File output17 = new File("output17.html");
			String pattern17 = "101111";
			process.add(new Object[]{pattern17, bitString2, output17, });

			File output18 = new File("output18.html");
			String pattern18 = "1010101010";
			process.add(new Object[]{pattern18, bitString2, output18, });
			
			File output19 = new File("output19.html");
			String pattern19 = "0101010101";
			process.add(new Object[]{pattern19, bitString2, output19, });
			
			File output20 = new File("output20.html");
			String pattern20 = "1110000";
			process.add(new Object[]{pattern20, bitString3, output20, });
		
			File output21 = new File("output21.html");
			String pattern21 = "1111000";
			process.add(new Object[]{pattern21, bitString3, output21, });
			
			File output22 = new File("output22.html");
			String pattern22 = "1111100";
			process.add(new Object[]{pattern22, bitString3, output22, });
			
			File output23 = new File("output23.html");
			String pattern23 = "0000";
			process.add(new Object[]{pattern23, bitString3, output23, });
			
			File output24 = new File("output24.html");
			String pattern24 = "00000000000000";
			process.add(new Object[]{pattern24, bitString3, output24, });
			
			File output25 = new File("output25.html");
			String pattern25 = "00000000";
			process.add(new Object[]{pattern25, bitString3, output25, });
			
			
			
			
			// RUNNING ALL THE ALGORITHMS 
			int[][] maxAndMin = new int[process.size()][2];
			int maxLength = 0;
			Algorithm[] algos = new Algorithm[process.size() * 3]; 
	 		for (int i = 0; i < process.size(); i++) {
	 			BruteForce brf = new BruteForce((String) process.get(i)[0], (File)process.get(i)[1],(File) process.get(i)[2]); 
	 			Horspool hrs = new Horspool((String) process.get(i)[0], (File)process.get(i)[1],(File) process.get(i)[2]);
	 			BoyerMoore byr = new BoyerMoore((String) process.get(i)[0], (File)process.get(i)[1],(File) process.get(i)[2]);
	 			
	 			brf.run();
	 			hrs.run();
	 			byr.run();
	 			
	 			algos[i*3] = brf;
				algos[i*3+1] = hrs;
				algos[i*3+2] = byr;

	            maxAndMin[i][0] = Math.max(brf.getComparison(), Math.max(hrs.getComparison(), byr.getComparison()));
	            maxAndMin[i][1] = Math.min(brf.getComparison(), Math.min(hrs.getComparison(), byr.getComparison()));
	            maxLength = (maxLength < ((String) process.get(i)[0]).length())? ((String) process.get(i)[0]).length() : maxLength;
	 		}
	 		
	 		
	 		// COLLECT ALL THE DATA
	 		int size = (maxLength + 2 < 14) ? 14 : maxLength + 2;
			Object[][] data1 = new Object[process.size() * 4][8];
	 		Object[][] data2 = new Object[process.size() * 3][size];
	 		Object[][] data3 = new Object[process.size() * 5][size];

	 		int s;
	 		for (int i = 0; i < algos.length; i++) { 			
	 			
	 			// set the general output table 
	 			s = i + i/3;
	        	data1[s][0] = "input" + (i/3 + 1);
	        	data1[s][1] = algos[i].getName();
	        	data1[s][2] = algos[i].getPattern();
	        	data1[s][3] = algos[i].getComparison();
	        	data1[s][4] = algos[i].getOccurence();
	        	data1[s][5] = algos[i].getSt();
	        	data1[s][6] = algos[i].getFt();
	        	data1[s][7] = algos[i].getFt() - algos[i].getSt();
	        	
	        	// set the horpool table
	        	if (algos[i].getName() == "Horspool") {
	        		s = (i/3) * 3;
	        		
	            	data2[s][0] = "input" + (i/3 +1);
	            	data2[s+1][0] = "input" + (i/3 +1); 
	        		Set<Character> hash = new HashSet<>();
	        	    for (int j = 0; j < algos[i].getPattern().length(); j++) {
	        	    	hash.add(algos[i].getPattern().charAt(j));
	        	    }
	        	    
	        	    int x =1;
	        	    for (char c : hash) {
	        	    	data2[s][x] = c;
	        	    	data2[s+1][x] = ((Horspool)algos[i]).getShiftTable()[c];
	        	    	x++;
	        	    }
	        	    data2[s][x] = "OTHER";
	        	    data2[s+1][x] = algos[i].getPattern().length();
	           	
	        	}
	        	
	        	//set the boyer moore table
	        	if (algos[i].getName() == "Boyer Moore") {
	        		s = (i/3) * 5;
	        		
	        		data3[s][0] = "input" + (i/3 +1);
	        		data3[s+1][0] = "input" + (i/3 +1);
	        		
	        		for (int j = 1; j < algos[i].getPattern().length(); j++) {
	        			data3[s][j] = "k=" + j;
	        			data3[s+1][j] = ((BoyerMoore) algos[i]).getGoodSuffix()[j];
	        		}
	        		
	        		data3[s+2] = data2[(i/3) * 3];
	        		data3[s+3] = data2[(i/3) * 3 + 1];
	        	}
	 		}
	 		
	 		
			// create tables
			TableWindow t = new TableWindow(size, maxAndMin, data1, data2, data3);
			t.setTableWindow();
			
			// create graphs
			GraphWindow g = new GraphWindow(data1);
			g.setGraph();
			
			// create tabbed which includes tables and graphs
			JTabbedPane tabbedPane = new JTabbedPane();
	        tabbedPane.addTab("GENERAL OUTPUT", t.scrollPane1);
	        tabbedPane.addTab("HORSPOOL SHIFT TABLE", t.scrollPane2);
	        tabbedPane.addTab("GOOD SUFFIX AND BAD SYMBOL", t.scrollPane3);
	        tabbedPane.addTab("GRAPH", g.scrollPane4);
	        
	        // create output window
	        JFrame frame = new JFrame("OUTPUT TABLE");
	        frame.add(tabbedPane);
	        frame.pack();
	        frame.setSize(1100,600);
			frame.setVisible(true);
			
            
		} catch (Exception e) {
		    StackTraceElement[] stackTrace = e.getStackTrace();
		    for (StackTraceElement element : stackTrace) {
		        if (element.getClassName().startsWith("com.example")) {
		            System.out.println("Error " + element.getClassName() + " in this class.");
		            break;
		        }
		    }
		    System.out.println("Error: " + e.getMessage());
		    e.printStackTrace();
		}
	}
}
