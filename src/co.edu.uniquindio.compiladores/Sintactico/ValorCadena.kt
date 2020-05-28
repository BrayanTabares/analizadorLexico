package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

class ValorCadena(var valor: Valor) : Valor() {
    override fun toString(): String {
        return "$valor"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return valor.getArbolVisual()
    }
}