package picasso.view.commands;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JOptionPane;

import picasso.model.Pixmap;
import picasso.parser.ExpressionTreeGenerator;
import picasso.parser.ParseException;
import picasso.parser.language.ExpressionTreeNode;
import picasso.util.Command;
import picasso.view.Frame;

/**
 * Evaluate an expression for each pixel in a image.
 *
 * @author Robert C Duvall
 * @author Sara Sprenkle
 */
public class Evaluator implements Command<Pixmap> {
	public static final double DOMAIN_MIN = -1;
	public static final double DOMAIN_MAX = 1;
	private static String input;
//	static HistoryLog log = new HistoryLog();

	/**
	 * Evaluate an expression for each point in the image.
	 */
	
	//try catch loop for error handling 
	//need to update 
	public void execute(Pixmap target) {
		ExpressionTreeNode expr;
		// create the expression to evaluate just once
		try {

			expr = createExpression();
			HistoryLog.addLine(input);

		} catch (ParseException e) {
			// TODO: update gui
			System.out.println("Grace was here (gui update later)");
			e.printStackTrace();
			return;
		}

		// evaluate it for each pixel
		Dimension size = target.getSize();
		for (int imageY = 0; imageY < size.height; imageY++) {
			double evalY = imageToDomainScale(imageY, size.height);
			for (int imageX = 0; imageX < size.width; imageX++) {
				double evalX = imageToDomainScale(imageX, size.width);
				Color pixelColor = expr.evaluate(evalX, evalY).toJavaColor();
				target.setColor(imageX, imageY, pixelColor);
			}
		}
	}

	/**
	 * Convert from image space to domain space.
	 */
	protected double imageToDomainScale(int value, int bounds) {
		double range = DOMAIN_MAX - DOMAIN_MIN;
		return ((double) value / bounds) * range + DOMAIN_MIN;
	}

	/**
	 *
	 * A place holder for a more interesting way to build the expression.
	 */
	private ExpressionTreeNode createExpression() {
		// Note, when you're testing, you can use the ExpressionTreeGenerator to
		// generate expression trees from strings, or you can create expression
		// objects directly (as in the commented statement below).

		// error handling (try catch blocks)

		ExpressionTreeGenerator expTreeGen = new ExpressionTreeGenerator();
		
		input = Input.getInput();
		
		if (input.equals("")) {
			JOptionPane.showMessageDialog(null, "No expression entered", "Error", JOptionPane.ERROR_MESSAGE);
			throw new ParseException("Empty text");
		}
				
		return expTreeGen.makeExpression(input);
		// return new Multiply( new X(), new Y() );
	}
}
