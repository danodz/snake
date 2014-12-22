
package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.Object;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;

public class Util 
{
    public static String[] tilesConvertion;
    
    public static void init()
    {
        File f = new File("Data/tiles");
        File[] fs = f.listFiles();
        String name;
       
        tilesConvertion = new String[ fs.length ];
       
        for( int i = 0 ; i < fs.length ; i++ )
        {
            name = fs[i].toString().split("/")[ fs[i].toString().split("/").length -1 ];

            if ( name.contains(".png") )
            {
                name = name.split(".png")[0];
               
                String[] spl = name.split("#");
               
                tilesConvertion[ Integer.parseInt( spl[1] ) ] = spl[0];
            }
        }
    }

    public static void save(String folderName, String content)
    {
        try
        {
            FileWriter fstream = new FileWriter(folderName);
            BufferedWriter out = new BufferedWriter(fstream);
            out.write(content);
            out.close();
        }
        catch (Exception e)
        {//Catch exception if any
             System.err.println("Error: " + e.getMessage());
        }
    }
    
    public static String openFile(String path)
    {
        String chaine="";

        //lecture du fichier texte	
        try
        {
            InputStream ips=new FileInputStream(path);
            InputStreamReader ipsr=new InputStreamReader(ips);
            BufferedReader br=new BufferedReader(ipsr);
            String ligne;
            while ((ligne=br.readLine())!=null)
            {
                chaine+=ligne+"\n";
            }
            br.close();
        }		
        catch (Exception e)
        {
            System.out.println(e.toString());
        }
        
        return chaine;
    }
    
   public static boolean betweenE( float x1 , float x2 , float y )
   {
       if (( y >= x1 && y <= x2 ) || ( y <= x1 && y >= x2 ))
           return true;
       else
           return false;
   }
   
   public static boolean between( float x1 , float x2 , float y )
   {
       if (( y > x1 && y < x2 ) || ( y < x1 && y > x2 ))
           return true;
       else
           return false;
   }
   
   public static int limit( int x , int min , int max )
   {
       if ( x < min ) x = min;
       if ( x > max ) x = max;
       
       return x;
   }
   
   public static float CubDist(float x , float y , float x2 , float y2)
   {
       return (float)( Math.pow( Math.abs( x-x2 ), 2 ) + Math.pow( Math.abs(y-y2) , 2) );
   }
 
}
