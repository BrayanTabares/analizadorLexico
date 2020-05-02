package Sintactico

import javafx.scene.control.TreeItem

open class Condicion : Sentencia() {
    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Condición Vacía")
    }
}