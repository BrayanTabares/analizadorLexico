package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class InicializacionArreglo(var tipo: TipoDato?, var cantidad: ValorNumerico, var nombre: Token): ComandoArreglo(){
    override fun toString(): String {
        return "Inicializacion de un Arreglo (tipo= $tipo, cantidad= $cantidad, nombre= $nombre"
    }

    override fun getArbolVisual (): TreeItem<String> {
        var raiz = TreeItem("Inicialización de arreglo")
        raiz.children.add(TreeItem("Tipo de dato: $tipo"))
        raiz.children.add(TreeItem("Identificador: ${nombre.darLexema()}"))
        raiz.children.add(TreeItem("Cantidad de datos: $cantidad"))
        return raiz

    }

    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ) {
        tablaSimbolos.guardarSimboloVariable(nombre.darLexema(),tipo.toString(),ambito,nombre.fila,nombre.columna)
    }
}