package Sintactico

class ValorRelacional(var expresion: ExpresionRelacional) : Valor(){
    override fun toString(): String {
        return "ValorRelacional(expresion=$expresion)"
    }
}