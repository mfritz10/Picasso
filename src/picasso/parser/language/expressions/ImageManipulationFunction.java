package picasso.parser.language.expressions;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import picasso.parser.ParseException;
import picasso.parser.language.ExpressionTreeNode;

/**
 * Represents a function that edits an image
 * 
 * @author Matthew Fritz
 *
 */
public abstract class ImageManipulationFunction extends ExpressionTreeNode {

	ExpressionTreeNode xFunc;
	ExpressionTreeNode yFunc;
	String fileName;
	
	private String myFileName;
	BufferedImage myImage;
	private Dimension mySize;
	
	/**
	 * 
	 * @param fileName name of file to first display
	 * @param x x manipulation to be done
	 * @param y y manipulation to be done
	 */
	public ImageManipulationFunction(String fileName, ExpressionTreeNode x, ExpressionTreeNode y) {
		//process image in constructor
		this.fileName = "images/" + fileName;
		readFile(this.fileName);
		this.xFunc = x;
		this.yFunc = y;
	}
	
	/**
	 * 
	 * @param value between -1 and 1 for the point on the pixmap
	 * @param bound width of the image
	 * @return value in the correct range
	 */
	public int domainToImageScale(double value, int bound) { 
		
		int center = (bound -1)/2;
		return (int) (value * center) + center;
		
	}
	
	/**
	 * Read the image named by fileName
	 * 
	 * @param fileName the name of the image file to be read in
	 */
	public void readFile(String fileName) {
		
		try {
			myFileName = fileName;
			myImage = ImageIO.read(new File(myFileName));
			mySize = new Dimension(myImage.getWidth(), myImage.getHeight());
			
		} catch (IOException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "No file with this name exists in images directory", "Error", JOptionPane.ERROR_MESSAGE);
			throw new ParseException("No file with this name exists in images directory");
		}
		
	}
	
	/**
	 * Returns the string representation of the function in the format "<ClassName>:
	 * <parameter>"
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String classname = this.getClass().getName();
		return classname.substring(classname.lastIndexOf(".")) + "(" + fileName + "," + xFunc + "," + yFunc + ")";
	}

	@Override
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}

		if (!(o instanceof ImageManipulationFunction)) {
			return false;
		}

		// Make sure the objects are the same type

		if (o.getClass() != this.getClass()) {
			return false;
		}

		ImageManipulationFunction uf = (ImageManipulationFunction) o;

		// check if their parameters are equal
		if (!this.xFunc.equals(uf.xFunc)) {
			return false;
		}
		if (!this.yFunc.equals(uf.yFunc)) {
			return false;
		}

		return true;
	}

}
