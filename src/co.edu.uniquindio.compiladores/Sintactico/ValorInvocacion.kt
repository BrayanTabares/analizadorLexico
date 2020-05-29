package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ValorInvocacion(var invocacion: InvocacionFuncion) : Valor() {
    override fun toString(): String {
        return "${invocacion}"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return invocacion.getArbolVisual()
    }

    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ): String {
       return invocacion.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        invocacion.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
    }

    override fun getToken(): Token {
        return invocacion.identificador
    }

    override fun getJavaCode(): String {
        return invocacion.getJavaCode()
    }
}