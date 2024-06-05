package picasso.parser;

import java.util.Stack;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.Exponentiate;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the exponent or "exponential function".
 * 
 * @author Matthew Fritz
 * 
 */
public class ExponentiateAnalyzer implements SemanticAnalyzerInterface {


	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		System.out.println(tokens);
		tokens.pop(); // Remove the exponent token
		
		
		ExpressionTreeNode newExpressionTreeRight = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode newExpressionTreeLeft = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		return new Exponentiate(newExpressionTreeLeft, newExpressionTreeRight);
	}

}
