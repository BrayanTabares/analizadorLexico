package co.edu.uniquindio.compiladores.Sintactico
import co.edu.uniquindio.compiladores.lexico.Token
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

}