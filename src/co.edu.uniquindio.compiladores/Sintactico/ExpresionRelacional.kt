package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ExpresionRelacional(var expresion1: Valor?, var operador: Token, var expresion2: Valor, var expresionA : ExpresionAritmetica?) : Expresion(){
    var tablaSimbolos: TablaSimbolos = TablaSimbolos(ArrayList())
    var erroresSemanticos: ArrayList<Error> = ArrayList()
    var ambito: String = ""

    override fun toString(): String {
        return "$expresion1 $operador $expresion2"
    }

    override fun getArbolVisual(): TreeItem<String> {
        val raiz : TreeItem<String> = TreeItem("Expresión Relacional")
        val raizO : TreeItem<String> = TreeItem("Operador ${operador.darLexema()}")
        if(expresion1!=null){
            raizO.children.add(expresion1!!.getArbolVisual())
        }
        if(expresionA!=null){
            raizO.children.add(expresionA!!.getArbolVisual())
        }
        raizO.children.add(expresion2.getArbolVisual())
        raiz.children.add(raizO)
        return raiz
    }

    override fun obtenerTipo(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String): String {
        return "dich"
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        this.tablaSimbolos=tablaSimbolos
        this.erroresSemanticos=erroresSemanticos
        this.ambito=ambito
        if(expresion1!=null){
            expresion1!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }
        if(expresionA!=null){
            expresionA!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }
        expresion2.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        var exp1 : String =""
        if(expresion1!=null){
            exp1=expresion1!!.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
        }
        if(expresionA!=null){
            exp1=expresionA!!.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
        }
        val exp2 : String =expresion2.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
        if(exp1!=exp2){
            erroresSemanticos.add(Error("No se puede relacionar un valor ${exp1} con un valor ${exp2} ",expresion2.getToken().fila,expresion2.getToken().columna))
        }else if((exp1=="dich"||exp1=="fessel"||exp1.contains("rolle"))&&(operador.darLexema()!="=~" && operador.darLexema()!="/~")){
            erroresSemanticos.add(Error("Los valores de tipo ${exp1} no se pueden comparar como números",expresion2.getToken().fila,expresion2.getToken().columna))
        }
    }

    override fun getJavaCode(): String {
        val exp1 : String =expresion2.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
        if(exp1=="fessel"|| exp1.contains("rolle")){
            if(operador.darLexema()=="=~"){
                if(expresion1!=null){
                    expresion1!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
                }
                return ""+expresionA!!.getJavaCode() +".equals("+expresion2.getJavaCode()+")"
            }
            if(operador.darLexema()=="/~"){
                if(expresion1!=null){
                    "!"+expresion1!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
                }
                return "!"+expresionA!!.getJavaCode() +".equals("+expresion2.getJavaCode()+")"
            }
        }
        if(expresion1!=null){
            return "("+expresion1!!.getJavaCode() +")"+operador.getJavaCode()+expresion2.getJavaCode()
        }
        return "("+expresionA!!.getJavaCode() +")"+operador.getJavaCode()+expresion2.getJavaCode()
    }
}