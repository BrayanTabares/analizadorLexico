package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.Simbolo
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class Asignacion (var identificador : Token, var valor: Valor): Sentencia() {
    override fun toString(): String {
        return "Asignacion(identificador=$identificador, valor=$valor)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Asignación")
        raiz.children.add(TreeItem("identificador: ${identificador.darLexema()}"))
        var valorR = TreeItem("Valor")
        if(valor!=null){
            valorR.children.add(valor.getArbolVisual())
        }
        raiz.children.add(valorR)
        return raiz
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        val simbolo : Simbolo? = tablaSimbolos.buscarSimboloVariable(identificador.darLexema(),ambito)
        if(simbolo==null){
            erroresSemanticos.add(Error("La variable ${identificador.darLexema()} no existe",identificador.fila,identificador.columna))
        }else if(simbolo.tipo != valor.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)){
            erroresSemanticos.add(Error("El tipo de dato de la asignación (${simbolo.tipo}) no coincide con el del valor (${valor.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)})",identificador.fila,identificador.columna))
        }
    }

}