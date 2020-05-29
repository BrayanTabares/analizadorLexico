package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class InvocacionFuncion(var identificador: Token, var listaArgumentos: ArrayList<Valor>) : Sentencia() {
    override fun toString(): String {
        return "${identificador.lexema}"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Invocación")

        raiz.children.add(TreeItem("Identificador: ${identificador.darLexema()}"))

        var raizA : TreeItem<String> = TreeItem("Argumentos")
        for (f in listaArgumentos){
            raizA.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizA)

        return raiz
    }

    override fun obtenerTipo(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String): String {
        var argumentos:ArrayList<String> = ArrayList()
        for(v in listaArgumentos){
            argumentos.add(v.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito))
        }
        val tipo : String? = tablaSimbolos.buscarSimboloFuncion(identificador.darLexema(),argumentos)?.tipo
        if(tipo!=null) {
            return tipo
        }else{
            erroresSemanticos.add(Error("El metodo ${identificador.darLexema()} ${argumentos.toString()} no existe o posee distintos argumentos",identificador.fila,identificador.columna))
            return ""
        }
    }
}