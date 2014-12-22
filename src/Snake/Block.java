package Snake;

import org.lwjgl.input.Keyboard;
import util.GM;

public class Block 
{
    public int x;
    public int y;
    
    public float alpha = 0.6f;
    
    public String img;
    
    public Boolean eaten = false;
    public int eatenLength;
    
    public Boolean eatable = false;
    
    public Boolean boostingHead = false;
    
    public int countDown = 125;
    
    public Head headThatAte;
    
    public Block(int x, int y, String img)
    {
        this.x = x;
        this.y = y;
        this.img = img;
    }
    
    public void update()
    {
        GM.bindTexture("tile");
        
        if(eatable)
        {
            alpha = 1;
            GM.bindTexture("blank");
            
            if(Common.difficulty.equals("Abusif"))
            {
                if(countDown == 0 && !eaten)
                {
                    eatable = false;
                    Common.addFood = true;
                    
                    alpha = 0.6f;
                    countDown = 125;
                }
                
                countDown -= 1;
            }
        }
        
        if(eaten)
        {       
            eatenLength -= 1;
            eatable = false;
            
            if(eatenLength <= 2)
            {
                if(Common.difficulty.equals("Difficile") || Common.difficulty.equals("Abusif"))
                    Common.killers.add(new Killer(x, y));
                
                eaten = false;
                alpha = 0.6f;
            }
        }
        
        GM.drawTexture(x, y, 23, 23, 1, 0);
    }
}
