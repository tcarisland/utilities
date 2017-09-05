import java.util.Scanner;
import java.io.*;

public class Cols {

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
