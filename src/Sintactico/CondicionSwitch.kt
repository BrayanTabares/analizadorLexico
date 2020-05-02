package Sintactico

class CondicionSwitch(var expresion: Expresion, var listaCasos: ArrayList<Caso>) : Condicion() {
    override fun toString(): String {
        return "CondicionSwitch(expresion=$expresion, listaCasos=$listaCasos)"
    }
}