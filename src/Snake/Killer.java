package Snake;

import util.GM;

public class Killer 
{
    public int x;
    public int y;
    
    public Killer(int x, int y)
    {
        this.x = x;
        this.y = y;
    }
    
    public void update()
    {
        GM.bindTexture("tete");
        GM.drawTexture(x, y, 23, 23, 1, 0);
        
        for(Block bl : Common.blocks)
        {
            if(bl.x == x && bl.y == y && bl.eatable && bl.eatenLength != 10)
            {
                Common.removeBlocks.add(bl);
                Common.addFood = true;
            }
        }
    }
}
