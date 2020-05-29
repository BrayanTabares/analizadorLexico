package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.Simbolo
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class Identificador (var identificador: Token) : Valor() {
    override fun toString(): String {
        return "${identificador.darLexema()}"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Identificador: ${identificador.darLexema()}")
    }

    override fun obtenerTipo(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String): String {
        val simbolo : Simbolo? = tablaSimbolos.buscarSimboloVariable(identificador.darLexema(),ambito)
        if(simbolo==null || simbolo.fila > identificador.fila){
            erroresSemanticos.add(Error("El identificador ${identificador.darLexema()} no existe o no fue declarado antes",identificador.fila,identificador.columna))
            return ""
        }else {
            val tipo : String? = simbolo.tipo
            if(tipo!=null) {
                return tipo
            }
        }
       return ""
    }

    override fun getToken(): Token {
        return identificador
    }

    override fun getJavaCode(): String {
        return identificador.getJavaCode()
    }
}