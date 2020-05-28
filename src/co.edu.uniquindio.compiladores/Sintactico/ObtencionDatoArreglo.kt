package co.edu.uniquindio.compiladores.Sintactico
import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ObtencionDatoArreglo (var nombre: Token, var posicion: ValorNumerico ):Valor(){
    override fun toString(): String {
        return "${nombre.darLexema()}"
    }

    override fun getArbolVisual (): TreeItem<String> {
        var raiz = TreeItem("Obtención datos de arreglo")
        raiz.children.add(TreeItem("Identificador: ${nombre.darLexema()}"))
        raiz.children.add(posicion.getArbolVisual())
        return raiz

    }

    override fun obtenerTipo(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ): String {
        val tipo : String? = tablaSimbolos.buscarSimboloVariable(nombre.darLexema(),ambito)?.tipo
        if(tipo!=null) {
            if(tipo.contains("rolle")){
                return tipo.substring(6,tipo.length-1)
            }else{
                erroresSemanticos.add(Error("El identificador ${nombre.darLexema()} no corresponde a un arreglo",nombre.fila,nombre.columna))
            }
        }else{
            erroresSemanticos.add(Error("El arreglo ${nombre.darLexema()} no existe",nombre.fila,nombre.columna))
        }
        return ""+tipo
    }

    override fun getToken(): Token {
        return nombre
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        posicion.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
    }

}