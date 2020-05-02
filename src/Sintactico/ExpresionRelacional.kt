package Sintactico

import Mundo.Token

class ExpresionRelacional(var expresion1: Expresion, var operador: Token?, var expresion2: Expresion?) : Expresion(){
}