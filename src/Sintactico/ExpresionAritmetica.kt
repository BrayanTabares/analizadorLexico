package Sintactico

import Mundo.Token

open class ExpresionAritmetica(var expresion1: ExpresionAritmetica?, var operador: Token?, var expresion2: ExpresionAritmetica?) : Expresion(){
    override fun toString(): String {
        return "ExpresionAritmetica(expresion1=$expresion1, operador=$operador, expresion2=$expresion2)"
    }
}