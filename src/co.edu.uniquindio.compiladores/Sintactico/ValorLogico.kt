package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

open class ValorLogico (val valor : Valor) : Valor() {

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Valor Lógico")
        raiz.children.add(valor.getArbolVisual())
        return raiz
    }

    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ): String {
        return valor.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
    }
}