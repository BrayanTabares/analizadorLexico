package Sintactico

import Mundo.Token

class ExpresionCadena(var cadena: Token,var  valor: ArrayList<ValorCadena>) : Expresion(){
    override fun toString(): String {
        return "ExpresionCadena(cadena=$cadena, valorCadena=$valor)"
    }
}