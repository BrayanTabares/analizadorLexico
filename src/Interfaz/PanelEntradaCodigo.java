/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quind�o (Armenia - Colombia)
 * Programa de Ingenier�a de Sistemas y Computaci�n
 *
 * Asignatura: Teor�a de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Autor del c�digo inicial: Leonardo A. Hern�ndez R. - Abril 2014, Agosto 2008, septiembre 2013
 * Autor:
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package Interfaz;
/**
 * Panel para ingresar texto al analizador l�xico
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelEntradaCodigo extends JPanel implements ActionListener
{

    // -----------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------

    private static final String VERTOKENS = "VER TOKENS";

    // -----------------------------------------------------------
    // Atributos de interfaz
    // -----------------------------------------------------------

    /**
     * Ventana principal
     */
    private InterfazAnalizadorLexico ventanaPrincipal;

    /**
     * Etiqueta c�digo
     */
    private JLabel etiquetaCodigo;

     /**
     * Campo donde se ingresa el c�digo fuente
     */
    private JTextField campoCodigo;

     /**
     * Bot�n ver tokens
     */
    private JButton botonVerTokens;

    // -----------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------

    /**
     * Constructor del panel.
     * @param principal Ventana principal. principal != null.
     */
    public PanelEntradaCodigo( InterfazAnalizadorLexico principal )
    {
        ventanaPrincipal = principal;
        setLayout( new GridLayout( 1, 3 ) );

        etiquetaCodigo = new JLabel( "C�digo fuente que se va a analizar: " );
        campoCodigo = new JTextField( 20);
        botonVerTokens = new JButton( "Ver tokens" );
        botonVerTokens.addActionListener( this );
        botonVerTokens.setActionCommand( VERTOKENS );

        add( etiquetaCodigo );
        add( campoCodigo );
        add( botonVerTokens );
    }

    // -----------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------

     /**
     * Responde ante las acciones en el panel.
     * @param e Evento que gener� la acci�n.
     */
    public void actionPerformed( ActionEvent e )
    {
//        if( e.getActionCommand( ) == VERTOKENS )
      if( e.getActionCommand( ).equals(VERTOKENS)  )
        {
        	ventanaPrincipal.verTokens(campoCodigo.getText());
        }

    }
}
