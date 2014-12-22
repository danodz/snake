package Snake;

import java.util.Vector;
import org.lwjgl.input.Keyboard;
import util.GM;
import util.Main;
import util.Util;

public class Head 
{
    public int x;
    public int y;
    public int direction = 0;
    public int nextDirection = 0;
    public int cooldown = 0;
    public int angle;
    
    public int left;
    public int right;
    public int up;
    public int down;
    public int boost;
    
    public Boolean boosting = true;
    
    public int warpCooldownX = 4;
    public int warpCooldownY = 4;
    
    public Boolean addBody = false;
        
    public String storeImg;
    
    public Vector<Body> bodys;
    public Vector<Body> removeBodys;
    
    public Head(int x, int y, int angle, int left, int right, int up, int down, int boost)
    {
        this.x = x;
        this.y = y;
        this.angle = angle;
        this.left = left;
        this.right = right;
        this.up = up;
        this.down = down;
        this.boost = boost;
    }
    
    public void init()
    {
        bodys = new Vector();
        removeBodys = new Vector();
        
        for(int i = 0; i <=2; i++)
        {
            bodys.add(new Body(x - 25, y, String.valueOf(i)));
        }        
    }
    
    public void draw()
    {
        GM.bindTexture("snake's head");
        GM.drawTexture(x, y, 23, 23, 1, angle);
    }
    
    public void update()
    {
        if(!Keyboard.isKeyDown(boost))
            boosting = true;
        else
            boosting = false;

        if(boosting)
        {
            cooldown += 1;
        }
        else
        {
            cooldown += 2;
            Common.boostTime += 1/30f;
        }
        
        Common.playTime += 1/30f;
        
        if(cooldown >= 3)
        {
            storeImg = bodys.firstElement().img;
            bodys.remove(bodys.firstElement());
            
            direction = nextDirection;
            
            if(direction == 0)
            {
                x += 25;
                angle = 90;
                
                bodys.add(new Body(x - 25, y, storeImg));
            }

            if(direction == 1)
            {
                x -= 25;
                angle = 270;
                
                bodys.add(new Body(x + 25, y, storeImg));
            }

            if(direction == 2)
            {
                y += 25;
                angle = 0;
                
                bodys.add(new Body(x, y - 25, storeImg));
            }

            if(direction == 3)
            {
                y -= 25;
                angle = 180;
                
                bodys.add(new Body(x, y + 25, storeImg));
            }
            
            cooldown = 0;
        }
        
        if(Keyboard.isKeyDown(right) && (direction == 2 || direction == 3))
        {
            nextDirection = 0;
        }
        
        if(Keyboard.isKeyDown(left) && (direction == 2 || direction == 3))
        {
            nextDirection = 1;
        }
        
        if(Keyboard.isKeyDown(up) && (direction == 0 || direction == 1))
        {
            nextDirection = 2;
        }
        
        if(Keyboard.isKeyDown(down) && (direction == 0 || direction == 1))
        {
            nextDirection = 3;
        }
        
        for(Killer ki : Common.killers)
        {
            ki.update();
            
            if(x == ki.x && y == ki.y)
            {
                Common.death();
            }
        }
        
        for(Block bo : Common.blocks)
        {
            if(x == bo.x && y == bo.y && bo.eatable && !bo.eaten)
            {                
                bodys.add(new Body(1000, 1000, String.valueOf((int)(Math.random()*3))));
                bodys.add(new Body(1000, 1000, String.valueOf((int)(Math.random()*3))));
                bodys.add(new Body(1000, 1000, String.valueOf((int)(Math.random()*3))));
                
                bo.headThatAte = this;
                
                bo.eaten = true;
                
                bo.eatenLength = 5;                    
                
                Common.score += 1;
                
                Common.addFood = true;
            }
        }
        
        if(Common.difficulty.equals("Facile"))
        {
            warpCooldownX += 1;
            warpCooldownY += 1;
            
            if(warpCooldownX >= 4 && x == 388)
            {
                x = -362;
                warpCooldownX = 0;
            }
            
            if(warpCooldownX >= 4 && x == -387)
            {
                x = 363;
                warpCooldownX = 0;
            }
            
            if(warpCooldownY >= 4 && y == 288)
            {
                y = -262;
                warpCooldownY = 0;
            }
            
            if(warpCooldownY >= 4 && y == -287)
            {
                y = 263;
                warpCooldownY = 0;
            }
        }
    }
}
