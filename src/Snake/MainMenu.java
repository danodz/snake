package Snake;

import java.util.Vector;
import org.lwjgl.input.Keyboard;
import util.GM;
import util.Main;

public class MainMenu 
{
    public static int y = 250;
    
    public static int diffSelect = 1;
    
    public static int cooldown;
    public static int subMenuCooldown;
    
    public static int lastScore;
    public static int highScoreE;
    public static int highScoreM;
    public static int highScoreH;
    public static int highScoreI;
    
    public static int cursor = 0;
    
    public static Widjet[] widjets;
    
    public static SubMenu subMenu;
    
    public static float animCount = 0;
    public static Vector <TitleTile> titleTiles;
    
    public static Boolean removeDeath = false;
    
    public static void init()
    {
        widjets = new Widjet[4];
        
        widjets[0] = new Button(-250, -75, "Jouer", 20, 1);
        widjets[1] = new Button(250, -75, "Instructions", 20, 1);
        widjets[2] = new Button(-250, -200, "Statistiques", 20, 1);
        widjets[3] = new Button(250, -200, "Options", 20, 1);
        
        titleTiles = new Vector();
        
        //s
        tileRow(-266, 275, 4, false);
        titleTiles.add(new TitleTile(-350, 247));
        tileRow(-350, 219, 4, true);
        titleTiles.add(new TitleTile(-266, 191));
        tileRow(-266, 163, 4, false);
        
        //n
        tileCollumn(-227, 163, 5, false);
        for(int i = 0; i < 5; i++)
            titleTiles.add(new TitleTile(-199 + 14*i, 275 - 28*i));
        tileCollumn(-115, 163, 5, false);
        
        //a
        tileCollumn(-76, 163, 5, false);
        tileRow(-48, 275, 2, true);
        tileCollumn(8, 275, 5, true);
        tileRow(-48, 219, 2, true);
        
        //k
        tileCollumn(47, 275, 5, true);
        titleTiles.add(new TitleTile(75, 219));
        titleTiles.add(new TitleTile(103, 247));
        titleTiles.add(new TitleTile(131, 275));
        titleTiles.add(new TitleTile(103, 191));
        titleTiles.add(new TitleTile(131, 163));
        
        //e
        tileCollumn(170, 275, 5, true);
        tileRow(199, 275, 3, true);
        tileRow(199, 219, 3, true);
        tileRow(199, 163, 3, true);
        
        //!
        tileCollumn(340, 275, 3, true);
        titleTiles.add(new TitleTile(340, 163));
        
        //Ligne en bas
        tileRow(-340, 50, 24, true);
    }
    
    public static void draw()
    {
        GM.bindTexture("bg");
        GM.drawTexture(0, 0, Main.screenWidth, Main.screenHeight , 1, 0);
        
        for(int i = 0; i < widjets.length; i++)
        {
            widjets[i].draw();
        }
        
        for(int i = 0; i <= titleTiles.indexOf(titleTiles.lastElement()); i++)
        {
            if(i <= animCount)
            {
                if(i + 1 <= titleTiles.indexOf(titleTiles.lastElement()))
                {
                    GM.bindTexture("snake's head");
                    GM.drawTexture(titleTiles.elementAt(i + 1).x, titleTiles.elementAt(i + 1).y, 30, 30, 1, 0);
                }
                titleTiles.elementAt(i).draw();
            }
        }
        
        if(animCount > titleTiles.indexOf(titleTiles.lastElement()) - 1)
        {
            GM.bindTexture("snake's head");
            GM.drawTexture(335, 50, 30, 30, 1, 0);
        }
        
        if(animCount <= titleTiles.indexOf(titleTiles.lastElement()))
            animCount += 0.5;
        
        if(subMenu != null)
        {
            subMenu.draw();
        }
    }
    
    public static void update()
    {   
        if(subMenuCooldown > 0)
            subMenuCooldown -= 1;
        
        if(subMenu != null)
        {
            subMenu.update();
            if((Keyboard.isKeyDown(Keyboard.KEY_ESCAPE) || (Keyboard.isKeyDown(Keyboard.KEY_RETURN) && subMenu.continu.focus)) && subMenuCooldown <= 0 || removeDeath)
            {
                subMenu = null;
                subMenuCooldown = 20;
                removeDeath = false;
            }
        }
        
        for(int i = 0; i < widjets.length; i++)
        {
            if(i == cursor)
                widjets[i].focus = true;
            else
                widjets[i].focus = false;
        }
        
        if(subMenu == null)
            cursorControl();
        
        if(Keyboard.isKeyDown(Keyboard.KEY_RETURN) && subMenuCooldown <= 0 && subMenu == null)
        {
            if(widjets[0].focus)
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
            
            if(widjets[1].focus)
            {
                subMenuCooldown = 20;
                subMenu = new Instructions();
            }
            
            if(widjets[2].focus)
            {
                subMenuCooldown = 20;
                subMenu = new Statistiques();
            }
            
            if(widjets[3].focus)
            {
                subMenuCooldown = 20;
                subMenu = new Options();
            }
        }
        
        //Util.save("save.txt", String.valueOf(highScoreE) + "\n" + String.valueOf(highScoreM) + "\n" 
          //      + String.valueOf(highScoreH) + "\n" + String.valueOf(highScoreI));
    }
    
    public static void cursorControl()
    {
        cooldown += 1;
        
        if(cooldown >= 3)
        {
            if(Keyboard.isKeyDown(Keyboard.KEY_RIGHT) && cursor != 3 && cursor != 1)
            {
                cursor += 1;
                cooldown = 0;
            }

            if(Keyboard.isKeyDown(Keyboard.KEY_LEFT) && cursor != 0 && cursor != 2)
            {
                cursor -= 1;
                cooldown = 0;
            }

            if(Keyboard.isKeyDown(Keyboard.KEY_UP) && cursor != 0 && cursor != 1)
            {
                cursor -= 2;
                cooldown = 0;
            }

            if(Keyboard.isKeyDown(Keyboard.KEY_DOWN) && cursor != 2 && cursor != 3)
            {
                cursor += 2;
                cooldown = 0;
            }
        }
    }
    
    public static void tileRow(int x, int y, int length, Boolean fromLeft)
    {
        for(int i = 0; i < length; i++)
        {
            if(fromLeft)
                titleTiles.add(new TitleTile(x + 28*i, y));
            else
                titleTiles.add(new TitleTile(x - 28*i, y));
        }
    }
    
    public static void tileCollumn(int x, int y, int length, Boolean fromTop)
    {
        for(int i = 0; i < length; i++)
        {
            if(fromTop)
                titleTiles.add(new TitleTile(x, y - 28*i));
            else
                titleTiles.add(new TitleTile(x, y + 28*i));
        }
    }
}