package co.edu.uniquindio.compiladores.Sintactico
import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

open class ExpresionLogica(var expresion1: ExpresionLogica?, var operador: Token?, var  expresion2: ExpresionLogica?, var valor :ValorLogico?) : Expresion(){
    override fun toString(): String {
        if(expresion1!=null && expresion2==null) {
            return "$expresion1"
        }else if(valor!=null && expresion2==null){
            return "$valor"
        } else if(expresion1!=null && expresion2 !=null){
            return "$expresion1 $operador $expresion2"
        }else if(valor!=null && expresion2 !=null){
            return "$valor $operador $expresion2"
        } else if(expresion2 !=null){
            return "$operador $expresion2"
        }
        return "ExpresionLogica(expresion1=$expresion1, operador=$operador, expresion2=$expresion2)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz : TreeItem<String> = TreeItem("Expresión Lógica")

        if(operador!=null){
            var raizO : TreeItem<String> = TreeItem("Operador ${operador?.darLexema()}")

            if(expresion1!=null){
                raizO.children.add(expresion1?.getArbolVisual())
            }

            if(expresion2!=null){
                raizO.children.add(expresion2?.getArbolVisual())
            }

            if(valor!=null){
                raizO.children.add(valor?.getArbolVisual())
            }

            raiz.children.add(raizO)
        }else{
            if(expresion1!=null){
                raiz.children.add(expresion1?.getArbolVisual())
            }
            if(valor!=null){
                raiz.children.add(valor?.getArbolVisual())
            }
        }

        return raiz
    }

    override fun obtenerTipo(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String): String {
        return "dich"
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        if(expresion1!=null){
            expresion1!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }
        if(expresion2!=null){
            expresion2!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        }
        if(valor!=null){
            valor!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
            if(valor!!.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)!="dich"){
                erroresSemanticos.add(Error("El identificador ${valor!!.getToken().darLexema()} no corresponde a un valor lógico",valor!!.getToken().fila,valor!!.getToken().columna))
            }
        }
    }

    override fun getJavaCode(): String {
        if(expresion1!=null && expresion2==null) {
            return expresion1!!.getJavaCode()
        }else if(valor!=null && expresion2==null){
            return valor!!.getJavaCode()
        } else if(expresion1!=null && expresion2 !=null){
            return expresion1!!.getJavaCode()+" "+operador!!.getJavaCode()+" "+ expresion2!!.getJavaCode()
        }else if(valor!=null && expresion2 !=null){
            return valor!!.getJavaCode()+" "+operador!!.getJavaCode()+" "+ expresion2!!.getJavaCode()
        } else if(expresion2 !=null){
            return operador!!.getJavaCode()+" "+ expresion2!!.getJavaCode()
        }
        return "ExpresionLogica(expresion1=$expresion1, operador=$operador, expresion2=$expresion2)"
    }
}