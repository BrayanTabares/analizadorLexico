package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

class ContenidoCaso( var listaSentencias: ArrayList<Sentencia>, var contenido: ContenidoCaso?, var caso: Caso? ) {
    override fun toString(): String {
        return "ContenidoCaso(listaSentencias=$listaSentencias, contenido=$contenido, caso=$caso)"
    }

    fun getArbolVisual(): TreeItem<String> {
        var raiz : TreeItem<String> = TreeItem("Caso")

        var raizS = TreeItem("Sentencias")
        for (f in listaSentencias){
            raizS.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizS)

        if(contenido!=null){
            raiz.children.add(contenido?.getArbolVisual())
        }

        if(caso!=null){
            raiz.children.add(caso?.getArbolVisual())
        }

        return raiz
    }
}