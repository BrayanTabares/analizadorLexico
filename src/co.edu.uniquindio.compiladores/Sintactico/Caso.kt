package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

class Caso(var expresion: Expresion, var contenido: ContenidoCaso?) {
    override fun toString(): String {
        return "Caso(expresion=$expresion, contenido=$contenido)"
    }

    fun getArbolVisual(): TreeItem<String>? {
        var raiz : TreeItem<String> = TreeItem("Caso")
        var raizE : TreeItem<String> = TreeItem("Expresion de Caso")
        raizE.children.add(expresion.getArbolVisual())
        raiz.children.add(raizE)

        if(contenido!=null){
            raiz.children.add(contenido?.getArbolVisual())
        }

        return raiz
    }
}