package Controladores;

import ConexionBaseDeDatos.ConexionBDD;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.sql.Statement;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

public class controllerRegisterForm{
    
    //Declaracion de variables para permitir que la ventana se pueda mover sin redimensionarlo
    private double xOffset = 0;
    private double yOffset = 0;
    
    //Declaramos los componentes con los que trabajaremos de la Inferfaz registro
    @FXML
    private Button registrarBttn;

    @FXML
    private Button atrasBttn;

    @FXML
    private TextField usernameRegistrar;

    @FXML
    private PasswordField passwordRegistrar;

    @FXML
    private TextField correoRegistrar;

    @FXML
    private TextField telefonoRegistrar;

    @FXML
    private TextField nameRegistrar;

    @FXML
    private TextField idRegistrar;

    @FXML
    private TextField direccionRegistrar;

    //METODO DEL BOTON QUE CONFIRMA EL REGISTRO DEL CIUDADANO Y VERIFICA QUE NO HAYAN CAMPOS DE TEXTO SIN COMPLETAR
    public void registrarBttnOnAction(ActionEvent e) throws ClassNotFoundException, SQLException {
        //Vereficamos si los campos de texto estan vacios
        if (usernameRegistrar.getText().isBlank() == false && passwordRegistrar.getText().isBlank() == false && correoRegistrar.getText().isBlank() == false
                && telefonoRegistrar.getText().isBlank() == false && nameRegistrar.getText().isBlank() == false && idRegistrar.getText().isBlank() == false
                && direccionRegistrar.getText().isBlank() == false) {
            System.out.println("Intentaste registrarte");
            registrarCiudadano(); //Llamamos el metodo para validar el login
        } else {
            System.out.println("Porfavor no deje espacios en blanco");
        }
    }

    //METODO DEL BOTON PARA VOLVER ATRAS
    public void atrasBttnOnAction(ActionEvent e) throws IOException {
        //Creamos una instancia del ViewMain y ubicamos el archivo para redirigir luego
        Parent root = FXMLLoader.load(getClass().getResource("/view/ViewMain.fxml"));

        Scene scene = new Scene(root);
        
        Stage VentanaInicio = new Stage();
        
        VentanaInicio.initStyle(StageStyle.UNDECORATED); //PARA QUE APAREZCA SIN LA BARRA DE MINIMIZAR, CERRAR ETC...
        VentanaInicio.setResizable(false); //PARA QUE NO SE PUEDA CAMBIAR EL TAMAÑO DE LA INTERFAZ
        
        // Listener para permitir mover la ventana desde el borde superior
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            VentanaInicio.setX(event.getScreenX() - xOffset);
            VentanaInicio.setY(event.getScreenY() - yOffset);
        });
        
        //obtenemos la ventana actual para cerrarla
        Stage stage = (Stage) atrasBttn.getScene().getWindow();
        stage.close();
        
        VentanaInicio.setScene(scene);
        VentanaInicio.getIcons().add(new Image("/images/PRGCA COLOR VERDE.png"));
        VentanaInicio.show();
    }

    public void registrarCiudadano() throws ClassNotFoundException, SQLException {
        ConexionBDD conexionActual = new ConexionBDD(); //Creamos una nueva conexion de la clase ConexionBDD (Objeto de esta clase con el constructor por defecto)
        Connection conectarBDD = (Connection) conexionActual.getConnection(); //Conectamos e inicializamos la BDD haciendo uso de un casteo

        // Definimos la 'consulta' o 'query' para registrar nuevos registros en los respectivos campos de valor
        String consultaQuery = "INSERT INTO cuentausuarios (IdCuenta, NombreCompleto, Username, Password, Direccion, Telefono, Descripcion, correo, id_rol) "
                + "VALUES (" + idRegistrar.getText() + ",'" + nameRegistrar.getText() + "','" + usernameRegistrar.getText() + "','" + passwordRegistrar.getText() + "','" + direccionRegistrar.getText() + "',"
                + telefonoRegistrar.getText() + ",'','" + correoRegistrar.getText() + "', 3)";

        //Instanciamos de tipo statement para la consola
        Statement instancia = (Statement) conectarBDD.createStatement();

        int resultadoQuery = instancia.executeUpdate(consultaQuery);

        //CONDICIONAL PARA SABER SI SE ENCONTRO EL USUARIO O NO (DEPENDIENDO DE LAS FILAS)
        if (resultadoQuery > 0) {
            // Si se encontró un usuario, la consulta devolverá al menos una fila
            JOptionPane.showConfirmDialog(null, "¡Usuario registrado!");
        } else {
            // Si no se encontró un usuario, la consulta no devolverá filas
            JOptionPane.showConfirmDialog(null, "Usuario no registrado. Verifica tus credenciales.");
        }

    }

    
}
