package co.edu.uniquindio.compiladores.controladores

import co.edu.uniquindio.compiladores.Sintactico.AnalizadorSintactico
import co.edu.uniquindio.compiladores.Sintactico.UnidadDeCompilacion
import co.edu.uniquindio.compiladores.lexico.AnalizadorLexico
import co.edu.uniquindio.compiladores.lexico.Error
import co.edu.uniquindio.compiladores.lexico.Token
import co.edu.uniquindio.compiladores.semantica.AnalizadorSemantico
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.Initializable
import javafx.scene.control.*
import javafx.scene.control.cell.PropertyValueFactory
import javafx.scene.image.Image
import javafx.scene.image.ImageView
import java.io.File
import java.net.URL
import java.util.*


class InicioController: Initializable{

    @FXML lateinit var codigoFuente:TextArea
    @FXML lateinit var tablaTokens:TableView<Token>
    @FXML lateinit var colLexema:TableColumn<Token,String>
    @FXML lateinit var colCategoria:TableColumn<Token,String>
    @FXML lateinit var colFila:TableColumn<Token,Int>
    @FXML lateinit var colColumna:TableColumn<Token,Int>

    @FXML lateinit var tablaErroresL:TableView<Error>
    @FXML lateinit var colErrorL:TableColumn<Error,String>
    @FXML lateinit var colFilaL:TableColumn<Error,Int>
    @FXML lateinit var colColumnaL:TableColumn<Error,Int>

    @FXML lateinit var tablaErroresS:TableView<Error>
    @FXML lateinit var colErrorS:TableColumn<Error,String>
    @FXML lateinit var colFilaS:TableColumn<Error,Int>
    @FXML lateinit var colColumnaS:TableColumn<Error,Int>

    @FXML lateinit var tablaErroresS1:TableView<Error>
    @FXML lateinit var colErrorS1:TableColumn<Error,String>
    @FXML lateinit var colFilaS1:TableColumn<Error,Int>
    @FXML lateinit var colColumnaS1:TableColumn<Error,Int>

    @FXML lateinit var imageI: ImageView
    @FXML lateinit var imageD: ImageView
    @FXML lateinit var imageI2: ImageView
    @FXML lateinit var imageI21: ImageView
    @FXML lateinit var imageD2: ImageView
    @FXML lateinit var imageD21: ImageView

    @FXML lateinit var arbolVisual: TreeView<String>

    @FXML lateinit var btnTraducir: Button

    var unidadCompilacion : UnidadDeCompilacion? = null

    @FXML
    fun analizarCodigo(e: ActionEvent){
        tablaTokens.items = null
        tablaErroresL.items = null
        tablaErroresS.items = null
        tablaErroresS1.items = null
        arbolVisual.root = null
         if(codigoFuente.text.length > 0){
             val lexico = AnalizadorLexico(codigoFuente.text)
             lexico.analizar()
             tablaTokens.items = FXCollections.observableArrayList(lexico.listaTokens)
             tablaErroresL.items = FXCollections.observableArrayList(lexico.listaErroes)
             if(lexico.listaErroes.isEmpty()){
                 val sintaxis = AnalizadorSintactico( lexico.listaTokens )
                 val uc = sintaxis.esUnidadDeCompilacion()
                 if(uc!=null){
                     arbolVisual.root = uc.getArbolVisual()
                     val semantico=AnalizadorSemantico(uc)
                     semantico.llenarTablaSimbolos()
                     println(semantico.tablaSimbolos)
                     semantico.analizarSemantica()
                     if(!semantico.erroresSemanticos.isEmpty()){
                         tablaErroresS1.items = FXCollections.observableArrayList(semantico.erroresSemanticos)
                         var alerta = Alert(Alert.AlertType.ERROR)
                         alerta.contentText = "Hay errores semánticos en el código fuente"
                         alerta.show()
                     }else{
                         btnTraducir.setDisable(false)
                         unidadCompilacion=uc
                         println(uc.getJavaCode())
                     }
                 }
                 if(!sintaxis.listaErrores.isEmpty()){
                     tablaErroresS.items = FXCollections.observableArrayList(sintaxis.listaErrores)
                     var alerta = Alert(Alert.AlertType.ERROR)
                     alerta.contentText = "Hay errores sintácticos en el código fuente"
                     alerta.show()
                 }

             } else {
                 var alerta = Alert(Alert.AlertType.ERROR)
                 alerta.contentText = "Hay errores lexicos en el código fuente"
                 alerta.show()
             }
         }
    }

    @FXML
    fun traducirCodigo(event: ActionEvent?) {
        val codigoFuente = unidadCompilacion!!.getJavaCode()
        File("src/Principal.java").writeText( codigoFuente )
        try {
            val p = Runtime.getRuntime().exec("javac src/Principal.java")
            p.waitFor()
            Runtime.getRuntime().exec("java Principal", null, File("src"))
        } catch (ea: Exception) {
            ea.printStackTrace()
        }


       print(unidadCompilacion!!.getJavaCode())
    }

    override fun initialize(location: URL?, resources: ResourceBundle?) {
        colLexema.cellValueFactory = PropertyValueFactory("lexema")
        colCategoria.cellValueFactory = PropertyValueFactory("categoria")
        colFila.cellValueFactory = PropertyValueFactory("fila")
        colColumna.cellValueFactory = PropertyValueFactory("columna")

        colErrorL.cellValueFactory = PropertyValueFactory("error")
        colFilaL.cellValueFactory = PropertyValueFactory("fila")
        colColumnaL.cellValueFactory = PropertyValueFactory("columna")

        colErrorS.cellValueFactory = PropertyValueFactory("error")
        colFilaS.cellValueFactory = PropertyValueFactory("fila")
        colColumnaS.cellValueFactory = PropertyValueFactory("columna")

        colErrorS1.cellValueFactory = PropertyValueFactory("error")
        colFilaS1.cellValueFactory = PropertyValueFactory("fila")
        colColumnaS1.cellValueFactory = PropertyValueFactory("columna")

        val i = Image(File("resources/barraI.gif").toURI().toString())
        imageI.setImage(i)
        val d = Image(File("resources/barraD.gif").toURI().toString())
        imageD.setImage(d)
        val i2 = Image(File("resources/snopI.gif").toURI().toString())
        imageI2.setImage(i2)
        imageI21.setImage(i2)
        val d2 = Image(File("resources/snopD.gif").toURI().toString())
        imageD2.setImage(d2)
        imageD21.setImage(d2)

        btnTraducir.setDisable(false)
    }
}