package Sintactico

import javafx.scene.control.TreeItem

class CicloFor(var inicializacion: Inicializacion?,var expresionRelacional: ExpresionRelacional, var incremento: Incremento, var sentencias: ArrayList<Sentencia>) : Iterador(){
    override fun toString(): String {
        return "CicloFor(inicializacion=$inicializacion, expresionRelacional=$expresionRelacional, incremento=$incremento, sentencias=$sentencias)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Ciclo For")
        if(inicializacion!=null){
            raiz.children.add(inicializacion?.getArbolVisual())
        }

        raiz.children.add(expresionRelacional.getArbolVisual())

        raiz.children.add(incremento.getArbolVisual())

        var raizS = TreeItem("Sentencias")
        for (f in sentencias){
            raizS.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizS)

        return raiz
    }
}