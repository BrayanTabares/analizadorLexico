package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

class Caso(var expresion: Expresion, var contenido: ContenidoCaso?) {
    override fun toString(): String {
        return "Caso(expresion=$expresion, contenido=$contenido)"
    }

    fun getArbolVisual(): TreeItem<String>? {
        var raiz : TreeItem<String> = TreeItem("Caso")

        raiz.children.add(expresion.getArbolVisual())

        if(contenido!=null){
            raiz.children.add(contenido?.getArbolVisual())
        }

        return raiz
    }
}