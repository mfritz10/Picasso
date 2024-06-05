package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Sine;
import picasso.parser.tokens.Token;
/**
 * Handles parsing the sin function.
 * 
 * 
 */
public class SinAnalyzer extends UnaryFunctionAnalyzer {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Need to remove the cosine token
		// the parameter is the next token on the stack.
		// But, it needs to be processed
		return new Sine(SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens));
	}

}
