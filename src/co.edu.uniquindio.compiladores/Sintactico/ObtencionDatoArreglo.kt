package co.edu.uniquindio.compiladores.Sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class ObtencionDatoArreglo (var nombre: Token, var posicion: ValorNumerico ):ComandoArreglo(){
    override fun toString(): String {
        return "Obtencion de un dato del arreglo ( nombre= $nombre, posicion= $posicion"
    }

    override fun getArbolVisual (): TreeItem<String> {
        var raiz = TreeItem("Declaracion de arreglo")
        raiz.children.add(TreeItem("posicion: ${posicion.getArbolVisual()}"))
        raiz.children.add(TreeItem("identificador: ${nombre.darLexema()}"))
        return raiz

    }

}