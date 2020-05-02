package Sintactico

class ContenidoCaso( var listaSentencias: ArrayList<Sentencia>, var contenido: ContenidoCaso?, var caso: Caso? ) {
    override fun toString(): String {
        return "ContenidoCaso(listaSentencias=$listaSentencias, contenido=$contenido, caso=$caso)"
    }
}