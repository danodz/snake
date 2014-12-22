package Snake;

import java.util.Vector;
import org.lwjgl.input.Keyboard;

import java.awt.Font;
import java.io.InputStream;
 
import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.opengl.GL11.*;

 
import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.util.ResourceLoader;

public class Common 
{
    public static Vector<Block> blocks;
    
    public static Vector<Killer> killers;
    
    public static Vector<Block> removeBlocks;
    
    public static Vector<Head> heads;
      
    public static int score;
        
    public static String[] number;

    public static Boolean addFood = true;
    
    public static String gameState = "menu";
    
    public static String difficulty = "Facile";
    
    public static int players = 1;
    
    public static TrueTypeFont[] fonts;
    
    public static String[] difficultys;
    
    public static float playTime = 0;
    public static float boostTime = 0;
    
    public static void init()
    {
        fonts = new TrueTypeFont[3];
        
        Font awtFont = new Font("Helvetica", Font.PLAIN, 24);
	fonts[0] = new TrueTypeFont(awtFont, true);
        
        awtFont = new Font("Helvetica", Font.PLAIN, 48);
	fonts[1] = new TrueTypeFont(awtFont, true);
        
        awtFont = new Font("Helvetica", Font.PLAIN, 36);
	fonts[2] = new TrueTypeFont(awtFont, true);
        
        
        blocks = new Vector();
        killers = new Vector();
        removeBlocks = new Vector();
        heads = new Vector();
        
        heads.add(new Head(13, 13, 90, Keyboard.KEY_LEFT, Keyboard.KEY_RIGHT, Keyboard.KEY_UP, Keyboard.KEY_DOWN, Keyboard.KEY_SPACE));
        
        for(Head he : heads)
        {
            he.init();
        }
        
        number = new String[10];
        
        number[0] = "zero";
        number[1] = "one";
        number[2] = "two";
        number[3] = "three";
        number[4] = "four";
        number[5] = "five";
        number[6] = "six";
        number[7] = "seven";
        number[8] = "eight";
        number[9] = "nine";
        
        difficultys = new String[4];
        
        difficultys[0] = "Facile";
        difficultys[1] = "Medium";
        difficultys[2] = "Difficile";
        difficultys[3] = "Abusif";
//        MainMenu.highScoreE = Util.openFile("save.txt");
    }
    
    public static void update()
    {   
        if(addFood)
        {
            Common.blocks.elementAt((int)(Math.random() * blocks.size())).eatable = true;
            
            addFood = false;
        }
        
        for(Block bo : Common.blocks)
        {
            bo.update();
        }
        
        for(Block bl : removeBlocks)
        {
            Common.blocks.remove(bl);
        }
        removeBlocks = new Vector();
        
        for(Head he : heads)
        {
            he.update();
            he.draw();
            
            for(Body bo : he.bodys)
            {
                bo.draw();

                if(he.x == bo.x && he.y == bo.y)
                {
                    Common.death();
                }
            }
        }
        
    }
    
    public static void death()
    {
            Common.gameState = "menu";

            heads = new Vector();
            Common.killers = new Vector();
            Common.blocks = new Vector();
            
            if(players == 1)
                heads.add(new Head(13, 13, 90, Keyboard.KEY_LEFT, Keyboard.KEY_RIGHT, Keyboard.KEY_UP, Keyboard.KEY_DOWN, Keyboard.KEY_SPACE));
        
            if(players == 2)
            {
                heads.add(new Head(13, 13, 90, Keyboard.KEY_LEFT, Keyboard.KEY_RIGHT, Keyboard.KEY_UP, Keyboard.KEY_DOWN, Keyboard.KEY_SPACE));
                heads.add(new Head(13, 13+50, 90, Keyboard.KEY_A, Keyboard.KEY_D, Keyboard.KEY_W, Keyboard.KEY_S, Keyboard.KEY_LSHIFT));
            }
            
            for(Head he : heads)
            {
                he.init();
            }
            
            MainMenu.subMenu = new DeathMenu(score);
            MainMenu.lastScore = score;
            score = 0;

            if(MainMenu.lastScore > MainMenu.highScoreE && Common.difficulty.equals("Facile"))
                MainMenu.highScoreE = MainMenu.lastScore;

            if(MainMenu.lastScore > MainMenu.highScoreM && Common.difficulty.equals("Medium"))
                MainMenu.highScoreM = MainMenu.lastScore;

            if(MainMenu.lastScore > MainMenu.highScoreH && Common.difficulty.equals("Difficile"))
                MainMenu.highScoreH = MainMenu.lastScore;

            if(MainMenu.lastScore > MainMenu.highScoreI && Common.difficulty.equals("Abusif"))
                MainMenu.highScoreI = MainMenu.lastScore;
            
            Background.init();

            Common.addFood = true;
    }
    
    public static void drawString( float x , float y , String text , Color color, int size)
    {
        glPushMatrix();
        //glScalef( 1 , -1 , 1 );
        glRotatef( 180 , 1 , 0 , 0 );
        
        fonts[size].drawString( x, -y, text , color);
        
        glPopMatrix();
    }
    
    public static void drawBoundedString(int x, int y, String text, int width, Color color, int size)
    {
        String[] splittedTxt = text.split(" ");
        String tempTxt = "";
        
        for(int i = 0; i < splittedTxt.length; i++)
        {
            if(splittedTxt[i].equals("\n"))
            {
                drawString(x, y, tempTxt, color, size);
                y -= fonts[size].getHeight(tempTxt) + 25;
                tempTxt = "";
            }
            else if(fonts[size].getWidth(tempTxt + splittedTxt[i]) <= width)
            {
                tempTxt += splittedTxt[i] + " ";
            }
            else
            {
                drawString(x, y, tempTxt, color, size);
                y -= fonts[size].getHeight(tempTxt) + 3;
                tempTxt = splittedTxt[i] + " ";
            }
        }
        drawString(x, y, tempTxt, color, size);
    }
}