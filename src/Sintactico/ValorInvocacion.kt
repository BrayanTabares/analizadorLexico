package Sintactico

import Mundo.Token

class ValorInvocacion (var identificador: Token, var listaArgumentos: ArrayList<Argumento>) : Valor() {
    override fun toString(): String {
        return "ValorInvocacion(identificador=$identificador, listaArgumentos=$listaArgumentos)"
    }
}