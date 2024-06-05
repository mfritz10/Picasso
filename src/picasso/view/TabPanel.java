package picasso.view;
/**
 * Creates a panel for the new tab
 * 
 */
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import picasso.Main;

@SuppressWarnings("serial")
public class TabPanel extends JPanel {
	private Canvas myView;
	private final JTabbedPane tabbedPane;
	private final JButton addButton;
//	private JPanel panel;

	public TabPanel(Canvas view) {
		myView = view;
//		tabbedPane = new JTabbedPane(JTabbedPane.LEFT);
		tabbedPane = new JTabbedPane();
		tabbedPane.setPreferredSize(Main.SIZE);
		tabbedPane.setTabPlacement(JTabbedPane.LEFT);
		
		addButton = new JButton("+");
		addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	System.out.println("Click");
                // Create a panel for the new tab
//            	tabbedPane.addTab("🌸", new JPanel());
//        		Canvas canvas = new Canvas(null);
				add();
//                // Add the new tab to the tabbed pane
//                tabbedPane.addTab("2", canvas);
            }
        });
        add(addButton);

	}
	
	public void add() {
		myView.refresh();
	    // Add the panels to the tabbed pane
	    tabbedPane.addTab("🌸", myView);
	    
	    add(tabbedPane);
	   
	}

}
