package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents exponentiation in Picasso language
 * 
 * @author Matthew Fritz
 */
public class Exponentiate extends BinaryFunction {

	/**
	 * Create an exponentiated expression that takes as parameters the given expressions
	 * 
	 * @param left the expression to the left of the operator
	 * @param right the expression to the right of the operator
	 */
	public Exponentiate(ExpressionTreeNode left, ExpressionTreeNode right) {
		super(left, right);
	}

	/**
	 * Evaluates this expression at the given x,y point by evaluating the exponentiation of
	 * the function's parameters.
	 * 
	 * @return the color from evaluating the exponentiation of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor leftResult = left.evaluate(x, y);
		RGBColor rightResult = right.evaluate(x, y);
		double red = Math.pow(leftResult.getRed(), rightResult.getRed());
		double green = Math.pow(leftResult.getGreen(), rightResult.getGreen());
		double blue = Math.pow(leftResult.getBlue(), rightResult.getBlue());

		return new RGBColor(red, green, blue);
	}

}
