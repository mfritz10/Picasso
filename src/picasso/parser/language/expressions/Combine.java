
package picasso.parser.language.expressions;

import picasso.model.ImprovedNoise;
import picasso.parser.language.ExpressionTreeNode;


public class Combine extends BinaryFunction {
	
	
	public Combine(ExpressionTreeNode xExpress, ExpressionTreeNode yExpress) {
		super(xExpress, yExpress);
	}

	
	@Override
	public RGBColor evaluate(double x, double y) {
		RGBColor leftResult = left.evaluate(x, y);
		RGBColor rightResult = right.evaluate(x, y);
		double red = ((leftResult.getRed() + rightResult.getRed())/2);
		double green = ((leftResult.getGreen() + rightResult.getGreen())/2);
		double blue = ((leftResult.getGreen() + rightResult.getGreen())/2);

		return new RGBColor(red, green, blue);
	}
}