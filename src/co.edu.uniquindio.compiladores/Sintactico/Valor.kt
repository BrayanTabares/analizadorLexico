package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Categoria
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

open class Valor : Expresion() {

    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("Valor Vacío")
    }

    override fun toString(): String {
        return "Valor()"
    }

    open fun getToken ():Token {
        return Token("",Categoria.CADENA,0,0)
    }

}