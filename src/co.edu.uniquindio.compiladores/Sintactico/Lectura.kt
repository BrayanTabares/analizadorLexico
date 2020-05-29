package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class Lectura (var expresion : Valor?) : Sentencia() {
    override fun toString(): String {
        return "lessen <$expresion>"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz:TreeItem<String> = TreeItem("Lectura")
        if(expresion!=null){
            raiz.children.add(expresion?.getArbolVisual())
        }
        return raiz
    }

    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ): String {
        return "fessel"
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        expresion?.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
    }
}