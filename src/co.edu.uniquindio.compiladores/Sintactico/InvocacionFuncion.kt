package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class InvocacionFuncion(var identificador: Token, var listaArgumentos: ArrayList<Valor>) : Sentencia() {
    override fun toString(): String {
        return "InvocacionFuncion(identificador=$identificador, listaArgumentos=$listaArgumentos)"
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

    fun obtenerTipo(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String): String {
        val tipo : String? = tablaSimbolos.buscarSimboloVariable(identificador.darLexema(),ambito)?.tipo
        if(tipo!=null) {
            return tipo
        }else{
            erroresSemanticos.add(Error("El metodo ${identificador.darLexema()} no existe",identificador.fila,identificador.columna))
            return ""
        }
    }
}