import java.util.Random;

/**
 * La clase NaveAlienigenaA extiende a la superclase NaveAlienigena, agregando las características 
 * que no estaban definidas en su superclase, como una imagen específica para el tipo A, 
 * o la velocidad a la que debe moverse, que se le pasará por parámetro en su constructor.
 * 
 * @author Jesus Lopez Garcia 
 * @version 1.1
 */



public class NaveAlienigenaA extends NaveAlienigena
{

          

    public NaveAlienigenaA(int dificultad)
    {
        super(dificultad);
                     
        setImagen("images/alien_A_m.png");

    }
    
    public void mover()
    {
        //Movimiento Horizontal
        getPosicion().setX(getPosicion().getX()-getVelocidad());
                
        //CONTROL DE MARGEN ANCHO
        if ((getPosicion().getX()+getImagen().getAncho())<0)
            getPosicion().setX(getLimiteDerecho());
        
    }
    
}
