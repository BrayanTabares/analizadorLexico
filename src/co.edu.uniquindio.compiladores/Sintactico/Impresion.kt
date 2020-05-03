package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

class Impresion (var expresion : Expresion?) : Sentencia() {
    override fun toString(): String {
        return "Impresion(expresion=$expresion)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz : TreeItem<String> = TreeItem("Impresión")
        if(expresion!=null){
            raiz.children.add(expresion?.getArbolVisual())
        }
        return raiz
    }
}