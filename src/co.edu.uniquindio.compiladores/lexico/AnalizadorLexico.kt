package co.edu.uniquindio.compiladores.lexico


class AnalizadorLexico(var codigoFuente:String) {

    var posicionActual = 0
    var caracterActual = codigoFuente[0]
    var listaTokens = ArrayList<Token>()
    var listaErroes = ArrayList<Error>()
    var finCodigo = 0.toChar()
    var filaActual = 0
    var columnaActual = 0

    fun almacenarToken(lexema:String, categoria: Categoria, fila:Int, columna:Int) = listaTokens.add(
        Token(lexema, categoria, fila, columna)
    )

    fun almacenarError(error:String,fila:Int,columna:Int) = listaErroes.add(Error(error, fila, columna))

    fun hacerBT(posicionInicial:Int,filaInicial:Int,columnaInicial:Int){
        posicionActual = posicionInicial
        filaActual = filaInicial
        columnaActual = columnaInicial
        caracterActual = codigoFuente[posicionActual]
    }
    fun analizar(){
        while(caracterActual != finCodigo){
            if(caracterActual == ' ' || caracterActual == '\t' || caracterActual == '\n' ){
                obtenerSiguienteCaracter()
                continue
            }
            if(esEntero()) continue
            if(esDecimal()) continue
            if(esDatoEntero()) continue
            if(esDatoReal()) continue
            if(esDatoCaracter()) continue
            if(esCaracter()) continue
            if(esDatoString()) continue
            if(esString()) continue
            if(esDatoBoolean()) continue
            if(esBoolean()) continue
            if(esOperadorAritmetico()) continue
            if(esOperadorAsignacion()) continue
            if(esOperadorRelacional()) continue
            if(esOperadorLogico())  continue
            if(esComentarioBloque()) continue
            if(esIdentificador())continue
            if(esCondicionIf()) continue
            if(esCondicionElse()) continue
            if(esSwitch()) continue
            if(esCasosSwitch()) continue
            if(esBreak()) continue
            if(esIteradorFor()) continue
            if(esCicloWhile()) continue
            if(esClase()) continue
            if(esProcedimiento()) continue
            if(esFuncion()) continue
            if(esConstante()) continue
            if(esOperadorTerminal()) continue
            if(esOperadorAgrupacion()) continue
            if(esOperadorSeparacion()) continue
            if(esBloqueSentencia()) continue
            if(esOperadorIncremento()) continue
            if(esOperadorDecremento()) continue
            if(esPunto()) continue
            if(esDosPuntos()) continue
            if(esRetorno()) continue
            if(esLectura()) continue
            if(esImpresion()) continue

            almacenarToken(""+caracterActual,
                Categoria.DESCONOCIDO,filaActual,columnaActual)
            obtenerSiguienteCaracter()
        }
    }

    fun esDatoEntero():Boolean{
        if(caracterActual == 'g'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()
            if (caracterActual == 'a'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if (caracterActual == 'n'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if (caracterActual == 'z'){

                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        almacenarToken(lexema,
                                Categoria.DATO_ENTERO,filaInicial,columnaInicial)
                        return true
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }
    fun esDatoReal():Boolean{
        if (caracterActual == 'e'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()
            if (caracterActual == 'c'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if (caracterActual == 'h'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if (caracterActual == 't'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()

                        almacenarToken(lexema,
                                Categoria.DATO_REAL,filaInicial,columnaInicial)
                        return true
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }
    fun esDatoCaracter():Boolean{
        if(caracterActual == 'z'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'e'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'i'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'c'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 'h' ){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()
                            if(caracterActual =='e'){
                                lexema += caracterActual
                                obtenerSiguienteCaracter()
                                if (caracterActual == 'n'){
                                    lexema += caracterActual
                                    obtenerSiguienteCaracter()

                                    almacenarToken(lexema,
                                            Categoria.DATO_CARACTER,filaInicial,columnaInicial)
                                    return true
                                }
                                hacerBT(posicionInicial,filaInicial,columnaInicial)
                                return false
                            }
                            hacerBT(posicionInicial,filaInicial,columnaInicial)
                            return false
                        }
                        hacerBT(posicionInicial,filaInicial,columnaInicial)
                        return false
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }

        }
        return false
    }
    fun esCaracter():Boolean{
        if(caracterActual == '|'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()
            // es una cadena, hace bt
            if(caracterActual == '|'){
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            if(caracterActual == '&'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'n' || caracterActual == 's' || caracterActual == 't' || caracterActual == 'l'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == '|'){

                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        almacenarToken(lexema,
                                Categoria.CARACTER,filaInicial,columnaInicial)
                        return true
                    }
                }
                almacenarError("Caracter de escape no valido",filaActual,columnaActual)
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == '|'){
                lexema += caracterActual
                obtenerSiguienteCaracter()

                almacenarToken(lexema,
                        Categoria.CARACTER,filaInicial,columnaInicial)
                return true
            }
            almacenarError("No cerro el caracter",filaActual,columnaActual)
            // por si no cierra el caracter
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false


        }
        return false
    }
    fun esDatoString():Boolean{
        if(caracterActual == 'f'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'e'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 's'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 's'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 'e'){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()
                            if(caracterActual == 'l'){
                                lexema += caracterActual
                                obtenerSiguienteCaracter()

                                almacenarToken(lexema,
                                        Categoria.DATO_STRING,filaInicial,columnaInicial)
                                return true
                            }
                            hacerBT(posicionInicial,filaInicial,columnaInicial)
                            return false
                        }
                        hacerBT(posicionInicial,filaInicial,columnaInicial)
                        return false
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }
    //falta
    fun esString():Boolean{
        if(caracterActual == '|'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == '|'){

                lexema += caracterActual
                obtenerSiguienteCaracter()

                while( caracterActual != '|' && caracterActual != finCodigo){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == '&'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 'n' || caracterActual == 's' || caracterActual == 't' || caracterActual == 'l'){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()
                        }
                        else{
                            hacerBT(posicionInicial,filaInicial,columnaInicial)
                            return false
                        }

                    }
                }
                if(caracterActual == finCodigo){
                    print("No cerro la cosa fin")
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }

                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual== '|'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    almacenarToken(lexema,
                        Categoria.CADENA,filaInicial,columnaInicial)
                    return true
                }
                print("No cerro la cosa cosa")
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
                //reporta error hace bt

            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }

    fun esDatoBoolean():Boolean{
        if(caracterActual == 'd'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'i'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'c'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'h'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()

                        almacenarToken(lexema,
                                Categoria.DATO_BOOLEAN,filaInicial,columnaInicial)
                        return true
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }
    fun esBoolean():Boolean{
        if(caracterActual == 'w' || caracterActual == 's'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            if(caracterActual == 'w'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'a'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'h'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 'r'){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()
                            almacenarToken(lexema,
                                    Categoria.BOOLEAN,filaInicial,columnaInicial)
                            return true
                        }
                        hacerBT(posicionInicial,filaInicial,columnaInicial)
                        return false
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }


            if(caracterActual == 's'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'a'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'u'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 'm'){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()
                            almacenarToken(lexema,
                                    Categoria.BOOLEAN,filaInicial,columnaInicial)
                            return true
                        }
                        hacerBT(posicionInicial,filaInicial,columnaInicial)
                        return false
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
        }
        return false
    }
    fun esOperadorAritmetico():Boolean{
        if(caracterActual == '$' || caracterActual == '¬' || caracterActual == '_' || caracterActual == '.'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()

            // hace bt porque puede ser un identificador
            if(caracterActual.isLetter()){
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            almacenarToken(lexema,
                    Categoria.OPERADOR_ARITMETICO,filaInicial,columnaInicial)
            return true
        }
        return false
    }
    fun esOperadorAsignacion():Boolean{
        if(caracterActual == '~'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()
            almacenarToken(lexema,
                    Categoria.OPERADOR_ASIGNACION,filaInicial,columnaInicial)
            return true
        }
        return false
    }
    // primero hacer automata
    fun esOperadorRelacional():Boolean{
        if(caracterActual == '=' || caracterActual == '/'|| caracterActual == '«'|| caracterActual == '»'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            if(caracterActual == '=' || caracterActual == '/'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == '~'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()

                    almacenarToken(lexema,
                        Categoria.OPERADOR_RELACIONAL,filaInicial,columnaInicial)
                    return true
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == '~')
            {
                lexema += caracterActual
                obtenerSiguienteCaracter()

            }
            almacenarToken(lexema,
                Categoria.OPERADOR_RELACIONAL,filaInicial,columnaInicial)
            return true

        }
        return false
    }

    fun esOperadorLogico():Boolean{
        if(caracterActual == '@' || caracterActual == '#'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            if(caracterActual == '@')
            {
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == '@'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()

                    almacenarToken(lexema,
                            Categoria.OPERADOR_LOGICO,filaInicial,columnaInicial)
                    return true
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }

            if(caracterActual == '#'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == '#'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()

                    almacenarToken(lexema,
                            Categoria.OPERADOR_LOGICO,filaInicial,columnaInicial)
                    return true
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
        }
        return false
    }
    fun esComentarioBloque():Boolean{
        if(caracterActual == '%'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()

            while( caracterActual != '%' && caracterActual != finCodigo){
                lexema += caracterActual
                obtenerSiguienteCaracter()
            }

            if(caracterActual == '%'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                almacenarToken(lexema,
                        Categoria.COMENTARIO_BLOQUE,filaInicial,columnaInicial)
                return true
            }

            // es porque no lo cerro
            almacenarError("Falta cerrar el comentario de bloque",filaActual,columnaActual)
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
            // se supone que debe saber cuando lleguee al final para
            // determinar que nunca cerro el coso
        }
        return false
    }
    fun esCondicionIf():Boolean{
        if(caracterActual == 'w'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'e'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'n'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'n'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()

                        almacenarToken(lexema,
                                Categoria.CONDICION_If,filaInicial,columnaInicial)
                        return true
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false

        }
        return false
    }
    fun esCondicionElse():Boolean{
        if(caracterActual == 'h'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'e'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'i'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'n'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()

                        almacenarToken(lexema,
                                Categoria.CONDICION_ELSE,filaInicial,columnaInicial)
                        return true
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }
    fun esSwitch():Boolean{
        if(caracterActual == 'a'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'n'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'r'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'e'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()

                        almacenarToken(lexema,
                                Categoria.SWITCH,filaInicial,columnaInicial)
                        return true
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }
    fun esCasosSwitch():Boolean{
        if(caracterActual == 'f'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'a'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'l'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'l'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()

                        almacenarToken(lexema,
                                Categoria.CASO,filaInicial,columnaInicial)
                        return true
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }
    fun esBreak():Boolean{
        if(caracterActual == 'b'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'r'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'u'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'n'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 'c'){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()
                            if(caracterActual == 'h'){
                                lexema += caracterActual
                                obtenerSiguienteCaracter()

                                almacenarToken(lexema,
                                        Categoria.BREAK,filaInicial,columnaInicial)
                                return true
                            }
                            hacerBT(posicionInicial,filaInicial,columnaInicial)
                            return false
                        }
                        hacerBT(posicionInicial,filaInicial,columnaInicial)
                        return false
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }
    fun esIteradorFor():Boolean{
        if(caracterActual == 'k'){

            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'r'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'e' ){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'i'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 's'){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()

                            almacenarToken(lexema,
                                    Categoria.ITERADOR_FOR,filaInicial,columnaInicial)
                            return true
                        }
                        hacerBT(posicionInicial,filaInicial,columnaInicial)
                        return false
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }
    fun esCicloWhile():Boolean{
        if(caracterActual == 'r'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'e'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'p'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'r'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 'i'){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()
                            if(caracterActual == 's'){
                                lexema += caracterActual
                                obtenerSiguienteCaracter()
                                if(caracterActual == 'e'){
                                    lexema += caracterActual
                                    obtenerSiguienteCaracter()

                                    almacenarToken(lexema,
                                            Categoria.CICLO_WHILE,filaInicial,columnaInicial)
                                    return true
                                }
                                hacerBT(posicionInicial,filaInicial,columnaInicial)
                                return false
                            }
                            hacerBT(posicionInicial,filaInicial,columnaInicial)
                            return false
                        }
                        hacerBT(posicionInicial,filaInicial,columnaInicial)
                        return false
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }
    fun esClase():Boolean{
        if(caracterActual == 'k'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'l'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'a'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 's'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 's'){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()
                            if(caracterActual == 'e'){
                                lexema += caracterActual
                                obtenerSiguienteCaracter()

                                almacenarToken(lexema,
                                        Categoria.CLASE,filaInicial,columnaInicial)
                                return true
                            }
                            hacerBT(posicionInicial,filaInicial,columnaInicial)
                            return false
                        }
                        hacerBT(posicionInicial,filaInicial,columnaInicial)
                        return false
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }
    fun esProcedimiento():Boolean{
        if(caracterActual == 's'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'h'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'u'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'l'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 'e'){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()

                            almacenarToken(lexema,
                                    Categoria.PROCEDIMIENTO,filaInicial,columnaInicial)
                            return true
                        }
                        hacerBT(posicionInicial,filaInicial,columnaInicial)
                        return false
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }
    fun esFuncion():Boolean{
        if(caracterActual == 't'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'e'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'c'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'h'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 'n'){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()
                            if(caracterActual == 'i'){
                                lexema += caracterActual
                                obtenerSiguienteCaracter()
                                if(caracterActual == 'k'){
                                    lexema += caracterActual
                                    obtenerSiguienteCaracter()
                                    almacenarToken(lexema,
                                            Categoria.FUNCION,filaInicial,columnaInicial)
                                    return true
                                }
                            }
                        }
                    }
                }
            }
        }
        return false
    }
    fun esConstante():Boolean{
        if(caracterActual == 'e'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'n'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'd'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'e'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()

                        almacenarToken(lexema,
                                Categoria.CONSTANTE,filaInicial,columnaInicial)
                        return true
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }
    fun esOperadorTerminal():Boolean{
        if(caracterActual == '!'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()

            almacenarToken(lexema,
                    Categoria.OPERADOR_TERMINAL,filaInicial,columnaInicial)
            return true

        }
        return false
    }
    fun esOperadorAgrupacion():Boolean{
        if(caracterActual == '<' || caracterActual == '>'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()

            almacenarToken(lexema,
                    Categoria.OPERADOR_AGRUPACION,filaInicial,columnaInicial)
            return true
        }
        return false
    }
    fun esOperadorSeparacion():Boolean{
        if(caracterActual == ':'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()

            almacenarToken(lexema,
                    Categoria.OPERADOR_SEPARACION,filaInicial,columnaInicial)
            return true
        }
        return false
    }
    fun esBloqueSentencia():Boolean{
        if(caracterActual == '(' || caracterActual == ')'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()

            almacenarToken(lexema,
                    Categoria.BLOQUE_SENTENCIA,filaInicial,columnaInicial)
            return true
        }
        return false
    }
    fun esOperadorIncremento():Boolean{
        if(caracterActual == '+'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == '$'){
                lexema += caracterActual
                obtenerSiguienteCaracter()

                almacenarToken(lexema,
                        Categoria.OPERADOR_INCREMENTO,filaInicial,columnaInicial)
                return true
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false

        }
        return false
    }
    fun esOperadorDecremento():Boolean{
        if(caracterActual == '-'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == '¬'){
                lexema += caracterActual
                obtenerSiguienteCaracter()

                almacenarToken(lexema,
                        Categoria.OPERADOR_DECREMENTO,filaInicial,columnaInicial)
                return true
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false

        }
        return false
    }
    fun esPunto():Boolean{
        if(caracterActual == ';'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()

            almacenarToken(lexema,
                    Categoria.PUNTO,filaInicial,columnaInicial)
            return true
        }
        return false
    }
    fun esDosPuntos():Boolean{
        if(caracterActual == '?'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()

            almacenarToken(lexema,
                    Categoria.DOS_PUNTOS,filaInicial,columnaInicial)
            return true
        }
        return false
    }
    fun esEntero():Boolean{
        if(caracterActual.isDigit()) {
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual
            lexema += caracterActual
            obtenerSiguienteCaracter()
            while( caracterActual.isDigit()){
                lexema += caracterActual
                obtenerSiguienteCaracter()
            }

            if(caracterActual.toInt() == 39){
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            almacenarToken(lexema,
                Categoria.ENTERO,filaInicial,columnaInicial)
            return true
        }
        //RI
        return false
    }
    fun esDecimal():Boolean{
        if (caracterActual.toInt() ==  39 || caracterActual.isDigit()){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            if(caracterActual.toInt() == 39){

                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual.isDigit()){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()


                }
            }else{
                lexema += caracterActual
                obtenerSiguienteCaracter()

                while(caracterActual.isDigit()){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                }
                if(caracterActual.toInt() == 39) {

                    lexema += caracterActual
                    obtenerSiguienteCaracter()


                }
            }
            while(caracterActual.isDigit()){
                lexema += caracterActual
                obtenerSiguienteCaracter()
            }
            almacenarToken(lexema,
                Categoria.DECIMAL,filaInicial,columnaInicial)
            return true
        }
        return false
    }
    fun esIdentificador():Boolean{
        if(caracterActual == '-') {
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual.isLetter()){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                while( caracterActual.isLetter() ){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                }

                almacenarToken(lexema,
                        Categoria.IDENTIFICADOR,filaInicial,columnaInicial)
                return true

            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false

        }
        return false
    }

    fun esRetorno():Boolean{
        if(caracterActual == 'e'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'r'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 't'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'r'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 'a'){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()
                            if(caracterActual == 'g'){
                                lexema += caracterActual
                                obtenerSiguienteCaracter()

                                almacenarToken(lexema,
                                    Categoria.RETORNO,filaInicial,columnaInicial)
                                return true
                            }
                            hacerBT(posicionInicial,filaInicial,columnaInicial)
                            return false
                        }
                        hacerBT(posicionInicial,filaInicial,columnaInicial)
                        return false
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }

    fun esImpresion():Boolean{
        if(caracterActual == 'd'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'r'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 'u'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'k'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 'e'){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()
                            if(caracterActual == 'n'){
                                lexema += caracterActual
                                obtenerSiguienteCaracter()

                                almacenarToken(lexema,
                                    Categoria.IMPRESION,filaInicial,columnaInicial)
                                return true
                            }
                        }
                    }
                }
            }
        }
        return false
    }

    fun esLectura():Boolean{
        if(caracterActual == 'l'){
            var lexema = ""
            var filaInicial = filaActual
            var columnaInicial = columnaActual
            var posicionInicial = posicionActual

            lexema += caracterActual
            obtenerSiguienteCaracter()
            if(caracterActual == 'e'){
                lexema += caracterActual
                obtenerSiguienteCaracter()
                if(caracterActual == 's'){
                    lexema += caracterActual
                    obtenerSiguienteCaracter()
                    if(caracterActual == 'e'){
                        lexema += caracterActual
                        obtenerSiguienteCaracter()
                        if(caracterActual == 'n'){
                            lexema += caracterActual
                            obtenerSiguienteCaracter()

                            almacenarToken(lexema,
                                Categoria.LECTURA,filaInicial,columnaInicial)
                            return true
                        }
                        hacerBT(posicionInicial,filaInicial,columnaInicial)
                        return false
                    }
                    hacerBT(posicionInicial,filaInicial,columnaInicial)
                    return false
                }
                hacerBT(posicionInicial,filaInicial,columnaInicial)
                return false
            }
            hacerBT(posicionInicial,filaInicial,columnaInicial)
            return false
        }
        return false
    }

    fun obtenerSiguienteCaracter(){
        if(posicionActual == codigoFuente.length-1) {
            caracterActual = finCodigo
        }else{
            if(caracterActual == '\n'){
                filaActual++
                columnaActual = 0
            }else{
                columnaActual++
            }
            posicionActual++
            caracterActual = codigoFuente[posicionActual]
        }

    }


}