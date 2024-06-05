package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents the yCrCbToRGB function in the Picasso language.
 * 
 * @author Robert C. Duvall
 * @author Sara Sprenkle
 * 
 */
public class YCrCbToRGB extends UnaryFunction {

	/**
	 * Create a rgbToYCrCb expression that takes as a parameter the given expression
	 * 
	 * @param param the expression to rgbToYCrCb
	 */
	public YCrCbToRGB(ExpressionTreeNode param) {
		super(param);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the yCrCbToRGB of
	 * the function's parameter.
	 * 
	 * @return the color from evaluating the yCrCbToRGB of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		
		RGBColor result = param.evaluate(x, y);
		double red = result.getRed() + result.getBlue() * 1.4022;
		double green = result.getRed() + result.getGreen() * -0.3456 + result.getBlue() * -0.7145;
		double blue = result.getRed() + result.getGreen() * 1.7710;
		
		return new RGBColor(red, green, blue);
	}
	

}