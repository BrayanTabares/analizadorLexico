package Sintactico

open class ValorExpresion (var expresion: Expresion) : Valor() {
    override fun toString(): String {
        return "ValorExpresion(expresion=$expresion)"
    }
}