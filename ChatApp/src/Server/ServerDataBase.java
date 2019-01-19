package Server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Client.ChatClientUI;

public class ServerDataBase {
	private static final String FILENAME = "/Users/Jack/git/hui2-chatapp/ChatApp/src/Server/ChatHistory.txt";
	private static final String REGFILENAME = "/Users/Jack/git/hui2-chatapp/ChatApp/src/Server/Registration.txt";
	public Node head;
	protected Node tail;
	public static ServerDataBase test = new ServerDataBase();
	//testing
	/*
	public static void main( String[] args )
	{
		//ServerDataBase test = new ServerDataBase();
		//ChatServer Object = new ChatServer();
		test.InputLogin();
		test.display();
		
		
	}
	*/
	//store message content into text file
	public void StoreMessage(String UserName, String Message)
	{
		try
		{
			File file =new File(FILENAME);
	    	  if(!file.exists()){
	    	 	file.createNewFile();
	    	  }
	    	  //This will add a new line to the file content
	    	  FileWriter fw = new FileWriter(file,true);
	    	  BufferedWriter bw = new BufferedWriter(fw);
	    	  PrintWriter pw = new PrintWriter(bw);
	         
	    	  pw.println(UserName + ": " + Message);
	    	  pw.close();
	    }
		catch(IOException ioe)
		{
	    	   System.out.println("Exception occurred:");
	    	   ioe.printStackTrace();
	    }
	}
	
	//print out the history chat into global chat box to all user
	public String PrintOutMessage() 
	{
		try {
			FileReader fr = new FileReader(FILENAME);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			String LongString = "";
			while(line != null)
			{
				LongString = LongString + line + "\n";
				line = br.readLine();
			}
			fr.close();
			
			return LongString;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	//store the user name into a linear linked list
	public void StoreUser(String UserName, String Password)
	{
		try
		{
			File file =new File(REGFILENAME);
	    	  if(!file.exists()){
	    	 	file.createNewFile();
	    	  }
	    	  //This will add a new line to the file content
	    	  FileWriter fw = new FileWriter(file,true);
	    	  BufferedWriter bw = new BufferedWriter(fw);
	    	  PrintWriter pw = new PrintWriter(bw);
	        
	    	  pw.println(UserName);
	    	  pw.println(Password);
	    	  pw.close();
	    }
		catch(IOException ioe)
		{
	    	   System.out.println("Exception occurred:");
	    	   ioe.printStackTrace();
	    }
	}
	//load the file and store the user and password to a linear linked list
	public void InputLogin()
	{
		try {
			FileReader fr = new FileReader(REGFILENAME);
			BufferedReader br = new BufferedReader(fr);
			String User = br.readLine();
			String Pass = br.readLine();
			
			if(head == null)
			{
				head = new Node(User, Pass);
			}
			
			Node current = head;
			Node temp = new Node(null, null);
			while(User != null)
			{
				temp = new Node(User, Pass);
				while(current.Get_next() != null)
					current = current.Get_next();
				current.Set_next(temp);
				temp.Set_next(null);
				current = head;
				User = br.readLine();
				Pass = br.readLine();
			}while(User != null)
			fr.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/*
	//store the login information to a linear linked list
	public void StoreLogin(Node temp)
	{
		if(head == null)
		{
			head = temp;
			return;
		}
		StoreLogin(head, temp);
		return;
	}
	
	public void StoreLogin(Node head, Node temp)
	{
		if(head == null)
		{
			head = temp;
			return;
		}
		if(head.Get_next() == null)
		{
			head.Set_next(temp);
			return;
		}
		StoreLogin(head.Get_next(), temp);
	}
	*/
	
	//testing function
	public void display(Node head)
	{
		if(head == null)
		{
			return;
		}
		System.out.println(head.UserName);
		System.out.println(head.Password);
		display(head.Get_next());
	}
	//testing function
	public void display()
	{
		display(head);
	}

	//compare for if the user name and password match to login to the server
	public boolean Compare(String User, String Pass) 
	{
		return Compare(head, User, Pass);
	}
	
	public boolean Compare(Node head, String User, String Pass)
	{
		if(head == null)
		{
			return false;
		}
		else if(head.UserName.equals(User) && head.Password.equals(Pass))
		{
			return true;
		}
		return Compare(head.Get_next(), User, Pass);
	}
	
	
	//Compare for user name only during registration
	public boolean CompareUser(String User) 
	{
		return CompareUser(head, User);
	}
	
	public boolean CompareUser(Node head, String User)
	{
		if(head == null)
		{
			return false;
		}
		else if(head.UserName.equals(User))
		{
			return true;
		}
		return CompareUser(head.Get_next(), User);
	}


	public void compareOnline(String onlineUser)
	{
		if(head == null)
			return;
		if(head.UserName.equals(onlineUser))
		{
			
		}
	}


}

