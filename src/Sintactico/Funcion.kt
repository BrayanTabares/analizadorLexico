package Sintactico

import Mundo.Token

class Funcion (var nombre : Token, var parametros : ArrayList<Parametro>, var tipoDato : Token?, var sentencias : ArrayList<Sentencia> ) {
    override fun toString(): String {
        return "Funcion(nombre=$nombre, parametros=$parametros, tipoDato=$tipoDato, sentencias=$sentencias)"
    }
}