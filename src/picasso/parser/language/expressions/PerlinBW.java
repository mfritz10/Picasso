package picasso.parser.language.expressions;

import picasso.model.ImprovedNoise;
import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents perlinBW in Picasso language
 * 
 * @author Matthew Fritz
 */
public class PerlinBW extends BinaryFunction {

	/**
	 * Create an perlinBW expression that takes as parameters the given expressions
	 * 
	 * @param xExpress the expression to manipulate the x
	 * @param yExpress the expression to manipulate the y
	 */
	public PerlinBW(ExpressionTreeNode xExpress, ExpressionTreeNode yExpress) {
		super(xExpress, yExpress);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the perlinBW of
	 * the function's parameters.
	 * 
	 * @return the color from evaluating the perlinBW of the expression's parameters
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor xRes = left.evaluate(x, y);
		RGBColor yRes = right.evaluate(x, y);
		
		double grey = ImprovedNoise.noise(xRes.getRed() + yRes.getRed(), xRes.getGreen() + yRes.getGreen(),
				xRes.getBlue() + yRes.getBlue());
		return new RGBColor(grey, grey, grey);
		

	}

}
