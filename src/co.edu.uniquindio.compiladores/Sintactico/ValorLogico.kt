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

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        if(valor!=null){
            valor!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
            val tipo:String?=valor!!.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
            if(tipo!="dich"){
                erroresSemanticos.add(Error("El identificador ${valor.toString()} no corresponde a un valor lógico",valor.getToken().fila,valor.getToken().columna))
            }
        }
    }

}