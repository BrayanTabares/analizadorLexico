package Sintactico

import Mundo.Token

class InvocacionFuncion (var identificador: Token, var listaArgumentos: ArrayList<Argumento>) : Sentencia() {
    override fun toString(): String {
        return "InvocacionFuncion(identificador=$identificador, listaArgumentos=$listaArgumentos)"
    }
}