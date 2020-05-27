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
        val tipo : String? = tablaSimbolos.buscarSimboloVariable(identificador.darLexema(),ambito)?.tipo
        if(tipo!=null) {
            return tipo
        }else{
            erroresSemanticos.add(Error("El identificador ${identificador.darLexema()} no existe",identificador.fila,identificador.columna))
            return ""
        }
    }
}