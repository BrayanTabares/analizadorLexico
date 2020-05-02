package Sintactico

class Retorno(var expresion: Expresion) : Sentencia() {
    override fun toString(): String {
        return "Retorno(expresion=$expresion)"
    }
}