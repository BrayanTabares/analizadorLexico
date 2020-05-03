package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class Incremento(var nombre: Token, var tipoIncremento: Token) : Sentencia() {
    override fun toString(): String {
        return "${nombre.darLexema()}"+""+"${tipoIncremento.darLexema()}"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("${nombre.darLexema()}"+" "+"${tipoIncremento.darLexema()}")
    }
}