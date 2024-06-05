package picasso.parser.language.expressions;

import picasso.model.ImprovedNoise;
import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents perlinColor in Picasso language
 * 
 * @author Matthew Fritz
 */
public class PerlinColor extends BinaryFunction {
	
	/**
	 * Create an perlinBW expression that takes as parameters the given expressions
	 * 
	 * @param xExpress the expression to manipulate the x
	 * @param yExpress the expression to manipulate the y
	 */
	public PerlinColor(ExpressionTreeNode xExpress, ExpressionTreeNode yExpress) {
		super(xExpress, yExpress);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the perlinColor of
	 * the function's parameters.
	 * 
	 * @return the color from evaluating the perlinColor of the expression's parameters
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor xRes = left.evaluate(x, y);
		RGBColor yRes = right.evaluate(x, y);
		
		double red = ImprovedNoise.noise(xRes.getRed() + 0.3, yRes.getRed() + 0.3, 0);
		double blue = ImprovedNoise.noise(xRes.getBlue() + 0.1, yRes.getBlue() + 0.1, 0);
		double green = ImprovedNoise.noise(xRes.getGreen() - 0.8, yRes.getGreen() - 0.8, 0);
		return new RGBColor(red, green, blue);
		

	}
}
