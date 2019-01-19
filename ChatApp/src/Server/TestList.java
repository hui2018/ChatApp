package Server;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class TestList extends JFrame
{ 
	Node head = new Node(null, null);
    public TestList()
    {
        super( "Test List" );
        String[] data = { "Frodo",
                          "Sam",
                          "Merry",
                          "Pippin",
                          "Gandalf",
                          "Legolas",
                          "Gimli",
                          "Aragorn",
                          "Boromir" };
        JList list = new JList(data);
        list.setCellRenderer( new MyListRenderer() );
        JPanel p = new JPanel();
        JScrollPane sp = new JScrollPane( list );
        p.add( sp );
        getContentPane().add( p );
        //addWindowListener( new ExitHandler() );
        setSize( 300, 300 );
        setVisible( true );
    }
  
    public static void main( String[] arg )
    {
        new TestList();
    }
    
    
}