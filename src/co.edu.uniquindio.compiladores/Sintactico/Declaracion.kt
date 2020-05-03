package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class Declaracion (var tipoDato:TipoDato, var listaIdentificadores : ArrayList<Token>) : Sentencia() {
    override fun toString(): String {
        return "Declaracion(tipoDato=$tipoDato, listaIdentificadores=$listaIdentificadores)"
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
}