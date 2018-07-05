
/**
 * La clase Posicion ha sido creada debido a que todos los elementos del juego
 * ya sean naves o disparos o mensajes tienen una posición asignada
 * 
 * @author Jesus Lopez Garcia 
 * @version 1.1
 */

public class Posicion
{

    private int x;
    private int y;


    public Posicion()
    {
        x = 0;
        y = 0;
    }
    
  
    public void setX(int x) 
    {
        this.x=x;
    }
    
    public void setY(int y) 
    {
        this.y=y;
    }
    
    public int getX() 
    {
        return x;
    }
    
    public int getY() 
    {
        return y;
    }

    
}
