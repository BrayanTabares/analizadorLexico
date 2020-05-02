package Sintactico

import Mundo.Token

class ExpresionRelacional(var expresion1: Expresion, var operador: Token?, var expresion2: Expresion?) : Expresion(){
    override fun toString(): String {
        return "ExpresionRelacional(expresion1=$expresion1, operador=$operador, expresion2=$expresion2)"
    }
}