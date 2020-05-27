package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
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

    fun llenarTablaSimbolos(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {

        contenido?.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, ambito)

    }
}