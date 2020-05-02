package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem

class ValorBooleano(var valor: Token) : Valor(){
    override fun toString(): String {
        return "ValorBooleano(valor=$valor)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("${valor.darLexema()}")
    }
}