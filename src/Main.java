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

public class Main extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField enter_text;
	private static String username = "anon";
	
	private static DataInputStream in;
	private static DataOutputStream out;
	
	private static JTextArea editorPane0;
	private JScrollPane editorPane;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			
			File usname = new File("username");
			Socket s = new Socket("137.158.59.46",9999);
			
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
			while(true){
				String tmp = in.readUTF();
				editorPane0.append(tmp+'\n');
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public Main() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			editorPane0 = new JTextArea();
			editorPane0.setEditable(false);
			editorPane = new JScrollPane(editorPane0);
			editorPane.setFont(new Font("DejaVu Sans Light", Font.PLAIN, 12));
			contentPanel.add(editorPane, BorderLayout.CENTER);
		}
		{
			JPanel buttonPane = new JPanel();
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			buttonPane.setLayout(new BorderLayout(0, 0));
			{
				enter_text = new JTextField();
				buttonPane.add(enter_text);
				enter_text.setColumns(10);
			}
			enter_text.addKeyListener(new KeyListener(){
				public void keyPressed(KeyEvent arg0) {
					if (arg0.getKeyCode() == KeyEvent.VK_ENTER){
						String tmp = username+" "+enter_text.getText();
						try {
							out.writeUTF(tmp);
							out.flush();
						} catch (IOException e) {
							e.printStackTrace();
						}
						enter_text.setText("");
					}
				}
				public void keyReleased(KeyEvent arg0) {
				}
				public void keyTyped(KeyEvent arg0) {
				}
			});
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
