package Sintactico

import Mundo.Token

class AnalizadorSintactico(var listaTokens: ArrayList<Token>) {
    var posicionActual = 0
    var tokenActual = listaTokens[posicionActual]
    var listaErrores = ArrayList<Error>()

    fun obtenerSiguienteToken() {
        posicionActual++;
        if (posicionActual < listaTokens.size) {
            tokenActual = listaTokens[posicionActual]
        }
    }

    fun reportarError(mensaje: String) {

    }

    /**
     * <Unidad Compilación> ::= “klasse” “(” [<Lista Metodos>] “)”
     */
    fun esUnidadDeCompilacion(): UnidadDeCompilacion? {
        if (tokenActual.darTipo() == Categoria.PALABRA_RESERVADA &&
            tokenActual.darLexema() == "klasse"
        ) {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA &&
                tokenActual.darLexema() == "("
            ) {
                obtenerSiguienteToken()
                val listaFunciones: ArrayList<Funcion> = esListaFunciones()
                if (listaFunciones.size >= 0) {
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA &&
                        tokenActual.darLexema() == ")"
                    ) {
                        UnidadDeCompilacion(listaFunciones)
                    } else {

                    }
                } else {

                }
            } else {

            }
        } else {

        }
        return null
    }

    /**
     * <Lista Funciones> ::= <Funcion> [<Lista Funciones>]
     */
    fun esListaFunciones(): ArrayList<Funcion> {
        var lista: ArrayList<Funcion> = ArrayList<Funcion>()
        var f: Funcion? = esFuncion()
        while (f != null) {
            lista.add(f)
            f = esFuncion()
        }
        return lista
    }

    /**
     * <Funcion> ::= “technik” <Identificador> “<” [<Lista Parametros>] “>” “<Tipo de Dato>” “(” [<Lista Sentencias>] “)”
     */
    fun esFuncion(): Funcion? {
        if (tokenActual.darTipo() == Categoria.PALABRA_RESERVADA &&
            tokenActual.darLexema() == "technik"
        ) {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
                val nombre = tokenActual
                obtenerSiguienteToken()
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == "<"
                ) {
                    obtenerSiguienteToken()
                    val parametros: ArrayList<Parametro> = esListaParametros()
                    if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                        tokenActual.darLexema() == ">"
                    ) {
                        obtenerSiguienteToken()
                        if (tokenActual.darTipo() == Categoria.TIPO_DATO) {
                            val tipoDato: Token = tokenActual
                            obtenerSiguienteToken()
                            if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA &&
                                tokenActual.darLexema() == "("
                            ) {
                                obtenerSiguienteToken()
                                val sentencias: ArrayList<Sentencia> = esListaSentencias()
                                if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA &&
                                    tokenActual.darLexema() == ")"
                                ) {
                                    obtenerSiguienteToken()
                                    return Funcion(nombre, parametros, tipoDato, sentencias)
                                } else {

                                }
                            } else {

                            }
                        } else {

                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        }
        return null
    }

    /**
     * <Lista Parametros> ::= <Parametro> [“:” <Lista Parametros>]
     */
    fun esListaParametros(): ArrayList<Parametro> {
        var lista: ArrayList<Parametro> = ArrayList<Parametro>()
        var f: Parametro? = esParametro()
        while (f != null) {
            lista.add(f)
            f = null
            if (tokenActual.darTipo() == Categoria.OPERADOR_SEPARACION) {
                obtenerSiguienteToken()
                f = esParametro()
            }
        }
        return lista
    }

    /**
     * <Parametro> ::= <Tipo de Dato> <Identificador>
     */
    fun esParametro(): Parametro? {
        if (tokenActual.darTipo() == Categoria.TIPO_DATO) {
            val tipoDato: Token = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
                val identificador: Token = tokenActual
                obtenerSiguienteToken()
                return Parametro(tipoDato, identificador)
            } else {

            }
        }
        return null
    }

    /**
     * <Lista Sentencias> ::= <Sentencia> [ <Lista Sentencias>]
     */
    fun esListaSentencias(): ArrayList<Sentencia> {
        var lista: ArrayList<Sentencia> = ArrayList()
        var f: Sentencia? = esSentencia()
        while (f != null) {
            lista.add(f)
            f = esSentencia()
        }
        return lista
    }

    /**
     * <Sentencia> ::= <Condición if> | <Declaracion> | <Impresión> | <Asignación> | <Lectura> | <Invocacion Metodo> | <Iteración> | <Incremento> | <Inicializacion> | <Retorno>
     */
    fun esSentencia(): Sentencia? {
        var tipo: Sentencia? = esCondicionIf()
        if (tipo != null) {
            return tipo
        }
        tipo = esDeclaracion()
        if (tipo != null) {
            return tipo
        }
        tipo = esImpresion()
        if (tipo != null) {
            return tipo
        }
        tipo = esAsignacion()
        if (tipo != null) {
            return tipo
        }
 /*       tipo = esLectura()
        if (tipo != null) {
            return tipo
        }
        tipo = esInvocacionMetodo()
        if (tipo != null) {
            return tipo
        }
        tipo = esIteracion()
        if (tipo != null) {
            return tipo
        }
        tipo = esIncremento()
        if (tipo != null) {
            return tipo
        }
        tipo = esInicializacion()
        if (tipo != null) {
            return tipo
        }
        tipo = esRetorno()
        if (tipo != null) {
            return tipo
        }*/
        return null
    }

    /**
     * <Declaracion> ::= <Tipo de Dato> <Lista Identificadores> “!”
     */
    fun esDeclaracion(): Declaracion? {
        if (tokenActual.darTipo() == Categoria.TIPO_DATO) {
            val tipoDato: Token = tokenActual
            obtenerSiguienteToken()
            var lista: ArrayList<Token> = esListaIdentificadores()
            if(lista.size > 0){
                if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                    obtenerSiguienteToken()
                    return Declaracion(tipoDato, lista)
                } else {

                }
            }else{

            }
        }
        return null;
    }

    /**
     * <Asignacion> ::= <Identificador> “~” <Expresion> “!” | <Identificador> “~” <identificador> “!” | <Identificador> “~” <Invocacion Metodo> “!”
     */
    fun esAsignacion(): Asignacion? {
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
            val identificador: Token = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_ASIGNACION &&
                tokenActual.darLexema() == "<"
            ) {

            }
        }
        return null;
    }

    /**
     * <Impresion> ::= “druken” “<” [<Expresion>] “>” “!”
     */
    fun esImpresion(): Impresion? {
        if (tokenActual.darTipo() == Categoria.PALABRA_RESERVADA &&
            tokenActual.darLexema() == "druken") {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                tokenActual.darLexema() == "<"
            ) {
                obtenerSiguienteToken()
                var expresion: Expresion? = esExpresion()
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"
                ) {
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                        obtenerSiguienteToken()
                        return Impresion(expresion)
                    } else {

                    }
                }
            } else {

            }
        }
        return null;
    }

    /**
     * <Lista Identificadores> ::= <Identificador> [“:” <Lista Identificadores>]
     */
    fun esListaIdentificadores(): ArrayList<Token>{
        var lista: ArrayList<Token> = ArrayList()
        while (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
            val identificador: Token = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_SEPARACION) {
                obtenerSiguienteToken()
                lista.add(identificador)
            } else {

            }
        }
        return lista
    }

    /**
     * <Condicion if> ::= “wenn” “<” <Expresion Logica> “>” “(“ [<Lista Sentencias>] “)” [“hein” “(“ [<Lista Sentencias>] “)”]
     */
    fun esCondicionIf(): CondicionIf? {
        if (tokenActual.darTipo() == Categoria.PALABRA_RESERVADA &&
            tokenActual.darLexema() == "wenn"
        ) {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                tokenActual.darLexema() == "<"
            ) {
                obtenerSiguienteToken()
                val expresionL: ExpresionLogica? = esExpresionLogica()
                if (expresionL != null) {
                    if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"
                    ) {
                        obtenerSiguienteToken()
                        if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA && tokenActual.darLexema() == "("
                        ) {
                            obtenerSiguienteToken()
                            val sentencias: ArrayList<Sentencia> = esListaSentencias()
                            if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA && tokenActual.darLexema() == ")"
                            ) {
                                obtenerSiguienteToken()
                                val sentenciasElse: ArrayList<Sentencia> = ArrayList()
                                if (tokenActual.darTipo() == Categoria.PALABRA_RESERVADA &&
                                    tokenActual.darLexema() == "hein"
                                ) {
                                    obtenerSiguienteToken()
                                    if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA && tokenActual.darLexema() == "("
                                    ) {
                                        obtenerSiguienteToken()
                                        val sentencias: ArrayList<Sentencia> = esListaSentencias()
                                        if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA && tokenActual.darLexema() == ")"
                                        ) {
                                            obtenerSiguienteToken()
                                            return CondicionIf(sentencias, sentenciasElse)
                                        } else {

                                        }

                                    } else {

                                    }
                                }
                                return CondicionIf(sentencias, sentenciasElse)
                            } else {

                            }
                        } else {

                        }

                    } else {

                    }
                } else {

                }
            } else {

            }

        }
        return null
    }

    fun esExpresion(): Expresion? {
        return null
    }

    fun esExpresionLogica(): ExpresionLogica? {
        return null
    }
}