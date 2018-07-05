import java.awt.Graphics2D;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;

/**
 * La clase Imagen ha sido creada ya que todos los elementos del juego 
 * ya sean naves, disparos o mensajes, tienen una imagen asignada.
 * Esta clase nos permite crear objetos de tipo imagen desde la ruta que le indiquemos. 
 * 
 * @author Jesus Lopez Garcia 
 * @version 1.1
 */


public class Imagen
{
    private int alto;
    private int ancho;

    private Image png;//icono
    private boolean visible;
    

    public Imagen(String ruta)
    {

        ImageIcon ii = new ImageIcon(this.getClass().getResource(ruta));
        png = ii.getImage();
        
        visible=true;
        
    }

    
    public int getAncho() 
    {
        return png.getWidth(null);
    }
    
    public int getAlto() 
    {
        return png.getHeight(null);
    }
    
    public boolean getVisible() 
    {
        return visible;
    }
    
    public void setVisible(boolean visible) 
    {
        this.visible=visible;
    }
    
    public Image getImage()
    {
        return png;
    }
    
    
}
