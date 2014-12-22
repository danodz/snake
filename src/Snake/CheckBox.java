package Snake;

import util.GM;

public class CheckBox extends Widjet
{
    public int x;
    public int y;
    
    public CheckBox(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void draw()
    {
            GM.bindTexture("bg");
            GM.drawTexture(x, y, 32, 32, 1, 0);
            
            GM.bindTexture("black");
            GM.drawTexture(x, y, 26, 26, 1, 0);
            
            if(focus)
            {
                GM.bindTexture("check");
                GM.drawTexture(x + 10, y + 8, 34, 32, 1, 0);
            }
    }
}
