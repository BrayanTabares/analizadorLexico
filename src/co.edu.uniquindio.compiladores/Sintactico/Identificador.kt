package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class Identificador (var identificador: Token) : Valor() {
    override fun toString(): String {
        return "Identificador(identificador=$identificador)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Identificador: ${identificador.darLexema()}")
    }
}