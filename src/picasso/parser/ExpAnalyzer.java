package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Exponent;
import picasso.parser.tokens.Token;
/**
 * Handles parsing the exponent function.
 * 
 * @author Grace Owens
 * 
 */
public class ExpAnalyzer extends UnaryFunctionAnalyzer {
	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Need to remove the exponent token
		// the parameter is the next token on the stack.
		// But, it needs to be processed
		return new Exponent(SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens));
	}

}
