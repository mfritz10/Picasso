package picasso.parser.tokens.operations;

import picasso.parser.tokens.chars.CharToken;
import picasso.parser.language.CharConstants;

/**
 * Represents the exponentiate token
 * 
 */
public class ExponentiateToken extends CharToken implements OperationInterface {
	public ExponentiateToken() {
		super(CharConstants.CARET);
		
	}

}
