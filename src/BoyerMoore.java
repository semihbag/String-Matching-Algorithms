import java.io.File;
import java.util.Arrays;
import java.lang.Math; 

public class BoyerMoore extends Algorithm{
	private int[] badSymbol;
	private int[] goodSuffix;
	
	public BoyerMoore(String pattern, File inputFile, File outputFile) throws Exception {
		super("Boyer Moore", pattern, inputFile, outputFile);
		setBadSymbol(getPattern().length());
		setGoodSuffix();
	}	
	
	@Override
	public int findShitf(int j) {
		int pl = getPattern().length();
		if (j > 0 && j < pl) {
			return Math.max(Math.max(getBadSymbol()[getLine().charAt(getIndex() -  j)], 1) -j, getGoodSuffix()[j]);
		}
		return (j == 0)? getBadSymbol()[getLine().charAt(getIndex())] : 1;
	}
	
	//getter and setter methods
	public int[] getBadSymbol() {
		return badSymbol;
	}

	public void setBadSymbol(int pl) {
		int[] table = new int[256];
	    Arrays.fill(table, pl);
	    for (int i = 0; i < pl - 1; i++) {
	    	table[getPattern().charAt(i)] = pl - i - 1;
	    }
		this.badSymbol = table;
	}
	
	public int[] getGoodSuffix() {
		return goodSuffix;
	}
	
	public void setGoodSuffix() {
		/*  length of pattern = pl
		 *  i indicates amount of shifting and also starting index
		 *  k indicates number of matches
		 *  j indicates index which is scanning from array
		 */
		int i=1, k=0, j=0, pl = getPattern().length();		
		int[] table = new int[pl];				// we need length-1 index for table but the first index will not used
	    Arrays.fill(table, -1);					// so we crate size of length array and fill with -1 initially

		for (i = 1; i <= pl - 1; i++) {
			k = 0 ; j = 0;
			while (i + j <= pl -1 && getPattern().charAt(pl - 1 -j) == getPattern().charAt(pl - 1 - i - j)) {
				k++; j++;
			}
			if (i + j >= pl) {
				int v = i, k1 = k;
				Arrays.setAll(table, x -> table[x] == -1 && x >= k1 ? v : table[x]);
			}
			else {
				table[k] = (table[k] == -1)? i : table[k];
			}
		}		
		Arrays.setAll(table, x -> table[x] == -1 ? pl : table[x]);
		this.goodSuffix = table;
	}
}