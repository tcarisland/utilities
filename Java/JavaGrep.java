import java.util.Scanner;
import java.io.*;


public class JavaGrep {

    private String inputFileName;
    
    public static void main(String args[]) {
	if(args.length == 1) {
	    JavaGrep jg = new JavaGrep(args[0]);
	    jg.doJavaGrep();
	} else if (args.length > 0) {
	    JavaGrep jg = new JavaGrep(args[0]);
	    jg.doJavaGrep(args[1]);
	}
    }

    public JavaGrep(String inputFileName) {
	this.inputFileName = inputFileName;
    }
    
    public void doJavaGrep() {
	String grepLine = "([0-9\\.:].*?)";
	doJavaGrep(grepLine);
    }
    
    public void doJavaGrep(String grep) {
	try {
	    System.out.println("GREPLINE: " + grep);
	    File inputFile = new File(inputFileName);
	    Scanner in = new Scanner(inputFile);
	    String newFileName = inputFile.getPath();
	    if(newFileName.contains(".")) {
		String filePrefix = newFileName.substring(0, newFileName.length() - (newFileName.length() - newFileName.indexOf('.')));
		String fileSuffix = newFileName.substring(newFileName.length() - (newFileName.length() - newFileName.indexOf('.')));
		newFileName = filePrefix + "_NEW" + fileSuffix;
	    } else {
		newFileName = newFileName + "_NEW.txt";
	    }
	    
	    PrintWriter out = new PrintWriter(new File(newFileName));

	    String inputLine, outputLine = "";
	    while(in.hasNextLine()) {
		inputLine = in.nextLine();
		outputLine = inputLine.replaceAll(grep, "");
		System.out.println(outputLine);
		out.println(outputLine);
	    }
	    out.close();
	} catch (Exception e) {
	    System.out.println("EXCEPTION!");
	    e.printStackTrace();
	}
    }

}
