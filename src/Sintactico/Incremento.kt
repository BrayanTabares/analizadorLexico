package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem

class Incremento(var nombre: Token,var tipoIncremento: Token) : Sentencia() {
    override fun toString(): String {
        return "Incremento(nombre=$nombre, tipoIncremento=$tipoIncremento)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("${nombre.darLexema()}"+" "+"${tipoIncremento.darLexema()}")
    }
}