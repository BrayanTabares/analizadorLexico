package Sintactico

class CicloFor(var inicializacion: Inicializacion?,var expresionRelacional: ExpresionRelacional, var incremento: Incremento, var sentencias: ArrayList<Sentencia>) : Iterador(){
    override fun toString(): String {
        return "CicloFor(inicializacion=$inicializacion, expresionRelacional=$expresionRelacional, incremento=$incremento, sentencias=$sentencias)"
    }
}