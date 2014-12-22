package Snake;

import util.GM;

import org.newdawn.slick.Color;
import org.newdawn.slick.TrueTypeFont;

public class Button extends Widjet
{
    public String text;
    public int padding;
    
    public int x;
    public int y;
    
    public String img = "bgBtn";
    
    public TrueTypeFont font;
    
    public int fontSize;
    
    public Button(int x, int y, String text, int padding, int fontSize)
    {
        this.text = text;
        this.padding = padding;
        
        this.x = x;
        this.y = y;
        
        font = Common.fonts[fontSize];
        this.fontSize = fontSize;
    }
    
    public void draw()
    {
        
        if(focus)
        {
            GM.bindTexture("blank");
            GM.drawTexture(x, y, font.getWidth(text) + padding + 15, font.getHeight(text) + padding + 15, 1, 0);
            
            GM.bindTexture("bgBtnFocus");
            GM.drawTexture(x, y, font.getWidth(text) + padding, font.getHeight(text) + padding, 1, 0);
        }
        else
        {
            GM.bindTexture(img);
            GM.drawTexture(x, y, font.getWidth(text) + padding, font.getHeight(text) + padding, 1, 0);
        }
        
        Common.drawString(x - font.getWidth(text)/2, y + font.getHeight(text)/2, text, Color.white, fontSize);
        
    }
}