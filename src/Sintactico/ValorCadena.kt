package Sintactico

import javafx.scene.control.TreeItem

class ValorCadena(var valor: Valor) {
    override fun toString(): String {
        return "ValorCadena(valor=$valor)"
    }

    fun getArbolVisual(): TreeItem<String> {
        return valor.getArbolVisual()
    }
}