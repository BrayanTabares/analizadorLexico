package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

open class Valor : Expresion() {

    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Valor Vacío")
    }

    override fun toString(): String {
        return "Valor()"
    }
}