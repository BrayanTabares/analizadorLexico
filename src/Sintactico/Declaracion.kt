package Sintactico

import Mundo.Token

class Declaracion (var tipoDato:Token, var listaIdentificadores : ArrayList<Token>) : Sentencia() {
}