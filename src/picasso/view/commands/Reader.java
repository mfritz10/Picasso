package picasso.view.commands;

import java.io.BufferedReader;
import java.io.FileReader;

import javax.swing.JFileChooser;

import picasso.model.Pixmap;
import picasso.util.FileCommand;
import picasso.view.Frame;

/**
 * Open the chosen image file and display in the Pixmap target.
 * 
 * @author Robert C Duvall
 */
public class Reader extends FileCommand<Pixmap> {

	/**
	 * Creates a Reader object, which prompts users for image files to open
	 */
	public Reader() {
		super(JFileChooser.OPEN_DIALOG);
	}

	/**
	 * Displays the image file on the given target.
	 */
	public void execute(Pixmap target) {
		String fileName = getFileName();
		String currentLine;
		String text = "";

		
		if (fileName != null) {
			
			try {
				BufferedReader br = new BufferedReader(new FileReader(fileName));
		        while ((currentLine = br.readLine()) != null) {
		        	if (currentLine.contains("//")) {
		        		continue;
		        	}
		        	text += currentLine + "\n";
		        }
		        
		        Input.setInput(text);
		        new Evaluator().execute(target);
		        
			} catch (Exception e) {
				  System.out.println(e.getClass());
			}
	}
	
}
}
