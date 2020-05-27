package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class CondicionIf(var expresion: ExpresionLogica, var sentencias: ArrayList<Sentencia>, var sentenciasElse: ArrayList<Sentencia>): Condicion() {
    override fun toString(): String {
        return "CondicionIf(expresion=$expresion, sentencia=$sentencias, sentenciaElse=$sentenciasElse)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Condicion If")

        raiz.children.add(expresion.getArbolVisual())

        var raizS = TreeItem("Sentencias")
        for (f in sentencias){
            raizS.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizS)

        var raizE = TreeItem("Sentencias Else")
        for (f in sentenciasElse){
            raizE.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizE)

        return raiz
    }

    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ) {
        for(s in sentencias){
            s.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, ambito)
        }
        for(s in sentenciasElse){
            s.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, ambito)
        }
    }

}