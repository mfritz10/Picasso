package picasso;

import java.awt.Dimension;

import picasso.view.commands.HistoryLog;
import picasso.view.commands.NewWindow;

/**
 * Starting point for Picasso.
 * 
 * @author Robert Duvall (rcd@cs.duke.edu)
 */
public class Main {
	// variable declarations
	public static final Dimension SIZE = new Dimension(600, 600);
	public static final Dimension SIZE2 = new Dimension(300, 300);
	
	// JFrame declarations
	public static HistoryLog log;
	public static NewWindow first;
	
	public static void main(String[] args) {
		
		first = new NewWindow(SIZE);
		
		log = new HistoryLog(SIZE2);
		log.setLocation(750,0);
		log.setVisible(true);
		
	}
}
