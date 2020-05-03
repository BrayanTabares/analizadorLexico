package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

class Lectura (var expresion : Expresion?) : Sentencia() {
    override fun toString(): String {
        return "Lectura(expresion=$expresion)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz:TreeItem<String> = TreeItem("Lectura")
        if(expresion!=null){
            raiz.children.add(TreeItem("${expresion?.getArbolVisual()}"))
        }
        return raiz
    }
}