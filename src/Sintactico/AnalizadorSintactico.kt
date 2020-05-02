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

    fun reportarError( mensaje: String) {
        listaErrores.add(Error(mensaje))
    }

    /**
     * <Unidad Compilación> ::= “klasse” “(” [<Lista Funciones>] “)”
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
     * <Funcion> ::= “technik” <Identificador> “<” [<Lista Parametros>] “>” [“<Tipo de Dato>”] “(” [<Lista Sentencias>] “)”
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
                        var tipoDato: Token? = null
                        if (tokenActual.darTipo() == Categoria.TIPO_DATO) {
                            tipoDato = tokenActual
                            obtenerSiguienteToken()
                        }

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
        }
        return null
    }

    /**
     * <Identificador> ::= “-” Texto “-”
     */
    fun esIdentificador(): Identificador? {
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
            obtenerSiguienteToken()
            return Identificador(tokenActual)
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
     * <Sentencia> ::= <Condición> | <Declaracion> | <Impresión> | <Asignación> | <Lectura> | <Invocacion Funcion> | <Iteración> | <Incremento> | <Inicializacion> | <Retorno>
     */
    fun esSentencia(): Sentencia? {
        var tipo: Sentencia? = esCondicion()
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
        tipo = esLectura()
        if (tipo != null) {
            return tipo
        }
        tipo = esInvocacionFuncion()
        if (tipo != null) {
            return tipo
        }
        tipo = esIterador()
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
        }
        return null
    }

    /**
     * <Incremento> ::= <Identificador> <Operador Incremento> “!”
     */
    fun esIncremento(): Incremento? {
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
            val identificador: Token = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_INCREMENTO || tokenActual.darTipo() == Categoria.OPERADOR_DECREMENTO ) {
                val tipoIncremento: Token = tokenActual
                obtenerSiguienteToken()
                if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                    obtenerSiguienteToken()
                    return Incremento(identificador, tipoIncremento)
                } else {
                    reportarError("No se usó el operador terminal")
                }
            } else {

            }
        }
        return null;
    }

    /**
     * <Retorno> ::= “ertrag” [<Expresion>] “!”
     */
    fun esRetorno(): Retorno? {
        if (tokenActual.darTipo() == Categoria.PALABRA_RESERVADA &&
            tokenActual.darLexema() == "ertrag") {
            val identificador: Token = tokenActual
            obtenerSiguienteToken()
            val expresion: Expresion? = esExpresion()
            if (expresion != null) {
                if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                    obtenerSiguienteToken()
                    return Retorno(expresion)
                } else {
                    reportarError("No se usó el operador terminal")
                }
            }
        }
        return null;
    }

    /**
     * <Iteracion> ::= <Ciclo While> | <Ciclo For>
     */
    fun esIterador(): Iterador? {
        var tipo: Iterador? = esCicloFor()
        if (tipo != null){
            return tipo
        }
        tipo = esCicloWhile()
        if (tipo != null){
            return tipo
        }
        return null
    }

    /**
     * <Ciclo While> ::= “reprise” “<” <Expresion Logica> “>” “(”  [<Lista Sentencias>] “)”
     */
    fun esCicloWhile(): CicloWhile? {
        if (tokenActual.darTipo() == Categoria.PALABRA_RESERVADA &&
            tokenActual.darLexema() == "reprise"
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
                                return CicloWhile(expresionL, sentencias)
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
     * <Ciclo For> ::= “kreis” “<” [<Inicializacion>] <Expresion Relacional> “!” <Incremento> “>” “(” [<Lista Sentencias>] “)”
     */
    fun esCicloFor(): CicloFor? {
        if (tokenActual.darTipo() == Categoria.PALABRA_RESERVADA &&
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
                                    if (tokenActual.darTipo() == Categoria.BLOQUE_SENTENCIA && tokenActual.darLexema() == ")"
                                    ) {
                                        obtenerSiguienteToken()
                                        return CicloFor(inicializacion, expresionR, incremento, sentencias)
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
            } else {

            }

        }
        return null
    }

    /**
     * <Invocacion Funcion> ::= <Indentificador> “<” [<Lista Argumentos>] “>”
     */
     fun esInvocacionFuncion(): InvocacionFuncion? {
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR){
            var invocacion: Token = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                tokenActual.darLexema() == "<"){
                obtenerSiguienteToken()
                var listaArgumentos: ArrayList<Argumento> = esListaArgumentos()
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"){
                    obtenerSiguienteToken()
                    return InvocacionFuncion(invocacion, listaArgumentos)
                }
            }
        }
        return null
    }

    /**
     * <Invocacion> ::= <Indentificador> “<” [<Lista Argumentos>] “>”
     */
    fun esValorInvocacion(): ValorInvocacion? {
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR){
            var invocacion: Token = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                tokenActual.darLexema() == "<"){
                obtenerSiguienteToken()
                var listaArgumentos: ArrayList<Argumento> = esListaArgumentos()
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"){
                    obtenerSiguienteToken()
                    return ValorInvocacion(invocacion, listaArgumentos)
                }
            }
        }
        return null
    }

    private fun esListaArgumentos(): ArrayList<Argumento> {
        var lista: ArrayList<Argumento> = ArrayList()
        return lista
    }

    /**
     * <lectura> ::= "lesen" "<" [<Expresion>] ">" "!"
     */
     fun esLectura(): Lectura? {
        if (tokenActual.darTipo() == Categoria.PALABRA_RESERVADA &&
            tokenActual.darLexema()=="lesen") {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                tokenActual.darLexema() == "<") {
                obtenerSiguienteToken()
                var expresion : Expresion? = esExpresion()
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"
                ) {
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                        obtenerSiguienteToken()
                        return Lectura(expresion)
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
     * <lectura> ::= "lesen" "<" [<Expresion>] ">" "!"
     */
    fun esValorLectura(): ValorLectura? {
        if (tokenActual.darTipo() == Categoria.PALABRA_RESERVADA &&
            tokenActual.darLexema()=="lesen") {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                tokenActual.darLexema() == "<") {
                obtenerSiguienteToken()
                var expresion : Expresion? = esExpresion()
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"
                ) {
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                        obtenerSiguienteToken()
                        return ValorLectura(expresion)
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
                    reportarError("No se usó el operador terminal")
                }
            }else{

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
                val valor:Valor?= esValor()
                if (valor!=null) {
                    if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                        obtenerSiguienteToken()
                        return Asignacion(identificador, valor)
                    } else {
                        reportarError("No se usó el operador terminal")
                    }
                } else {

                }
            } else {

            }
        }
        return null;
    }

    /**
     * <Inicializacion> ::= <Tipo de Dato> <Identificador> “~” <Valor> “!”
     */
    fun esInicializacion(): Inicializacion? {
        if (tokenActual.darTipo() == Categoria.TIPO_DATO) {
            val tipoDato: Token = tokenActual
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.IDENTIFICADOR) {
                val identificador: Token = tokenActual
                obtenerSiguienteToken()
                if (tokenActual.darTipo() == Categoria.OPERADOR_ASIGNACION) {
                    obtenerSiguienteToken()
                    val valor: Valor? = esValor()
                    if (valor != null) {
                        if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                            obtenerSiguienteToken()
                            return Inicializacion(tipoDato,identificador,valor)
                        } else {
                            reportarError("No se usó el operador terminal")
                        }
                    } else {

                    }
                } else {

                }
            } else {

            }
        }
        return null;
    }

    /**
     * <Valor> ::= <Identificador> | <Expresion> | <Invocacion Funcion>
     */
    fun esValor(): Valor? {
        var tipo: Valor? = esValorInvocacion()
        if (tipo != null){
            return tipo
        }
        tipo = esValorExpresion()
        if (tipo != null){
            return tipo
        }
        tipo = esIdentificador()
        if (tipo != null){
            return tipo
        }
        tipo = esValorLectura()
        if (tipo != null){
            return tipo
        }
        return null
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
                break
            }
        }
        return lista
    }

    /**
     * <Condicion> ::= <Condicion If> | <Condicion Switch>
     */
    fun esCondicion(): Condicion? {
        var tipo: Condicion? = esCondicionIf()
        if (tipo != null){
            return tipo
        }
        tipo = esCondicionSwitch()
        if (tipo != null){
            return tipo
        }
        return null
    }

    /**
     * <Condicion Switch> ::= “anre” “<” <Expresion> “>” “(“ [<Lista Casos>] “)”
     */
    fun esCondicionSwitch(): CondicionSwitch? {
        if (tokenActual.darTipo() == Categoria.PALABRA_RESERVADA &&
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
     * <Lista Casos> ::= <Caso> [<Lista Casos>]
     */
    fun esListaCasos(): ArrayList<Caso>{
        var lista: ArrayList<Caso> = ArrayList()
        var caso: Caso? = esCaso()
        while (caso!=null) {
            lista.add(caso)
            caso = esCaso()
        }
        return lista
    }

    /**
     * <Caso> ::= “fill” <Expresion> “?” [<Contenido Caso>]
     */
    fun esCaso(): Caso? {
        if (tokenActual.darTipo() == Categoria.PALABRA_RESERVADA &&
            tokenActual.darLexema()=="fill") {
            obtenerSiguienteToken()
            val expresion: Expresion? = esExpresion()
            if (expresion != null) {
                if (tokenActual.darTipo() == Categoria.DOS_PUNTOS) {
                    obtenerSiguienteToken()
                    var contenido: ContenidoCaso? = esContenidoCaso()
                    return Caso(expresion, contenido)
                } else {

                }
            }
        }
        return null
    }

    /**
     * <Contenido Caso> ::= <Lista Sentencias> [ <Contenido Caso>] | <Caso> | <Break>
     */
    fun esContenidoCaso(): ContenidoCaso? {
        val listaSentencias: ArrayList<Sentencia> = esListaSentencias()
        if (listaSentencias.size > 0) {
            obtenerSiguienteToken()
            val contenido: ContenidoCaso? = esContenidoCaso()
            return ContenidoCaso(listaSentencias, contenido, null)
        }
        val caso: Caso? = esCaso()
        if (caso!=null) {
            obtenerSiguienteToken()
            return ContenidoCaso(ArrayList(), null, caso)
        }
        if (esBreak()) {
            obtenerSiguienteToken()
            return ContenidoCaso(ArrayList(), null, null)
        }
        return null
    }

    /**
     * <Break> ::= “brunch” “!”
     */
    fun esBreak(): Boolean {
        if (tokenActual.darTipo() == Categoria.PALABRA_RESERVADA &&
            tokenActual.darLexema() == "brunch") {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_TERMINAL) {
                obtenerSiguienteToken()
                return true
            } else {

            }
        }
        return false
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

    /**
     * <Expresion> ::= <Expresion Aritmetica> | <Expresion Relacional> | <Expresion Logica> | <Expresion Cadena> | <Caracter>
     */
    fun esExpresion(): Expresion? {
        var tipo: Expresion? = esExpresionAritmetica()
        if (tipo!= null){
            return tipo
        }
        tipo = esExpresionRelacional()
        if (tipo != null){
            return tipo
        }
        tipo = esExpresionLogica()
        if (tipo != null){
            return tipo
        }
        tipo = esExpresionCadena()
        if (tipo!= null){
            return tipo
        }
        tipo  = esExpresionCaracter()
        if (tipo != null)
        {
            return tipo
        }
        return null
    }

    /**
     * <Caracter> ::= “|” Caracter “|” | <Identificador>
     */
     fun esExpresionCaracter(): ExpresionCaracter?{
        if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
            tokenActual.darLexema() =="|"){
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.CARACTER){
                obtenerSiguienteToken()
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION
                    && tokenActual.darLexema() == "|"){
                    return ExpresionCaracter()
                }
            }
        }
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR){
            //imprime
            return ExpresionCaracter()

        }
        return null
    }

    /**
     *<Expresion Cadena> ::= <Cadena> [“$” <Expresion>]
     */
    private fun esExpresionCadena(): ExpresionCadena? {
        if (tokenActual.darTipo() == Categoria.CADENA) {
            obtenerSiguienteToken()
            if (tokenActual.darTipo() == Categoria.OPERADOR_ARITMETICO &&
                tokenActual.darLexema() == "$"){
                var tipo: Expresion? = esExpresion()
                if (tipo != null){
                    return ExpresionCadena()
                }

            }else{
                return ExpresionCadena()
            }
        }

            return null
    }

    /**
     * <Expresion Relacional> ::= "<"<Expresion Relacional>">" <Operador Relacional> <Expresion Relacional> | <Expresion Aritemetica> [<Operador Relacional> <Expresion Aritmetica>]
     */
     fun esExpresionRelacional(): ExpresionRelacional? {
        var tipo: Expresion?
        if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
            tokenActual.darLexema() == "<"){
            obtenerSiguienteToken()
           tipo = esExpresionRelacional()
            if (tipo != null){
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema()==">"){
                    obtenerSiguienteToken()
                   if (tokenActual.darTipo() == Categoria.OPERADOR_RELACIONAL){
                       obtenerSiguienteToken()
                       tipo = esExpresionRelacional()
                       if (tipo != null){
                           return ExpresionRelacional()
                       }
                   }
                }
            }
        }
        tipo = esExpresionAritmetica()
        if (tipo != null){
            if (tokenActual.darTipo() == Categoria.OPERADOR_RELACIONAL){
                obtenerSiguienteToken()
                tipo = esExpresionAritmetica()
                if (tipo!= null){
                    return ExpresionRelacional()
                }
            } else {
                // En caso de que solo sea una expresion Aritmetica
                return ExpresionRelacional()
            }
        }
        return null


    }

    /**
     * <Expresion Aritmetica> ::= "<"<Expresion Aritmetica>">" [<Operador Aritmetico> <Expresion Aritmetica>] | <Valor Numerico> [<Operador Aritmetico> <Expresion Aritmetica>]
     */
    fun esExpresionAritmetica(): ExpresionAritmetica? {
        var tipo: Expresion?
        if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
            tokenActual.darLexema() == "<"){
            obtenerSiguienteToken()
            tipo = esExpresionAritmetica()
            if (tipo != null){
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION && tokenActual.darLexema() == ">"){
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.OPERADOR_ARITMETICO){
                        obtenerSiguienteToken()
                        tipo = esExpresionAritmetica()
                        if (tipo != null){
                            return ExpresionAritmetica()
                        }
                    }else {
                        return ExpresionAritmetica()
                    }

                }

            }

        }
        return null
    }

    /**
     * <Expresion Logica> ::= "<"<Expresion Logica>">" [<Operador Logico> <Expresion Logica>] | <Valor Logico> [<Operador Logico> <Expresion Logica>] | <Operador Logico> <Expresion Logica>
     */
    fun esValorExpresion(): ValorExpresion? {
        return null
    }

    fun esExpresionLogica(): ExpresionLogica? {
        var tipo: Expresion?
        if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
            tokenActual.darLexema() == "<"){
            obtenerSiguienteToken()
            tipo = esExpresionLogica()
            if (tipo!= null){
                if (tokenActual.darTipo() == Categoria.OPERADOR_AGRUPACION &&
                    tokenActual.darLexema() == ">"){
                    obtenerSiguienteToken()
                    if (tokenActual.darTipo() == Categoria.OPERADOR_LOGICO){
                        obtenerSiguienteToken()
                        tipo = esExpresionLogica()
                        if (tipo!= null){
                            return ExpresionLogica()
                        }
                    } else{
                        return ExpresionLogica()
                    }
                }
            }
        }
        var verificacion: ValorLogico? = esValorLogico()
        if (verificacion != null){
            if (tokenActual.darTipo() == Categoria.OPERADOR_LOGICO){
                obtenerSiguienteToken()
                tipo = esExpresionLogica()
                if (tipo!= null){
                    return ExpresionLogica()
                }
            }
        }
        if (tokenActual.darTipo() == Categoria.OPERADOR_LOGICO && tokenActual.darLexema() == "/"){
            obtenerSiguienteToken()
            tipo = esExpresionLogica()
            if (tipo!= null){
                return ExpresionLogica()
            }
        }


        return null
    }

    /**
     * <Valor Logico> ::= “wahr” | “saum” | <Expresion Relacional> | <Indentificador>
     */
     fun esValorLogico(): ValorLogico? {
        var tipo: Expresion?
        if (tokenActual.darTipo() == Categoria.BOOLEAN){
            return ValorLogico()
        }
        tipo = esExpresionRelacional()
        if (tipo!= null){
            return ValorLogico()
        }
        if (tokenActual.darTipo() == Categoria.IDENTIFICADOR){
            return ValorLogico
        }


    }

    fun esInvocacion(): Invocacion? {
        return null
    }

}