package Sintactico

import javafx.scene.control.TreeItem

class ValorLectura(var lectura: Lectura?) : Valor() {
    override fun toString(): String {
        return "ValorLectura(lectura=$lectura)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        if(lectura!=null){
            return lectura!!.getArbolVisual()
        }
        return TreeItem("Lectura Vacía")
    }
}