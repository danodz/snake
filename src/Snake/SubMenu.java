package Snake;

import org.lwjgl.input.Keyboard;
import util.GM;

public class SubMenu 
{
    public Button continu;
    
    public SubMenu()
    {
        continu = new Button(0, -200, "Retour", 20, 1);
        continu.focus = true;
    }
    
    public void draw()
    {
        GM.bindTexture("bgSubMenu");
        GM.drawTexture(0, 0, 800, 800, 1, 0);
        
        continu.draw();
    }
    
    public void update()
    {
        
    }
}