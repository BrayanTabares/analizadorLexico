package co.edu.uniquindio.compiladores.Sintactico
import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.Simbolo
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class AgregacionDatoArreglo (var nombre: Token, var posicion: ValorNumerico, var valor: Valor): ComandoArreglo(){
    override fun toString(): String {
        return "Agregacion de dato a un arreglo (nombre= $nombre, posicion= $posicion, valor= $valor"
    }

    override fun getArbolVisual (): TreeItem<String> {
        var raiz = TreeItem("Agregación de datos a arreglo")
        raiz.children.add(TreeItem("Identificador: ${nombre.darLexema()}"))
        raiz.children.add(TreeItem("Posición: $posicion"))
        var raizv = TreeItem("Valor a Agregar")
        raizv.children.add(valor.getArbolVisual())
        raiz.children.add(raizv)
        return raiz

    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        val simbolo : Simbolo? = tablaSimbolos.buscarSimboloVariable(nombre.darLexema(),ambito)
        if(simbolo==null) {
            erroresSemanticos.add(Error("el arreglo ${nombre.darLexema()} no existe", nombre.fila, nombre.columna))
        }else{
            val tipo :String? = simbolo.tipo
            if(tipo!=null) {
                if (!tipo.contains("rolle")) {
                    erroresSemanticos.add(Error("El tipo de dato del identificador ${nombre.darLexema()} no corresponde a un array", nombre.fila, nombre.columna))
                } else if (tipo.substring(6,tipo.length-1)!= valor.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)) {
                    erroresSemanticos.add(Error("El tipo de dato del arreglo (${tipo.substring(6,tipo.length-1)}) no coincide con el del valor (${valor.obtenerTipo(tablaSimbolos, erroresSemanticos, ambito)})", nombre.fila, nombre.columna))
                }
            }
        }
    }
}