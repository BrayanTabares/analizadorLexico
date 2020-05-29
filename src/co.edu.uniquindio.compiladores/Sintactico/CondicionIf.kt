package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class CondicionIf(var expresion: Valor, var sentencias: ArrayList<Sentencia>, var sentenciasElse: ArrayList<Sentencia>): Condicion() {
    override fun toString(): String {
        return "wenn <$expresion> ( $sentencias ) ( $sentenciasElse )"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Condicion If")

        raiz.children.add(expresion.getArbolVisual())

        var raizS = TreeItem("Sentencias")
        for (f in sentencias){
            raizS.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizS)

        var raizE = TreeItem("Sentencias Else")
        for (f in sentenciasElse){
            raizE.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizE)

        return raiz
    }

    override fun llenarTablaSimbolos(
        tablaSimbolos: TablaSimbolos,
        erroresSemanticos: ArrayList<Error>,
        ambito: String
    ) {
        for(s in sentencias){
            s.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, "$ambito $this if")
        }
        for(s in sentenciasElse){
            s.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, "$ambito $this else")
        }
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        expresion.analizarSemantica(tablaSimbolos, erroresSemanticos, "$ambito $this")
        val tipo = expresion.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)
        if(tipo!="dich"){
            erroresSemanticos.add(Error("El valor de la condicion del if ($tipo) no es un valor lógico",expresion.getToken().fila,expresion.getToken().columna))
        }
        for(s in sentencias){
            s.analizarSemantica(tablaSimbolos, erroresSemanticos, "$ambito $this if")
        }
        for(s in sentenciasElse){
            s.analizarSemantica(tablaSimbolos, erroresSemanticos, "$ambito $this else")
        }
    }

    override fun getJavaCode(): String {
        var codigo = "if(" + expresion.getJavaCode() + "){"
        for (sentencia in sentencias) {
            codigo += sentencia.getJavaCode()
        }
        codigo += "}"
        if( sentenciasElse!=null ){
            codigo += "else{"
            for (sentencia in sentenciasElse) {
                codigo += sentencia.getJavaCode()
            }
            codigo += "}"
        }
        return codigo
    }

}