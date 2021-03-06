package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ExpresionCaracter(var caracter: Token) :Expresion(){
    override fun toString(): String {
        return "$caracter [${caracter.fila},${caracter.columna}]"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresión Carácter")

        raiz.children.add(TreeItem("Carácter: ${caracter.darLexema()}"))

        return raiz
    }

    override fun obtenerTipo(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String): String {
        return "zeichen"
    }

    override fun getJavaCode(): String {
        return caracter!!.getJavaCode()
    }
}