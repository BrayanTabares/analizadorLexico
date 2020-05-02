package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem

open class ExpresionAritmetica(var expresion1: ExpresionAritmetica?, var operador: Token?, var expresion2: ExpresionAritmetica?) : Expresion(){
    override fun toString(): String {
        return "ExpresionAritmetica(expresion1=$expresion1, operador=$operador, expresion2=$expresion2)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz : TreeItem<String> = TreeItem("Expresión Aritmética")

        if(operador!=null){
            var raizO : TreeItem<String> = TreeItem("Operador ${operador?.darLexema()}")

            if(expresion1!=null){
                raizO.children.add(expresion1?.getArbolVisual())
            }

            if(expresion2!=null){
                raizO.children.add(expresion2?.getArbolVisual())
            }

            raiz.children.add(raizO)
        }else{
            if(expresion1!=null){
                raiz.children.add(expresion1?.getArbolVisual())
            }
        }

        return raiz
    }
}