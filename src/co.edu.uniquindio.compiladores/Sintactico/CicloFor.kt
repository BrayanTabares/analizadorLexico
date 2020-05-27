package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
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

        raiz.children.add(TreeItem("Incremento: $incremento"))

        var raizS = TreeItem("Sentencias")
        for (f in sentencias){
            raizS.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizS)

        return raiz
    }

    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ) {
        inicializacion?.llenarTablaSimbolos(tablaSimbolos,erroresSemanticos,ambito)
        for(s in sentencias){
            s.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, ambito)
        }
    }
}