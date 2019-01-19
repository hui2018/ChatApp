package Client;
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import Server.ChatServer;
import Server.Node;
import Server.ServerDataBase;

import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

public class ChatClientUI {
	private static ChatClient Chat;
	private static ServerDataBase DB;
	public static String UserName = "Anonymous";
	private static PrivateChat NewChat;
	
	//Client interface
	public static JFrame MainWindow = new JFrame();
	private static JButton BUTTON_CONNECT = new JButton();
	private static JButton BUTTON_DISCONNECT = new JButton();
	private static JButton BUTTON_SEND = new JButton();
	private static JLabel LABEL_MESSAGE = new JLabel();
	public static JTextField TEXTFIELD_MESSAGE = new JTextField();
	private static JLabel LABEL_CONVERSATION = new JLabel();
	public static JTextArea TEXTAREA_CONVERSATION = new JTextArea();
	private static JScrollPane SCROLL_CONVERSATION = new JScrollPane();
	private static JLabel LABEL_ONLINE = new JLabel();
	public static JList LIST_ONLINE = new JList();
	private static JScrollPane SCROLL_ONLINE = new JScrollPane();
	private static JLabel LABEL_LOGGEDINAS = new JLabel();
	private static JLabel LABEL_LOGGEDINASBOX = new JLabel();
	
	
	//login
	public static JFrame LoginWindow = new JFrame();
	public static JTextField TF_UserNameBox = new JTextField(15);
	public static JTextField TF_PasswordBox = new JTextField(15);
	private static JButton B_Login = new JButton("Login");
	private static JButton B_Register = new JButton("Register");
	private static JLabel L_EnterUserName = new JLabel("UserName: ");
	private static JLabel L_EnterPassword = new JLabel("Password: ");
	private static JPanel P_Login = new JPanel();
	
	
	//register
	public static JFrame RegisterWindow = new JFrame();
	public static JTextField TF_RegisterUserName = new JTextField(15);
	public static JTextField TF_RegisterPassword = new JTextField(15);
	private static JLabel L_RegisterUserName = new JLabel("UserName: ");
	private static JLabel L_RegisterPassword = new JLabel("Password: ");
	private static JButton B_Confirm = new JButton("Confirm");
	private static JButton B_Cancel = new JButton("Cancel");
	private static JPanel P_Register = new JPanel();
	
	
	public static void main(String args[])
	{
		createMainWindow();
		initialize();
		DB.test.InputLogin();
	}
	//creating the main window
	public static void createMainWindow()
	{
		
		MainWindow.setTitle(UserName + "'s Chat box");
		MainWindow.setSize(450, 500);
		MainWindow.setLocation(220, 180);
		MainWindow.setResizable(false);
		configMainWindow();
		actionMainWindow();
		MainWindow.setVisible(true);
		
	}
	
	//setting the window configuration window
	public static void configMainWindow()
	{
		MainWindow.setBackground(new java.awt.Color(255, 255, 255));
		MainWindow.setSize(500, 320);
		MainWindow.getContentPane().setLayout(null);
		
		BUTTON_SEND.setBackground(new java.awt.Color(0, 0, 255));
		BUTTON_SEND.setForeground(new java.awt.Color(255, 255, 255));
		BUTTON_SEND.setText("SEND");
		MainWindow.getContentPane().add(BUTTON_SEND);
		BUTTON_SEND.setBounds(250, 40, 81, 25);
		
		BUTTON_DISCONNECT.setBackground(new java.awt.Color(0, 0, 255));
		BUTTON_DISCONNECT.setForeground(new java.awt.Color(255, 255, 255));
		BUTTON_DISCONNECT.setText("DISCONNECT");
		MainWindow.getContentPane().add(BUTTON_DISCONNECT);
		BUTTON_DISCONNECT.setBounds(10, 40, 110, 25);
		
		BUTTON_CONNECT.setBackground(new java.awt.Color(0, 0, 255));
		BUTTON_CONNECT.setForeground(new java.awt.Color(255, 255, 255));
		BUTTON_CONNECT.setText("CONNECT");
		MainWindow.getContentPane().add(BUTTON_CONNECT);
		BUTTON_CONNECT.setBounds(130, 40, 110, 25);
		
		LABEL_MESSAGE.setText("Message:");
		MainWindow.getContentPane().add(LABEL_MESSAGE);
		LABEL_MESSAGE.setBounds(10, 10, 60, 20);
		
		TEXTFIELD_MESSAGE.setForeground(new java.awt.Color(0,0,225));
		TEXTFIELD_MESSAGE.requestFocus();
		MainWindow.getContentPane().add(TEXTFIELD_MESSAGE);
		TEXTFIELD_MESSAGE.setBounds(70,4,260,30);
		
		LABEL_CONVERSATION.setHorizontalAlignment(SwingConstants.CENTER);
		LABEL_CONVERSATION.setText("Conversation");
		MainWindow.getContentPane().add(LABEL_CONVERSATION);
		LABEL_CONVERSATION.setBounds(100, 70, 140, 16);
		
		TEXTAREA_CONVERSATION.setColumns(20);
		TEXTAREA_CONVERSATION.setFont(new java.awt.Font("Tahoma", 0, 12));
		TEXTAREA_CONVERSATION.setForeground(new java.awt.Color(0,0,225));
		TEXTAREA_CONVERSATION.setLineWrap(true);
		TEXTAREA_CONVERSATION.setRows(5);
		TEXTAREA_CONVERSATION.setEditable(false);
		
		SCROLL_CONVERSATION.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SCROLL_CONVERSATION.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		SCROLL_CONVERSATION.setViewportView(TEXTAREA_CONVERSATION);
		MainWindow.getContentPane().add(SCROLL_CONVERSATION);
		SCROLL_CONVERSATION.setBounds(10,90,330,180);
		
		LABEL_ONLINE.setHorizontalAlignment(SwingConstants.CENTER);
		LABEL_ONLINE.setText("List of User");
		LABEL_ONLINE.setToolTipText("");
		MainWindow.getContentPane().add(LABEL_ONLINE);
		LABEL_ONLINE.setBounds(350, 70, 130, 16);
		
		SCROLL_ONLINE.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		SCROLL_ONLINE.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		SCROLL_ONLINE.setViewportView(LIST_ONLINE);
		MainWindow.getContentPane().add(SCROLL_ONLINE);
		SCROLL_ONLINE.setBounds(350,90,130,180);
		
		LABEL_LOGGEDINAS.setFont(new java.awt.Font("Tahoma", 0, 12));
		LABEL_LOGGEDINAS.setText("Currently logged in as");
		MainWindow.getContentPane().add(LABEL_LOGGEDINAS);
		LABEL_LOGGEDINAS.setBounds(348, 0, 140, 15);
		
		LABEL_LOGGEDINASBOX.setHorizontalAlignment(SwingConstants.CENTER);
		LABEL_LOGGEDINASBOX.setFont(new java.awt.Font("Tahoma", 0, 12));
		LABEL_LOGGEDINASBOX.setForeground(new java.awt.Color(255,0,0));
		LABEL_LOGGEDINASBOX.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0,0,0)));
		MainWindow.getContentPane().add(LABEL_LOGGEDINASBOX);
		LABEL_LOGGEDINASBOX.setBounds(340,17,150,20);
	}
	
	//login action button
	public static void login_Action()
	{
		B_Login.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{ACTION_B_LOGIN();}
				}
		);
	}
	
	//login button function to connect or not to connect to the server
	public static void ACTION_B_LOGIN()
	{
		//check for characters and blank space only for user name
		//if(TF_UserNameBox.getText().equals("test") || TF_UserNameBox.getText().equals("try"))
		//if(!TF_UserNameBox.getText().equals(" "))
		////JOptionPane.showMessageDialog("Working");
		if(DB.test.Compare(TF_UserNameBox.getText(), TF_PasswordBox.getText()))
		{
			UserName = TF_UserNameBox.getText().trim();
			LABEL_LOGGEDINASBOX.setText(UserName);
			ChatServer.currentUsers.add(UserName);
			MainWindow.setTitle(UserName + "'s Chat Box");
			LoginWindow.setVisible(false);
			BUTTON_SEND.setEnabled(true);
			BUTTON_DISCONNECT.setEnabled(true);
			BUTTON_CONNECT.setEnabled(false);
			connect();
		}
		else
		{
			JOptionPane.showMessageDialog(null,"Wrong Password / Username");
			TF_UserNameBox.setText("");
			TF_PasswordBox.setText("");
		}
		
	}
	
	//initialize what buttons can are clickable initially 
	public static void initialize()
	{
		BUTTON_SEND.setEnabled(false);
		BUTTON_DISCONNECT.setEnabled(false);
		BUTTON_CONNECT.setEnabled(true);
	}
	
	private static void jList1ValueChanged(ListSelectionEvent evt) {
        //set text on right here
        String s = (String) LIST_ONLINE.getSelectedValue();
        
        if (!s.equals(UserName)) {
        	//JOptionPane.showMessageDialog("Working");
        	NewChat.createMainWindow(s);
        	
        }
    }
	
	//connect to server
	public static void connect()
	{
		//JOptionPane.showMessageDialog("Working");
		try
		{
			final int PORT = 1234;
			final String HOST = "localhost";
			Socket sock = new Socket(HOST, PORT);
			System.out.println("You connected to: " + HOST);
			
			Chat = new ChatClient(sock);
			
			PrintWriter out = new PrintWriter(sock.getOutputStream());

			//print out all user name with different color
			
			//do a compare if there is a match change the color
			//DB.test.compareOnline(UserName);
			
			out.println(UserName);
			
			//clickable name to create new chat window
			LIST_ONLINE.addListSelectionListener(new ListSelectionListener() {

	            @Override
	            public void valueChanged(ListSelectionEvent evt) {
	                jList1ValueChanged(evt);
	            }
	        });

			out.flush();
			
			Thread x = new Thread(Chat);
			x.start();
			
			//print out all of the chat history
			TEXTAREA_CONVERSATION.setText(Chat.DisplayChat());
			TEXTAREA_CONVERSATION.setLineWrap(true);
			TEXTAREA_CONVERSATION.setWrapStyleWord(true);
			}
		catch(Exception x)
		{
			System.out.print(x);
			JOptionPane.showMessageDialog(null, "Server not responding.");
			System.exit(0);
		}
	}
	
	
	//Main UI clickable action
	public static void actionMainWindow()
	{
		BUTTON_SEND.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					ACTION_B_SEND();
				}
			}
		);
		
		BUTTON_DISCONNECT.addActionListener(
			new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					ACTION_B_DISCONNECT();
				}
			}
		);
		
		BUTTON_CONNECT.addActionListener(
			new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				BuildLogInWindow();
			}
		}
	);
	}
	//loginwindow UI
	public static void BuildLogInWindow()
	{
		//JOptionPane.showMessageDialog("Working");
		LoginWindow.setTitle("Login Authentification");
		LoginWindow.setSize(300,200);
		LoginWindow.setLocation(500, 280);
		LoginWindow.setResizable(false);
		
		L_EnterUserName.setBounds(10,30,150,20);
		TF_UserNameBox.setBounds(80,30,150,20);
		
		L_EnterPassword.setBounds(10,65,150,20);
		TF_PasswordBox.setBounds(80,65,150,20);

		
		P_Login = new JPanel();
		
		P_Login.add(L_EnterUserName);
		P_Login.add(TF_UserNameBox);
		
		P_Login.add(L_EnterPassword);
		P_Login.add(TF_PasswordBox);
		P_Login.add(B_Login);
		P_Login.add(B_Register);
		
		LoginWindow.add(P_Login);
		
		
		String user = TF_UserNameBox.getText();
		
		login_Action();
		register_Action();
		LoginWindow.setVisible(true);
		RegisterWindow.setVisible(false);
	
	}
	//send button function
	public static void ACTION_B_SEND()
	{
		//JOptionPane.showMessageDialog("Working");
		if(!TEXTFIELD_MESSAGE.getText().equals(""))
		{
			Chat.SEND(TEXTFIELD_MESSAGE.getText());
			TEXTFIELD_MESSAGE.requestFocus();
		}
	}
	//disconnection button function
	public static void ACTION_B_DISCONNECT()
	{
		//JOptionPane.showMessageDialog("Working");
		try
		{
			Chat.DISCONNECT();
		}
		catch(Exception Y)
		{
			Y.printStackTrace();
		}
	}
	
	//Registration button function
	public static void register_Action()
	{
		B_Register.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{BuildRegisterWindow();}
				}
		);
	}
	
	//building the registration window
	public static void BuildRegisterWindow()
	{
		RegisterWindow.setTitle("Register");
		RegisterWindow.setSize(300,200);
		RegisterWindow.setLocation(500, 280);
		RegisterWindow.setResizable(false);

		L_RegisterUserName.setBounds(10,30,150,20);
		TF_RegisterUserName.setBounds(80,30,150,20);
		
		L_RegisterPassword.setBounds(10,65,150,20);
		TF_RegisterPassword.setBounds(80,65,150,20);
		
		P_Register = new JPanel();
		P_Register.add(L_RegisterUserName);
		P_Register.add(TF_RegisterUserName);
		P_Register.add(L_RegisterPassword);
		P_Register.add(TF_RegisterPassword);
		P_Register.add(B_Confirm);
		P_Register.add(B_Cancel);
		RegisterWindow.add(P_Register);
		
		register_Close();
		LoginWindow.setVisible(false);
		RegisterWindow.setVisible(true);
	}
	
	//confirm and close button for registration window
	public static void register_Close()
	{
		//JOptionPane.showMessageDialog("Working");
		B_Confirm.addActionListener(
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						String User = TF_RegisterUserName.getText();
						String Pass = TF_RegisterPassword.getText();

						RegisterWindow.setVisible(false);
						LoginWindow.setVisible(true);
						
						if(!DB.test.CompareUser(User) && !User.equals(" "))
						{
							DB.test.StoreUser(User, Pass);
							DB.test.InputLogin();
						}
						else
						{
							if(User.equals(" "))
							{
								JOptionPane.showMessageDialog(null,"Please insert characters in user name");
							}
							else
							{
								JOptionPane.showMessageDialog(null,"User name already exist");

							}
							TF_RegisterUserName.setText("");
							TF_RegisterPassword.setText("");
							TF_UserNameBox.setText("");
							TF_PasswordBox.setText("");
							LoginWindow.setVisible(false);
							RegisterWindow.setVisible(true);
						}
					}
					
				}
			);
			
		B_Cancel.addActionListener(
				//JOptionPane.showMessageDialog("Working");
				new ActionListener()
				{
					public void actionPerformed(ActionEvent ae)
					{
						RegisterWindow.setVisible(false);
						LoginWindow.setVisible(true);
					}
				}
			);
	}
}
