package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ValorBooleano(var valor: Token) : Valor(){
    override fun toString(): String {
        return "${valor.darLexema()}"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("${valor.darLexema()}")
    }

    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ): String {
        return "dich"
    }

    override fun getToken(): Token {
        return valor
    }

    override fun getJavaCode(): String {
        return valor.getJavaCode()
    }
}