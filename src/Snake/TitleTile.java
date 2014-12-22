package Snake;

import util.GM;

public class TitleTile 
{
    public int x;
    public int y;
    
    public TitleTile(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void draw()
    {
        GM.bindTexture("grid");
        GM.drawTexture(x, y, 30, 30, 1, 0);
        GM.bindTexture("blank");
        GM.drawTexture(x, y, 24, 24, 1, 0);
    }
}
