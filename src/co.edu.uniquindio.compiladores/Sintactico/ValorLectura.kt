package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ValorLectura(var lectura: Lectura?) : Valor() {
    override fun toString(): String {
        return "ValorLectura(lectura=$lectura)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        if(lectura!=null){
            return lectura!!.getArbolVisual()
        }
        return TreeItem("Lectura Vacía")
    }

    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ): String {
        if(lectura!=null){
            return lectura!!.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
        }
       return ""
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        lectura?.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
    }

}