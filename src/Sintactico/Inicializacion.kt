package Sintactico

import Mundo.Token

class Inicializacion (var tipoDato: Token, var identificador: Token, var valor: Valor?) : Sentencia() {
    override fun toString(): String {
        return "Inicializacion(tipoDato=$tipoDato, identificador=$identificador, valor=$valor)"
    }
}