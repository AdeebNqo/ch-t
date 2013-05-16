import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;


public class Server {
	private static Vector<DataOutputStream> clients;
	
	public static void main(String[] args){
		try {
			clients = new Vector<DataOutputStream>();
			ServerSocket ss = new ServerSocket(9999);
			while(true){
				Client tmp_client = new Client(ss.accept());
				clients.add(tmp_client.out);
				tmp_client.start();
				System.out.println(tmp_client.s.getInetAddress()+" now connected");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	 * Class for handling text coming and going to server
	 * 
	 */
	static class Client extends Thread{
		Socket s;
		DataOutputStream out;
		DataInputStream in;
		public Client(Socket someSocket){
			this.s = someSocket;
			try{
				out = new DataOutputStream(s.getOutputStream());
				in = new DataInputStream(s.getInputStream());
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		public void run(){
			while(true){
				try {
					String tmp = in.readUTF();
					for (DataOutputStream writer:clients){
						writer.writeUTF(tmp);
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
