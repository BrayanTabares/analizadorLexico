package Sintactico

import Mundo.Token

class Declaracion (var tipoDato:Token, var listaIdentificadores : ArrayList<Token>) : Sentencia() {
    override fun toString(): String {
        return "Declaracion(tipoDato=$tipoDato, listaIdentificadores=$listaIdentificadores)"
    }
}