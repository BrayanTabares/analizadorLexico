package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ExpresionCadena(var cadena: Token, var  valor: ArrayList<ValorCadena>) : Expresion(){
    override fun toString(): String {
        return "$cadena $ $valor [${cadena.fila},${cadena.columna}]"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Expresión Cadena")

        raiz.children.add(TreeItem("Cadena: ${cadena.darLexema()}"))

        if(valor.size>0){
            var raizV : TreeItem<String> = TreeItem("Valores")
            for (f in valor){
                raizV.children.add(f.getArbolVisual())
            }
            raiz.children.add(raizV)
        }
        return raiz
    }

    override fun obtenerTipo(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String): String {
        return "fessel"
    }

    override fun getJavaCode(): String {
        var codigo = cadena.getJavaCode()
        if (valor.isNotEmpty()){
            codigo+="+"
            for (c in valor){
                codigo+= c.getJavaCode()+"+"
            }
            codigo = codigo.substring(0,codigo.length-1)
        }
        return codigo
    }
}