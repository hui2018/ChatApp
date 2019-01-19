package Server;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class ChatServer {
	
	//keep track of user connections and users
	public static ArrayList<Socket> connectionArray = new ArrayList<Socket>();
	public static ArrayList<String> currentUsers = new ArrayList<String>();
	private static final String REGFILENAME = "/Users/Jack/git/hui2-chatapp/ChatApp/src/Server/Registration.txt";
	static ServerDataBase Obj = new ServerDataBase();
	
	public static void main(String[] args) throws IOException
	{
		try
		{
			final int PORT = 1234;
			ServerSocket svrSock = new ServerSocket(PORT);
			System.out.println("Waiting for clients...");
			while(true)
			{
				Socket sock = svrSock.accept();
				connectionArray.add(sock);
				System.out.println("Client connected");
				
				addUserName(sock);
				
				ChatServerReturn chat = new ChatServerReturn(sock);
				Thread x = new Thread(chat);
				x.start();
				
			}
			
		}
		catch(Exception x) 
		{
			System.out.print(x);
		}
	}
	
	public static void addUserName(Socket X) throws IOException
	{
		Scanner INPUT = new Scanner(X.getInputStream());
		String UserName = INPUT.nextLine();
		currentUsers.add(UserName);
		for(int i = 1; i <= ChatServer.connectionArray.size(); i++)
		{
			Socket temp = (Socket) ChatServer.connectionArray.get(i-1);
			PrintWriter OUT = new PrintWriter(temp.getOutputStream());
			OUT.println("#?!" + currentUsers);
			OUT.flush();
		}
	}
}
