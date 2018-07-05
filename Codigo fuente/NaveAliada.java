import java.util.ArrayList;

/**
 * La clase NaveAliada extiende a Elemento. En ella se definirán las peculiaridades de esta nave
 * como su imagen, la velocidad a la que ha de moverse, la posición en la que ha de aparecer, 
 * los limites que ha de respetar en su movimiento así como un controlador de teclado para que
 * el usuario pueda moverla
 * 
 * @author Jesus Lopez Garcia 
 * @version 1.1
 */

public class NaveAliada extends Elemento
{

    private static final int VELOCIDAD_NAVEALIADA=4;
    
    private KeyListener teclado;
    private ArrayList<Disparo> disparosActivos;
    
    
    public NaveAliada(int centro)
    {
       setImagen("images/aliada_obiwan_m.png");
      
       //Le añadimos a este objeto un controlador de teclado, pasando como parametro la velocidad a la que queremos 
       //que se mueva la NaveAliada
       teclado=new KeyListener(VELOCIDAD_NAVEALIADA);

       //creo el arraylist para almacenar los disparos
       disparosActivos=new ArrayList<Disparo>();

       //situo la nave en el centro del alto la pantalla
       getPosicion().setY(centro);
       setVisible(false);
       
       
    }
    
    public KeyListener getTeclado()
    {
        return teclado;
    }
    
    public ArrayList<Disparo> getDisparos()
    {
        return disparosActivos;
    }
    
    //cuando llamemos a disparar, agregamos elementos al arraylist de disparos, situandolos en el centro de la nave
    public void disparar()
    {
        Disparo disparo;
        
        if (teclado.getEspacio()==1)
        {
            
            //Creamos y configuramos la posicion donde se crea el disparo
            disparo=new Disparo();
            //Coordenada X es donde acaba la NaveAliada
            disparo.getPosicion().setX(getPosicion().getX() + getImagen().getAncho());
            //Coordenada Y es la mitad de la altura de la NaveAliada menos la mitad de la altura del disparo, para que quede centrado
            disparo.getPosicion().setY(getPosicion().getY() + (getImagen().getAlto()/2) - (disparo.getImagen().getAlto()/2));
        
            disparosActivos.add(disparo);   

            teclado.setEspacio(0);                                           
        }
    }
    
    
    public void borrarDisparosActivos()
    {
        //Elimino todos los elementos del arraylist de disparos
        for (int i=0;i<disparosActivos.size();i++)
        {
                disparosActivos.remove(i);
        }
        //Reinicializo la coleccion para asegurar que todos los elementos sean borrados
        disparosActivos=new ArrayList<Disparo>();
    }
    
    public void mover()
    {
        int dx=getTeclado().getDx();
        int dy=getTeclado().getDy();  
        
        
        if (getPosicion().getX()+dx<=(getLimiteDerecho()-getImagen().getAncho())&&
            getPosicion().getX()+dx>=0)
        {
            getPosicion().setX(getPosicion().getX()+dx);
        }
        
        if (getPosicion().getY()+dy<=(getLimiteInferior()-getImagen().getAlto())&&
            getPosicion().getY()+dy>=0)
        {  
            getPosicion().setY(getPosicion().getY()+dy);
        }
        
    }

}
