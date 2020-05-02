package Sintactico

import Mundo.Token
import javafx.scene.control.TreeItem

class ValorNumerico(var signo: Token?,var  numero: Token) : ExpresionAritmetica(null,null,null) {
    override fun toString(): String {
        return "ValorNumerico(signo=$signo, numero=$numero)"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Valor Numérico")

        if(signo!=null){
            raiz.children.add(TreeItem("Signo: ${signo?.darLexema()}"))
        }

        raiz.children.add(TreeItem("Número: ${numero.darLexema()}"))

        return raiz
    }
}