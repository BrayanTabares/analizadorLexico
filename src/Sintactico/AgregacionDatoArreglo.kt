package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem

class AgregacionDatoArreglo (var nombre: Token, var posicion: ValorNumerico,var valor: Valor): ComandoArreglo(){
    override fun toString(): String {
        return "Agregacion de dato a un arreglo (nombre= $nombre, posicion= $posicion, valor= $valor"
    }

     override fun getArbolVisual (): TreeItem<String> {
        var raiz = TreeItem("Declaracion de arreglo")
        raiz.children.add(TreeItem("posicion: ${posicion.getArbolVisual()}"))
        raiz.children.add(TreeItem("identificador: ${nombre.darLexema()}"))
        raiz.children.add(TreeItem("valor a agregar: ${valor.getArbolVisual()}"))
        return raiz

    }

}