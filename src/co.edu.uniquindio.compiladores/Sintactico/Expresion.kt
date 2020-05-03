package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

open class Expresion : Sentencia() {
    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Expresión Vacía")
    }
}