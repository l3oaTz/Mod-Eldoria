package utilities;

public class Stringint
{
	public static String value(Integer va)
	{
	    String vas = va.toString();
	    if ((vas.length() >= 1) && (vas.length() < 4)) {
	    	return vas;
	    }
	    if (vas.length() == 4) {
	    	return "" + vas.charAt(0) + "," + vas.charAt(1) + vas.charAt(2) + vas.charAt(3);
	    }
	    if (vas.length() == 5) {
	    	return "" + vas.charAt(0) + vas.charAt(1) + "," + vas.charAt(2) + vas.charAt(3) + vas.charAt(4);
	    }
	    if (vas.length() == 6) {
	    	return "" + vas.charAt(0) + vas.charAt(1) + vas.charAt(2) + "," + vas.charAt(3) + vas.charAt(4) + vas.charAt(5);
	    }
	    if (vas.length() == 7) {
	    	return "" + vas.charAt(0) + "," + vas.charAt(1) + vas.charAt(2) + vas.charAt(3) + "," + vas.charAt(4) + vas.charAt(5) + vas.charAt(6);
	    }
	    if (vas.length() == 8) {
	    	return "" + vas.charAt(0) + vas.charAt(1) + "," + vas.charAt(2) + vas.charAt(3) + vas.charAt(4) + "," + vas.charAt(5) + vas.charAt(6) + vas.charAt(7);
	    }
	    if (vas.length() == 9) {
	    	return "" + vas.charAt(0) + vas.charAt(1) + vas.charAt(2) + "," + vas.charAt(3) + vas.charAt(4) + vas.charAt(5) + "," + vas.charAt(6) + vas.charAt(7) + vas.charAt(8);
	    }
	    if (vas.length() == 10) {
	    	return "" + vas.charAt(0) + "," + vas.charAt(1) + vas.charAt(2) + vas.charAt(3) + "," + vas.charAt(4) + vas.charAt(5) + vas.charAt(6) + "," + vas.charAt(7) + vas.charAt(8) + vas.charAt(9);
	    }
	    return "";
	}
}