 package Snake;

import java.util.Vector;
import util.GM;

public class Body 
{
    public int x;
    public int y;
        
    public String img;
    
    public Body( int x, int y, String img)
    {
        this.x = x;
        this.y = y;
        this.img = img;
    }
    
    public void draw()
    {
        GM.bindTexture("blank");
        GM.drawTexture(x, y, 23, 23, 1, 0);
    }
}
