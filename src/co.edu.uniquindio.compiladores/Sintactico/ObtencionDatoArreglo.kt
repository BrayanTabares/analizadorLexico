package co.edu.uniquindio.compiladores.Sintactico
import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ObtencionDatoArreglo (var nombre: Token, var posicion: ValorNumerico ):Valor(){
    override fun toString(): String {
        return "Obtencion de un dato del arreglo ( nombre= $nombre, posicion= $posicion"
    }

    override fun getArbolVisual (): TreeItem<String> {
        var raiz = TreeItem("Obtención datos de arreglo")
        raiz.children.add(TreeItem("Identificador: ${nombre.darLexema()}"))
        raiz.children.add(TreeItem("Posicion: $posicion"))
        return raiz

    }

    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ): String {
        val tipo : String? = tablaSimbolos.buscarSimboloVariable(nombre.darLexema(),ambito)?.tipo
        if(tipo!=null) {
            return tipo
        }else{
            erroresSemanticos.add(Error("El arreglo ${nombre.darLexema()} no existe",nombre.fila,nombre.columna))
            return ""
        }
    }

}