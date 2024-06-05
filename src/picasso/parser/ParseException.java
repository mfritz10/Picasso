package picasso.parser;

/**
 * Describe an exception that occurred during parsing.
 * 
 * @author Sara Sprenkle
 *
 */
@SuppressWarnings("serial")
public class ParseException extends RuntimeException {

	public ParseException(String message) {
		super("ParseException: " + message);
	}
}	
	
	

	
	//Possible errors
	
	/**
	 * Users pass in characters that are not referencing the operators
	 * 
	 */
//	public letterException(String message) {
//		
//	}
	
	/**
	 * Two binary operators were passed in a row
	 * 
	 */
//	public binaryException(String message) {
//		
//	}
	
	/**
	 * Only two arguments were passed to RGBColor
	 * 
	 */
//	public rgbException(String message) {
//		
//	}

	/**
	 * Checks expressions if results are out of [-1,1] bounds
	 * 
	 */
//	public boundException(String message) {
//		
//	}

	/**
	 * Number of parentheses is odd, meaning that there is either
	 * an extra or missing parentheses
	 * 
	 */
//	public paranException(String message) {
//		
//	}


