package picasso.view.commands;

import picasso.view.Frame;

/**
 * Tracks equation to pass to evaluator.
 * 
 * @author Julie Ham
 *
 */

public class Input {
	
	static String function;
	static Frame current;

	public Input(Frame thisFrame) {
		current = thisFrame;
	}
	
	/**
	 * Saves equation in private variable
	 * 
	 * @param text the equation to pass
	 */
	public static void setInput(String text) {
        Frame.bar.setText(text);
	}
	
	
	/**
	 * Passes reference of equation
	 * 
	 * @return function the equation
	 */
	public static String getInput() {
		function = (Frame.getBar());
		return function;
	}

}
