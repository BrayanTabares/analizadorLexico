package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class Parametro (var tipoDato : TipoDato, var nombre : Token) {
    override fun toString(): String {
        return "Parametro(tipoDato=$tipoDato, nombre=$nombre)"
    }

    fun getArbolVisual(): TreeItem<String> {
        return TreeItem(tipoDato.toString()+" "+"${nombre.darLexema()}")
    }
}