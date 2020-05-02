package Sintactico

import javafx.scene.control.TreeItem

open class Valor : ValorLogico() {
    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Valor Vacío")
    }
}