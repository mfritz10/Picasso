package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Modulo;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the modulo or "remainder function".
 * 
 * @author Matthew Fritz
 * 
 */
public class ModuloAnalyzer implements SemanticAnalyzerInterface{

	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		System.out.println(tokens);
		tokens.pop(); // Remove the mod token
		
		
		// the parameters are the next tokens on the stack.
		// But, they need to be processed
		// TODO: Need to finish.
		ExpressionTreeNode newExpressionTreeRight = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode newExpressionTreeLeft = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		return new Modulo(newExpressionTreeLeft, newExpressionTreeRight);
	}


}
