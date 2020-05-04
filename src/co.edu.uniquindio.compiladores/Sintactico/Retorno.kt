package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

class Retorno(var expresion: Expresion?) : Sentencia() {
    override fun toString(): String {
        return "Retorno(expresion=$expresion)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        if(expresion!=null){
            var raiz:TreeItem<String> = TreeItem ("Retornar")
            raiz.children.add(expresion?.getArbolVisual())
            return raiz
        }
        return TreeItem("Retorno Vacío")
    }
}