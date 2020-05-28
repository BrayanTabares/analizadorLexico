package co.edu.uniquindio.compiladores.Sintactico
import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class DeclaracionArreglo(var tipoDato: TipoDato?, var nombre: Token):ComandoArreglo(){
    override fun toString(): String {
        return "Declaracion de Arreglo (TipoDato= $tipoDato, nombre= $nombre )"
    }

    override fun getArbolVisual (): TreeItem<String>{
        var raiz = TreeItem("Declaracion de arreglo")
        raiz.children.add(TreeItem("tipo de dato: $tipoDato"))
        raiz.children.add(TreeItem("identificador: ${nombre.darLexema()}"))
        return raiz

    }

    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ) {
        tablaSimbolos.guardarSimboloVariable(nombre.darLexema(),"rolle("+tipoDato.toString()+")",ambito,nombre.fila,nombre.columna)
    }

}