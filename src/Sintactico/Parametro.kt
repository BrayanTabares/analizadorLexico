package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem

class Parametro (var tipoDato : Token, var nombre : Token ) {
    override fun toString(): String {
        return "Parametro(tipoDato=$tipoDato, nombre=$nombre)"
    }

    fun getArbolVisual(): TreeItem<String> {
        return TreeItem("${tipoDato?.darLexema()}"+" "+"${nombre.darLexema()}")
    }
}