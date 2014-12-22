package Snake;

import org.newdawn.slick.Color;
import util.GM;

public class Instructions extends SubMenu
{
    public Instructions()
    {
        super();
    }
    
    @Override
    public void draw()
    {
        super.draw();
        Common.drawBoundedString(-250, 250, "Ce jeu est un jeu de type \"Snake\". Le but est "
            + "de manger la bonne nourriture sans manger de "
            + "nourriture empoisonnée. Et il faut faire bien "
            + "attention à ne pas se manger soi-même. "
            + "\n "
            + "Utilise les flèches pour déplacer le premier "
            + "joueur et appuis sur espace pour accélérer. "
            + "\n "
            + "Utilise \"wasd\" pour déplacer le second "
            + "joueur et appuis sur \"shift\" pour accélérer.", 500,Color.white, 0);
        
        GM.bindTexture("blank");
        GM.drawTexture(-150, -65, 80, 80, 1, 0);
        
        Common.drawString(-225, -125, "Bonne nourriture", Color.white, 0);
        
        GM.bindTexture("tete");
        GM.drawTexture(150, -65, 80, 80, 1, 0);
        
        Common.drawString(50, -125, "Mauvaise nourriture", Color.white, 0);
    }
}