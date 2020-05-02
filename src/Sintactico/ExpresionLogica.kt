package Sintactico

import Mundo.Token

open class ExpresionLogica(var expresion1: ExpresionLogica?, var operador: Token?,var  expresion2: ExpresionLogica?) : Expresion(){
}