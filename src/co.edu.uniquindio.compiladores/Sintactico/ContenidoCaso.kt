package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ContenidoCaso( var listaSentencias: ArrayList<Sentencia>, var contenido: ContenidoCaso?, var caso: Caso? ) {
    override fun toString(): String {
        return "$listaSentencias, $contenido, $caso"
    }

    fun getArbolVisual(): TreeItem<String> {
        var raiz : TreeItem<String> = TreeItem("Contenido Caso")

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

    fun llenarTablaSimbolos(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        for(s in listaSentencias){
            s.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, "$ambito $this")
        }
    }

    fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        contenido?.analizarSemantica(tablaSimbolos, erroresSemanticos, "$ambito $this")
        caso?.analizarSemantica(tablaSimbolos, erroresSemanticos, "$ambito $this")
        for(s in listaSentencias){
            s.analizarSemantica(tablaSimbolos, erroresSemanticos, "$ambito $this")
        }
    }
}