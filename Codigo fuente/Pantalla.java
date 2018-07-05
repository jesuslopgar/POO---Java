import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Toolkit;
import java.awt.Rectangle;

/**
 * La clase Pantalla extiende JPanel e implementa el interface Actionlistener 
 * ya que en esta clase se escucharán los eventos del teclado para hacer que 
 * la Nave Aliada se mueva y dispare. 
 * En esta clase se incluye:
 * -Pintado de los objetos existentes sobrescribiendo paint() de JPanel
 * -Logica del juego, establecemos el timer que marcará los tiempos
 * -Control de colisiones entre objetos
 * 
 * 
 * @author Jesus Lopez Garcia 
 * @version 1.1
 */


public class Pantalla extends JPanel implements ActionListener
{
    //Imagenes de los mensajes y sus posiciones
    private Imagen imagenMenu;
    private Imagen imagenVictoria;
    private Imagen imagenGameOver;
    
    private Posicion posicionMenu;
    private Posicion posicionVictoria;
    private Posicion posicionGameOver;
    
    private NaveAliada aliada;
   
    private int ancho; //Dimensiones de la pantalla de juego
    private int alto; //Dimensiones de la pantalla de juego
    
    private int dificultad; //Dificultad del juego
    
    //Coleccion que contendra las naves alienigenas A y B
    private ArrayList<NaveAlienigena> navesAlienigenas;

    Timer timer;

    public Pantalla(int ancho,int alto)
    {

        this.alto = alto;
        this.ancho = ancho;
        
        crearMensajes();

        aliada = new NaveAliada(alto/2);
        navesAlienigenas=new ArrayList<NaveAlienigena>();
        
        //Agrego el escuchador de teclado que creamos en NaveAliada, se crea alli porque necesitamos pasarle cuantos pixeles ha de avanzar la nave
        //Aunque sea de la nave, lo utilizamos tambien para las opciones del menu
        addKeyListener(aliada.getTeclado());
        
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
        
        timer = new Timer (10, this);
        timer.start();

    }
    
    //Sobreescribe el método paint de Jpanel 
    public void paint (Graphics grafico)
    {
        super.paint(grafico);
        Graphics2D grafico2 = (Graphics2D) grafico;
        
        RenderingHints rh =  new RenderingHints(RenderingHints.KEY_ANTIALIASING,
        RenderingHints.VALUE_ANTIALIAS_ON);
        
        rh.put(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
        
        grafico2.setRenderingHints(rh);
                
        //Voy dibujando los elementos que tengan a true el atributo visible
        if (imagenMenu.getVisible())
            grafico2.drawImage(imagenMenu.getImage(),posicionMenu.getX(),posicionMenu.getY(),this);
            
        if (imagenVictoria.getVisible())
            grafico2.drawImage(imagenVictoria.getImage(),posicionVictoria.getX(),posicionVictoria.getY(),this);
            
        if (imagenGameOver.getVisible())
            grafico2.drawImage(imagenGameOver.getImage(),posicionGameOver.getX(),posicionGameOver.getY(),this);
        
        if (aliada.getVisible())
            grafico2.drawImage(aliada.getImagen().getImage(),aliada.getPosicion().getX(),aliada.getPosicion().getY(),this);
        
        if (imagenVictoria.getVisible()==false&&imagenGameOver.getVisible()==false&&imagenMenu.getVisible()==false){
            ArrayList<Disparo> dis = aliada.getDisparos();
            
            //Dibujo los disparos que tenga en el arraylist
            for (int i=0;i<dis.size();i++)
            {
                Disparo d=(Disparo) dis.get(i);
                //if (d.getVisible())
                    grafico2.drawImage(d.getImagen().getImage(),d.getPosicion().getX(),d.getPosicion().getY(),this);
            
            }
            
            //Dibujo las naves alienigenas que tenga en el arraylist
            for (int i=0;i<navesAlienigenas.size();i++)
            {
                NaveAlienigena na=navesAlienigenas.get(i);
                //if (na.getVisible())
                    grafico2.drawImage(na.getImagen().getImage(),na.getPosicion().getX(),na.getPosicion().getY(),this);
            }
        }
        
        Toolkit.getDefaultToolkit().sync();
        grafico2.dispose();
  
    }
    
    
    public void actionPerformed(ActionEvent e)
    {
        if (imagenMenu.getVisible())
        {
            //leemos el teclado en el menu para establecer la dificultad del juego
            if (aliada.getTeclado().getTecla()>=1&&aliada.getTeclado().getTecla()<=4)
            {
                if (aliada.getTeclado().getTecla()==1)
                    dificultad=10;
                if (aliada.getTeclado().getTecla()==2)
                    dificultad=15;
                if (aliada.getTeclado().getTecla()==3)
                    dificultad=20;
                if (aliada.getTeclado().getTecla()==4)
                    dificultad=30;
                    
                crearNavesAlienigenas();    
                imagenMenu.setVisible(false);
                aliada.setVisible(true);
                addKeyListener(aliada.getTeclado());
            }
        }
        else if (imagenVictoria.getVisible())
        {
            victoria();
        }
        else if (imagenGameOver.getVisible())
        {
            derrota();
        }
        else
            jugar();
           
        repaint();
    
    }
    
    //Crea los objetos Imagen de los mensajes de victoria y derrota
    private void crearMensajes()
    {
        imagenMenu=new Imagen("images/menu.png");
        posicionMenu=new Posicion();
        posicionMenu.setX((ancho/2)-(imagenMenu.getAncho()/2));
        posicionMenu.setY((alto/2)-(imagenMenu.getAlto()/2));
        
        imagenVictoria=new Imagen("images/msg_victoria.png");
        posicionVictoria=new Posicion();
        posicionVictoria.setX((ancho/2)-(imagenVictoria.getAncho()/2));
        posicionVictoria.setY((alto/2)-(imagenVictoria.getAlto()/2));
        imagenVictoria.setVisible(false);
        
        imagenGameOver=new Imagen("images/msg_gameover.png");
        posicionGameOver=new Posicion();
        posicionGameOver.setX((ancho/2)-(imagenGameOver.getAncho()/2));
        posicionGameOver.setY((alto/2)-(imagenGameOver.getAlto()/2));
        imagenGameOver.setVisible(false);
    
    }
    
    //Lanza el mensaje de victoria y espera a que pulsemos la tecla C para reiniciar el juego
    private void victoria()
    {  
        aliada.setVisible(false);
        imagenVictoria.setVisible(true);
        if (aliada.getTeclado().getTecla()==10){
                reiniciar_juego();
        }
    }
    
    //Lanza el mensaje de derrota y espera a que pulsemos la tecla C para reiniciar el juego
    private void derrota()
    {
        imagenGameOver.setVisible(true);
        if (aliada.getTeclado().getTecla()==10)
        {
                reiniciar_juego();              
        }
    }
    
    //Reinicia el juego, borrando las naves alienígenas y disparos que quedasen, 
    //reubica la nave aliada en su posición original y muestra de nuevo el menú
    private void reiniciar_juego()
    {
        borrarNavesAlienigenas();

        aliada.setVisible(false);
        aliada.borrarDisparosActivos();
        aliada.getPosicion().setX(0);
        aliada.getPosicion().setY(alto/2);

        imagenVictoria.setVisible(false);
        imagenGameOver.setVisible(false);
        
        imagenMenu.setVisible(true);
    }
    
    //Tras comprobrobar las colisiones, recorre las colecciones de disparos y naves alienigenas, 
    //moviendolos si estan visibles o borrandolos si no lo están.
    private void jugar()
    {
        
        aliada.setVisible(true);
        
        //Comprobamos las colisiones para poner invisibles los objetos que hayan colisionado
        checkCollisions();
        
        ArrayList<Disparo> dis = aliada.getDisparos();
            
            //Recorremos la coleccionde disparos para si estan visibles moverlos
            //y si no eliminarlos
            for (int i=0;i<dis.size();i++)
            {
                 Disparo d=(Disparo) dis.get(i);
                 if(d.getVisible())
                     d.mover();
                 else
                     dis.remove(i);
             }
            
            //Recorremos la coleccionde naves alienigenas para si estan visibles moverlas
            //y si no eliminarlas
            if (navesAlienigenas.size()>0)
            {
                 for (int i=0;i<navesAlienigenas.size();i++)
                 {
                     NaveAlienigena na=navesAlienigenas.get(i);
                    if(na.getVisible())
                        na.mover();
                    else
                        navesAlienigenas.remove(i);
                 }
            }
            else
                victoria();   

            //aliada.mover(aliada.getTeclado().getDx(),aliada.getTeclado().getDy());            
            aliada.mover();            
            aliada.disparar();
            
            repaint();

    } 
    
    public ArrayList<NaveAlienigena> getNavesAlienigenas()
    {
        return navesAlienigenas;
    }
    
    //Atendiendo a la dificultad que indique el usuario en el menú inicial añade las 
    //NavesAlienigenas correspondientes al ArrayList navesAlienigenas. 
    //La mitad de ellas serán de tipo A y la otra mitad de tipo B.
    private void crearNavesAlienigenas()
    {
        for (int i=0;i<this.dificultad;i++)
        {
            if (i%2==0)
                navesAlienigenas.add(new NaveAlienigenaA(dificultad));
            else
                navesAlienigenas.add(new NaveAlienigenaB(dificultad));
            
        }
        
    }
    
    //Recorre el ArrayList navesAlienigenas y borra todos sus elementos.
    private void borrarNavesAlienigenas()
    {
        
        for (int i=0;i<navesAlienigenas.size();i++)
        {  
                navesAlienigenas.remove(i);   
        }
        navesAlienigenas=new ArrayList<NaveAlienigena>();
        
    }
    
    //Comprueba las colisiones entre NaveAliada, NavesAlienigenas y Disparos 
    //poniendo visibles o invisibles los que hayan colisionado
    private void checkCollisions()
    {
        
        //Ponemos invisibles los objetos que intersecten para ser borrados posteriormente en el método jugar()
        
        Rectangle rAliada;
        Rectangle rDisparo;
        Rectangle rAlien;
        
        rAliada=aliada.getBounds();
        
        //Colision de alien y Aliada
        for (int i=0;i<navesAlienigenas.size();i++)
        {
            NaveAlienigena na=navesAlienigenas.get(i);
            rAlien=na.getBounds();
            
            if (rAliada.intersects(rAlien))
            {
                aliada.setVisible(false);
                derrota();
            
            }
            
        }
        
       //Colision de alien y disparos
       ArrayList<Disparo> dis = aliada.getDisparos();
   
       for (int i=0;i<dis.size();i++)
       {
                Disparo d = (Disparo) dis.get(i);
                
                rDisparo = d.getBounds();
                
                for (int j=0;j<navesAlienigenas.size();j++)
                {
                    NaveAlienigena na=navesAlienigenas.get(j);
                    rAlien=na.getBounds();
                    
                    if (rDisparo.intersects(rAlien))
                    {
                        d.setVisible(false);
                        na.setVisible(false);
                        //dis.remove(i);
                        //navesAlienigenas.remove(j);
                    }
        
                }
       }
    }

}
