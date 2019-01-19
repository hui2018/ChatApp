package Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class PrivateChat {
		private static ChatClient Chat;
		private static Private pvt;
		
	//Client interface
		public static JFrame MainWindow = new JFrame();
		private static JButton BUTTON_SEND = new JButton();
		private static JLabel LABEL_MESSAGE = new JLabel();
		public static JTextField TEXTFIELD_MESSAGE = new JTextField();
		private static JLabel LABEL_CONVERSATION = new JLabel();
		public static JTextArea TEXTAREA_CONVERSATION = new JTextArea();
		private static JScrollPane SCROLL_CONVERSATION = new JScrollPane();
		
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
		}
		public static void ACTION_B_SEND()
		{
			if(!TEXTFIELD_MESSAGE.getText().equals(""))
			{
				pvt.SEND(TEXTFIELD_MESSAGE.getText());
				TEXTFIELD_MESSAGE.requestFocus();
			}
		}
		public static void createMainWindow(String Name)
		{
			
			MainWindow.setTitle(Name + "'s Chat box");
			MainWindow.setSize(450, 500);
			MainWindow.setLocation(220, 180);
			MainWindow.setResizable(false);
			configMainWindow();
			actionMainWindow();
			connect();
			MainWindow.setVisible(true);	
		}
		
		public static void connect()
		{
			try
			{
				final int PORT = 1234;
				final String HOST = "localhost";
				Socket sock = new Socket(HOST, PORT);
				System.out.println("You connected to: " + HOST);
				
				Chat = new ChatClient(sock);
				
				PrintWriter out = new PrintWriter(sock.getOutputStream());

				out.flush();
				
				Thread x = new Thread(Chat);
				x.start();

				}
			catch(Exception x)
			{
				System.out.print(x);
				JOptionPane.showMessageDialog(null, "Server not responding.");
				System.exit(0);
			}
		}
		
		
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
			
		}
}

