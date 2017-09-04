import java.util.Scanner;
import java.io.*;

public class HashMapMaker {

    public static void main(String args[]) {
	try {
	    Scanner in = new Scanner(new File(args[0]));
	    PrintWriter out = new PrintWriter(new File(args[1]));
	    out.println("private static final Map<String, String> m = new HashMap<String, String>() {{");
	    while(in.hasNextLine()) {
		String pair[] = in.nextLine().split("\\s+");
		if(pair.length > 1)
		    out.println("put (\"" + pair[0] + "\", \"" + pair[1] + "\");");
	    }
	    in.close();
	    out.println("}};");
	    out.close();
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
}
