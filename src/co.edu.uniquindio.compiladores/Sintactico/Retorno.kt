package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

class Retorno(var expresion: Expresion?) : Sentencia() {
    override fun toString(): String {
        return "Retorno(expresion=$expresion)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        if(expresion!=null){
            return TreeItem("Retornar: ${expresion?.getArbolVisual()}")
        }
        return TreeItem("Retorno Vacío")
    }
}