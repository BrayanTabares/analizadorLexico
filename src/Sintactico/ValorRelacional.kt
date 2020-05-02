package Sintactico

import javafx.scene.control.TreeItem

class ValorRelacional(var expresion: ExpresionRelacional) : Valor(){
    override fun toString(): String {
        return "ValorRelacional(expresion=$expresion)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return expresion.getArbolVisual()
    }
}