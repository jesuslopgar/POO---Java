 import javax.swing.JFrame;

/**
 * La clase  Rtype es la clase que contiene el método public static void main(String[] args).
 * Será la clase encargada de lanzar el interface de usuario IGU.
 * 
 * @author Jesus Lopez Garcia 
 * @version 1.1
 */

public class RType extends JFrame
{
    
    private static final int ANCHO_PANTALLA=java.awt.Toolkit.getDefaultToolkit().getScreenSize().width;
    private static final int ALTO_PANTALLA=java.awt.Toolkit.getDefaultToolkit().getScreenSize().height-50;
    
    public RType()
    {
        add(new Pantalla(ANCHO_PANTALLA,ALTO_PANTALLA));

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(ANCHO_PANTALLA,ALTO_PANTALLA);
        setLocationRelativeTo(null);
        setTitle("R-Type StarWars Edition");
        setVisible(true);
        setResizable(false);
   
    }
    
    public static void main(String[] args)
    {
        new RType();
    }

    
}
