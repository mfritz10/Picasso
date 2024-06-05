package picasso.parser.tokens.operations;

import picasso.parser.tokens.chars.CharToken;
import picasso.parser.language.CharConstants;

/**
 * Represents the modulo sign token
 * 
 */
public class ModuloToken extends CharToken implements OperationInterface{

	public ModuloToken() {
		super(CharConstants.MOD);
	}

}
