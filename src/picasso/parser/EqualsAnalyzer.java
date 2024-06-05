package picasso.parser;

import java.util.Stack;

/**
 * Handles parsing the equals function.
 * 
 * 
 */

import javax.swing.JOptionPane;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.tokens.IdentifierToken;
import picasso.parser.tokens.Token;
import picasso.parser.language.expressions.Assignment;
import picasso.parser.language.expressions.Variable;

/**
 * Handles parsing for assignment.
 * 
 * @author Harry Crutcher
 * 
 */
public class EqualsAnalyzer implements SemanticAnalyzerInterface {

	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		System.out.println(tokens);
		tokens.pop(); // Remove the equals token

		// the parameters are the next tokens on the stack. All tokens but our
		// identifier token (While loop unitl Semantic.getinstance is identifier token?)
		// But, they need to be processed
		ExpressionTreeNode newExpressionTreeRight = SemanticAnalyzer.getInstance().generateExpressionTree(tokens);
		// Get the token
		System.out.println(tokens);
		Token token = tokens.pop();
		// Check if its idetifier
		//doesn't give guidance otherwise!!! (if not identifier token..?
		if (token instanceof IdentifierToken) {
			// cast as idetfier token
			IdentifierToken t = (IdentifierToken) token;
			// Create variable
			String id = t.getName();
			System.out.println(id);
			if (id.equals("x")) {
				JOptionPane.showMessageDialog(null, "Cannot assign x as variable", "Error", JOptionPane.ERROR_MESSAGE);
				throw new ParseException("Cannot assign x as variable");
			}
			if (id.equals("y")) {
				JOptionPane.showMessageDialog(null, "Cannot assign y as variable", "Error", JOptionPane.ERROR_MESSAGE);
				throw new ParseException("Cannot assign y as variable");
			}
			Variable newVar = new Variable(id);
			// Want left parameter to be the variable and right to be expression. How?
			return new Assignment(newVar, newExpressionTreeRight);
		}
		//error handling 
		JOptionPane.showMessageDialog(null, "No variable assigned", "Error", JOptionPane.ERROR_MESSAGE);
		throw new ParseException("No variable assigned");


	}

}
