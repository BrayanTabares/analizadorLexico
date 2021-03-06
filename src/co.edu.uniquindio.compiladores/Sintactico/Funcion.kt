package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class Funcion (var nombre : Token, var parametros : ArrayList<Parametro>, var tipoDato : TipoDato?, var sentencias : ArrayList<Sentencia> ) {
    override fun toString(): String {
        return "${nombre.darLexema()}"+obtenerTiposParametros()
    }

    fun getArbolVisual(): TreeItem<String> {
        var raiz = TreeItem("Función")
        raiz.children.add(TreeItem("Nombre: ${nombre.darLexema()}"))

        var raizP = TreeItem("Parametros")
        for (f in parametros){
            raizP.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizP)

        if(tipoDato!=null){
            raiz.children.add(TreeItem("Tipo de Retorno: $tipoDato"))
        }

        var raizS = TreeItem("Sentencias")
        for (f in sentencias){
            raizS.children.add(f.getArbolVisual())
        }
        raiz.children.add(raizS)

        return raiz
    }

    fun obtenerTiposParametros() :ArrayList<String> {
        var lista =ArrayList<String>()
        for (f in parametros){
            lista.add(f.tipoDato.toString())
        }
        return lista
    }

    fun llenarTablaSimbolos(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>) {
        tablaSimbolos.guardarSimboloFuncion(nombre.darLexema(),tipoDato.toString(),obtenerTiposParametros(),nombre.fila,nombre.columna)
        for(p in parametros){
            p.llenarTablaSimbolos(tablaSimbolos,this.toString())
        }
        for(s in sentencias){
            s.llenarTablaSimbolos(tablaSimbolos, erroresSemanticos, this.toString())
        }
    }

    fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>) {
        var ward: Boolean = true
        var tipo:String="void"
        var tipoRetorno:String="void"
        for(s in sentencias){
            s.analizarSemantica(tablaSimbolos, erroresSemanticos, this.toString())
            if(s is Retorno){
                ward=false
                 tipoRetorno=s.obtenerTipo(tablaSimbolos,erroresSemanticos,this.toString())
                if(tipoDato!=null){
                    tipo=tipoDato.toString()
                }
                if( tipo != tipoRetorno){
                    erroresSemanticos.add(Error("El tipo del retorno del método ${nombre.darLexema()} (${tipo}) no es compatible con ${tipoRetorno}",nombre.fila,nombre.columna))
                }
            }
        }
        if(ward && tipoDato!=null){
            erroresSemanticos.add(Error("El método ${nombre.darLexema()} debe tener un retorno",nombre.fila,nombre.columna))
        }
    }

    fun getJavaCode(): String {
        var tipo = "void"
        if (tipoDato != null) {
            tipo = tipoDato!!.getJavaCode()
        }
        var codigo = "public static $tipo ${nombre.getJavaCode()} ("
        if (nombre.darLexema().equals("-main") && parametros.isEmpty() && tipoDato==null) {
            codigo += "String[] args"
        } else {
            if (!parametros.isEmpty()) {
                for (p in parametros) {
                    codigo += p.getJavaCode() + ","
                }
                codigo = codigo.substring(0, codigo.length - 1)
            }
        }
        codigo += "){"
        for (sentencia in sentencias) {
            codigo += sentencia.getJavaCode()
        }
        codigo += "}"
        return codigo
    }
}