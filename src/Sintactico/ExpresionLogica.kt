package Sintactico

import Mundo.Token

open class ExpresionLogica(var expresion1: ExpresionLogica?, var operador: Token?,var  expresion2: ExpresionLogica?) : Expresion(){
    override fun toString(): String {
        return "ExpresionLogica(expresion1=$expresion1, operador=$operador, expresion2=$expresion2)"
    }
}