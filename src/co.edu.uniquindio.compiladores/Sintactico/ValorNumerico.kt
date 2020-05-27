package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Categoria
import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class ValorNumerico(var signo: Token?, var  numero: Token?, var valor:Valor?) : Valor() {
    override fun toString(): String {
        return if(signo==null){
            numero.toString()
        }else if(numero!=null){
            signo!!.darLexema()+" "+numero!!.darLexema();
        }else{
            signo!!.darLexema()+" "+valor!!.toString();
        }
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
        }else if(valor is Identificador){
            val identificador: Identificador = valor as Identificador
            val tipo:String=identificador.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
            if(tipo=="ganz" || tipo == "echt"){
                return tipo
            }else{
                erroresSemanticos.add(Error("El identificador ${identificador.identificador.darLexema()} no corresponde a un valor numérico",identificador.identificador.fila,identificador.identificador.columna))
                return ""
            }
        }else if(valor is ValorInvocacion){
            val identificador: ValorInvocacion = valor as ValorInvocacion
            val tipo:String=identificador.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
            if(tipo=="ganz" || tipo == "echt"){
                return tipo
            }else{
                erroresSemanticos.add(Error("El metodo ${identificador.invocacion.identificador.darLexema()} no corresponde a un valor numérico",identificador.invocacion.identificador.fila,identificador.invocacion.identificador.columna))
                return ""
            }
        }else if(valor is ObtencionDatoArreglo){
            val identificador: ObtencionDatoArreglo = valor as ObtencionDatoArreglo
            val tipo:String=identificador.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
            if(tipo=="ganz" || tipo == "echt"){
                return tipo
            }else{
                erroresSemanticos.add(Error("El metodo ${identificador.nombre.darLexema()} no corresponde a un valor numérico",identificador.nombre.fila,identificador.nombre.columna))
                return ""
            }
        }
        return ""
    }
}