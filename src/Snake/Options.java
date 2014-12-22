package Snake;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

public class Options extends SubMenu
{
    public Widjet[][] diffWidjets;
    public Widjet[][] playerWidjets;
    
    public int cursorY = 0;
    public int cursorX = 0;
    public int storeCursorY = 0;
    public int cooldown = 0;
    
    public Boolean changeDiff = true;
    
    public Options()
    {
        super();

        diffWidjets = new Widjet[4][2];
        playerWidjets = new Widjet[2][2];
        
        for(int i = 0; i < 4; i++)
        {
            diffWidjets[3-i][0] = new Button(-170, 140 - (i * 75), Common.difficultys[i], 10, 2);
            diffWidjets[3-i][1] = new CheckBox(-75, 140 - (i * 75));
        }
        
        for(int i = 0; i < 2; i++)
        {
            playerWidjets[i][0] = new Button(50 + (i * 75), 130, String.valueOf(i+1), 25, 1);
            playerWidjets[i][1] = new CheckBox(50 + (i * 75), 55);
        }
        
        for(int i = 0; i < diffWidjets.length; i ++)
        {
            if(Common.difficulty.equals(Common.difficultys[3-i]))
                diffWidjets[i][1].focus = true;
            else
                diffWidjets[i][1].focus = false;
        }
            
        for(int i = 0; i < 2; i++)
        {
            if(Common.players == i+1)
                playerWidjets[i][1].focus = true;
            else
                playerWidjets[i][1].focus = false;
        }
    }
    
    @Override
    public void draw()
    {
        super.draw();
        
        Common.drawBoundedString(30, 230, "Nombre de joueurs", 200, Color.white, 0);
        Common.drawBoundedString(-175, 230, "Sélectionnez la difficultée", 200, Color.white, 0);
        
        for(int i = 0; i < diffWidjets.length; i ++)
        {
            diffWidjets[i][0].draw();
            diffWidjets[i][1].draw();
        }
        
        for(int i = 0; i < 2; i++)
        {
            playerWidjets[i][0].draw();
            playerWidjets[i][1].draw();
        }
    }
    
    @Override
    public void update()
    {   
        cooldown += 1;
        
        if(cooldown >= 3)
        {
            if(cursorX == 0)
            {
                if(Keyboard.isKeyDown(Keyboard.KEY_UP) && cursorY != 4)
                {
                    cursorY += 1;
                    cooldown = 0;
                }

                if(Keyboard.isKeyDown(Keyboard.KEY_DOWN) && cursorY != 0)
                {
                    cursorY -= 1;
                    cooldown = 0;
                }
                storeCursorY = cursorY;
            }
            else
            {
                if(Keyboard.isKeyDown(Keyboard.KEY_UP))
                {
                    cursorY = storeCursorY;
                    cooldown = 0;
                }

                if(Keyboard.isKeyDown(Keyboard.KEY_DOWN))
                {
                    cursorY = 0;
                    cooldown = 0;
                }
            }
            
            if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && cursorX != 2)
            {
                cursorX += 1;
                cooldown = 0;
            }
            
            if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) && cursorX != 0)
            {
                cursorX -= 1;
                cooldown = 0;
            }
        }
        
        for(int i = 1; i < diffWidjets.length + 1; i ++)
        {
            if(cursorY == i && cursorX == 0)
            {
                diffWidjets[i-1][0].focus = true;
            }
            else
                diffWidjets[i-1][0].focus = false;
        }
        
        for(int i = 1; i < 3; i++)
        {
            if(cursorX == i)
                playerWidjets[i-1][0].focus = true;
            else
                playerWidjets[i-1][0].focus = false;
        }
        
        if(cursorY == 0)
        {
            for(int i = 0; i < diffWidjets.length; i ++)
                diffWidjets[i][0].focus = false;
        
            for(int i = 0; i < 2; i++)
                playerWidjets[i][0].focus = false;
            
            continu.focus = true;
        }
        else
            continu.focus = false;
        
        if(Keyboard.isKeyDown(Keyboard.KEY_RETURN))
        {
            for(int i = 0; i < diffWidjets.length; i ++)
            {
                if(diffWidjets[i][0].focus)
                {
                    Common.difficulty = Common.difficultys[3-i];
                }
                
                if(Common.difficulty.equals(Common.difficultys[3-i]))
                    diffWidjets[i][1].focus = true;
                else
                    diffWidjets[i][1].focus = false;
            }
            
            for(int i = 0; i < 2; i++)
            {
                if(playerWidjets[i][0].focus)
                {
                    Common.players = i+1;
                }
                
                if(Common.players == i+1)
                    playerWidjets[i][1].focus = true;
                else
                    playerWidjets[i][1].focus = false;
            }
        }
    }
}