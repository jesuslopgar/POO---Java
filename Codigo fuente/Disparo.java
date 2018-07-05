/**
 * La clase Disparo extiende a Elemento. En ella se definirán las peculiaridades 
 * del elemento Disparo como su Imagen o la velocidad a la que debe desplazarse por la pantalla 
 * 
 * @author Jesus Lopez Garcia 
 * @version 1.1
 */

public class Disparo extends Elemento
{
    
    private static final int VEL_DISPARO=8;
    
    public Disparo()
    {
        setImagen("images/laser_m.png");
        setVisible(true);
    }
    
    public void mover()
    {
        //Movimiento horizontal
        getPosicion().setX(getPosicion().getX()+VEL_DISPARO);
        
        //Cuando el disparo sale de la pantalla desaparece, para ser borrado posteriormente
        if(getPosicion().getX()>getLimiteDerecho())
            setVisible(false);
    }

}
