import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JTextArea;
import javax.swing.JTabbedPane;

public class Main extends JDialog {
	private static String username = "anon";
	
	private static DataInputStream in;
	private static DataOutputStream out;
	private JTabbedPane tabbedPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			File usname = new File("username");
			Socket s = new Socket("localhost",9999);
			
			try{
				Scanner usnamereader = new Scanner(new FileReader(usname));
				String tmp_usname = usnamereader.nextLine();
				if (tmp_usname.equals("")){
					username = sanitize(s.getInetAddress().toString());
				}
				else{
					username = tmp_usname;
				}
				usnamereader.close();
			}catch(Exception e){
				username = sanitize(s.getInetAddress().toString());
			}
			out = new DataOutputStream(s.getOutputStream());
			in = new DataInputStream(s.getInputStream());
			
			UIManager.setLookAndFeel("de.javasoft.plaf.synthetica.SyntheticaBlackEyeLookAndFeel");
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
		}
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	/*
	 * 
	 */
	public static String sanitize(String someString){
			someString = someString.substring(1);
			return someString;
	}
}
