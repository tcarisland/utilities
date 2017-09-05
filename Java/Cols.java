import java.util.Scanner;
import java.io.*;

public class Cols {

    /**
     * This program takes a csv file separated by whitespace and replaces the first with the second column.
     * @name Cols
     * @author tcarisland
     * @date 6.sep.2017
     */

    public static void main(String args[]) {
	try {
	    Scanner in = new Scanner(new File(args[0]));
	    PrintWriter out = new PrintWriter(new File(args[1]));
	    while(in.hasNextLine()) {
		String cols[] = in.nextLine().split("\\s+");
		if(cols.length > 1)
		    out.println(cols[1] + " " + cols[0]);
	    }
	    in.close();
	    out.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

}
