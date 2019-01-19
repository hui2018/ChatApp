package Client;

import java.net.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.JOptionPane;

import Server.ChatServer;
import Server.ServerDataBase;

import java.net.Socket;

public class ChatClient implements Runnable {

	Socket sock;
	Scanner INPUT;
	Scanner Send = new Scanner(System.in);
	PrintWriter OUTPUT;
	ServerDataBase History = new ServerDataBase();
	ChatServer UserConnect = new ChatServer();
	
	public ChatClient(Socket sock) {
		this.sock = sock;
	}

	//disconnected user
	public void DISCONNECT() throws IOException{
		//JOptionPane.showMessageDialog("Working");
		OUTPUT.println(ChatClientUI.UserName + " has disconnected.");
		OUTPUT.flush();
		sock.close();
		JOptionPane.showMessageDialog(null, " You have been disconnected");
		System.exit(0);
		
	}
	

	public void SEND(String text) {
		OUTPUT.println(ChatClientUI.UserName + ": " + text);
		//JOptionPane.showMessageDialog("Working");
		//store into text file
		History.StoreMessage(ChatClientUI.UserName, text);
		OUTPUT.flush();
		ChatClientUI.TEXTFIELD_MESSAGE.setText("");
		
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
				History.InputLogin();
				History.display();
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
				
				//ChatClientUI.LIST_ONLINE.setForeground(new java.awt.Color(255,0,0));
				ChatClientUI.LIST_ONLINE.setListData(CurrentUsers);
				
			}
			else
				ChatClientUI.TEXTAREA_CONVERSATION.append(Message + "\n");
		}
		
	}
	public static void main( String[] args )
	{	
		//ChatClient test = new ChatClient(null);
		//test.Display();
	}
	
	//testing function
	public void Display()
	{
		
		ChatClientUI.TEXTAREA_CONVERSATION.setText(History.PrintOutMessage());
	}

	//return the string of all text inside the text file
	public String DisplayChat()
	{
		return History.PrintOutMessage();
	}

	public void StoreName()
	{
		//History.StoreUser(ChatClientUI.TF_RegisterUserName, ChatClientUI.TF_RegisterPassword);
		//History.StoreUser(ChatClientUI.TF_RegisterUserName.getText(), ChatClientUI.TF_RegisterPassword.getText());
		System.out.println("WORK");
	}
	
}
