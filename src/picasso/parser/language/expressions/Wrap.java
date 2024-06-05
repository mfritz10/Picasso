package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the wrap function in the Picasso language.
 * 
 * @author Robert C. Duvall
 * @author Sara Sprenkle
 * 
 */
public class Wrap extends UnaryFunction {

	/**
	 * Create a floor expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to clamp
	 */
	public Wrap(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Creates a function that wraps the numbers
	 * 
	 * @param x		the coord after manipulating expression
	 */
	public double wrap(double x) {
		
		double moddedX = x % 2;
		if (moddedX < -1) {
			return moddedX + 2;
		}
		if (moddedX > 1) {
			return moddedX - 2;
		}
		return moddedX;
	}
	
	/**
	 * 
	 * Evaluates this expression at the given x,y point by wrapping
	 * the function's parameter.
	 * 
	 * @return the color from wrapping the expression's parameter between 1 and -1
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor result = param.evaluate(x, y);
		double red =  wrap(result.getRed());
		double green = wrap(result.getGreen());
		double blue = wrap(result.getBlue());

		return new RGBColor(red, green, blue);
	}

}
