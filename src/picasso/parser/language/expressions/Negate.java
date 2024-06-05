package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the negate function in the Picasso language.
 * 
 * @author Matthew Fritz
 * 
 */
public class Negate extends UnaryFunction {

	
	/**
	 * Negates the given expression
	 * 
	 * @param param the expression to negate
	 */
	public Negate(ExpressionTreeNode param) {
		super(param);
	}

	
	/**
	 * Evaluates this expression at the given x,y point by evaluating the negation of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the negation of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		return new RGBColor(-result.getRed(), -result.getGreen(), -result.getBlue());
	}

}
