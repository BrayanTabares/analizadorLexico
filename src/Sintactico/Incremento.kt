package Sintactico

import Mundo.Token

class Incremento(var nombre: Token,var tipoIncremento: Token) : Sentencia() {
    override fun toString(): String {
        return "Incremento(nombre=$nombre, tipoIncremento=$tipoIncremento)"
    }
}