package co.edu.uniquindio.compiladores

import co.edu.uniquindio.compiladores.lexico.AnalizadorLexico

fun main(){
    val lexico = AnalizadorLexico("8789.798974  \n hola 45")
    lexico.analizar()
    print(lexico.listaTokens)
}
