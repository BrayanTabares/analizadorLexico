package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

class CicloWhile( var expresionL: ExpresionLogica, var sentencias: ArrayList<Sentencia>) : Iterador() {
    override fun toString(): String {
        return "CicloWhile(expresionL=$expresionL, sentencias=$sentencias)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Ciclo While")

        raiz.children.add(expresionL.getArbolVisual())

        var raizS = TreeItem("Sentencias")
        for (f in sentencias){
            raizS.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizS)

        return raiz
    }
}