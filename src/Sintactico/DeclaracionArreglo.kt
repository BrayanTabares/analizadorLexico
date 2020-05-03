package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem


class DeclaracionArreglo (var tipoDato: Token, var nombre: Token):ComandoArreglo(){
    override fun toString(): String {
        return "Declaracion de Arreglo (TipoDato= $tipoDato, nombre= $nombre )"
    }

    override fun getArbolVisual (): TreeItem<String>{
         var raiz = TreeItem("Declaracion de arreglo")
         raiz.children.add(TreeItem("tipo de dato: ${tipoDato.darLexema()}"))
         raiz.children.add(TreeItem("identificador: ${nombre.darLexema()}"))
         return raiz

    }

}