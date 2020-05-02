package Sintactico

import Mundo.Token

class Parametro (var tipoDato : Token, var nombre : Token ) {
    override fun toString(): String {
        return "Parametro(tipoDato=$tipoDato, nombre=$nombre)"
    }
}