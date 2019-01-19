package Server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatServerReturn implements Runnable {
	Socket sock;
	private Scanner INPUT;
	private PrintWriter OUTPUT;
	String Message = "";
	
	public ChatServerReturn(Socket X)
	{
		this.sock = X;
	}
	
	public void checkConnection() throws IOException
	{
		if(!sock.isConnected())
		{
			for(int i = 1; i< ChatServer.connectionArray.size(); i++)
			{
				if(ChatServer.connectionArray.get(i) == sock)
				{
					ChatServer.connectionArray.remove(i);
				}
			}
			
			for(int i = 1; i< ChatServer.connectionArray.size(); i++)
			{
				Socket temp = (Socket) ChatServer.connectionArray.get(i-1);
				PrintWriter out = new PrintWriter(temp.getOutputStream());
				out.println(temp.getLocalAddress().getHostName() + "disconnected!");
				out.flush();
				
				System.out.println(temp.getLocalAddress().getHostName() + "disconnected!");
				
			}
		}
	}

	@Override
	public void run() {
		try
		{
			try
			{
				INPUT = new Scanner(sock.getInputStream());
				OUTPUT = new PrintWriter(sock.getOutputStream());
				
				while(true)
				{
					checkConnection();
					
					if(!INPUT.hasNext())
						return;
					
					Message = INPUT.nextLine();
					System.out.println("Client said: " + Message);
					
					for(int i = 1; i<= ChatServer.connectionArray.size(); i++)
					{
						Socket temp = (Socket) ChatServer.connectionArray.get(i-1);
						PrintWriter out = new PrintWriter(temp.getOutputStream());
						out.println(Message);
						out.flush();
						System.out.println("Send to:" + temp.getLocalAddress().getHostName());
						
					}
					
				}
			}
			finally
			{
				sock.close();
			}
		}
		catch(Exception x)
		{
			System.out.println(x);
		}
	}
}
