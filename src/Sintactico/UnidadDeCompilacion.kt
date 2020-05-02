package Sintactico

import javafx.scene.control.TreeItem

class UnidadDeCompilacion(var listaFunciones:ArrayList<Funcion>) {
    override fun toString(): String {
        return "UnidadDeCompilacion(listaFunciones=$listaFunciones)"
    }

    fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Unidad de Compilación")
        for (f in listaFunciones){
            raiz.children.add(f.getArbolVisual())
        }
        return raiz
    }
}