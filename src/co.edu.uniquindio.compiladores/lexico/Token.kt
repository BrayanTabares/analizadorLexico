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

        if (categoria == Categoria.DATO_ENTERO) {
            return "int"
        }
        if (categoria == Categoria.DATO_BOOLEAN){
            return "boolean"
        }
        if (categoria == Categoria.DATO_CARACTER){
            return "char"
        }
        if (categoria == Categoria.DATO_REAL){
            return "float"
        }
        if (categoria == Categoria.DATO_STRING){
            return "String"
        }
        if (categoria == Categoria.CONDICION_If) {
            return "if"
        }
        if (categoria == Categoria.CONDICION_ELSE){
            return "else"
        }
        if (categoria == Categoria.SWITCH){
            return "switch"
        }
        if (categoria == Categoria.CASO){
            return "case"
        }
        if (categoria == Categoria.BOOLEAN){
            return if (lexema == "wahr"){
                "true"
            }else {
                "false"
            }
        }
        if (categoria == Categoria.DECIMAL){
            return lexema.replace("'", ".")
        }
        if (categoria == Categoria.CADENA) {
            return lexema.replace("*","\"")
        }
        if (categoria == Categoria.BREAK){
            return "break"
        }
        if (categoria == Categoria.CICLO_WHILE){
            return "While"
        }
        if (categoria == Categoria.CARACTER){
            return lexema.replace("|","\"")
        }
        if (categoria == Categoria.CLASE){
            return "class"
        }
        /*

         */
        if (categoria == Categoria.COMENTARIO_BLOQUE){
            return lexema.replace("%", "/*")
        }
        if (categoria == Categoria.CONSTANTE){
            return "final"
        }
        if (categoria == Categoria.DOS_PUNTOS){
            return ":"
        }
        if (categoria == Categoria.OPERADOR_AGRUPACION){
            lexema.replace(">",")")
            return lexema.replace("<","(")
        }
        if (categoria == Categoria.OPERADOR_ARITMETICO){
            if (lexema == "$"){
                return "+"
            }
            if (lexema == "¬"){
                return "-"
            }
            if (lexema == "_"){
                return "/"
            }
            if (lexema == "."){
                return "*"
            }
        }
        if (categoria == Categoria.OPERADOR_ASIGNACION){
            return "="
        }
        if (categoria == Categoria.OPERADOR_LOGICO){
            if (lexema == "##"){
                return "&&"
            }
            if (lexema == "@@"){
                return "||"
            }
            if (lexema == "/"){
                return "!"
            }
        }
        if (categoria == Categoria.OPERADOR_TERMINAL){
            return ";"
        }
        if (categoria == Categoria.OPERADOR_RELACIONAL){
            if (lexema == "=~"){
                return "=="
            }
            if (lexema == "/~"){
                return "!="
            }
            if (lexema == "«"){
                return "<"
            }
            if (lexema == "«~"){
                return "<="
            }
            if (lexema == "»"){
                return ">"
            }
            if (lexema == "»~"){
                return ">="
            }
        }
        if (categoria == Categoria.IDENTIFICADOR){
            return lexema.substring(1,lexema.length)
        }
        if(categoria==Categoria.OPERADOR_INCREMENTO){
            return "++"
        }
        if(categoria==Categoria.OPERADOR_DECREMENTO){
            return "--"
        }
        if(categoria==Categoria.ENTERO){
            return lexema
        }
        if(categoria==Categoria.DECIMAL){
            return lexema.replace("'",".")
        }

        return "[Sin Traducción:+${categoria.name}]"
    }
}