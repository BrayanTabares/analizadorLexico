package co.edu.uniquindio.compiladores.Sintactico

import javafx.scene.control.TreeItem

class ValorInvocacion(var invocacion: InvocacionFuncion) : Valor() {
    override fun toString(): String {
        return "ValorInvocacion(${invocacion})"
    }

    override fun getArbolVisual(): TreeItem<String> {
        return invocacion.getArbolVisual()
    }
}