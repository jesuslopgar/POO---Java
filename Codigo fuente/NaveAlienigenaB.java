import java.util.Random;

/**
 * La clase NaveAlienigenaB  al igual que NaveAlienigenaA extiende a la superclase NaveAlienigena,
 * agregando las características que no estaban definidas en su superclase, como una imagen específica
 * para el tipo B, o la velocidad a la que debe moverse, que se le pasará por parámetro en su constructor.
 * 
 * @author Jesus Lopez Garcia 
 * @version 1.1
 */


public class NaveAlienigenaB extends NaveAlienigena
{

    
    private static final int FREC_CAMBIO=50; //Cada cuantos pixeles cambiara la nave de direccion
    
    
    Random Aleatorio;
    private int direccionV;//Direccion vertical. 0 sigue de frente, 1 sube, 2 baja.
    private int contador;
    
     
    public NaveAlienigenaB(int dificultad)
    {
        super(dificultad);
            
        Aleatorio=new Random();
        
        setImagen("images/alien_B_m.png"); 
        direccionV=Aleatorio.nextInt(3);
        contador=FREC_CAMBIO;
        
    }
    
    
    public void mover()
    {
        
        //Movimiento horizontal
        //this.getPosicion().setX(this.getPosicion().getX()-this.velocidad);
        getPosicion().setX(getPosicion().getX()-getVelocidad());
        
        int direccion=Aleatorio.nextInt(100);
            
        //Nave B Puede moverse de arriba a abajo aleatoriamente. Controla el movimiento vertical para que la nave no tiemble 
        //la variable contador hace que solo cambie de direccion cada tantos pixeles como se establezcan en frec_cambio
        if(contador==0)
        {
            direccionV=Aleatorio.nextInt(3);
            contador=FREC_CAMBIO;
        }
                        
        if (direccionV==2)
            getPosicion().setY(getPosicion().getY()+getVelocidad());
            
        if (direccionV==1)
            getPosicion().setY(getPosicion().getY()-getVelocidad());
            
            
        //CONTROL DE MARGEN  ANCHO
        if ((getPosicion().getX()+getImagen().getAncho())<0)
            getPosicion().setX(getLimiteDerecho());
        
        //CONTROL DE MARGEN NAVE B - ALTO 
        if (getPosicion().getY()<0)
        {
            getPosicion().setY(0);
            direccionV=Aleatorio.nextInt(3);
        }
        
        if ((getPosicion().getY()+getImagen().getAlto())>getLimiteInferior())
        {
            getPosicion().setY(getLimiteInferior()-getImagen().getAlto());
            direccionV=Aleatorio.nextInt(3);
        }
            
        contador--; 
        
    }
    
}
