package Input;

import org.lwjgl.input.*;

public class Key
{
    public Boolean isDown = false;
    
    public Boolean wasDown = false;
    
    public Boolean isClicked = false;
    
    public Boolean isPressed = false;
    
    public int id;
    
    public Key(int id)
    {
        this.id = id;
    }
    
    public void update()
    {
        wasDown = isDown;
        
        isDown = Keyboard.isKeyDown(id);
        
        isClicked = wasDown && !isDown;
        
        isPressed = isDown && !wasDown;
    }
}
