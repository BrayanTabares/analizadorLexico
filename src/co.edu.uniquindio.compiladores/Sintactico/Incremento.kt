package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.Simbolo
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class Incremento(var nombre: Token, var tipoIncremento: Token) : Sentencia() {
    override fun toString(): String {
        return "${nombre.darLexema()}"+""+"${tipoIncremento.darLexema()}"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return TreeItem("${nombre.darLexema()}"+" "+"${tipoIncremento.darLexema()}")
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        val simbolo : Simbolo? = tablaSimbolos.buscarSimboloVariable(nombre.darLexema(),ambito)
        if(simbolo!=null) {
            if(simbolo.tipo!="ganz" && simbolo.tipo!="echt"){
                erroresSemanticos.add(Error("El identificador ${nombre.darLexema()} no posee un tipo de dato de incremento",nombre.fila,nombre.columna))
            }
        }else{
            erroresSemanticos.add(Error("El identificador ${nombre.darLexema()} no existe",nombre.fila,nombre.columna))
        }
    }
}