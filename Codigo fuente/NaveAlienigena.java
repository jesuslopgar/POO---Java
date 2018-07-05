import java.util.Random;

/**
 * Esta clase será superclase de NaveAlienigenaA y NaveAlienigenaB, no podremos instanciar objetos
 * de esta pero en ella inicializaremos algunos campos de la naves como su posición utilizando 
 * dos variables aleatorias para posicionar inicialmente las naves en distintas posiciones.
 * 
 * @author Jesus Lopez Garcia 
 * @version 1.1
 */


public abstract class NaveAlienigena extends Elemento
{

    //private boolean visible;
    private Random Aleatorio;
    //constantes para espaciar las naves entre si
    //el margen en pixeles de alto para que las naves no aparezcan ni muy arriba ni muy abajo
    //el espaciado en pixeles a lo ancho para que vayan saliendo poco a poco e intentando que no se solapen
    private static final int MARGENES_ALTO=200;
    private static final int ESPACIADO_ANCHO=1500;
    
    private int velocidad;

    public NaveAlienigena(int dificultad)
    {
        if (dificultad==10)
            velocidad=2;
            
        if (dificultad==15)
            velocidad=4;
            
        if (dificultad==20)
            velocidad=5;
            
        if (dificultad==30)
            velocidad=7;
        
        Aleatorio=new Random();
        
        //Creamos dos variables aleatorias para posicionar inicialmente las naves en distintas posiciones
        //intentando que se ubiquen a lo alto de la pantalla y a lo ancho de 1500 pixeles a la derecha
        //de la pantalla, para que luego vayan apareciendo por la derecha repartidas por la pantalla
        int indiceY=Aleatorio.nextInt(getLimiteInferior()-MARGENES_ALTO);
        int indiceX=Aleatorio.nextInt(ESPACIADO_ANCHO);

        getPosicion().setX(getLimiteDerecho()+indiceX);
        getPosicion().setY(indiceY);

        setVisible(true);
    }
    
    public int getVelocidad(){
        return velocidad;
    }
    
    abstract public void mover();
    
    

}
