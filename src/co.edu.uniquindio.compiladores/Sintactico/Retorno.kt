package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class Retorno(var expresion: Valor?) : Sentencia() {
    override fun toString(): String {
        return "ertrag $expresion"
    }

    override fun getArbolVisual(): TreeItem<String> {
        if(expresion!=null){
            var raiz:TreeItem<String> = TreeItem ("Retornar")
            raiz.children.add(expresion?.getArbolVisual())
            return raiz
        }
        return TreeItem("Retorno Vacío")
    }

    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ): String {
        if(expresion!=null){
           return expresion!!.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
        }
        return "void"
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        expresion?.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
    }

    override fun getJavaCode(): String {
        if(expresion!=null){
            return "return "+expresion!!.getJavaCode()+";"
        }else{
            return "return;"
        }
    }
}