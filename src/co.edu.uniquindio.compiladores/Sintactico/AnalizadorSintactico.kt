package co.edu.uniquindio.compiladores.Sintactico

import co.edu.uniquindio.compiladores.lexico.Categoria
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.lexico.Error
import org.omg.PortableInterceptor.ObjectReferenceTemplate

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
        listaErrores.add(Error(mensaje, tokenActual.fila, tokenActual.columna))
    }

    /**
     * <Unidad Compilación> ::= “klasse” “(” [<Lista Funciones>] “)”
     */
    fun esUnidadDeCompilacion(): UnidadDeCompilacion? {
        if (tokenActual.darTipo() == Categoria.CLASE) {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA &&
                tokenActual.darLexema() == "("
            ) {
                obtenerSiguienteToken()
                val listaFunciones: ArrayList<Funcion> = esListaFunciones()
                if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA &&
                    tokenActual.darLexema() == ")"
                ) {
                    return UnidadDeCompilacion(listaFunciones)
                } else {
                    reportarError("Falta cerrar la clase")
                }
            } else {
                reportarError("Falta apertura de clase")
            }
        } else {
            reportarError("Nombre de clase incorrecto")
        }
        return null
    }

    /**
     * <Lista Funciones> ::= <Funcion> [<Lista Funciones>]
     */
    fun esListaFunciones(): ArrayList<Funcion> {
        val lista: ArrayList<Funcion> = ArrayList()
        var f: Funcion? = esFuncion()
        while (f != null || tokenActual.darTipo() == Categoria.COMENTARIO_BLOQUE) {
            if (f != null) {
                lista.add(f)
            } else {
                obtenerSiguienteToken()
            }
            f = esFuncion()
        }
        return lista
    }

    /**
     * <Funcion> ::= “technik” <Identificador> “<” [<Lista Parametros>] “>” [“<Tipo de Dato>”] “(” [<Lista Sentencias>] “)”
     */
    fun esFuncion(): Funcion? {
        if (tokenActual.darTipo() == Categoria.FUNCION) {
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
                        val tipoDato: TipoDato? = esTipoDato()
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
                                reportarError("Falta cerrar bloque de sentencia función ")
                            }
                        } else {
                            reportarError("Falta abrir bloque de sentencia función"+tokenActual)
                        }
                    } else {
                        reportarError("Falta cierre de parametros de función")
                    }
                } else {
                    reportarError("Falta apertura de parametros de función")
                }
            } else {
                reportarError("Falta identificador de función")
            }
        }
        return null
    }

    /**
     * <Identificador> ::= “-” Texto “-”
     */
    fun esIdentificador(): Identificador? {
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
            var token: Token = tokenActual
            obtenerSiguienteToken()
            return Identificador(token)
        }
        return null
    }

    /**
     * <Lista Parametros> ::= <Parametro> [“:” <Lista Parametros>]
     */
    fun esListaParametros(): ArrayList<Parametro> {
        val lista: ArrayList<Parametro> = ArrayList<Parametro>()
        var f: Parametro? = esParametro()
        while (f != null) {
            lista.add(f)
            f = null
            if (tokenActual.darTipo() == Categoria.OPERADOR_SEPARACION) {
                obtenerSiguienteToken()
                f = esParametro()
                if (f == null) {
                    reportarError("Falta concatenar parámetro")
                }
            }
        }
        return lista
    }

    /**
     * <Parametro> ::= <Tipo de Dato> <Identificador>
     */
    fun esParametro(): Parametro? {
        val tipoDato: TipoDato? = esTipoDato()
        if (tipoDato != null) {
            if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
                val identificador: Token = tokenActual
                obtenerSiguienteToken()
                return Parametro(tipoDato, identificador)
            } else {
                reportarError("Falta Identificador")
            }
        }
        return null
    }

    /**
     * <Lista Sentencias> ::= <Sentencia> [ <Lista Sentencias>]
     */
    fun esListaSentencias(): ArrayList<Sentencia> {
        val lista: ArrayList<Sentencia> = ArrayList()
        var f: Sentencia? = esSentencia()
        while (f != null) {
            lista.add(f)
            f = esSentencia()
        }
        return lista
    }

    /**
     * <Sentencia> ::= <Condición> | <Declaracion> | <Impresión> | <Asignación> | <Lectura> | <Invocacion Funcion> | <Iteración> | <Incremento> |
    <Inicializacion> | <Retorno> | <Declaración Arreglo> | <Inicializar Arreglo> | <Agregar Arreglo>
     */
    fun esSentencia(): Sentencia? {
        var token: Token = tokenActual
        var pos: Int = posicionActual
        var tipo: Sentencia? = esCondicion()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = pos
        tipo = esInicializacion()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = pos
        tipo = esDeclaracion()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = pos
        tipo = esImpresion()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = pos
        tipo = esAsignacion()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = pos
        tipo = esLectura()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = pos
        tipo = esInvocacionFuncion()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = pos
        tipo = esIterador()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = pos
        tipo = esIncremento()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = pos
        tipo = esRetorno()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = pos
        tipo = esDeclaracionArreglo()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = pos
        tipo = esInicializacionArreglo()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = pos
        tipo = esAgregacionDatoArreglo()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = pos
        return null
    }

    /**
     * <Incremento> ::= <Identificador> <Operador Incremento> “!”
     */
    fun esIncremento(): Incremento? {
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
            val identificador: Token = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_INCREMENTO || tokenActual.darTipo() == Categoria.OPERADOR_DECREMENTO) {
                val tipoIncremento: Token = tokenActual
                obtenerSiguienteToken()
                if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                    obtenerSiguienteToken()
                    return Incremento(identificador, tipoIncremento)
                } else {
                    reportarError("No se usó el operador terminal")
                }
            }
        }
        return null;
    }

    /**
     * <Retorno> ::= “ertrag” [<Valor>] “!”
     */
    fun esRetorno(): Retorno? {
        if (tokenActual.darTipo() == Categoria.RETORNO &&
            tokenActual.darLexema() == "ertrag"
        ) {
            obtenerSiguienteToken()
            val expresion: Valor? = esValor()
            if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                obtenerSiguienteToken()
                return Retorno(expresion)
            } else {
                reportarError("No se usó el operador terminal")
            }
        }
        return null;
    }

    /**
     * <Iteracion> ::= <Ciclo While> | <Ciclo For>
     */
    fun esIterador(): Iterador? {
        val token: Token = tokenActual
        val posicion: Int = posicionActual
        var tipo: Iterador? = esCicloFor()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        tipo = esCicloWhile()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        return null
    }

    /**
     * <Ciclo While> ::= “reprise” “<” <Expresion Logica> “>” “(”  [<Lista Sentencias>] “)”
     */
    fun esCicloWhile(): CicloWhile? {
        if (tokenActual.darTipo() == Categoria.CICLO_WHILE) {
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
                            esBreak()
                            if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA && tokenActual.darLexema() == ")"
                            ) {
                                obtenerSiguienteToken()
                                return CicloWhile(expresionL, sentencias)
                            } else {
                                reportarError("Falta cerrar bloque de sentencia ciclo while")
                            }
                        } else {
                            reportarError("Falta abrir bloque de sentencia ciclo while")
                        }
                    } else {
                        reportarError("Falta cierre de expresión de ciclo while")
                    }
                } else {
                    reportarError("Falta expresión de ciclo while")
                }
            } else {
                reportarError("Falta apertura de expresión de ciclo while")
            }

        }
        return null
    }

    /**
     * <Ciclo For> ::= “kreis” “<” [<Inicializacion>] <Expresion Relacional> “!” <Incremento> “>” “(” [<Lista Sentencias>] “)”
     */
    fun esCicloFor(): CicloFor? {
        if (tokenActual.darTipo() == Categoria.ITERADOR_FOR &&
            tokenActual.darLexema() == "kreis"
        ) {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                tokenActual.darLexema() == "<"
            ) {
                obtenerSiguienteToken()
                val inicializacion: Inicializacion? = esInicializacion()
                val expresionR: ExpresionRelacional? = esExpresionRelacional()
                if (expresionR != null) {
                    if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                        obtenerSiguienteToken()
                        val incremento: Incremento? = esIncremento()
                        if (incremento != null) {
                            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                                tokenActual.darLexema() == ">"
                            ) {
                                obtenerSiguienteToken()
                                if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA && tokenActual.darLexema() == "("
                                ) {
                                    obtenerSiguienteToken()
                                    val sentencias: ArrayList<Sentencia> = esListaSentencias()
                                    esBreak()
                                    if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA && tokenActual.darLexema() == ")"
                                    ) {
                                        obtenerSiguienteToken()
                                        return CicloFor(inicializacion, expresionR, incremento, sentencias)
                                    } else {
                                        reportarError("Falta cierre de bloque de sentencia ciclo for")
                                    }
                                } else {
                                    reportarError("Falta apertura de bloque de sentencia ciclo for")
                                }
                            } else {
                                reportarError("Falta cierre de expresión de ciclo for")
                            }
                        } else {
                            reportarError("Falta incremento de ciclo for")
                        }
                    } else {
                        reportarError("Falta terminal de expresión relacional ciclo for " + tokenActual.darLexema())
                    }
                } else {
                    reportarError("Falta expresión relacional de ciclo for")
                }
            } else {
                reportarError("Falta apertura de expresion de ciclo for")
            }
        }
        return null
    }

    /**
     * <Invocacion Funcion> ::= <Indentificador> “<” [<Lista Argumentos>] “>” "!"
     */
    fun esInvocacionFuncion(): InvocacionFuncion? {
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
            val invocacion: Token = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                tokenActual.darLexema() == "<"
            ) {
                obtenerSiguienteToken()
                val listaArgumentos: ArrayList<Valor> = esListaArgumentos()
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"
                ) {
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                        obtenerSiguienteToken()
                        return InvocacionFuncion(invocacion, listaArgumentos)
                    } else {
                        reportarError("Falta operador terminal de Invocaciòn")
                    }
                } else {
                    reportarError("Falta cierre de invocación" + tokenActual.darLexema())
                }
            }
        }
        return null
    }

    /**
     * <Invocacion> ::= <Indentificador> “<” [<Lista Argumentos>] “>”
     */
    fun esValorInvocacion(): ValorInvocacion? {
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
            val invocacion: Token = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                tokenActual.darLexema() == "<"
            ) {
                obtenerSiguienteToken()
                val listaArgumentos: ArrayList<Valor> = esListaArgumentos()
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"
                ) {
                    obtenerSiguienteToken()
                    return ValorInvocacion(InvocacionFuncion(invocacion, listaArgumentos))
                } else {
                    reportarError("Falta cierre de invocación" + tokenActual.darLexema())
                }
            }
        }
        return null
    }

    /**
     * <Lista Argumentos> ::= <Valor> [“:” <Lista Argumento>]
     */
    private fun esListaArgumentos(): ArrayList<Valor> {
        val lista: ArrayList<Valor> = ArrayList<Valor>()
        var f: Valor? = esValor()
        while (f != null) {
            lista.add(f)
            f = null
            if (tokenActual.darTipo() == Categoria.OPERADOR_SEPARACION) {
                obtenerSiguienteToken()
                f = esValor()
                if (f == null) {
                    reportarError("Falta concatenar argumento")
                }
            }
        }
        return lista
    }

    /**
     * <lectura> ::= "lesen" "<" [<Expresion>] ">" "!"
     */
    fun esLectura(): Lectura? {
        if (tokenActual.darTipo() == Categoria.LECTURA &&
            tokenActual.darLexema() == "lesen"
        ) {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                tokenActual.darLexema() == "<"
            ) {
                obtenerSiguienteToken()
                val expresion: Expresion? = esExpresion()
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"
                ) {
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                        obtenerSiguienteToken()
                        return Lectura(expresion)
                    } else {
                        reportarError("Falta operador terminal de lectura")
                    }
                } else {
                    reportarError("Falta cierre de expresión de lectura")
                }
            } else {
                reportarError("Falta apertura de expresión de lectura")
            }
        }
        return null
    }

    /**
     * <lectura> ::= "lesen" "<" [<Expresion>] ">" "!"
     */
    fun esValorLectura(): ValorLectura? {
        if (tokenActual.darTipo() == Categoria.LECTURA &&
            tokenActual.darLexema() == "lesen"
        ) {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                tokenActual.darLexema() == "<"
            ) {
                obtenerSiguienteToken()
                val expresion: Expresion? = esExpresion()
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"
                ) {
                    obtenerSiguienteToken()
                    return ValorLectura(Lectura(expresion))

                } else {
                    reportarError("Falta cierre de expresión de lectura")
                }
            } else {
                reportarError("Falta apertura de expresión de lectura")
            }
        }
        return null
    }

    /**
     * <Declaracion> ::= <Tipo de Dato> <Lista Identificadores> “!”
     */
    fun esDeclaracion(): Declaracion? {
        val tipoDato: TipoDato? = esTipoDato()
        if (tipoDato != null) {
            val lista: ArrayList<Token> = esListaIdentificadores()
            if (lista.size > 0) {
                if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                    obtenerSiguienteToken()
                    return Declaracion(tipoDato, lista)
                } else {
                    reportarError("No se usó el operador terminal")
                }
            }
        }
        return null;
    }

    /**
     * <Asignacion>  ::= <Identificador> “~” <Valor> “!”
     */
    fun esAsignacion(): Asignacion? {
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
            val identificador: Token = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_ASIGNACION) {
                obtenerSiguienteToken()
                val valor: Valor? = esValor()
                if (valor != null) {
                    if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                        obtenerSiguienteToken()
                        return Asignacion(identificador, valor)
                    } else {
                        reportarError("No se usó el operador terminal")
                    }
                } else {
                    reportarError("Falta valor de asignación")
                }
            }
        }
        return null;
    }

    /**
     * <Inicializacion> ::= <Tipo de Dato> <Identificador> “~” <Valor> “!”
     */
    fun esInicializacion(): Inicializacion? {
        val tipoDato: TipoDato? = esTipoDato()
        if (tipoDato != null) {
            if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
                val identificador: Token = tokenActual
                obtenerSiguienteToken()
                if (tokenActual.darTipo() == Categoria.OPERADOR_ASIGNACION) {
                    obtenerSiguienteToken()
                    val valor: Valor? = esValor()
                    if (valor != null) {
                        if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                            obtenerSiguienteToken()
                            return Inicializacion(tipoDato, identificador, valor)
                        } else {
                            reportarError("No se usó el operador terminal")
                        }
                    } else {
                        reportarError("Falta valor de asignación")
                    }
                }
            }
        }
        return null;
    }

    /**
     * <Valor> ::= <Identificador> | <Expresion> | <Invocacion Funcion> | <Lectura> | <Invocación> | <Arreglo>
     */
    fun esValor(): Valor? {
        val posicion: Int = posicionActual
        val token: Token = tokenActual
        var tipo: Valor? = esObtencionDatoArreglo()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        tipo = esObtencionArreglo()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        tipo = esValorInvocacion()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        tipo = esIdentificador()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        tipo = esValorLectura()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        tipo = esValorExpresion()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        return null
    }

    /**
     * <Impresion> ::= “druken” “<” [<Expresion>] “>” “!”
     */
    fun esImpresion(): Impresion? {
        if (tokenActual.darTipo() == Categoria.IMPRESION &&
            tokenActual.darLexema() == "druken"
        ) {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                tokenActual.darLexema() == "<"
            ) {
                obtenerSiguienteToken()
                val expresion: Expresion? = esExpresion()
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"
                ) {
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                        obtenerSiguienteToken()
                        return Impresion(expresion)
                    } else {
                        reportarError("Falta operador terminal impresión")
                    }
                } else {
                    reportarError("Falta cierre de expresión impresión")
                }
            } else {
                reportarError("Falta apertura de expresión impresión")
            }
        }
        return null;
    }

    /**
     * <Lista Identificadores> ::= <Identificador> [“:” <Lista Identificadores>]
     */
    fun esListaIdentificadores(): ArrayList<Token> {
        val lista: ArrayList<Token> = ArrayList()
        while (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
            val identificador: Token = tokenActual
            lista.add(identificador)
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_SEPARACION) {
                obtenerSiguienteToken()
                if (tokenActual.darTipo() != Categoria.IDENTIFICADOR) {
                    reportarError("Falta identificador a concatenar")
                }
            }
        }
        return lista
    }

    /**
     * <Condicion> ::= <Condicion If> | <Condicion Switch>
     */
    fun esCondicion(): Condicion? {
        var tipo: Condicion? = esCondicionIf()
        if (tipo != null) {
            return tipo
        }
        tipo = esCondicionSwitch()
        if (tipo != null) {
            return tipo
        }
        return null
    }

    /**
     * <Condicion Switch> ::= “anre” “<” <Expresion> “>” “(“ [<Lista Casos>] “)”
     */
    fun esCondicionSwitch(): CondicionSwitch? {
        if (tokenActual.darTipo() == Categoria.SWITCH &&
            tokenActual.darLexema() == "anre"
        ) {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                tokenActual.darLexema() == "<"
            ) {
                obtenerSiguienteToken()
                val expresion: Expresion? = esExpresion()
                if (expresion != null) {
                    if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                        tokenActual.darLexema() == ">"
                    ) {
                        obtenerSiguienteToken()
                        if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA && tokenActual.darLexema() == "("
                        ) {
                            obtenerSiguienteToken()
                            val listaCasos: ArrayList<Caso> = esListaCasos()
                            if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA && tokenActual.darLexema() == ")"
                            ) {
                                obtenerSiguienteToken()
                                return CondicionSwitch(expresion, listaCasos)
                            } else {
                                reportarError("Falta cierre de sentencia condición switch")
                            }
                        } else {
                            reportarError("Falta apertura de sentencia condición switch")
                        }
                    } else {
                        reportarError("Falta cierre de expresión de condición switch")
                    }
                } else {
                    reportarError("Falta expresión de condición switch")
                }
            } else {
                reportarError("Falta apertura de expresión condición switch")
            }
        }
        return null
    }

    /**
     * <Lista Casos> ::= <Caso> [<Lista Casos>]
     */
    fun esListaCasos(): ArrayList<Caso> {
        val lista: ArrayList<Caso> = ArrayList()
        var caso: Caso? = esCaso()
        while (caso != null) {
            lista.add(caso)
            caso = esCaso()
        }
        return lista
    }

    /**
     * <Caso> ::= “fill” <Expresion> “?” [<Contenido Caso>]
     */
    fun esCaso(): Caso? {
        if (tokenActual.darTipo() == Categoria.CASO) {
            obtenerSiguienteToken()
            val expresion: Expresion? = esExpresion()
            if (expresion != null) {
                if (tokenActual.darTipo() == Categoria.DOS_PUNTOS) {
                    obtenerSiguienteToken()
                    val contenido: ContenidoCaso? = esContenidoCaso()
                    return Caso(expresion, contenido)
                } else {
                    reportarError("Falta operador ? del switch")
                }
            } else {
                reportarError("Falta expresión de caso de switch ")
            }
        }
        return null
    }

    /**
     * <Contenido Caso> ::= <Lista Sentencias> [ <Contenido Caso>] | <Caso> | <Break>
     */
    fun esContenidoCaso(): ContenidoCaso? {
        if (esBreak()) {
            return ContenidoCaso(ArrayList(), null, null)
        }
        val listaSentencias: ArrayList<Sentencia> = esListaSentencias()
        if (listaSentencias.size > 0) {
            val contenido: ContenidoCaso? = esContenidoCaso()
            return ContenidoCaso(listaSentencias, contenido, null)
        }
        val caso: Caso? = esCaso()
        if (caso != null) {
            return ContenidoCaso(ArrayList(), null, caso)
        }
        return null
    }

    /**
     * <Break> ::= “brunch” “!”
     */
    fun esBreak(): Boolean {
        if (tokenActual.darTipo() == Categoria.BREAK) {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                obtenerSiguienteToken()
                return true
            } else {
                reportarError("Falta terminal de break")
            }
        }
        return false
    }

    /**
     * <Condicion if> ::= “wenn” “<” <Expresion Logica> “>” “(“ [<Lista Sentencias>] “)” [“hein” “(“ [<Lista Sentencias>] “)”]
     */
    fun esCondicionIf(): CondicionIf? {
        if (tokenActual.darTipo() == Categoria.CONDICION_If) {
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
                            esBreak()
                            if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA && tokenActual.darLexema() == ")"
                            ) {
                                obtenerSiguienteToken()
                                var sentenciasElse: ArrayList<Sentencia> = ArrayList()
                                if (tokenActual.darTipo() == Categoria.CONDICION_ELSE &&
                                    tokenActual.darLexema() == "hein"
                                ) {
                                    obtenerSiguienteToken()
                                    if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA && tokenActual.darLexema() == "("
                                    ) {
                                        obtenerSiguienteToken()
                                        sentenciasElse = esListaSentencias()
                                        esBreak()
                                        if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA && tokenActual.darLexema() == ")"
                                        ) {
                                            obtenerSiguienteToken()
                                            return CondicionIf(expresionL, sentencias, sentenciasElse)
                                        } else {
                                            reportarError("Falta cierre de bloque de sentencias de else")
                                        }
                                    } else {
                                        reportarError("Falta apertura de bloque de sentencias de else")
                                    }
                                }
                                return CondicionIf(expresionL, sentencias, sentenciasElse)
                            } else {
                                reportarError("Falta cierre de bloque de sentencias de if")
                            }
                        } else {
                            reportarError("Falta apertura de bloque de sentencia de if")
                        }
                    } else {
                        reportarError("Falta cierre de expresión de if" + tokenActual.darLexema())
                    }
                } else {
                    reportarError("Falta expresión de if ")
                }
            } else {
                reportarError("Falta apertura de expresión de if")
            }
        }
        return null
    }

    /**
     * <Expresion> ::= <Expresion Aritmetica> | <Expresion Relacional> | <Expresion Logica> | <Expresion Cadena> | <Expresión Caracter> | <Identificador>
     */
    fun esExpresion(): Expresion? {
        val token: Token = tokenActual
        val posicion: Int = posicionActual
        var tipo: Expresion? = esIdentificador()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        tipo = esExpresionAritmetica()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        tipo = esExpresionRelacional()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        tipo = esExpresionLogica()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        tipo = esExpresionCadena()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        tipo = esExpresionCaracter()
        if (tipo != null) {
            return tipo
        }
        tokenActual = token
        posicionActual = posicion
        return null
    }

    fun esValorExpresion(): ValorExpresion? {
        val tipo: Expresion? = esExpresion()
        if (tipo != null) {
            return ValorExpresion(tipo)
        }
        return null
    }

    /**
     * <Caracter> ::= “|” Caracter “|” | <Identificador>
     */
    fun esExpresionCaracter(): ExpresionCaracter? {
        if (tokenActual.darTipo() == Categoria.CARACTER || tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
            val token: Token = tokenActual
            obtenerSiguienteToken()
            return ExpresionCaracter(token)
        }
        return null
    }

    /**
     *<Expresion Cadena> ::= <Cadena> [“$” <ValorCadena>]
     */
    private fun esExpresionCadena(): ExpresionCadena? {
        if (tokenActual.darTipo() == Categoria.CADENA) {
            val token: Token = tokenActual
            obtenerSiguienteToken()
            var valor: ArrayList<ValorCadena> = ArrayList()
            if (tokenActual.darTipo() == Categoria.OPERADOR_ARITMETICO &&
                tokenActual.darLexema() == "$"
            ) {
                obtenerSiguienteToken()
                valor = esValorCadena()
                if (valor.size > 0) {
                    return ExpresionCadena(token, valor)
                } else {
                    reportarError("Falta valor de concatenación de cadena")
                }
            }
            return ExpresionCadena(token, valor)
        }
        return null
    }

    /**
     * <Valor Cadena> ::= <Valor> [“$” <ValorCadena>]
     */
    private fun esValorCadena(): ArrayList<ValorCadena> {
        val lista: ArrayList<ValorCadena> = ArrayList()
        var f: Valor? = esValor()
        while (f != null) {
            lista.add(ValorCadena(f))
            f = null
            if (tokenActual.darTipo() == Categoria.OPERADOR_ARITMETICO &&
                tokenActual.darLexema() == "$"
            ) {
                obtenerSiguienteToken()
                f = esValor()
                if (f == null) {
                    reportarError("Falta valor de concatenación de cadena")
                }
            }
        }
        return lista
    }

    /**
     * <Expresion Relacional> ::= "<"<Expresion Relacional>">" [<Operador Relacional> <Expresion Relacional2>] | <Expresion Aritemetica> <Operador Relacional> <Expresion Relacional2>
     */
    fun esExpresionRelacional(): ExpresionRelacional? {
        if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
            tokenActual.darLexema() == "<"
        ) {
            obtenerSiguienteToken()
            val expresion1: Expresion? = esExpresionRelacional()
            if (expresion1 != null) {
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"
                ) {
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.OPERADOR_RELACIONAL) {
                        val operador: Token = tokenActual
                        obtenerSiguienteToken()
                        val expresion2: Expresion? = esExpresionRelacional2()
                        if (expresion2 != null) {
                            return ExpresionRelacional(expresion1, operador, expresion2)
                        } else {
                            reportarError("Falta expresión a relacionar")
                        }
                    } else if(tokenActual.darTipo() == Categoria.OPERADOR_LOGICO){
                        return null
                    }
                    return ExpresionRelacional(expresion1, null, null)
                } else {
                    reportarError("Falta cerrar la agrupación de expresión")
                }
            }
        }
        val expresion1: Expresion? = esExpresionAritmetica()
        if (expresion1 != null) {
            if (tokenActual.darTipo() == Categoria.OPERADOR_RELACIONAL) {
                val operador: Token = tokenActual
                obtenerSiguienteToken()
                val expresion2: Expresion? = esExpresionRelacional2()
                if (expresion2 != null) {
                    return ExpresionRelacional(expresion1, operador, expresion2)
                } else {
                    reportarError("Falta expresión a relacionar")
                }
            }else if(tokenActual.darTipo() == Categoria.OPERADOR_LOGICO){
                return null
            } else if(tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION){
                return ExpresionRelacional(expresion1, null, null)
            }else{
                reportarError("Falta operador relacional"+tokenActual.darLexema())
            }
        }
        return null
    }

    /**
     * <Expresion Relacional2> ::= "<"<Expresion Relacional>">" [<Operador Relacional> <Expresion Relacional2>] | <Expresion Aritemetica> [<Operador Relacional> <Expresion Relacional2>]
     */
    fun esExpresionRelacional2(): ExpresionRelacional? {
        if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
            tokenActual.darLexema() == "<"
        ) {
            obtenerSiguienteToken()
            val expresion1: Expresion? = esExpresionRelacional()
            if (expresion1 != null) {
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"
                ) {
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.OPERADOR_RELACIONAL) {
                        val operador: Token = tokenActual
                        obtenerSiguienteToken()
                        val expresion2: Expresion? = esExpresionRelacional2()
                        if (expresion2 != null) {
                            return ExpresionRelacional(expresion1, operador, expresion2)
                        } else {
                            reportarError("Falta expresión a relacionar")
                        }
                    } else if(tokenActual.darTipo() == Categoria.OPERADOR_LOGICO){
                        return null
                    }
                    return ExpresionRelacional(expresion1, null, null)
                } else {
                    reportarError("Falta cerrar la agrupación de expresión")
                }
            }
        }
        val expresion1: Expresion? = esExpresionAritmetica()
        if (expresion1 != null) {
            if (tokenActual.darTipo() == Categoria.OPERADOR_RELACIONAL) {
                val operador: Token = tokenActual
                obtenerSiguienteToken()
                val expresion2: Expresion? = esExpresionRelacional2()
                if (expresion2 != null) {
                    return ExpresionRelacional(expresion1, operador, expresion2)
                } else if(tokenActual.darTipo() == Categoria.OPERADOR_LOGICO){
                    return null
                } else {
                    reportarError("Falta expresión a relacionar")
                }
            }
            return ExpresionRelacional(expresion1, null, null)
        }
        return null
    }

    fun esValorRelacional(): ValorRelacional? {
        val tipo: ExpresionRelacional? = esExpresionRelacional()
        if (tipo != null) {
            return ValorRelacional(tipo)
        }
        return null
    }

    /**
     * <Expresion Aritmetica> ::= "<"<Expresion Aritmetica>">" [<Operador Aritmetico> <Expresion Aritmetica>] | <Valor Numerico> [<Operador Aritmetico> <Expresion Aritmetica>]
     */
    fun esExpresionAritmetica(): ExpresionAritmetica? {
        if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
            tokenActual.darLexema() == "<"
        ) {
            obtenerSiguienteToken()
            val expresion1: ExpresionAritmetica? = esExpresionAritmetica()
            if (expresion1 != null) {
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"
                ) {
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.OPERADOR_ARITMETICO) {
                        val operador: Token = tokenActual
                        obtenerSiguienteToken()
                        val expresion2: ExpresionAritmetica? = esExpresionAritmetica()
                        if (expresion2 != null) {
                            return ExpresionAritmetica(expresion1, operador, expresion2,null)
                        } else {
                            reportarError("Falta expresión a relacionar")
                        }
                    } else if(tokenActual.darTipo() == Categoria.OPERADOR_RELACIONAL || tokenActual.darTipo() == Categoria.OPERADOR_LOGICO){
                        return null
                    }
                    return ExpresionAritmetica(expresion1, null, null, null)
                } else {
                    reportarError("Falta cerrar la agrupación de expresión")
                }
            }
        }
        val valor: ValorNumerico? = esValorNumerico()
        if (valor != null) {
            if (tokenActual.darTipo() == Categoria.OPERADOR_ARITMETICO) {
                val operador: Token = tokenActual
                obtenerSiguienteToken()
                val expresion2: ExpresionAritmetica? = esExpresionAritmetica()
                if (expresion2 != null) {
                    return ExpresionAritmetica(null, operador, expresion2, valor)
                } else {
                    reportarError("Falta expresión a relacionar")
                }
            }
            return ExpresionAritmetica(null, null, null, valor)
        }
        return null
    }

    /**
     * <Expresion Logica> ::= "<"<Expresion Logica>">" [<Operador Logico> <Expresion Logica>] | <Valor Logico> [<Operador Logico> <Expresion Logica>] | <Operador Logico> <Expresion Logica>
     */
    fun esExpresionLogica(): ExpresionLogica? {
        if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
            tokenActual.darLexema() == "<"
        ) {
            obtenerSiguienteToken()
            val expresion1: ExpresionLogica? = esExpresionLogica()
            if (expresion1 != null) {
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"
                ) {
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.OPERADOR_LOGICO && tokenActual.darLexema() != "/") {
                        val operador: Token = tokenActual
                        obtenerSiguienteToken()
                        val expresion2: ExpresionLogica? = esExpresionLogica()
                        if (expresion2 != null) {
                            return ExpresionLogica(expresion1, operador, expresion2,null)
                        } else {
                            reportarError("Falta expresión a operar")
                        }
                    } else if (tokenActual.darTipo() == Categoria.OPERADOR_LOGICO && tokenActual.darLexema() == "/") {
                        reportarError("El operador / es Unario")
                    }
                    return ExpresionLogica(expresion1, null, null,null)
                } else {
                    reportarError("Falta cerrar la agrupación de expresión")
                }
            }
        }
        var valor: ValorLogico? = esValorLogico()
        if (valor != null) {
            if (tokenActual.darTipo() == Categoria.OPERADOR_LOGICO && tokenActual.darLexema() != "/") {
                val operador: Token = tokenActual
                obtenerSiguienteToken()
                val expresion2: ExpresionLogica? = esExpresionLogica()
                if (expresion2 != null) {
                    return ExpresionLogica(null, operador, expresion2,valor)
                } else {
                    reportarError("Falta expresión a operar")
                }
            } else if (tokenActual.darTipo() == Categoria.OPERADOR_LOGICO && tokenActual.darLexema() == "/") {
                reportarError("El operador / es unario")
            }
            return ExpresionLogica(null, null, null,valor)
        }
        if (tokenActual.darTipo() == Categoria.OPERADOR_LOGICO && tokenActual.darLexema() == "/") {
            val operador: Token = tokenActual
            obtenerSiguienteToken()
            val expresion: ExpresionLogica? = esExpresionLogica()
            if (valor != null) {
                return ExpresionLogica(null, operador, expresion,null)
            } else {
                reportarError("Falta expresión a operar")
            }
        } else if (tokenActual.darTipo() == Categoria.OPERADOR_LOGICO && tokenActual.darLexema() != "/") {
            reportarError("El operador ${tokenActual.darLexema()} es binario")
        }
        return null
    }

    /**
     * <Valor Logico> ::= “wahr” | “saum” | <Expresion Relacional> | <Indentificador>
     */
    fun esValorLogico(): ValorLogico? {
        val posicion: Int = posicionActual
        val token: Token = tokenActual
        var tipo: Valor? = esObtencionDatoArreglo()
        if (tipo != null) {
            return ValorLogico(tipo)
        }
        tokenActual = token
        posicionActual = posicion
        tipo = esValorInvocacion()
        if (tipo != null) {
            return ValorLogico(tipo)
        }
        tokenActual = token
        posicionActual = posicion
        var valor: Valor? = esIdentificador()
        if (valor != null) {
            return ValorLogico(valor)
        }
        tokenActual = token
        posicionActual = posicion
        valor = esValorBooleano()
        if (valor != null) {
            return ValorLogico(valor)
        }
        tokenActual = token
        posicionActual = posicion
        valor = esValorRelacional()
        if (valor != null) {
            return ValorLogico(valor)
        }
        tokenActual = token
        posicionActual = posicion
        return null
    }

    /**
     * <Valor Numerico> ::= [<Signo>] <Real> | [<Signo] <Entero> | <Identificador> | <Valor Invocacion>
     */
    fun esValorNumerico(): ValorNumerico? {
        val posicion: Int = posicionActual
        val token: Token = tokenActual
        var posicion1: Int = posicionActual
        var token1: Token = tokenActual
        var signo: Token? = null
        if (tokenActual.darTipo() == Categoria.OPERADOR_ARITMETICO &&
            (tokenActual.darLexema() == "$" || tokenActual.darLexema() == "¬")
        ) {
            signo = tokenActual
            obtenerSiguienteToken()
            posicion1=posicionActual
            token1=tokenActual
        }
        var tipo: Valor? = esObtencionDatoArreglo()
        if (tipo != null) {
            return ValorNumerico(signo,null,tipo)
        }
        tokenActual = token1
        posicionActual = posicion1
        tipo = esValorInvocacion()
        if (tipo != null) {
            return ValorNumerico(signo,null,tipo)
        }
        tokenActual = token1
        posicionActual = posicion1
        tipo = esIdentificador()
        if (tipo != null) {
            return ValorNumerico(signo,null,tipo)
        }
        tokenActual = token1
        posicionActual = posicion1
        if (tokenActual.darTipo() == Categoria.DECIMAL) {
            val numero: Token = tokenActual
            obtenerSiguienteToken()
            return ValorNumerico(signo, numero,null)
        } else if (tokenActual.darTipo() == Categoria.ENTERO) {
            val numero: Token = tokenActual
            obtenerSiguienteToken()
            return ValorNumerico(signo, numero,null)
        }
        tokenActual = token
        posicionActual = posicion
        return null
    }

    /**
     *
     */
    fun esValorBooleano(): ValorBooleano? {
        if (tokenActual.darTipo() == Categoria.BOOLEAN) {
            val valor: Token = tokenActual
            obtenerSiguienteToken()
            return ValorBooleano(valor)
        }
        return null
    }

    /**
     *<Tipo Dato> ::=  <Dato> | "rolle" "{" <Tipo Dato> "}"
     */
    fun esTipoDato(): TipoDato? {
        if (tokenActual.darTipo() == Categoria.DATO_ENTERO ||
            tokenActual.darTipo() == Categoria.DATO_REAL ||
            tokenActual.darTipo() == Categoria.DATO_STRING ||
            tokenActual.darTipo() == Categoria.DATO_BOOLEAN ||
            tokenActual.darTipo() == Categoria.DATO_CARACTER
        ) {
            val valor: Token = tokenActual
            obtenerSiguienteToken()
            return TipoDato(valor,null)
        }
        if(tokenActual.darTipo() == Categoria.ARREGLO){
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                tokenActual.darLexema() == "{"
            ) {
                obtenerSiguienteToken()
                var tipo: TipoDato? = esTipoDato()
                if(tipo!=null){
                    if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                        tokenActual.darLexema() == "}"
                    ) {
                        obtenerSiguienteToken()
                        return TipoDato(null, tipo)
                    } else{
                        reportarError("No se cerró el tipo de dato array")
                    }
                }
            }
        }

        return null
    }

    /**
     * <Obtener Dato> ::=  <Identificador> “{“ <Valor Numerico> “}”
     */
    private fun esObtencionDatoArreglo(): ObtencionDatoArreglo? {
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
            var nombre: Token = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                tokenActual.darLexema() == "{"
            ) {
                obtenerSiguienteToken()
                var posicion: ValorNumerico? = esValorNumerico()
                if (posicion != null) {
                    if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                        tokenActual.darLexema() == "}"
                    ) {
                        obtenerSiguienteToken()
                        return ObtencionDatoArreglo(nombre, posicion)
                    }
                }
            }

        }
        return null
    }

    /**
     * <Agregar Dato> ::= <Identificador> “{“ <Valor Numerico> “}” “~” <Valor> “!”
     */
    private fun esAgregacionDatoArreglo(): AgregacionDatoArreglo? {
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
            var tipo: Token = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                tokenActual.darLexema() == "{"
            ) {
                obtenerSiguienteToken()
                var posicion: ValorNumerico? = esValorNumerico()
                if (posicion != null) {
                    if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                        tokenActual.darLexema() == "}"
                    ) {
                        obtenerSiguienteToken()
                        if (tokenActual.darTipo() == Categoria.OPERADOR_ASIGNACION) {
                            obtenerSiguienteToken()
                            var valor: Valor? = esValor()
                            if (valor != null) {
                                if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                                    obtenerSiguienteToken()
                                    return AgregacionDatoArreglo(tipo, posicion, valor)
                                }

                            }
                        }
                    }
                }
            }
        }
        return null

    }

    /**
     *<Inicializar Arreglo>::=  <tipo de dato> “{“”}”  <Identificador> “~” <Tipo Dato>  “{“ <Valor Numerico> “}” “!”
     */
    private fun esInicializacionArreglo(): InicializacionArreglo? {
        val tipoDato1: TipoDato? = esTipoDato()
        if (tipoDato1 != null) {
            if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                tokenActual.darLexema() == "{"
            ) {
                obtenerSiguienteToken()
                if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                    tokenActual.darLexema() == "}"
                ) {
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
                        var nombre: Token = tokenActual
                        obtenerSiguienteToken()
                        if (tokenActual.darTipo() == Categoria.OPERADOR_ASIGNACION) {
                            obtenerSiguienteToken()
                            val tipoDato2: TipoDato? = esTipoDato()
                            if (tipoDato2 != null) {
                                if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                                    tokenActual.darLexema() == "{"
                                ) {
                                    obtenerSiguienteToken()
                                    var cantidad: ValorNumerico? = esValorNumerico()
                                    if (cantidad != null) {
                                        if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                                            tokenActual.darLexema() == "}"
                                        ) {
                                            obtenerSiguienteToken()
                                            if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                                                obtenerSiguienteToken()
                                                return InicializacionArreglo(tipoDato1, nombre,cantidad, tipoDato2)
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return null
    }


    /**
     *<Obtencion Arreglo>::=   <Tipo Dato>  “{“ <Valor Numerico> “}”
     */
    private fun esObtencionArreglo(): ObtencionArreglo? {
        val tipoDato: TipoDato? = esTipoDato()
        if (tipoDato != null) {
            if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                tokenActual.darLexema() == "{"
            ) {
                obtenerSiguienteToken()
                var cantidad: ValorNumerico? = esValorNumerico()
                if (cantidad != null) {
                    if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                        tokenActual.darLexema() == "}"
                    ) {
                        obtenerSiguienteToken()
                        return ObtencionArreglo(tipoDato,cantidad)
                    }
                }
            }
        }
        return null
    }
    /**
     * <Declarar Arreglo> ::=  <tipo de dato> “{“”}” <identificador> "!"
     */
    private fun esDeclaracionArreglo(): DeclaracionArreglo? {
        var tipoDato: TipoDato? = esTipoDato()
        if (tipoDato != null) {
            if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                tokenActual.darLexema() == "{"
            ) {
                obtenerSiguienteToken()
                if (tokenActual.darTipo() == Categoria.BLOQUE_ARREGLO &&
                    tokenActual.darLexema() == "}"
                ) {
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
                        var nombre: Token = tokenActual
                        obtenerSiguienteToken()
                        if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                            obtenerSiguienteToken()
                            return DeclaracionArreglo(tipoDato, nombre)

                        }
                    }
                }
            }
        }
        return null

    }

}