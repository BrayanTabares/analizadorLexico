package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem

class Inicializacion (var tipoDato: Token, var identificador: Token, var valor: Valor?) : Sentencia() {
    override fun toString(): String {
        return "Inicializacion(tipoDato=$tipoDato, identificador=$identificador, valor=$valor)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Inicialización")
        raiz.children.add(TreeItem("Tipo de Dato: ${identificador.darLexema()}"))
        raiz.children.add(TreeItem("Identificador: ${identificador.darLexema()}"))
        if(valor!=null){
            raiz.children.add(TreeItem("Valor: ${valor?.getArbolVisual()}"))
        }
        return raiz
    }
}