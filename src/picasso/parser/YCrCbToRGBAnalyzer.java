package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.YCrCbToRGB;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the yCrCbToRGB function.
 * 
 * @author Sara Sprenkle
 * 
 */
public class YCrCbToRGBAnalyzer extends UnaryFunctionAnalyzer {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop(); // Need to remove the yCrCbToRGB token
		// the parameter is the next token on the stack.
		// But, it needs to be processed
		return new YCrCbToRGB(SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens));
	}

}