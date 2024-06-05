package picasso.view.commands;

import java.awt.Dimension;

import picasso.view.Frame;

/**
 * Open a copy of the main container (Frame) for Picasso.
 * 
 * @author Julie Ham
 */
public class NewWindow {
	
	public Frame frame;
	public static int countInWindow;
		
	public NewWindow(Dimension size) {
		countInWindow ++;
//		System.out.println("Constructor NewWindow: " + countInWindow);
		frame = new Frame(size);
		frame.setVisible(true);
	}
	
	/**
	 * Creates a new JFrame from the class Frame
	 * 
	 * @param size size of the Frame imported from Main's declaration
	 */
	public static void addWindow(Dimension size) {
		countInWindow ++;
		Frame newFrame = new Frame(size);
		newFrame.setVisible(true);
		}

}
