package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

open class Iterador : Sentencia() {
    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Iterador Vacío")
    }
}