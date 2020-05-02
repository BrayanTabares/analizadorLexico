package Sintactico

import Mundo.Token

class ValorBooleano(var valor: Token) : Valor(){
    override fun toString(): String {
        return "ValorBooleano(valor=$valor)"
    }
}