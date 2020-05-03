package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class Asignacion (var identificador : Token, var valor: Valor): Sentencia() {
    override fun toString(): String {
        return "Asignacion(identificador=$identificador, valor=$valor)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Asignación")
        raiz.children.add(TreeItem("identificador: ${identificador.darLexema()}"))
        raiz.children.add(valor.getArbolVisual())
        return raiz
    }

}