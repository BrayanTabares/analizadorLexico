package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem

class InvocacionFuncion (var identificador: Token, var listaArgumentos: ArrayList<Argumento>) : Sentencia() {
    override fun toString(): String {
        return "InvocacionFuncion(identificador=$identificador, listaArgumentos=$listaArgumentos)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Invocación")

        raiz.children.add(TreeItem("Identificador: ${identificador.darLexema()}"))

        var raizA : TreeItem<String> = TreeItem("Argumentos")
        for (f in listaArgumentos){
            raiz.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizA)

        return raiz
    }
}