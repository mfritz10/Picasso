package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Rand;
import picasso.parser.tokens.Token;
/**
 * Handles parsing the random function.
 * 
 * @author Harry Crutcher
 * 
 */
public class RandAnalyzer extends UnaryFunctionAnalyzer {
	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Need to remove the random token
		// no more paramters should be there
		return new Rand();
	}

}
