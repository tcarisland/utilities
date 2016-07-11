/**
 * @name Code128
 * @author tcari
 * @date 11. jul. 2016
 * simple Code128 program
 */

package rammexperten;

public class Code128 {
    private String code128B_table[] = {
	" ","!","\"","#","$"," %","&","'","(",")","*","+",",","-",".","/",
	"0","1","2","3","4","5","6","7","8","9",":",";","<","=",">","?",
	"@","A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P",
	"Q","R","S","T","U","V","W","X","Y","Z","[","\\","]","^","_","`",
	"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p",
	"q","r","s","t","u","v","w","x","y","z","{","|","}","~","DEL","FNC3",
	"FNC2","ShiftA","CodeC","FNC4","CodeA","FNC1","StartCodeA","StartCodeB","StartCodeC"
    };
    private String code128_table[] = code128B_table;

    String norwegian = "ÆØÅæøå";

    public final char B_START = 'Ì';
    public final char B_END = 'Î';
    public final String TYPE;
    public final int STARTCODE;
    public final int MODULO = 103;

    public Code128(String type) {
	TYPE = type;
	STARTCODE = getStartCode("StartCode" + TYPE);
    }

    public Code128() {
	TYPE = "B";
	STARTCODE = getStartCode("StartCode" + TYPE);
    }


    public String checkString(String input) {
	String answer = "";
	for(int i = 0; i < input.length(); i++) {
	    char current = input.charAt(i);
	    if(Character.isLetterOrDigit(current) && !isNorwegian(current)) {
		answer += current;
	    } else {
		current = replaceCharacter(current);
		answer += current;
	    }
	}
	return answer;
    }

    /**
     * Replaces characters for use with Norwegian keyboard layouts
     * @param c input character
     * @return output character
     */
    public char replaceCharacter(char c) {
	if(c == '�')
	    return '\"';
	if(c == '�')
	    return '$';
	if(c == '/')
	    return '&';
	if(c == '�')
	    return '\'';
	if(c == ')')
	    return '(';
	if(c == '=')
	    return ')';
	if(c == '(')
	    return '*';
	if(c == '+')
	    return '-';
	if(c == '-')
	    return '/';
	if(c == '�')
	    return ':';
	if(c == '�')
	    return '[';
	if(c == '�')
	    return '\\';
	if(c == '&')
	    return '^';
	if(c == '?')
	    return '_';
	if(c == '|')
	    return '`';
	if(c == '�')
	    return ';';
	if(c == ';')
	    return '<';
	if(c == '\\')
	    return '=';
	if(c == ':')
	    return '>';
	if(c == '_')
	    return '?';
	if(c == '�')
	    return '@';
	if(c == '�')
	    return '{';
	if(c == '*')
	    return '|';
	if(c == '�')
	    return '~';
	else
	    return c;

    }

    public boolean isNorwegian(char c) {
	for(int i = 0; i < norwegian.length(); i++) {
	    if(c == norwegian.charAt(i))
		return true;
	}
	return false;
    }

    public String getBarcodeString(String input) {
	input = checkString(input);
	Code128 typeB = new Code128("B");
	String answer = "";
	answer += typeB.B_START;
	answer += input;
	answer += typeB.calculateCheckSum(input);
	answer += typeB.B_END;
	return answer;
    }

    public char calculateCheckSum(String input) {
	return checksumChar(checksumInt(input));
    }

    /**
     * Converts calculated checksum into character
	 * @param i calculated checksum
	 * @return character for printing checksum
	      */
    public char checksumChar(int i) {
	if(i < 95)
	    return (char) (i + 32);
	else
	    return (char) (i+ 100);
    }

    /**
     * Calculates the checksum
     * @param input String input for calculating checksum
     * @return the checksum in integer form
     */
    public int checksumInt(String input) {
	int checksum = STARTCODE;
	for(int i = 0; i < input.length(); i++) {
	    int value = charNumber(input.charAt(i));
	    if(value < 0)
		return value;
	    int position = i+1;
	    checksum += (value * position);
	}
	return checksum % 103;
    }

    /**
     * Helper function for checksumInt
     * Finds the number of the particular character using the Code128B table
     * @param c
     * @return
     */
    public int charNumber(char c) {
	for(int i = 0; i < code128_table.length; i++) {
	    if(code128_table[i].charAt(0) == c)
		return i;
	}
	System.out.println("Could not find " + c);
	return -1;
    }

    public int getStartCode(String s) {
	for(int i = 0; i < code128_table.length; i++)
	    if(s.equals(code128_table[i]))
		return i;
	return -1;
    }
}
