import java.awt.Rectangle;

/**
 * La clase Elemento es la superclase abstracta de la que heredan todos los objetos
 * que puedan dibujarse en nuestro JPanel a excepción del menú y los mensajes 
 * de victoria y derrota. Estos objetos son la NaveAliada, Disparo, NaveAlienigenaA y NaveAlienigenaB.
 * 
 * @author Jesus Lopez Garcia 
 * @version 1.1
 */

public abstract class Elemento
{

    private Posicion posicionElemento;
    private Imagen imagenElemento;
    
    private boolean visible;
    
    //limite inferior que los Elementos no deben rebasar. Es lo alto de la pantalla del equipo menos 80px para salvar la barra de tareas. 
    private static final int LIMITE_INFERIOR=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height-80;
    //limite derecho que los Elementos no deben rebasar. Este se utiliza principalmente en la NaveAliada
    private static final int LIMITE_DERECHO=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    
    
    public Elemento()
    {    
        posicionElemento = new Posicion();
        visible=true;
    }

    public Imagen getImagen()
    {
        return imagenElemento;
    }
    
    public Posicion getPosicion()
    {
        return posicionElemento;
    }
    
    public  void setImagen(String ruta)
    {
        imagenElemento = new Imagen(ruta);
    }
    
    public void setVisible(boolean estado)
    {
        visible=estado;
    }
    
    public boolean getVisible()
    {
        return visible;
    }
    
    public int getLimiteDerecho()
    {
        return LIMITE_DERECHO;
    }
    
    public int getLimiteInferior()
    {
        return LIMITE_INFERIOR;
    }
    
    //Devuelve el rectangulo asociado al elemento para comprobar las colisiones entre objetos
    public Rectangle getBounds()
    {
        int x=this.getPosicion().getX();
        int y=this.getPosicion().getY();
        int ancho=this.getImagen().getAncho();
        int alto=this.getImagen().getAlto();
        
        return new Rectangle(x,y,ancho,alto);
    
    }
    
    //El metodo mover se implementa en las subclases
    abstract public void mover();
    
    
}




