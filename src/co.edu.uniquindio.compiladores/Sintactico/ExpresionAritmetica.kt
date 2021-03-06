package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

open class ExpresionAritmetica(var expresion1: ExpresionAritmetica?, var operador: Token?, var expresion2: ExpresionAritmetica?, var valorNumerico : ValorNumerico?) : Expresion(){
    override fun toString(): String {
        if(expresion1!=null && expresion2==null) {
           return "$expresion1"
        }else if(valorNumerico!=null && expresion2==null){
           return "$valorNumerico"
        } else if(expresion1!=null && expresion2 !=null){
            return "$expresion1 $operador $expresion2"
        }else if(valorNumerico!=null && expresion2 !=null){
            return "$valorNumerico $operador $expresion2"
        }
        return "$expresion1, $operador, $expresion2, $valorNumerico)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz : TreeItem<String> = TreeItem("Expresión Aritmética")

        if(operador!=null){
            var raizO : TreeItem<String> = TreeItem("Operador ${operador?.darLexema()}")

            if(expresion1!=null){
                raizO.children.add(expresion1?.getArbolVisual())
            }

            if(valorNumerico!=null){
                raizO.children.add(valorNumerico?.getArbolVisual())
            }

            if(expresion2!=null){
                raizO.children.add(expresion2?.getArbolVisual())
            }

            raiz.children.add(raizO)
        }else{
            if(expresion1!=null){
                raiz.children.add(expresion1?.getArbolVisual())
            }

            if(valorNumerico!=null){
                raiz.children.add(valorNumerico?.getArbolVisual())
            }
        }

        return raiz
    }

    override fun obtenerTipo(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String): String {
        if(expresion1!=null && expresion2==null) {
            return expresion1!!.obtenerTipo(tablaSimbolos,erroresSemanticos,ambito)
        }else if(valorNumerico!=null && expresion2==null){
            return valorNumerico!!.obtenerTipo(tablaSimbolos, erroresSemanticos,ambito)
        } else if(expresion1!=null && expresion2 !=null){
            val ex1 : String =expresion1!!.obtenerTipo(tablaSimbolos,erroresSemanticos,ambito)
            val ex2 : String =expresion2!!.obtenerTipo(tablaSimbolos,erroresSemanticos,ambito)
            return if(ex1 == ex2){
                ex1
            }else{
                "echt"
            }
        }else if(valorNumerico!=null && expresion2 !=null){
            val ex1 : String =valorNumerico!!.obtenerTipo(tablaSimbolos,erroresSemanticos,ambito)
            val ex2 : String =expresion2!!.obtenerTipo(tablaSimbolos,erroresSemanticos,ambito)
            return if(ex1 == ex2){
                ex1
            }else{
                "echt"
            }
        }
        return ""
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        if(expresion1!=null){
            expresion1!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }
        if(expresion2!=null){
            expresion2!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }
        if(valorNumerico!=null){
            valorNumerico!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }
    }

    override fun getJavaCode(): String {
        var codigo=""
        if (expresion1!=null && expresion2 != null && operador!= null){
            return "("+expresion1!!.getJavaCode() +")"+ operador!!.getJavaCode() + expresion2!!.getJavaCode()
        }
        if (expresion1 != null && operador== null && valorNumerico== null &&  expresion2== null){
            return expresion1!!.getJavaCode()
        }
        if (expresion2!=null && valorNumerico!= null && operador!= null){
            return expresion2!!.getJavaCode() + operador!!.getJavaCode() + valorNumerico!!.getJavaCode()
        }else{
            return valorNumerico!!.getJavaCode()
        }
    }
}