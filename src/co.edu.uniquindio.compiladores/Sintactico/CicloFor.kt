package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class CicloFor(var inicializacion: Inicializacion?,var expresionRelacional: ExpresionRelacional, var incremento: Incremento, var sentencias: ArrayList<Sentencia>) : Iterador(){
    override fun toString(): String {
        return "kreis [${incremento.nombre.fila},${incremento.nombre.columna}]"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Ciclo For")
        if(inicializacion!=null){
            raiz.children.add(inicializacion?.getArbolVisual())
        }

        raiz.children.add(expresionRelacional.getArbolVisual())

        raiz.children.add(TreeItem("Incremento: $incremento"))

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
        inicializacion?.llenarTablaSimbolos(tablaSimbolos,erroresSemanticos,"$ambito $this")
        for(s in sentencias){
            s.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, "$ambito $this")
        }
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        inicializacion?.analizarSemantica(tablaSimbolos, erroresSemanticos, "$ambito $this")
        expresionRelacional.analizarSemantica(tablaSimbolos, erroresSemanticos, "$ambito $this")
        incremento.analizarSemantica(tablaSimbolos, erroresSemanticos, "$ambito $this")
        for(s in sentencias){
            s.analizarSemantica(tablaSimbolos, erroresSemanticos, "$ambito $this")
        }
    }

    override fun getJavaCode(): String {
        var codigo = "for("
        if(inicializacion!=null){
            codigo+= inicializacion!!.getJavaCode()
        }
        codigo+=expresionRelacional.getJavaCode()+";"+incremento.getJavaCode()
        codigo=codigo.substring(0,codigo.length-1)
        codigo+="){"
        for (sentencia in sentencias) {
            codigo += sentencia.getJavaCode()
        }
        codigo += "}"
        return codigo
    }
}