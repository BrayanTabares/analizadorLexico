package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class InicializacionArreglo(var tipo: TipoDato?, var cantidad: ValorNumerico, var nombre: Token): ComandoArreglo(){
    override fun toString(): String {
        return "Inicializacion de un Arreglo (tipo= $tipo, cantidad= $cantidad, nombre= $nombre"
    }

    override fun getArbolVisual (): TreeItem<String> {
        var raiz = TreeItem("Declaracion de arreglo")
        raiz.children.add(TreeItem("tipo de dato: $tipo"))
        raiz.children.add(TreeItem("identificador: ${nombre.darLexema()}"))
        raiz.children.add(TreeItem("cantidad: ${cantidad.getArbolVisual()}"))
        return raiz

    }
}