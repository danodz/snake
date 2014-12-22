package util;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.File;
import java.util.Vector;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.lwjgl.opengl.GL11.*;

public class GM 
{
    public static HashMap<String,Texture> imgs;
    
    public static final int ACTIVECAMERA = 0x01;

    
    public static float height;
    public static float width;
    public static float imgHeight;
    public static float imgWidth;
    
    public static void draw()
    {
        glTranslated(0.0f, 0.0f, -1000);
    }
    
    public static void bindTexture(String img)
    {
       try
       {
           imgs.get(img).bind();
           height = imgs.get(img).getHeight();
           width = imgs.get(img).getWidth();

           imgHeight = imgs.get(img).getImageHeight();
           imgWidth = imgs.get(img).getImageWidth();
           
           glTexParameteri( GL_TEXTURE_2D , GL_TEXTURE_WRAP_S , GL_CLAMP );
           glTexParameteri( GL_TEXTURE_2D , GL_TEXTURE_WRAP_T , GL_CLAMP );
           
           glTexParameteri( GL_TEXTURE_2D , GL_TEXTURE_MAG_FILTER , GL_NEAREST );
           glTexParameteri( GL_TEXTURE_2D , GL_TEXTURE_MIN_FILTER , GL_NEAREST );
       }
       catch (Exception ex)
       {
           System.out.println("binding exception img : " + img);
       }
    }
    
    public static Texture loadImgJar(String location)
    {
        try
        {
            return TextureLoader.getTexture("PNG", GM.class.getResourceAsStream("/"+location) );
        }
        catch (IOException ex) 
        {
            Logger.getLogger(GM.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public static void init()
    {
        imgs = new HashMap();
        
        loadImgs();
        
        /*
        File f = new File("data");
        File[] fs = f.listFiles();
        String name;
        
        for( int i = 0 ; i < fs.length ; i++ )
        {
            name = fs[i].toString().split("/")[ fs[i].toString().split("/").length -1 ];

            name = name.split(".png")[0];
            
            if ( name.charAt(0) != '.' )
            {
                imgs.put( name , loadImg( fs[i].toString() ) );
            }
        }
        */
    }
    
    public static void loadImgs()
    {
    	int loadTech = 0;
        
        if ( loadTech == 0 )
        {
            try
            {
                CodeSource src = GM.class.getProtectionDomain().getCodeSource();

                if( src != null )
                {
                    URL jar = src.getLocation();

                    ZipInputStream zip = new ZipInputStream( jar.openStream());
                    ZipEntry ze;
                    
                    while( ( ze = zip.getNextEntry() ) != null )
                    {
                        String entryName = ze.getName();

                        String[] parts = entryName.split("/");

                        if ( parts.length > 2 && parts[ 0 ].equals("Data") &&  parts[ 1 ].equals("imgs") )
                        {
                            String name = entryName.toString().split("/")[ entryName.toString().split("/").length -1 ];

                            name = name.split(".png")[0];

                            if ( name.charAt(0) != '.' )
                            {
                                imgs.put( name , loadImgJar( entryName ) );
                            }
                        }
                    }

                }
                else
                    System.out.println("faild");
            }
            catch( Exception e )  { System.out.println( e ); }

            try
            {
                URL url = GM.class.getResource("/Data/imgs/");
                File f = null;

                f = new File( url.toURI() );

                File[] fs = f.listFiles();
                String name;
                for( int i = 0 ; i < fs.length ; i++ )
                {
                    name = fs[i].toString().split("/")[ fs[i].toString().split("/").length -1 ];

                    name = name.split(".png")[0];

                    if ( name.charAt(0) != '.' )
                    {
                        imgs.put( name , loadImg( fs[i].toString() ) );
                    }
                }
            }
            catch( Exception e ) {  }
        
        }
        else if ( loadTech == 1 )
        {
            try
            {
                CodeSource src = GM.class.getProtectionDomain().getCodeSource();

                if( src != null )
                {
                    URL jar = src.getLocation();

                    ZipInputStream zip = new ZipInputStream( jar.openStream());
                    ZipEntry ze;

                    while( ( ze = zip.getNextEntry() ) != null )
                    {
                        String entryName = ze.getName();

                        String[] parts = entryName.split("/");

                        if ( parts.length == 4 &&  parts[ 1 ].equals("imgs") )
                        {
                            String name = entryName.toString().split("/")[ entryName.toString().split("/").length -1 ];

                            name = name.split(".png")[0];

                            if ( name.charAt(0) != '.' )
                            {
                                imgs.put( name , loadImgJar( entryName ) );
                            }
                        }
                    }

                }
                else
                    System.out.println("faild");

                System.out.println("end test");
            }
            catch( Exception e )  { System.out.println( e ); }

            URL url = GM.class.getResource("/Data/imgs/");
            File f = null;

            try
            {
                f = new File( url.toURI() );
            }
            catch (URISyntaxException ex) { Logger.getLogger(GM.class.getName()).log(Level.SEVERE, null, ex); }

            File[] fs = f.listFiles();
            String name;
            for( int i = 0 ; i < fs.length ; i++ )
            {
                name = fs[i].toString().split("/")[ fs[i].toString().split("/").length -1 ];

                name = name.split(".png")[0];

                if ( name.charAt(0) != '.' )
                {
                    imgs.put( name , loadImg( fs[i].toString() ) );
                }
            }
        }
    }
    
    public static Texture loadImg(String location)
    {
       try 
       {
           return TextureLoader.getTexture("PNG", new FileInputStream(location));
       }
       catch (IOException ex) 
       {
           return null;
       }
    }
    
    public static void drawTexture(float x , float y , float alpha , float angle , int bits)
    {
       if (( bits & ACTIVECAMERA ) > 0)
           drawTexture( x, y, imgWidth , imgHeight , alpha , angle );
       else
           drawTexture( x , y , imgWidth , imgHeight , alpha , angle );
    }

    public static void drawTexture(float x, float y, float h, float l,float alpha, float angle)
    {
        glColor4f(1, 1, 1, alpha);

        glPushMatrix();
        
        glTranslatef(x, y, 0.0f);
        glRotatef(angle, 0.0f, 0.0f, h);
        glTranslatef(-h / 2, l / 2, 0.0f);
        
        glBegin(GL_QUADS);
        // start
        
        glTexCoord2f( width , 0 );
        glVertex2f( h , 0 );
        
        glTexCoord2f( width , height );
        glVertex2f( h , -l );
        
        glTexCoord2f( 0 , height );
        glVertex2f( 0 , -l );
        
        glTexCoord2f( 0 , 0 );
        glVertex2f( 0 , 0 );
        
        // end
        glEnd();

        glPopMatrix();
    }
}
