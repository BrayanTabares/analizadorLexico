package Sintactico

import Mundo.Token

class Asignacion (var identificador : Token, var valor: Valor): Sentencia() {
    override fun toString(): String {
        return "Asignacion(identificador=$identificador, valor=$valor)"
    }
}