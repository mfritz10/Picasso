package picasso.parser.language.expressions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import picasso.parser.ParseException;
import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents imageClip in the Picasso language
 * 
 * @author Matthew Fritz
 */
public class ImageClip extends ImageManipulationFunction {


	/**
	 * Clips a read in image so it only appears once, based on an
	 * expression that takes as parameters the given expressions
	 * 
	 * @param fileName the name of the file to be read, including tag
	 * @param x the expression to manipulate the x
	 * @param y the expression to manipulate the y
	 */
	public ImageClip(String fileName, ExpressionTreeNode x, ExpressionTreeNode y) {
		//process image in constructor
		super(fileName, x, y);
	}
	
	/**
	 * Evaluates this expression at the given x,y point by clipping the image 
	 * based on the expressions passed as the function's parameters.
	 * 
	 * @return the color from evaluating the clipping of the expression's parameter
	 */
	@Override
	public RGBColor evaluate(double x, double y) {
		
		
		RGBColor xResult = xFunc.evaluate(x, y); //create a wrap function, test each part using easy colors
		double newX = xResult.getRed();
		double clippedX = clip(newX);
		
		RGBColor yResult = yFunc.evaluate(x, y);
		double newY = yResult.getRed();
		double clippedY = clip(newY);
		
		return new RGBColor(new Color(myImage.getRGB
				(domainToImageScale(clippedX, myImage.getWidth()), 
						domainToImageScale(clippedY, myImage.getHeight()))));
		
				
	}
	
	
	/**
	 * Creates a function that clips the numbers
	 * 
	 * @param x the coordinate after manipulating expression
	 */
	public double clip(double x) {
		
		if (x > 1) {
			x = 1;
		} else if (x < -1) {
			x = -1;
		} 
		return x;
	}

}
