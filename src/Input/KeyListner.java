package Input;

import java.util.HashMap;
import java.util.Vector;
import org.lwjgl.input.*;

public class KeyListner
{
    private Vector<KeyListening> classes;
    
    public HashMap<String , Key> keys;
    
    public String inputChar;
    
    public KeyListner()
    {
        classes = new Vector();
        
        keys = new HashMap();
        
        keys.put("backSpace", new Key( Keyboard.KEY_BACK ) );
        keys.put("enter", new Key( Keyboard.KEY_RETURN ) );
    }
    
    public void addListener( KeyListening k )
    {
        classes.add(k);
    }
    
    public void removeListener( KeyListening k )
    {
        classes.remove(k);
    }
    
    public void voidListener()
    {
        classes.removeAllElements();
    }
    
    public void update()
    {
        while ( Keyboard.next() )
        {
            if ( (int) Keyboard.getEventCharacter() != 0 )
                inputChar = String.valueOf( Keyboard.getEventCharacter() );
            else
                inputChar = "";
            
            for ( KeyListening k : classes )
                k.getKey();
        }
        
        for ( String s : keys.keySet() )
        {
            keys.get(s).update();
        }
    }
}
