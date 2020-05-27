package co.edu.uniquindio.compiladores.semantica

class Simbolo {
    var nombre: String? = null
    var tipo: String? = null
    var fila = 0
    var columna = 0
    var ambito: String? = null
    var tipoParametros: ArrayList<String>? = null
    constructor(nombre: String, tipo: String, ambito: String, fila:Int, columna:Int){
        this.nombre = nombre
        this.tipo = tipo
        this.ambito = ambito
        this.fila = fila
        this.columna = columna
    }
    constructor(nombre: String, tipo: String, tipoParametros: ArrayList<String>){
        this.nombre = nombre
        this.tipo = tipo
        this.tipoParametros = tipoParametros
    }
    override fun toString(): String {
        if(tipoParametros==null){
            return "Simbolo(nombre=$nombre, tipo=$tipo, fila=$fila, columna=$columna, ambito=$ambito)"
        }
        return "Simbolo(nombre=$nombre, tipo=$tipo, ambito=$ambito, tipoParametros=$tipoParametros)"
    }
}