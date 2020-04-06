package Mundo

/**
 * Clase que modela un token
 */
class Token
/**
 * Constructor de un token
 * @param elLexema - cadena - laCadena != null
 * @param elTipo - tipo del token - elTipo != null
 * @param elIndiceSiguiente - posici�n del siguiente token - laPosicionSiguiente > 0
 */(
    /**
     * Lexema
     */
    private val lexema: String,
    /**
     * tipo
     */
    private val tipo: String,
    /**
     * posici�n del siguiente lexema
     */
    private val indiceSiguiente: Int
) {
    // -----------------------------------------------------------------
// Atributos
// -----------------------------------------------------------------
    // -----------------------------------------------------------------
// M�todos
// -----------------------------------------------------------------
    /**
     * Entrega la informaci�n del token
     * @return Descripci�n del token
     */
    fun darDescripcion(): String {
        return "Mundo.Token: $lexema     Tipo: $tipo     �ndice del siguiente: $indiceSiguiente"
    }

    /**
     * M�todo que retorna el lexema del token
     * @return el lexema del token
     */
    fun darLexema(): String {
        return lexema
    }

    /**
     * M�todo que retorna la posici�n del siguiente lexema
     * @return posici�n del siguiente token
     */
    fun darIndiceSiguiente(): Int {
        return indiceSiguiente
    }

    /**
     * M�todo que retorna el tipo del token
     * @return el tipo del token
     */
    fun darTipo(): String {
        return tipo
    }

    companion object {
        // -----------------------------------------------------------------
// Constantes
// -----------------------------------------------------------------
        /**
         * Constantes para modelar los posibles tipos de token que se van a analizar
         */
        const val ENTERO = "Entero"
        const val OPERADORASIGNACION = "Operador de asignación"
        const val IDENTIFICADOR = "Identificador"
        const val NORECONOCIDO = "No reconocido"
        const val PALABRA_RESERVADA = "Palabra reservada"
        const val REAL = "Real"
        const val TIPO_DATO = "Tipo de dato"
        const val CARACTER = "Caracter"
        const val CADENA = "Cadena"
        const val BOOLEAN = "Boolean"
        const val BLOQUE_SENTENCIA = "Bloque de sentencia"
        const val OPERADOR_ARITMETICO = "Operador aritmético"
        const val OPERADOR_RELACIONAL = " Operador relacional"
        const val OPERADOR_TERMINAL = "Operador terminal"
        const val OPERADOR_ASIGNACION = "Operador de asignación"
        const val OPERADOR_LOGICO = "Operador logico"
        const val OPERADOR_AGRUPACION = "Operador de agrupación"
        const val OPERADOR_SEPARACION = "Operador de separación"

        const val OPERADOR_INCREMENTO = "Operador incremento"
        const val OPERADOR_DECREMENTO = "Operador decremento"
        const val COMENTARIO_BLOQUE = "Comentario de bloque"
        const val PUNTO = "Punto"
        const val DOS_PUNTOS = "Dos puntos"

    }
    // -----------------------------------------------------------------
// Constructores
// -----------------------------------------------------------------
}
