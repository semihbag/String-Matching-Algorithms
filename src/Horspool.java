import java.io.File;
import java.util.Arrays;

public class Horspool extends Algorithm{
	private int[] shiftTable;
	
	public Horspool(String pattern, File inputFile, File outputFile) throws Exception {
		super("Horspool", pattern, inputFile, outputFile);
		setShiftTable(getPattern().length());
	}
	
	@Override
	public int findShitf(int j) {
	//	System.out.println(getLine());
		return (j == getPattern().length())? 1 : getShiftTable()[getLine().charAt(getIndex())];
	}
	
	//getter and setter methods
	public int[] getShiftTable() {
		return shiftTable;
	}

	public void setShiftTable(int pl) {
		int[] table = new int[256];
	    Arrays.fill(table, pl);
	    for (int i = 0; i < pl - 1; i++) {
	    	table[getPattern().charAt(i)] = pl - i - 1;
	    }
		this.shiftTable = table;
	}
}