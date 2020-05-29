package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class Lectura (var expresion : Valor?, var tipo : TipoDato?) : Sentencia() {
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
        if(tipo==null){
            return "fessel"
        }
        return tipo.toString()
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        expresion?.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
    }

    override fun getJavaCode(): String {
        var valor=""
        if(expresion!=null){
            valor=expresion?.getJavaCode()+"+"
        }
        var cadena= "JOptionPane.showInputDialog(null,$valor\"\")"
        if(tipo!=null){
            when(tipo.toString()){
                "ganz" -> {
                    return "Integer.parseInt($cadena);"
                }
                "echt" -> {
                    return "Double.parseDouble($cadena);"
                }
                "dich" -> {
                    return "Boolean.parseBoolean($cadena);"
                }
                "zeichen" -> {
                    return "$cadena.charAt(0);"
                }
            }
        }
        return cadena+";"
    }
}