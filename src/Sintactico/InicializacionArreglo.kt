package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem

class InicializacionArreglo (var tipo: Token, var cantidad: ValorNumerico, var nombre: Token): ComandoArreglo(){
    override fun toString(): String {
        return "Inicializacion de un Arreglo (tipo= $Token, cantidad= $cantidad, nombre= $nombre"
    }

    override fun getArbolVisual (): TreeItem<String> {
        var raiz = TreeItem("Declaracion de arreglo")
        raiz.children.add(TreeItem("tipo de dato: ${tipo.darLexema()}"))
        raiz.children.add(TreeItem("identificador: ${nombre.darLexema()}"))
        raiz.children.add(TreeItem("cantidad: ${cantidad.getArbolVisual()}"))
        return raiz

    }
}