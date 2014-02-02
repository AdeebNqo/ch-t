import java.awt.BorderLayout;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.HashMap;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;

public class Main extends JDialog {
	
	//Application configuration
	private static HashMap<String,String> config;
	
	/**
	 * I dunnot wtf is this field even though I have seen for
	 * for about three years now
	 */
	private static final long serialVersionUID = 1L;

	private static String username = "anon";
	
	private static DataInputStream in;
	private static DataOutputStream out;
	private static JTabbedPane tabbedPane;
	private static Socket s;
	/*
	 * Panel that will keep the configuration and the
	 * communication interface.
	 */
	private static JPanel commPanel;
	private static JPanel configPanel;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			//load saved settings
			loadConfig();
			
			//Connecting to server
			s = new Socket("localhost",9999);
			
			out = new DataOutputStream(s.getOutputStream());
			in = new DataInputStream(s.getInputStream());
			
			//setting up look and feel
			UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel");
			//starting Client window
			Main dialog = new Main();
			
			/*while(true){
				String tmp = in.readUTF();
				editorPane0.append(tmp+'\n');
			}*/
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Main() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout(0, 0));
		{
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			getContentPane().add(tabbedPane, BorderLayout.CENTER);
			
			commPanel = new JPanel();
			configPanel = new JPanel();
			tabbedPane.add(commPanel, 0);
			tabbedPane.add(configPanel, 1);
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}
	/*
	 * Method for loading application configuration
	 * 
	 */
	public static void loadConfig(){
		
	}
}
