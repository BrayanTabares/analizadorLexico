package co.edu.uniquindio.compiladores.Sintactico
import co.edu.uniquindio.compiladores.lexico.Token
import javafx.scene.control.TreeItem

class ObtencionDatoArreglo (var nombre: Token, var posicion: ValorNumerico ):Valor(){
    override fun toString(): String {
        return "Obtencion de un dato del arreglo ( nombre= $nombre, posicion= $posicion"
    }

    override fun getArbolVisual (): TreeItem<String> {
        var raiz = TreeItem("Obtención datos de arreglo")
        raiz.children.add(TreeItem("Identificador: ${nombre.darLexema()}"))
        raiz.children.add(TreeItem("Posicion: $posicion"))
        return raiz

    }

}