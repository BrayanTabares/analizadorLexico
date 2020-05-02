package Sintactico

class ValorLectura (var expresion : Expresion?) : Valor() {
    override fun toString(): String {
        return "ValorLectura(expresion=$expresion)"
    }
}