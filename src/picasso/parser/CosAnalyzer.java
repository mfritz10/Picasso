package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Cosine;
import picasso.parser.tokens.Token;


/**
 * Handles parsing the cos function.
 * 
 * @author Julie 
 * @author Matthew
 * @author Harry
 * @author Grace
 * 
 */

public class CosAnalyzer extends UnaryFunctionAnalyzer{

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Need to remove the cosine token
		// the parameter is the next token on the stack.
		// But, it needs to be processed
		return new Cosine(SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens));
	}


}
