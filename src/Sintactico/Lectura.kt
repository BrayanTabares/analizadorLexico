package Sintactico

class Lectura (var expresion : Expresion?) : Sentencia() {
    override fun toString(): String {
        return "Lectura(expresion=$expresion)"
    }
}