package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class TipoDato(val valor: Token) {
    override fun toString(): String {
        return "${valor.darLexema()}"
    }

    fun getArbolVisual(): TreeItem<String> {
        return TreeItem("${valor.darLexema()}")
    }

}