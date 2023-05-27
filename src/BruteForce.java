import java.io.File;

public class BruteForce extends Algorithm{
	public BruteForce(String pattern, File inputFile, File outputFile) throws Exception {
		super("Brute Force", pattern, inputFile, outputFile);
	}	
	
	@Override
	public int findShitf(int j) {
		return 1;
	}
}