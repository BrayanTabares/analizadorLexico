package Sintactico

import Mundo.Token

class Identificador (var identificador: Token) : Valor() {
    override fun toString(): String {
        return "Identificador(identificador=$identificador)"
    }
}