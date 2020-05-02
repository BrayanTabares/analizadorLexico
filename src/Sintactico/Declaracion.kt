package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem

class Declaracion (var tipoDato:Token, var listaIdentificadores : ArrayList<Token>) : Sentencia() {
    override fun toString(): String {
        return "Declaracion(tipoDato=$tipoDato, listaIdentificadores=$listaIdentificadores)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Declaración")
        raiz.children.add(TreeItem("Tipo de dato: ${tipoDato.darLexema()}"))

        var raizI = TreeItem("Identificadores")
        for (f in listaIdentificadores){
            raizI.children.add(TreeItem("${f.darLexema()}"))
        }
        raiz.children.add(raizI)

        return raiz 
    }
}