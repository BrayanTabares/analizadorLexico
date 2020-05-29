package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class TipoDato(val valor: Token?, val tipo : TipoDato?) {
    override fun toString(): String {
        if(valor!=null){
            return valor.darLexema()
        }else if(tipo!=null){
            return "rolle("+tipo.toString()+")"
        }
        return ""
    }

    fun getArbolVisual(): TreeItem<String> {
        if(valor!=null){
            return TreeItem("${valor?.darLexema()}")
        } else if (tipo!=null){
            var raiz = TreeItem("Arreglo")
            raiz.children.add(tipo.getArbolVisual())
            return raiz
        }
       return TreeItem("")
    }

    fun getJavaCode(): String {
        if(valor!=null){
            return valor.getJavaCode()
        }else if(tipo!=null){
            return tipo.getJavaCode()+"[]"
        }
        return ""
    }

}