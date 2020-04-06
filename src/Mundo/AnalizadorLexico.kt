package Mundo

class AnalizadorLexico {
    // -----------------------------------------------------------------
// Métodos
// -----------------------------------------------------------------
    /**
     * Extrae los tokens de un código fuente dado
     *
     * @param cod - código al cúal se le van a extraer los tokens - !=null
     * @return vector con los tokens
     */
    fun extraerTokens(cod: String): ArrayList<Token?> {
        var token: Token?
        var vectorTokens: ArrayList<Token?> = ArrayList<Token?>()
        // El primer token se extrae a partir de posicin cero
        var i = 0
        // Ciclo para extraer todos los tokens
        while (i < cod.length) { // Extrae el token de la posicin i
            while (cod[i] == ' '){
                i++
            }
            token = extraerSiguienteToken(cod, i)
            vectorTokens.add(token)
            i = token!!.darIndiceSiguiente()
        }
        return vectorTokens
    }

    /**
     * Extrae el token de la cadena cod a partir de la posición i, basandose en el
     * Automata
     *
     * @param cod - código al cúal se le va a extraer un token - codigo!=null
     * @param i   - posición a partir de la cúal se va a extraer el token - i>=0
     * @return token que se extrajo de la cadena
     */
    fun extraerSiguienteToken(cod: String,  i: Int): Token? {

        var token: Token?
        token = extraerIdentificadorVariables(cod, i)
        if (token != null) return token
        token = extraerTipoDatoReal(cod, i)
        if (token != null) return token
        token = extraerEntero(cod, i)
        if (token != null) return token
        token = extraerReal(cod, i)
        if (token != null) return token
        token = extrarTipoDatoEntero(cod, i)
        if (token != null) return token
        token = extraerTipoDatoCaracter(cod, i)
        if (token != null) return token
        token = extraerCaracter(cod, i)
        if (token != null) return token
        token = extraerTipoDatoCadena(cod, i)
        if (token != null) return token
        token = extraerCadena(cod, i)
        if (token != null) return token
        token = extraerTipoDatoBoolean(cod, i)
        if (token != null) return token
        token = extraerBoolean(cod, i)
        if (token != null) return token
        token = extraerTipoDatoClase(cod, i)
        if (token != null) return token
        token = extraerTipoDatoProcedimiento(cod, i)
        if (token != null) return token
        token = extraerTipoDatoFuncion(cod, i)
        if (token != null) return token
        token = extraerTipoDatoConstante(cod, i)
        if (token != null) return token
        token = extraerTipoDatoCondicionSi(cod, i)
        if (token != null) return token
        token = extraerTipoDatoCondicionSINO(cod, i)
        if (token != null) return token
        token = extraerTipoDatoSwitch(cod, i)
        if (token != null) return token
        token = extraerTipoDatoSwitchCasos(cod, i)
        if (token != null) return token
        token = extraerTipoDatoSwitchCierre(cod, i)
        if (token != null) return token
        token = extraerOperadorAritmeticoSuma(cod, i)
        if (token != null) return token
        token = extraerOperadorAritmeticoResta(cod, i)
        if (token != null) return token
        token = extraerOperadorAritmeticoMultiplicacion(cod, i)
        if (token != null) return token
        token = extraerOperadorAritmeticoDivision(cod, i)
        if (token != null) return token
        token = extraerOperadorRelacional(cod, i)
        if (token != null) return token
        token = extraerOperadorLogico(cod, i)
        if (token != null) return token
        token = extraerBloqueSentencia(cod, i)
        if (token != null) return token
        token = extraerOperadorTerminal(cod, i)
        if (token != null) return token
        token = extraerDatoCicloFor(cod, i)
        if (token != null) return token
        token = extraerDatoCicloWhile(cod, i)
        if (token != null) return token
        token = extraerOperadorAsignacion(cod, i)
        if (token != null) return token
        token = extraerOperadorAgrupacion(cod, i)
        if (token != null) return token
        token = extraerOperadorSeparador(cod, i)
        if (token != null) return token
        token = extraerOperadorIncremento(cod, i)
        if (token != null) return token
        token = extraerComentarioBloque(cod, i)
        if (token != null) return token
        token = extraerNoReconocido(cod, i)
        return token
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso ganz que
     * representa los números enteros.
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cúal se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extrarTipoDatoEntero(cod: String, i: Int): Token? {
        var lex: String
        if (cod.substring(i, cod.length).length >= 4) {
            var j = i
            if (cod[j] == 'g') {
                j++
                if (cod[j] == 'a') {
                    j++
                    if (cod[j] == 'n') {
                        j++
                        if (cod[j] == 'z') {
                            j++
                            lex = cod.substring(i, j)
                            return Token(lex, Token.TIPO_DATO, j)
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * Método que extrae los valores que recibe el tipo de dato entero
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerEntero(cod: String, i: Int): Token? {
        var j = i
        val lex: String
        if (esDigito(cod[j])) {
            do {
                j++
            } while (j < cod.length && esDigito(cod[j]))

            if (j == cod.length){
                lex = cod.substring(i, j)
                return Token(lex, Token.ENTERO, j)
            }
            else
            {
                if (cod[j].toInt() != 39){
                    lex = cod.substring(i, j)
                    return Token(lex, Token.ENTERO, j)
                }
            }


        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso iecht que
     * representa los números reales.
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerTipoDatoReal(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 4) {
            if (cod[j] == 'i') {
                j++
                if (cod[j] == 'e') {
                    j++
                    if (cod[j] == 'c') {
                        j++
                        if (cod[j] == 'h') {
                            j++
                            if (cod[j] == 't') {
                                j++
                                lex = cod.substring(i, j)
                                return Token(lex, Token.TIPO_DATO, j)
                            }
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * Método que extrae los valores que recibe el tipo de dato real
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerReal(cod: String, i: Int): Token? {
        var j: Int
        val lex: String
        if (esDigito(cod[i])) {
            j = i + 1
            if (j < cod.length && esDigito(cod[j])) {
                do {
                    j++
                    if (j < cod.length && cod[j].toInt() == 39) {
                        j++
                        if (j < cod.length && esDigito(cod[j])) {
                            do {
                                j++
                            } while (j < cod.length && esDigito(cod[j]))
                            lex = cod.substring(i, j)
                            return Token(lex, Token.REAL, j)
                        }
                    }
                } while (j < cod.length && esDigito(cod[j]))
            }
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso zeichen que
     * representa los caracteres.
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerTipoDatoCaracter(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 7) {
            if (cod[j] == 'z') {
                j++
                if (cod[j] == 'e') {
                    j++
                    if (cod[j] == 'i') {
                        j++
                        if (cod[j] == 'c') {
                            j++
                            if (cod[j] == 'h') {
                                j++
                                if (cod[j] == 'e') {
                                    j++
                                    if (cod[j] == 'n') {
                                        j++
                                        lex = cod.substring(i, j)
                                        return Token(
                                            lex,
                                            Token.TIPO_DATO,
                                            j
                                        )
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
     * Método que extrae los valores que recibe el tipo de dato caracter
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerCaracter(cod: String, i: Int): Token? {
        var j = i
        var lex: String
        if (cod[j] == '|') {
            j++
            if (cod.length > j + 1) {
                j++
                if (cod[j] == '|') {
                    j++
                    lex = cod.substring(i, j)
                    return Token(lex, Token.CARACTER, j)
                }
            }
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso fessel que
     * representa las cadenas.
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerTipoDatoCadena(cod: String, i: Int): Token? {
        var lex: String
        if (cod.substring(i, cod.length).length >= 6) {
            var j = i
            if (cod[j] == 'f') {
                j++
                if (cod[j] == 'e') {
                    j++
                    if (cod[j] == 's') {
                        j++
                        if (cod[j] == 's') {
                            j++
                            if (cod[j] == 'e') {
                                j++
                                if (cod[j] == 'l') {
                                    j++
                                    lex = cod.substring(i, j)
                                    return Token(
                                        lex,
                                        Token.TIPO_DATO,
                                        j
                                    )
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
     * Método que extrae los valores que recibe el tipo de dato String
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerCadena(cod: String, i: Int): Token? {
        var j = i
        var lex:String
        if (cod[j] == '|') {
            j++
            if (j < cod.length && cod[j] == '|') {
                j++
                while (j < cod.length && cod[j] != '|') {
                    j++
                }
                j++
                if (j < cod.length && cod[j] == '|') {
                    j++
                    lex = cod.substring(i, j)
                    return Token(lex, Token.CADENA, j)
                }
            }
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso dich que
     * representa boolean.
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerTipoDatoBoolean(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 4) {
            if (cod[j] == 'd') {
                j++
                if (cod[j] == 'i') {
                    j++
                    if (cod[j] == 'c') {
                        j++
                        if (cod[j] == 'h') {
                            j++
                            lex = cod.substring(i, j)
                            return Token(lex, Token.TIPO_DATO, j)
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * Método que extrae los valores que recibe el tipo de dato boolean ( "whar"
     * equivale a "true" , "saum" equivale a "false").
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerBoolean(cod: String, i: Int): Token? {
        var lex:String
        if (cod.substring(i, cod.length).length >= 4) {
            var j = i
            if (cod[j] == 'w') {
                j++
                if (cod[j] == 'a') {
                    j++
                    if (cod[j] == 'h') {
                        j++
                        if (cod[j] == 'r') {
                            j++
                            lex = cod.substring(i, j)
                            return Token(lex, Token.BOOLEAN, j)
                        }
                    }
                }
            } else {
                j = i
                if (cod[j] == 's') {
                    j++
                    if (cod[j] == 'a') {
                        j++
                        if (cod[j] == 'u') {
                            j++
                            if (cod[j] == 'm') {
                                j++
                                lex = cod.substring(i, j)
                                return Token(lex, Token.BOOLEAN, j)
                            }
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso klasse que
     * representa la palabra reservada clase.
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerTipoDatoClase(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 6) {
            if (cod[j] == 'k') {
                j++
                if (cod[j] == 'l') {
                    j++
                    if (cod[j] == 'a') {
                        j++
                        if (cod[j] == 's') {
                            j++
                            if (cod[j] == 's') {
                                j++
                                if (cod[j] == 'e') {
                                    j++
                                    lex = cod.substring(i, j)
                                    return Token(
                                        lex,
                                        Token.PALABRA_RESERVADA,
                                        j
                                    )
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
     * Método para extraer el nombre del tipo de dato, en este caso shule que
     * representa la palabra reservada procedimiento.
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerTipoDatoProcedimiento(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 5) {
            if (cod[j] == 's') {
                j++
                if (cod[j] == 'h') {
                    j++
                    if (cod[j] == 'u') {
                        j++
                        if (cod[j] == 'l') {
                            j++
                            if (cod[j] == 'e') {
                                j++
                                lex = cod.substring(i, j)
                                return Token(
                                    lex,
                                    Token.PALABRA_RESERVADA,
                                    j
                                )
                            }
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso technik que
     * representa la palabra reservada función.
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerTipoDatoFuncion(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 7) {
            if (cod[j] == 't') {
                j++
                if (cod[j] == 'e') {
                    j++
                    if (cod[j] == 'c') {
                        j++
                        if (cod[j] == 'h') {
                            j++
                            if (cod[j] == 'n') {
                                j++
                                if (cod[j] == 'i') {
                                    j++
                                    if (cod[j] == 'k') {
                                        j++
                                        lex = cod.substring(i, j)
                                        return Token(
                                            lex,
                                            Token.PALABRA_RESERVADA,
                                            j
                                        )
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
     * Método para extraer el nombre del tipo de dato, en este caso ende que
     * representa la palabra reservada constante.
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerTipoDatoConstante(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 4) {
            if (cod[j] == 'e') {
                j++
                if (cod[j] == 'n') {
                    j++
                    if (cod[j] == 'd') {
                        j++
                        if (cod[j] == 'e') {
                            j++
                            lex = cod.substring(i, j)
                            return Token(
                                lex,
                                Token.PALABRA_RESERVADA,
                                j
                            )
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso wenn que
     * representa la palabra reservada Si (que se refiere a la condicion).
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerTipoDatoCondicionSi(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 4) {
            if (cod[j] == 'w') {
                j++
                if (cod[j] == 'e') {
                    j++
                    if (cod[j] == 'n') {
                        j++
                        if (cod[j] == 'n') {
                            j++
                            lex = cod.substring(i, j)
                            return Token(
                                lex,
                                Token.PALABRA_RESERVADA,
                                j
                            )
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso hein que
     * representa la palabra reservada Sino (que se refiere a la condicion).
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerTipoDatoCondicionSINO(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 4) {
            if (cod[j] == 'h') {
                j++
                if (cod[j] == 'e') {
                    j++
                    if (cod[j] == 'i') {
                        j++
                        if (cod[j] == 'n') {
                            j++
                            lex = cod.substring(i, j)
                            return Token(
                                lex,
                                Token.PALABRA_RESERVADA,
                                j
                            )
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso anre que
     * representa la palabra reservada Switch.
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerTipoDatoSwitch(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 4) {
            if (cod[j] == 'a') {
                j++
                if (cod[j] == 'n') {
                    j++
                    if (cod[j] == 'r') {
                        j++
                        if (cod[j] == 'e') {
                            j++
                            lex = cod.substring(i, j)
                            return Token(
                                lex,
                                Token.PALABRA_RESERVADA,
                                j
                            )
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso fall que
     * representa la palabra reservada casos (que se refiere a los casos de la
     * condicion Switch).
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerTipoDatoSwitchCasos(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 4) {
            if (cod[j] == 'f') {
                j++
                if (cod[j] == 'a') {
                    j++
                    if (cod[j] == 'l') {
                        j++
                        if (cod[j] == 'l') {
                            j++
                            lex = cod.substring(i, j)
                            return Token(
                                lex,
                                Token.PALABRA_RESERVADA,
                                j
                            )
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso brunch que
     * representa la palabra reservada break (que se refiere al cierre de un caso en
     * la condición Switch).
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerTipoDatoSwitchCierre(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 4) {
            if (cod[j] == 'b') {
                j++
                if (cod[j] == 'r') {
                    j++
                    if (cod[j] == 'u') {
                        j++
                        if (cod[j] == 'n') {
                            j++
                            if (cod[j] == 'c') {
                                j++
                                if (cod[j] == 'h') {
                                    j++
                                    lex = cod.substring(i, j)
                                    return Token(
                                        lex,
                                        Token.PALABRA_RESERVADA,
                                        j
                                    )
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
     * Método para extraer el nombre del tipo de dato, en este caso extrae el
     * simbolo para una de las operaciones basicas ( "$" equivale a "+").
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerOperadorAritmeticoSuma(cod: String, i: Int): Token? {
        if (cod[i] == '$') {
            val j = i + 1
            val lex = cod.substring(i, j)
            return Token(lex, Token.OPERADOR_ARITMETICO, j)
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso extrae el
     * simbolo para una de las operaciones basicas ("¬"equivale a "-" ).
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerOperadorAritmeticoResta(cod: String, i: Int): Token? {
        if (cod[i] == '¬') {
            val j = i + 1
            val lex = cod.substring(i, j)
            return Token(lex, Token.OPERADOR_ARITMETICO, j)
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso extrae el
     * simbolo para una de las operaciones basicas ("." equivale a "*" ).
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerOperadorAritmeticoMultiplicacion(cod: String, i: Int): Token? {
        if (cod[i] == '.') {
            val j = i + 1
            val lex = cod.substring(i, j)
            return Token(lex, Token.OPERADOR_ARITMETICO, j)
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso extrae el
     * simbolo para una de las operaciones basicas ( "_" equivale a "/" ).
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerOperadorAritmeticoDivision(cod: String, i: Int): Token? {
        if (cod[i] == '_') {
            val j = i + 1
            val lex = cod.substring(i, j)
            return Token(lex, Token.OPERADOR_ARITMETICO, j)
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso extrae los
     * simbolos para cada uno de los relacionadores ( "=~" equivale a "==", "/~"
     * equivale a "!=", "«~" equivale a "<=" , "»~" equivale a ">=", "»" equivale a
     * ">" , "«" equivale a "<").
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerOperadorRelacional(cod: String, i: Int): Token? {
        var j = i
        var lex:String
        if (cod.substring(i, cod.length).length >= 2) {
            if (cod[j] == '=') {
                j++
                if (cod[j] == '~') {
                    j++
                    lex = cod.substring(i, j)
                    return Token(lex, Token.OPERADOR_RELACIONAL, j)
                }
            }
            j = i
            if (cod[j] == '/') {
                j++
                if (cod[j] == '~') {
                    j++
                    lex = cod.substring(i, j)
                    return Token(lex, Token.OPERADOR_RELACIONAL, j)
                }
            }
            j = i
            if (cod[j] == '«') {
                j++
                if (cod[j] == '~') {
                    j++
                    lex = cod.substring(i, j)
                    return Token(lex, Token.OPERADOR_RELACIONAL, j)
                }
            }
            j = i
            if (cod[j] == '»') {
                j++
                if (cod[j] == '~') {
                    j++
                    lex = cod.substring(i, j)
                    return Token(lex, Token.OPERADOR_RELACIONAL, j)
                }
            }
        }
        j = i
        if (cod.substring(i, cod.length).length >= 1) {
            if (cod[j] == '»') {
                j++
                lex = cod.substring(i, j)
                return Token(lex, Token.OPERADOR_RELACIONAL, j)
            }
            j = i
            if (cod[j] == '«') {
                j++
                lex = cod.substring(i, j)
                return Token(lex, Token.OPERADOR_RELACIONAL, j)
            }
        }
        return null
    }

    /**
     * Método para extraer los operadores logicos and or y not
     * que en este caso ## equivale a un and, @@ equivale a un or
     * y / equivale a un not
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerOperadorLogico(cod: String, i: Int): Token? {
        var j = i
        var lex:String
        if (cod.substring(i, cod.length).length >= 2) {
            if (cod[i] == '#') {
                j++
                if (cod[i + 1] == '#') {
                    j++
                    lex = cod.substring(i, j)
                    return Token(lex, Token.OPERADOR_LOGICO, j)
                }
            }
            j = i
            if (cod[i] == '@') {
                j++
                if (cod[i + 1] == '@') {
                    j++
                    lex = cod.substring(i, j)
                    return Token(lex, Token.OPERADOR_LOGICO, j)
                }
            }
        }
        j = i
        if (cod[i] == '/') {
            j++
            lex = cod.substring(i, j)
            return Token(lex, Token.OPERADOR_LOGICO, j)
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso extrae el
     * simbolo que representa el terminal ("!" equivale a ";").
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerOperadorTerminal(cod: String, i: Int): Token? {
        if (cod[i] == '!') {
            val j = i + 1
            val lex = cod.substring(i, j)
            return Token(lex, Token.OPERADOR_TERMINAL, j)
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso kreis que
     * representa la palabra reservada for.
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerDatoCicloFor(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 5) {
            if (cod[j] == 'k') {
                j++
                if (cod[j] == 'r') {
                    j++
                    if (cod[j] == 'e') {
                        j++
                        if (cod[j] == 'i') {
                            j++
                            if (cod[j] == 's') {
                                j++
                                lex = cod.substring(i, j)
                                return Token(
                                    lex,
                                    Token.PALABRA_RESERVADA,
                                    j
                                )
                            }
                        }
                    }
                }
            }
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso reprise que
     * representa la palabra reservada while.
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerDatoCicloWhile(cod: String, i: Int): Token? {
        var lex:String
        var j = i
        if (cod.substring(i, cod.length).length >= 7) {
            if (cod[j] == 'r') {
                j++
                if (cod[j] == 'e') {
                    j++
                    if (cod[j] == 'p') {
                        j++
                        if (cod[j] == 'r') {
                            j++
                            if (cod[j] == 'i') {
                                j++
                                if (cod[j] == 's') {
                                    j++
                                    if (cod[j] == 'e') {
                                        j++
                                        lex = cod.substring(i, j)
                                        return Token(
                                            lex,
                                            Token.PALABRA_RESERVADA,
                                            j
                                        )
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
     * Método para extraer el nombre del tipo de dato, en este caso extrae el
     * simbolo que representa asignación ("~" equivale a "=").
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerOperadorAsignacion(cod: String, i: Int): Token? {
        if (cod[i] == '~') {
            val j = i + 1
            val lex = cod.substring(i, j)
            return Token(lex, Token.OPERADOR_ASIGNACION, j)
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso extrae los
     * simbolos para agrupar ( "<" equivale a "(", ">" equivale a ")" ).
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cual se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerOperadorAgrupacion(cod: String, i: Int): Token? {
        if (cod[i] == '<' || cod[i] == '>') {
            val j = i + 1
            val lex = cod.substring(i, j)
            return Token(lex, Token.OPERADOR_AGRUPACION, j)
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso extrae los
     * simbolos para separar ( ":" equivale a "," ).
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cúal se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerOperadorSeparador(cod: String, i: Int): Token? {
        if (cod[i] == ':') {
            val j = i + 1
            val lex = cod.substring(i, j)
            return Token(lex, Token.OPERADOR_SEPARACION, j)
        }
        return null
    }

    /**
     * Método para extraer el nombre del tipo de dato, en este caso extrae los
     * simbolos para determinar donde empieza y donde acaba un bloque ( "<!"
     * equivale a "{", "!>" equivale a "}" ).
     *
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cúal se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerBloqueSentencia(cod: String, i: Int): Token? {
        if (cod[i] == '[' || cod[i] == ']') {
            val j = i + 1
            val lex = cod.substring(i, j)
            return Token(lex, Token.OPERADOR_AGRUPACION, j)
        }
        return null
    }






    /**
     * Método para extraer el nombre del tipo de un identificador, en este caso extrae
     * el identificador de variables.
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cúal se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerIdentificadorVariables(cod: String, i: Int): Token? {
        var j = i
        var lex:String
        if (cod[j] == '-') {
            j++
            if (j < cod.length && esLetra(cod[j])) {
                do {
                    j++
                } while (j < cod.length && esLetra(cod[j]))
                if (j < cod.length && cod[j] == '-') {
                    j++
                    lex = cod.substring(i, j)
                    return Token(lex, Token.IDENTIFICADOR, j)
                }
            }
        }
        return null
    }

    /**
     * Método para extrar un operador de incremente el cual se encarga de incrementar
     * el valor de una variable positivamente
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cúal se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerOperadorIncremento(cod: String, i: Int): Token? {
        var j = i
        var lex:String
        if (cod.substring(i, cod.length).length >= 2) {
            if (cod[j] == '+') {
                j++
                if (cod[j] == '$') {
                    j++
                    lex = cod.substring(i, j)
                    return Token(lex, Token.OPERADOR_INCREMENTO,j)
                }
            }
        }
        return null
    }

    /**
     * Método para un comentario de bloque que inicia con % y finaliza con %
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cúal se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerComentarioBloque(cod: String, i: Int): Token? {
        var j = i
        var lex:String
        if (cod[j] == '%') {
            j++
            while (j < cod.length && cod[j] != '%') {
                j++
            }
            if (j == cod.length){
                return null
            }else{
                j++
                lex = cod.substring(i, j)
                return Token(lex, Token.COMENTARIO_BLOQUE, j)
            }


        }
        return null
    }

    /**
     * Método para extraer un operador de decremento el cual se encarga de decrementar
     * el valor de una variabale negativamente
     * @param cod codigo de el cúal se va a extraer el nombre - codigo!=null
     * @param i   posición a partir de la cúal se va a extraer nombre - i >=0
     * @return el nombre del tipo de la cadena
     */
    fun extraerOperadorDecremento(cod: String, i: Int): Token? {
        var j = i
        var lex:String
        if (cod.substring(i, cod.length).length >= 2) {
            if (cod[j] == '-') {
                j++
                if (cod[j] == '¬') {
                    j++
                    lex = cod.substring(i, j)
                    return Token(lex, Token.OPERADOR_DECREMENTO,j)
                }
            }
        }
        return null
    }

    /**
     * Extraer un lexema no reconocido de la cadena cod a partir de la posición i.
     * Antes de utilizar este método, debe haberse intentado todos los otros métodos
     * para los otros tipos de token
     *
     * @param cod - código al cual se le va a extraer el token no reconocido -
     * codigo!=null
     * @param i   - posición a partir de la cual se va a extraer el token no
     * reconocido - 0<=indice<codigo.length></codigo.length>()
     * @return el token no reconocido. El Mundo.Token se compone de lexema, el tipo y la
     * posición del siguiente lexema.
     */
    fun extraerNoReconocido(cod: String, i: Int): Token {
        val lexema = cod.substring(i, i + 1)
        val j = i + 1
        return Token(lexema, Token.NORECONOCIDO, j)
    }

    /**
     * Determina si un carácter es un dígito
     *
     * @param caracter - Carácter que se va a analizar - caracter!=null,
     * @return true o false según el carácter sea un dgito o no
     */
    fun esDigito(caracter: Char): Boolean {
        return caracter >= '0' && caracter <= '9'
    }

    /**
     * Determina si un caracter es una letra
     *
     * @param caracter - Caracter que se va a analizar - caracter!=null,
     * @return true o false segun el carácter sea una letra o no
     */
    fun esLetra(caracter: Char): Boolean {
        return caracter >= 'A' && caracter <= 'Z' || caracter >= 'a' && caracter <= 'z'
    }
}