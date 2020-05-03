package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Token
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
            raiz.children.add(tipoDato?.getArbolVisual())
        }

        var raizS = TreeItem("Sentencias")
        for (f in sentencias){
            raizS.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizS)

        return raiz
    }
}