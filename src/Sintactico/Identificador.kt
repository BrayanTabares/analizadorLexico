package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem

class Identificador (var identificador: Token) : Valor() {
    override fun toString(): String {
        return "Identificador(identificador=$identificador)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Identificador: ${identificador.darLexema()}")
    }
}