package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem

class Asignacion (var identificador : Token, var valor: Valor): Sentencia() {
    override fun toString(): String {
        return "Asignacion(identificador=$identificador, valor=$valor)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Asignación")
        raiz.children.add(TreeItem("identificador: ${identificador.darLexema()}"))
        raiz.children.add(TreeItem("Valor: ${valor.getArbolVisual()}"))
        return raiz
    }
}