package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the clamp function in the Picasso language.
 * 
 * @author Robert C. Duvall
 * @author Sara Sprenkle
 * 
 */
public class Clamp extends UnaryFunction {

	/**
	 * Create a floor expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to clamp
	 */
	public Clamp(ExpressionTreeNode param) {
		super(param);
	}

		
	protected static double clamp(double value) {
		return Math.max(-1, Math.min(1, value));
	}
	/**
	 * 
	 * Evaluates this expression at the given x,y point by evaluating the clamp of
	 * the function's parameter.
	 * 
	 * @return the color from clamping the expression's parameter between 1 and -1
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red =  clamp(result.getRed());
		double green = clamp(result.getGreen());
		double blue = clamp(result.getBlue());

		return new RGBColor(red, green, blue);
	}

}
