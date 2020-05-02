package Sintactico

import Mundo.Token

class ExpresionCaracter(var caracter: Token) :Expresion(){
    override fun toString(): String {
        return "ExpresionCaracter(caracter=$caracter)"
    }
}