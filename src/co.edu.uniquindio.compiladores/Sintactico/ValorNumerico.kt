package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Categoria
import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ValorNumerico(var signo: Token?, var  numero: Token?, var valor:Valor?) : Valor() {
    override fun toString(): String {
        var signoCadena = ""
        if(signo!=null){
            signoCadena= signo!!.darLexema()
        }else if(numero!=null){
            signoCadena+" "+numero!!.darLexema();
        }else{
            signoCadena+" "+valor!!.toString();
        }
        return ""
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Valor Numérico")

        if(signo!=null){
            raiz.children.add(TreeItem("Signo: ${signo?.darLexema()}"))
        }

        if(numero!=null){
            raiz.children.add(TreeItem("Número: ${numero!!.darLexema()}"))
        }

        if(valor!=null){
            raiz.children.add(valor!!.getArbolVisual())
        }
        return raiz
    }

    override fun obtenerTipo(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String): String {
        if(numero?.darTipo() == Categoria.ENTERO){
            return "ganz"
        }else if (numero?.darTipo() == Categoria.DECIMAL){
            return "echt"
        }else if(valor!=null){
            return valor!!.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
        }
        return ""
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        if(valor!=null){
            valor!!.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
            val tipo:String?=valor!!.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
            if(tipo!="ganz" && tipo != "echt"){
                erroresSemanticos.add(Error("El identificador ${valor.toString()} no corresponde a un valor numérico",valor!!.getToken().fila,valor!!.getToken().columna))
            }
        }
    }

    override fun getJavaCode(): String {
        var codigo = ""
        var signoCadena = ""
        if(signo!=null){
            signoCadena= signo!!.getJavaCode()
        }else if(numero!=null){
            signoCadena+numero!!.getJavaCode();
        }else{
            signoCadena+valor!!.getJavaCode();
        }
        return codigo
    }
}