import java.util.Scanner;
import java.io.*;


public class JavaGrep {

    public static void main(String args[]) {
	if(args.length > 0) {
	    doJavaGrep(args[0]);
	}
    }

    public static void doJavaGrep(String inputFileName) {
	String grepLine = "([0-9\\.:].*?)";
	doJavaGrep(inputFileName, grepLine);
    }
    
    public static void doJavaGrep(String inputFileName, String grep) {
	try {
	    File inputFile = new File(inputFileName);
	    Scanner in = new Scanner(inputFile);
	    String newFileName = inputFile.getPath();
	    if(newFileName.contains(".")) {
		String filePrefix = newFileName.substring(0, newFileName.length() - 4);
		String fileSuffix = newFileName.substring(newFileName.length() - 4);
		newFileName = filePrefix + "_NEW" + fileSuffix;
	    } else {
		newFileName = newFileName + "_NEW.txt";
	    }
	    
	    PrintWriter out = new PrintWriter(new File(newFileName));
	    System.out.println("GOING INTO LOOP");
	    String inputLine, outputLine = "";
	    while(in.hasNextLine()) {
		inputLine = in.nextLine();
		outputLine = inputLine.replaceAll(grep, "");
		out.println(outputLine);
	    }
	} catch (Exception e) {
	    System.out.println("EXCEPTION!");
	    e.printStackTrace();
	}
    }

}
