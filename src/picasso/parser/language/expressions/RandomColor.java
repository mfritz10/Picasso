package picasso.parser.language.expressions;

import picasso.parser.language.ExpressionTreeNode;
import java.util.Random;

/**
 * Generates a single random color
 * @author Matthew Fritz
 *
 */
public class RandomColor extends ExpressionTreeNode {
	
	
	Random rand = new Random();
	
	RGBColor myColor;
	
	@Override
	public RGBColor evaluate(double x, double y) {
		
		if (myColor == null) {
		
			double red = posOrNeg(rand.nextDouble());
			double green = posOrNeg(rand.nextDouble());
			double blue = posOrNeg(rand.nextDouble());

			myColor = new RGBColor(red, green, blue);
		} 
			
		return myColor;
	}
	
	private double posOrNeg(double d) {
		int decide = rand.nextInt(2);
		
		if (decide ==  1) {
			return d;
		} else {
			return d * -1;
		}
		
	}
}
