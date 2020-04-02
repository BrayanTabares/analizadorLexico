/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * $Id$
 * Universidad del Quind�o (Armenia - Colombia)
 * Programa de Ingenier�a de Sistemas y Computaci�n
 *
 * Asignatura: Teor�a de Lenguajes Formales
 * Ejercicio: AnalizadorLexico
 * Autor: Leonardo A. Hern�ndez R. - Agosto 2008, sep 2013
 * Autor:
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package Interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Di�logo para mostrar los tokens
 */
public class DialogoTokens extends JDialog
{

    // -----------------------------------------------------------------
    // Atributos de Interfaz
    // -----------------------------------------------------------------

    /**
     * Es el panel usado para contener la lista
     */
    private JScrollPane scrollDesplazamiento;

    /**
     * La lista donde se muestran los tokens
     */
    private JList listaTokens;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Di�logo donde se muestran los tokens
     */
    public DialogoTokens( )
    {
        setBackground( Color.white );
        setTitle( "Tokens" );
        scrollDesplazamiento = new JScrollPane( );

        // Lista donde se almacenaran los tokens
        listaTokens = new JList( );
        listaTokens.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );

        // Scroll que desplegar� la lista de tokens
        scrollDesplazamiento.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_ALWAYS );
        scrollDesplazamiento.setViewportView( listaTokens );
        add( scrollDesplazamiento );
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Modifica la lista de tokens mostrada
     * @param vectorTokensEditados La lista con la descripci�n de los tokens que
     * se van a mostrar en la lista
     */
    public void cambiarListaTokens( ArrayList vectorTokensEditados )
    {
        listaTokens.setListData( vectorTokensEditados.toArray( ) );
    }
}
