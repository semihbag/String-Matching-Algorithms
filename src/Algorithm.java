import java.io.*;

public abstract class Algorithm {
	private String name;
	private String pattern;
	private String line;
	private String updatedLine;
	private int index;
	private int occurence;
	private int comparison;
	private int m;   		// index of previous match 
	private long st;
	private long ft;
	private File inputFile;
	private File outputFile;
	public BufferedReader reader;
	public BufferedWriter writer;
	
	public Algorithm(String name, String pattern, File inputFile, File outputFile) throws Exception {
		setName(name);
		setPattern(pattern);
		setUpdatedLine("");
		setInputFile(inputFile);
		setOutputFile(outputFile);
		setIndex(pattern.length() - 1);
		setOccurence(0);
		setComparison(0);
		setM(-1);
		setReader();
		setWriter();
	}
	
	// abstract method, this method will calculate the amount of shifting algorithm by algorithm
	public abstract int findShitf(int j);
	
			
	// this code is the main part of all algorithm
	// only shift amount different and it will be determined by findshift()
	public void run() throws Exception {
        st = System.currentTimeMillis();
		int j, pl = getPattern().length();	
        while (getNextLine() != null) {
        	while (index < line.length()) {
        		for(j = 0; j < pl && compare(index - j, pl-1-j); j++) {}
        		if (j == pattern.length()) {
        			addOccur(1);
        			updateLine(index);
        		}
        		addIndex(findShitf(j));
        	}    
        	write();
    		setIndex(pl - 1);	
        }
        getReader().close();
        getWriter().close();
        ft = System.currentTimeMillis();
	}
	
	// read next line from input file
	public String getNextLine() throws IOException {		
		setLine(getReader().readLine());
		return line;
	}
	
	// update the currend line with <mark> and </mark> if it has any match
	public void updateLine(int i) {
		int pl = pattern.length();		// pl : pattern length
		int ll = line.length();			// ll : line length
		int p = pl - 1, d = i - m;		// p : pattern length - 1 ; d : difference between first and second match index
		int m_p = m - p;
		
		String s ="";
		if (d > pl) {
			s += (m == -1)? s : line.substring(m-p,m+1) + "</mark>";						// if it is first match do nothing else take previous matched pattern and add close mark
			s += (i == ll)?  line.substring(m+1,i) : line.substring(m+1,i-p) + "<mark>";	// take from last index of previous matched pattern until first index of new matched pattern and add open mark
		}																					// if it is first match we did nothing previous line. it will be handled itself automatically
		else {																				// i == ll condition will handle final addition
			m_p = (m_p < 0)? 0 : m_p;
			if (i == ll) {
				s += line.substring(m_p,m+1) + "</mark>" + line.substring(m+1,i);
			}
			else {
				s += (m ==-1)? "<mark>" : s;
				s += line.substring(m_p,i-p);
			}
		}																												  
		m = i;
		setUpdatedLine(updatedLine + s); 
	}

	// write line to output file and set the updateLine and m variable to use again in next line
	public void write() throws Exception {
		updateLine(line.length());
		getWriter().write(updatedLine + "\n");
    	setUpdatedLine("");
    	m = -1;
	}
	
	// compare process
	public boolean compare(int iLine, int iPattern) {
		addComp(1);
		return (line.charAt(iLine) == pattern.charAt(iPattern));
	}
	
	// just increment by x number of index
	public void addIndex(int x) {
		setIndex(index + x);
	}
	
	// just increment by x number of comparison
	public void addComp(int x) {
		setComparison(getComparison() + x);
	}
	
	// just increment by x number of occurence
	public void addOccur(int x) {
		setOccurence(getOccurence() + x);
	}
	
	
	/*________________________________________________________________*/
	// GETTER AND SETTER METHODS
	public String getName() {
		return name;
	}
	
	private void setName(String name) {
		this.name = name;
	}
	
	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}
	
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}
	
	public String getUpdatedLine() {
		return updatedLine;
	}

	public void setUpdatedLine(String updatedLine) {
		this.updatedLine = updatedLine;
	}

	public int getIndex() {
		return index;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public int getOccurence() {
		return occurence;
	}
	
	private void setOccurence(int occurence) {
		this.occurence = occurence;
	}

	public int getComparison() {
		return comparison;
	}
	
	private void setComparison(int comparison) {
		this.comparison = comparison;
	}

	public int getM() {
		return m;
	}

	public void setM(int matchIndex) {
		this.m = matchIndex;
	}

	public long getFt() {
		return ft;
	}

	public void setFt(long ft) {
		this.ft = ft;
	}

	public long getSt() {
		return st;
	}

	public void setSt(long st) {
		this.st = st;
	}

	public File getInputFile() {
		return inputFile;
	}

	private void setInputFile(File inputFile) {
		this.inputFile = inputFile;
	}
	
	public File getOutputFile() {
		return outputFile;
	}

	private void setOutputFile(File outputFile) {
		this.outputFile = outputFile;
	}

	public BufferedReader getReader() {
		return reader;
	}

	private void setReader() throws Exception {
		FileReader fr = new FileReader(getInputFile());
		this.reader = new BufferedReader(fr);
	}

	public BufferedWriter getWriter() {
		return writer;
	}

	public void setWriter() throws Exception {
		FileWriter fw = new FileWriter(getOutputFile());
		this.writer = new BufferedWriter(fw);
	}
}
