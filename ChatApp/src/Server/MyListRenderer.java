package Server;

import java.awt.Color;
import java.awt.Component;
import java.util.HashMap;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

class MyListRenderer extends DefaultListCellRenderer
{
    private HashMap theChosen = new HashMap();

    public Component getListCellRendererComponent( JList list,
            Object value, int index, boolean isSelected,
            boolean cellHasFocus )
    {
        super.getListCellRendererComponent( list, value, index,
                isSelected, cellHasFocus );

        if( isSelected )
        {
            theChosen.put( value, "chosen" );
        }

        if( theChosen.containsKey( value ) )
        {
            setForeground( Color.red );
        }
        else
        {
            setForeground( Color.black );
        }

        return( this );
    }
}
