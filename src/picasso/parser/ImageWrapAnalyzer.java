package picasso.parser;

import java.util.Stack;

import javax.swing.JOptionPane;

import picasso.parser.language.ExpressionTreeNode;
import picasso.parser.language.expressions.ImageClip;
import picasso.parser.language.expressions.ImageWrap;
import picasso.parser.tokens.StringToken;
import picasso.parser.tokens.Token;

/**
 * Handles parsing the imageWrap function.
 * 
 * @author Matthew Fritz
 * 
 */
public class ImageWrapAnalyzer implements SemanticAnalyzerInterface {


	
	@Override
	public ExpressionTreeNode generateExpressionTree(Stack<Token> tokens) {
		tokens.pop();
		
		
		ExpressionTreeNode newExpressionTreeY = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		ExpressionTreeNode newExpressionTreeX = SemanticAnalyzer.getInstance().generateExpressionTree(
				tokens);
		
		if (tokens.isEmpty()) {
			JOptionPane.showMessageDialog(null, "Must include image to be read", "Error", JOptionPane.ERROR_MESSAGE);
			throw new ParseException("Must include image to be read");
		}
		Token token = tokens.pop();
		
		if (token instanceof StringToken) {
			StringToken image = (StringToken) token;
			
			return new ImageWrap(image.getName(), newExpressionTreeX, newExpressionTreeY);
		}
		
		JOptionPane.showMessageDialog(null, "Must pass in image name as string", "Error", JOptionPane.ERROR_MESSAGE);
		throw new ParseException("Must pass in image name as string");
		
	}

}
