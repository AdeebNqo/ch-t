import java.awt.BorderLayout;

import javax.swing.JPanel;


public class UsersPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//
	public UsersPanel(){
		this.setLayout(new BorderLayout());
		//adding top panel for decorations
		JPanel decoration = new JPanel();
		add(decoration, BorderLayout.NORTH);
	}
}
