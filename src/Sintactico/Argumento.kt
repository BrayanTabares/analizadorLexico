package Sintactico

import javafx.scene.control.TreeItem

class Argumento (var expresion: Expresion) {
    override fun toString(): String {
        return "Argumento(expresion=$expresion)"
    }

    fun getArbolVisual(): TreeItem<String>? {
        return TreeItem("${expresion.getArbolVisual()}")
    }

}