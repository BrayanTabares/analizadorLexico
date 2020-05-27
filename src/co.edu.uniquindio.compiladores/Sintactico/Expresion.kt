package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

open class Expresion : Sentencia() {
    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Expresión Vacía")
    }

    open fun obtenerTipo(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String):String{
        return ""
    }
}