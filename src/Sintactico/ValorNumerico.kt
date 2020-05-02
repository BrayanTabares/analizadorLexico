package Sintactico

import Mundo.Token

class ValorNumerico(var signo: Token?,var  numero: Token) : ExpresionAritmetica(null,null,null) {
    override fun toString(): String {
        return "ValorNumerico(signo=$signo, numero=$numero)"
    }
}