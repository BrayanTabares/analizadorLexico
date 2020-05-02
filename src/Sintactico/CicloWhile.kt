package Sintactico

class CicloWhile( var expresionL: ExpresionLogica, var sentencias: ArrayList<Sentencia>) : Iterador() {
    override fun toString(): String {
        return "CicloWhile(expresionL=$expresionL, sentencias=$sentencias)"
    }
}