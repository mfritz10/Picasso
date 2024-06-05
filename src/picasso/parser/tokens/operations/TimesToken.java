package picasso.parser.tokens.operations;

import picasso.parser.tokens.chars.CharToken;
import picasso.parser.language.CharConstants;

/**
 * Represents the times sign token
 * 
 */
public class TimesToken extends CharToken implements OperationInterface {

	public TimesToken() {
		super(CharConstants.STAR);
		
	}

}
