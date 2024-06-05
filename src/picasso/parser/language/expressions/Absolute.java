package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the absolute value function in the Picasso language.
 * 
 * @author Julie
 * 
 */

public class Absolute extends UnaryFunction {
	
	public Absolute(ExpressionTreeNode param) {
		/**
		 * Create a absolute value expression that takes as a parameter the given expression
		 * 
		 * @param param the expression to cosine
		 */

		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the absolute value of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the absolute value of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red = Math.abs(result.getRed());
		double green = Math.abs(result.getGreen());
		double blue = Math.abs(result.getBlue());

		return new RGBColor(red, green, blue);
	}


}
