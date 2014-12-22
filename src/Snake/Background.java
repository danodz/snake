package Snake;

import java.util.HashMap;
import java.util.Vector;
import org.newdawn.slick.Color;
import util.GM;

public class Background 
{    
    public static void init()
    {   
        for(int x = -15; x < 15; x++)
        {
            for(int y = -11; y < 11; y++)
            {
                 Common.blocks.add(new Block(x * 25 + 13, y * 25 + 13, String.valueOf((int)(Math.random()*3))));
            }
        }
    }
    
    public static void update()
    {
        for(int i = 0; i < 460; i += 25)
        {
            GM.bindTexture("grid");
            GM.drawTexture(i, 0, 2, 700, 1, 0);
            GM.drawTexture(-i, 0, 2, 700, 1, 0);
            GM.drawTexture(0, i, 900, 2, 1, 0);
            GM.drawTexture(0, -i, 900, 2, 1, 0);
        }
        
        GM.bindTexture("blank");
        GM.drawTexture(375, 0, 5, 550, 1, 0);
        GM.drawTexture(-375, 0, 5, 550, 1, 0);
        GM.drawTexture(0, 275, 755, 5, 1, 0);
        GM.drawTexture(0, -275, 755, 5, 1, 0);
        
        GM.drawTexture(-302, 313, Common.fonts[0].getWidth("pointage : " + Common.score) + 24, 33, 1, 0);
        GM.drawTexture(302, 313, Common.fonts[0].getWidth("record : " + MainMenu.highScoreE) + 24, 33, 1, 0);

        GM.bindTexture("black");
        GM.drawTexture(-302, 313, Common.fonts[0].getWidth("pointage : " + Common.score) + 20, 29, 1, 0);
        GM.drawTexture(302, 313, Common.fonts[0].getWidth("record : " + MainMenu.highScoreE) + 20, 29, 1, 0);
        
        Common.drawString(-365, 322, String.valueOf("pointage : " + Common.score), Color.white, 0);
        
        if(Common.difficulty.equals("Facile"))
            Common.drawString(250, 322, String.valueOf("record : " + MainMenu.highScoreE), Color.white, 0);
        
        if(Common.difficulty.equals("Medium"))
            Common.drawString(250, 322, String.valueOf("record : " + MainMenu.highScoreM), Color.white, 0);
        
        if(Common.difficulty.equals("Difficile"))
            Common.drawString(250, 322, String.valueOf("record : " + MainMenu.highScoreH), Color.white, 0);
        
        if(Common.difficulty.equals("Abusif"))
            Common.drawString(250, 322, String.valueOf("record : " + MainMenu.highScoreI), Color.white, 0);
    }
}