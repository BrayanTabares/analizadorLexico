package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ValorLectura(var lectura: Lectura) : Valor() {
    override fun toString(): String {
        return "ValorLectura(lectura=$lectura)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return lectura.getArbolVisual()
    }

    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ): String {
        return lectura.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)

    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        lectura.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
    }

    override fun getJavaCode(): String {
        return lectura.getJavaCode()
    }

}