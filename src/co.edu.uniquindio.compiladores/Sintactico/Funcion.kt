package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class Funcion (var nombre : Token, var parametros : ArrayList<Parametro>, var tipoDato : TipoDato?, var sentencias : ArrayList<Sentencia> ) {
    override fun toString(): String {
        return "Funcion(nombre=$nombre, parametros=$parametros, tipoDato=$tipoDato, sentencias=$sentencias)"
    }

    fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Función")
        raiz.children.add(TreeItem("Nombre: ${nombre.darLexema()}"))

        var raizP = TreeItem("Parametros")
        for (f in parametros){
            raizP.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizP)

        if(tipoDato!=null){
            raiz.children.add(TreeItem("Tipo de Retorno: $tipoDato"))
        }

        var raizS = TreeItem("Sentencias")
        for (f in sentencias){
            raizS.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizS)

        return raiz
    }

    fun obtenerTiposParametros() :ArrayList<String> {
        var lista =ArrayList<String>()
        for (f in parametros){
            lista.add(f.tipoDato.toString())
        }
        return lista
    }

    fun llenarTablaSimbolos(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>) {
        tablaSimbolos.guardarSimboloFuncion(nombre.darLexema(),tipoDato.toString(),obtenerTiposParametros(),nombre.fila,nombre.columna)
        for(p in parametros){
            p.llenarTablaSimbolos(tablaSimbolos,nombre.darLexema())
        }
        for(s in sentencias){
            s.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, nombre.darLexema())
        }
    }

    fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>) {
        for(s in sentencias){
            s.analizarSemantica(tablaSimbolos, erroresSemanticos, nombre.darLexema())
        }
    }
}