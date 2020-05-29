package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.semantica.TablaSimbolos
import javafx.scene.control.TreeItem

class Impresion (var expresion : Valor?) : Sentencia() {
    override fun toString(): String {
        return "druken <$expresion>"
    }

    override fun getArbolVisual(): TreeItem<String> {
        var raiz : TreeItem<String> = TreeItem("Impresión")
        if(expresion!=null){
            raiz.children.add(expresion?.getArbolVisual())
        }
        return raiz
    }

    override fun analizarSemantica(tablaSimbolos: TablaSimbolos, erroresSemanticos: ArrayList<Error>, ambito: String) {
        expresion?.analizarSemantica(tablaSimbolos, erroresSemanticos, ambito)
    }

    override fun getJavaCode(): String {
        return "JOptionPane.showMessageDialog("+expresion?.getJavaCode()+"+\"\");"
    }
}