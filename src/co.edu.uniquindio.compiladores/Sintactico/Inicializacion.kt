package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class Inicializacion (var tipoDato: TipoDato, var identificador: Token, var valor: Valor?) : Sentencia() {
    override fun toString(): String {
        return "$tipoDato $identificador = $valor [${identificador.fila},${identificador.columna}]"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Inicialización")
        raiz.children.add(TreeItem("Tipo de Dato: $tipoDato"))
        raiz.children.add(TreeItem("Identificador: ${identificador.darLexema()}"))
        var valorR = TreeItem("Valor")
        if(valor!=null){
            valorR.children.add(valor?.getArbolVisual())
        }
        raiz.children.add(valorR)
        return raiz
    }

    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ) {
        tablaSimbolos.guardarSimboloVariable(identificador.darLexema(),tipoDato.toString(),ambito,identificador.fila,identificador.columna)
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        if(tipoDato?.toString() != valor!!.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)){
            erroresSemanticos.add(Error("El tipo de dato de la inicialización (${tipoDato?.toString()}) no coincide con el del valor (${valor!!.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)})",identificador.fila,identificador.columna))
        }
    }

    override fun getJavaCode(): String {
        return tipoDato.getJavaCode()+" "+identificador.getJavaCode()+" = "+valor!!.getJavaCode()+";"
    }
}