package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ObtencionArreglo(var tipo: TipoDato?, var cantidad: ValorNumerico): Valor(){
    override fun toString(): String {
        return "$tipo <$cantidad>"
    }

    override fun getArbolVisual (): TreeItem<String> {
        var raiz = TreeItem("Inicialización de arreglo")
        raiz.children.add(TreeItem("Tipo de dato: $tipo"))
        var c = TreeItem("Asignación")
        c.children.add(cantidad.getArbolVisual())
        raiz.children.add(c)
        return raiz

    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        cantidad.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
    }

    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ): String {
        return "rolle("+tipo.toString()+")"
    }
}