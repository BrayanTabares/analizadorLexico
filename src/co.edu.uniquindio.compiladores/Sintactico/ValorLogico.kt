package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

open class ValorLogico () : ExpresionLogica(null,null,null) {

    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Valor Lógico Vacío")
    }
}