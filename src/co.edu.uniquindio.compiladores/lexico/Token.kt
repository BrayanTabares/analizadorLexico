package co.edu.uniquindio.compiladores.lexico

import co.edu.uniquindio.compiladores.lexico.Categoria

class Token(var lexema:String, var categoria: Categoria, var fila:Int, var columna:Int) {

    override fun toString(): String {
        return "Token(lexema='$lexema', categoria=$categoria, fila=$fila, columna=$columna)"
    }

    fun darTipo(): Categoria{
        return categoria
    }

    fun darLexema(): String{
        return lexema
    }

    fun getJavaCode() :String {
        if(categoria==Categoria.CADENA){
            return lexema.replace("*","\"")
        }
        if(categoria==Categoria.DATO_ENTERO){
            return "int"
        }
        if(categoria==Categoria.DATO_REAL){
            return "double"
        }
        if(categoria==Categoria.DATO_BOOLEAN){
            return "boolean"
        }
        if(categoria==Categoria.DATO_CARACTER){
            return "char"
        }
        if(categoria==Categoria.DATO_STRING){
            return "String"
        }
        if(categoria==Categoria.IDENTIFICADOR){
            return lexema.substring(1)
        }
        if(categoria==Categoria.OPERADOR_INCREMENTO){
            return "++"
        }
        if(categoria==Categoria.OPERADOR_DECREMENTO){
            return "--"
        }
        return "[Sin Traducción:+${categoria.name}]"
    }
}