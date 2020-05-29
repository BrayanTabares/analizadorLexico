package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class InicializacionArreglo(var tipo: TipoDato?, var nombre: Token, var cantidad: ValorNumerico,var tipo2: TipoDato? ): ComandoArreglo(){
    override fun toString(): String {
        return "$tipo $nombre {} = $cantidad [${nombre.fila},${nombre.columna}]"
    }

    override fun getArbolVisual (): TreeItem<String> {
        var raiz = TreeItem("Inicialización de arreglo")
        raiz.children.add(TreeItem("Tipo de dato: $tipo"))
        raiz.children.add(TreeItem("Identificador: ${nombre.darLexema()}"))
        var c = TreeItem("Asignación")
        c.children.add(tipo2?.getArbolVisual())
        c.children.add(cantidad.getArbolVisual())
        raiz.children.add(c)
        return raiz

    }

    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ) {
        tablaSimbolos.guardarSimboloVariable(nombre.darLexema(),"rolle("+tipo.toString()+")",ambito,nombre.fila,nombre.columna)
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        cantidad.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
        if(tipo?.valor?.darLexema()!=tipo2?.valor?.darLexema()){
            erroresSemanticos.add(Error("El tipo del arreglo de la inicialización ${tipo?.valor?.darLexema()} no es compatible con el del valor (${tipo2?.valor?.darLexema()})",nombre.fila,nombre.columna))
        }
    }
}