package picasso.parser.tokens;

/**
 * Represents a string. Using equals, a StringToken object
 * compares as true only to another StringToken object with the same value
 * <P>
 * a StringToken is immutable, once created it doesn't change
 * 
 * @author Matthew Fritz
 */
public class StringToken extends Token {
	
	private final String myName;

	/**
	 * Constructs a token representing value
	 * 
	 * @param value the value of this string token
	 */
	public StringToken(String value) {
		super("String Token");
		myName = value;
		
	}
	
	/**
	 * @return true iff o is a NumberToken with same value
	 */
	@Override
	public boolean equals(Object o) {
		if( o == this ) {
			return true;
		}
		if (!(o instanceof StringToken)) {
			return false;
		}
		StringToken rhs = (StringToken) o;
		return myName.equals(rhs.myName);
	}

	/**
	 * @return the value of this token
	 */
	public String getName() {
		return myName;
	}

	public String toString() {
		return super.toString() + ": " + myName;
	}

	@Override
	public boolean isConstant() {
		return false;
	}

	@Override
	public boolean isFunction() {
		return false;
	}

}
