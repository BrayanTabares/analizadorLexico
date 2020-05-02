package Sintactico

class CondicionIf (var sentencia: ArrayList<Sentencia>, var sentenciaElse: ArrayList<Sentencia>): Condicion() {
    override fun toString(): String {
        return "CondicionIf(sentencia=$sentencia, sentenciaElse=$sentenciaElse)"
    }
}