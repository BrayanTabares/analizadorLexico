package Sintactico

class Caso(var expresion: Expresion, var contenido: ContenidoCaso?) {
    override fun toString(): String {
        return "Caso(expresion=$expresion, contenido=$contenido)"
    }
}