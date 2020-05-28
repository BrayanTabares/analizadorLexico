package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ValorCadena(var valor: Valor) : Valor() {
    override fun toString(): String {
        return "$valor"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return valor.getArbolVisual()
    }

    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ): String {
        return "fessel"
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        var tipo :String = valor.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
        if(tipo!="fessel"){
            erroresSemanticos.add(Error("El identificador ${valor.getToken().darLexema()} no corresponde a un valor cadena",valor.getToken().fila,valor.getToken().columna))
        }

    }

}