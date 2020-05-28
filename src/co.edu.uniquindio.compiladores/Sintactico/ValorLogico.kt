package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

open class ValorLogico (val valor : Valor) : Valor() {

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Valor Lógico")
        raiz.children.add(valor.getArbolVisual())
        return raiz
    }
}