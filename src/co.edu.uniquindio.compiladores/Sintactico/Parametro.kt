package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class Parametro (var tipoDato : TipoDato, var nombre : Token) {
    override fun toString(): String {
        return "$tipoDato $nombre [${nombre.fila},${nombre.columna}]"
    }

    fun getArbolVisual(): TreeItem<String> {
        return TreeItem(tipoDato.toString()+" "+"${nombre.darLexema()}")
    }

    fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        ambito: String
    ) {
        tablaSimbolos.guardarSimboloVariable(nombre.darLexema(),tipoDato.toString(),ambito,nombre.fila,nombre.columna)
    }

    fun getJavaCode(): String {
        return tipoDato.getJavaCode()+" "+nombre.getJavaCode()
    }
}