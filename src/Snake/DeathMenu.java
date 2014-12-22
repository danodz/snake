package Snake;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;
import util.GM;

public class DeathMenu extends SubMenu
{
    public Button[] buttons;
    public int highScore;
    public int score;
    
    public DeathMenu(int score)
    {
        buttons = new Button[2];
        
        buttons[0] = new Button(-150, -200, "Rejouer", 15, 1);
        buttons[1] = new Button(150, -200, "Menu", 15, 1);
        
        buttons[0].focus = true;
        
        continu.focus = false;
        
        if(Common.difficulty.equals("Facile"))
            highScore = MainMenu.highScoreE;
        
        if(Common.difficulty.equals("Medium"))
            highScore = MainMenu.highScoreM;
        
        if(Common.difficulty.equals("Difficile"))
            highScore = MainMenu.highScoreH;
        
        if(Common.difficulty.equals("Abusif"))
            highScore = MainMenu.highScoreI;
        
        this.score = score;
    }
    
    @Override
    public void draw()
    {
        GM.bindTexture("bgSubMenu");
        GM.drawTexture(0, 0, 800, 800, 1, 0);
        
        for(int i = 0; i < 2; i++)
            buttons[i].draw();
        
        Common.drawBoundedString(0 - Common.fonts[1].getWidth("Fin de la partie")/2, 240, "Fin de la partie", 500, Color.white, 1);
        Common.drawBoundedString(0 - Common.fonts[2].getWidth("Avec un pointage de : " + String.valueOf(score))/2, 125, "Avec un pointage de : " + String.valueOf(score), 500, Color.white, 2);
        Common.drawBoundedString(0 - Common.fonts[2].getWidth("Le record à battre est de : " + String.valueOf(highScore))/2, 25, "Le record à battre est de : " + String.valueOf(highScore), 500, Color.white, 2);
    }
    
    @Override
    public void update()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && !buttons[1].focus)
        {
            buttons[0].focus = false;
            buttons[1].focus = true;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) && !buttons[0].focus)
        {
            buttons[0].focus = true;
            buttons[1].focus = false;
        }
        
        if(Keyboard.isKeyDown(Keyboard.KEY_RETURN))
        {
            if(buttons[0].focus)
            {
                Common.gameState = "playing";
                
                if(!Common.difficulty.equals("Facile"))
                {
                    for(int x = -16; x < 16; x++)
                        Common.killers.add(new Killer(x * 25 + 13, 288));

                    for(int x = -16; x < 16; x++)
                        Common.killers.add(new Killer(x * 25 + 13, -287));

                    for(int y = -12; y < 12; y++)
                        Common.killers.add(new Killer(388, y * 25 + 13));

                    for(int y = -12; y < 12; y++)
                        Common.killers.add(new Killer(-387, y * 25 + 13));
                }
            }
            
            if(buttons[1].focus)
            {
                MainMenu.removeDeath = true;
            }
        }
    }
}
