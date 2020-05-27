package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class CondicionSwitch(var expresion: Expresion, var listaCasos: ArrayList<Caso>) : Condicion() {
    override fun toString(): String {
        return "CondicionSwitch(expresion=$expresion, listaCasos=$listaCasos)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Condicion Switch")

        raiz.children.add(expresion.getArbolVisual())

        var raizS = TreeItem("Lista Casos")
        for (f in listaCasos){
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
        for(s in listaCasos){
            s.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, ambito)
        }
    }
}