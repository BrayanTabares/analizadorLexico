package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class Inicializacion (var tipoDato: TipoDato, var identificador: Token, var valor: Valor?) : Sentencia() {
    override fun toString(): String {
        return "Inicializacion(tipoDato=$tipoDato, identificador=$identificador, valor=$valor)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Inicialización")
        raiz.children.add(TreeItem("Tipo de Dato: $tipoDato"))
        raiz.children.add(TreeItem("Identificador: ${identificador.darLexema()}"))
        if(valor!=null){
            raiz.children.add(TreeItem("Valor: ${valor?.getArbolVisual()}"))
        }
        return raiz
    }
}