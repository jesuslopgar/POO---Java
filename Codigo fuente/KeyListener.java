import java.awt.event.KeyAdapter ;
import java.awt.event.KeyEvent ;


/**
 * La clase KeyListener extiende a KeyAdapter, crea un escuchador de eventos de teclado 
 * que controlará las teclas que han sido pulsadas y en base a ello modificara sus campos 
 * dx y dx que indican las coordenadas a las que ha de moverse la NaveAliada cada vez que 
 * se detecte una pulsación de teclas de dirección. 
 * 
 * También guarda en sus variables espacio y tecla si se ha pulsado un espacio, 
 * que lo utilizaremos para crear disparos o si se ha pulsado alguna tecla necesaria como la 'c' 
 * cuando tras una victoria o derrota queremos continuar el juego.
 * 
 * @author Jesus Lopez Garcia 
 * @version 1.1
 */


public class KeyListener extends KeyAdapter
{
    
    private int dx;
    private int dy;
    private int avance;
    private int espacio;
    private int tecla;

    //constructor para escuchador de teclado simple. Cuando no necesitamos aplicar un avance, por ejemplo en menus
    public KeyListener()
    {
        dx=0;
        dy=0; 
        espacio=0;
        tecla=0;  
    }
    
    //constructor para escuchador de teclado que aplique el avance que le pasemos por parametro. Para la nave aliada
    public KeyListener(int avance)
    {
        dx=0;
        dy=0;
        this.avance=avance;
        espacio=0;
        tecla=0;
    }
      
    public int getDx()
    {
        return dx;
    } 
    
    public int getDy()
    {
        return dy;
    } 
    
    public int getEspacio()
    {
        return espacio;
    } 
    
    public int getTecla()
    {
        return tecla;
    } 
    
    public void setEspacio(int espacio)
    {
        this.espacio=espacio;
    } 
    
    
    
    public void keyPressed(KeyEvent e)
    {
    
        int key = e.getKeyCode();
        
        if (key==KeyEvent.VK_LEFT||key==KeyEvent.VK_O){
                dx =- avance;
                
        }
        
        if (key==KeyEvent.VK_RIGHT||key==KeyEvent.VK_P){
                dx = avance;
                
        }
        
        if (key==KeyEvent.VK_UP||key==KeyEvent.VK_Q){
                dy =- avance;
        }
        
        if (key==KeyEvent.VK_DOWN||key==KeyEvent.VK_A){
                dy = avance;
        }
        
        if (key==KeyEvent.VK_1){
                tecla = 1;
        }
        
        if (key==KeyEvent.VK_2){
                tecla = 2;
        }
        
        if (key==KeyEvent.VK_3){
                tecla = 3;
        }
        
        if (key==KeyEvent.VK_4){
                tecla = 4;
        }
        
        if (key==KeyEvent.VK_C){
                tecla = 10;
        }
        
              
        espacio=0;

    }
    
    public void keyReleased(KeyEvent e)
    {
        
        int key = e.getKeyCode();
        
        if (key==KeyEvent.VK_LEFT||key==KeyEvent.VK_O){
            dx = 0;
        }
        
        if (key==KeyEvent.VK_RIGHT||key==KeyEvent.VK_P){
            dx = 0;
        }
        
        if (key==KeyEvent.VK_UP||key==KeyEvent.VK_Q){
            dy = 0;
        }
        
        if (key==KeyEvent.VK_DOWN||key==KeyEvent.VK_A){
            dy = 0;
        }
        
        if (key==KeyEvent.VK_SPACE)
            espacio=1;
        
        tecla = 0;
        
    }
     
}
