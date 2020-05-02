package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem

class ExpresionCadena(var cadena: Token,var  valor: ArrayList<ValorCadena>) : Expresion(){
    override fun toString(): String {
        return "ExpresionCadena(cadena=$cadena, valorCadena=$valor)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresión Cadena")

        raiz.children.add(TreeItem("Cadena: ${cadena.darLexema()}"))

        var raizV : TreeItem<String> = TreeItem("Valores")
        for (f in valor){
            raizV.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizV)
        return raiz
    }
}