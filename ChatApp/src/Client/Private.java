package Client;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

import Server.ServerDataBase;

import java.net.Socket;

public class Private implements Runnable {

	Socket sock;
	Scanner INPUT;
	Scanner Send = new Scanner(System.in);
	PrintWriter OUTPUT;
	
	
	public Private(Socket sock) {
		this.sock = sock;
	}

	public void DISCONNECT() throws IOException{
		OUTPUT.println(ChatClientUI.UserName + " has disconnected.");
		OUTPUT.flush();
		sock.close();
		JOptionPane.showMessageDialog(null, " You have been disconnected");
		System.exit(0);
		
	}
	

	public void SEND(String text) {
		OUTPUT.println(ChatClientUI.UserName + ": " + text);
		
		//store into text file
		
		OUTPUT.flush();
		PrivateChat.TEXTFIELD_MESSAGE.setText("");
		
	}

	@Override
	public void run() {
		try
		{
			try
			{
				INPUT = new Scanner(sock.getInputStream());
				OUTPUT = new PrintWriter(sock.getOutputStream());
				OUTPUT.flush();
				CheckStream();
			}
			finally
			{
				sock.close();
			}
			
		}
		catch(Exception x)
			{
				System.out.print(x);
			}
	}

	private void CheckStream() {
		while(true)
		{
			recieve();
		}
		
	}

	private void recieve() {
		if(INPUT.hasNext())
		{
			String Message = INPUT.nextLine();
			
			if(Message.contains("#?!"))
			{
				String temp = Message.substring(3);
				temp = temp.replace("[","");
				temp = temp.replaceAll("]", "");
				
				String[] CurrentUsers = temp.split(", ");
				
			}
			else
				PrivateChat.TEXTAREA_CONVERSATION.append(Message + "\n");
		}
	}
}