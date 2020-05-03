package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

open class ValorExpresion (var expresion: Expresion) : Valor() {
    override fun toString(): String {
        return "ValorExpresion(expresion=$expresion)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return expresion.getArbolVisual()
    }
}