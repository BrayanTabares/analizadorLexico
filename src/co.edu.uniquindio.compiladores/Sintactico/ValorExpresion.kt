package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

open class ValorExpresion (var expresion: Expresion) : Valor() {
    override fun toString(): String {
        return "$expresion"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return expresion.getArbolVisual()
    }

    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ): String {
        return expresion.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        expresion.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
    }

}