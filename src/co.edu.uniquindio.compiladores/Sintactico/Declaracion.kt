package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class Declaracion (var tipoDato:TipoDato, var listaIdentificadores : ArrayList<Token>) : Sentencia() {
    override fun toString(): String {
        return "$tipoDato $listaIdentificadores [${listaIdentificadores[0].fila},${listaIdentificadores[0].columna}]"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Declaración")
        raiz.children.add(TreeItem("Tipo de dato: $tipoDato"))

        var raizI = TreeItem("Identificadores")
        for (f in listaIdentificadores){
            raizI.children.add(TreeItem("${f.darLexema()}"))
        }
        raiz.children.add(raizI)

        return raiz 
    }

    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ) {
        for(s in listaIdentificadores){
            tablaSimbolos.guardarSimboloVariable(s.darLexema(),tipoDato.toString(),ambito,s.fila,s.columna)
        }
    }
}