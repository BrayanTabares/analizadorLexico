package co.edu.uniquindio.compiladores.Sintactico
import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

open class ExpresionLogica(var expresion1: ExpresionLogica?, var operador: Token?, var  expresion2: ExpresionLogica?, var valor :ValorLogico?) : Expresion(){
    override fun toString(): String {
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
}