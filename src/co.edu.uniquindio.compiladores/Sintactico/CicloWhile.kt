package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class CicloWhile( var expresionL: Valor, var sentencias: ArrayList<Sentencia>) : Iterador() {
    override fun toString(): String {
        return "reprise <$expresionL> ( $sentencias )"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Ciclo While")

        raiz.children.add(expresionL.getArbolVisual())

        var raizS = TreeItem("Sentencias")
        for (f in sentencias){
            raizS.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizS)

        return raiz
    }

    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ) {
        for(s in sentencias){
            s.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, "$ambito $this")
        }
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        expresionL.analizarSemantica(tablaSimbolos, erroresSemanticos, "$ambito $this")
        val tipo = expresionL.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
        if(tipo!="dich"){
            erroresSemanticos.add(Error("El valor de la condicion del if ($tipo) no es un valor lógico",expresionL.getToken().fila,expresionL.getToken().columna))
        }
        for(s in sentencias){
            s.analizarSemantica(tablaSimbolos, erroresSemanticos, "$ambito $this")
        }

    }

    override fun getJavaCode(): String {
        var codigo ="while("+expresionL!!.getJavaCode()+"){"
        for (s in sentencias){
            codigo+=s.getJavaCode()
        }
        codigo+="}"
        return codigo
    }
}