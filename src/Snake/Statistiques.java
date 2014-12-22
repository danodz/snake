package Snake;

import org.newdawn.slick.Color;
import util.GM;

public class Statistiques extends SubMenu
{
    public String playTime = "";
    public String boostTime = "";
    
    public Statistiques()
    {
        super();
        
        if(Common.playTime >= 3600)
        {
            playTime += (int)(Common.playTime/3600) + "h, ";
        }
        
        if(Common.playTime >= 60)
        {
            playTime += (int)((Common.playTime%3600)/60) + "min, ";
        }
        
        playTime += (int)(Common.playTime%60) + "sec";
        
        if(Common.boostTime >= 3600)
        {
            boostTime += (int)(Common.boostTime/3600) + "h, ";
        }
        
        if(Common.boostTime >= 60)
        {
            boostTime += (int)((Common.boostTime%3600)/60) + "min, ";
        }
        
        boostTime += (int)(Common.boostTime%60) + "sec";
    }
    
    @Override
    public void draw()
    {
        super.draw();
        
        GM.bindTexture("bgBtnFocus");
        GM.drawTexture(-150, 100, 200, 250, 1, 0);//bg highScore
        GM.drawTexture(130, 175, 250, 100, 1, 0);//bg playTime
        GM.drawTexture(130, 15, 250, 125, 1, 0);//bg boostTime
        
        Common.drawBoundedString(-240, 215, "Meilleur Pointage"
            + " \n "
            + "Facile :     "
            + " \n "
            + "Medium :     "
            + " \n "
            + "Difficile :  "
            + " \n "
            + "Abusif :     ",
            1000, Color.white, 0);
        
        Common.drawBoundedString(-120, 215, " \n "
            + String.valueOf(MainMenu.highScoreE)
            + " \n "
            + String.valueOf(MainMenu.highScoreM)
            + " \n "
            + String.valueOf(MainMenu.highScoreH)
            + " \n "
            + String.valueOf(MainMenu.highScoreI),
            1000, Color.white, 0);
        
        Common.drawBoundedString(20, 215, "Temps de jeu total"
                + " \n "
                + playTime, 300, Color.white, 0);
        
        Common.drawBoundedString(20, 65, "Temps d'utilisation de l'accélérateur"
                + " \n "
                + boostTime, 300, Color.white, 0);
    }
}