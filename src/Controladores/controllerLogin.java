package Controladores;

import ConexionBaseDeDatos.ConexionBDD;
import java.io.IOException;
import java.sql.Statement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.sql.SQLException;
import java.sql.Connection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javax.swing.JOptionPane;

public class controllerLogin {

    //Declaramos los componentes con los que trabajaremos de la Inferfaz Login
    @FXML
    private Button atrasButton;

    @FXML
    private BorderPane brdrpaneLogin;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField usernameTextField;

    //METODO DEL BOTON PARA LOGEARSE Y VERIFICAR QUE NO ESTEN VACIOS LOS CAMPOS DE TEXTO(Evento en Interfaz)
    public void loginButtonOnAction(ActionEvent e) throws ClassNotFoundException, SQLException, IOException {

        //Vereficamos si los campos de texto estan vacios
        if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            System.out.println("Intentaste iniciar sesion");
            validarLogin(); //Llamamos el metodo para validar el login
        } else {
            System.out.println("Porfavor ingrese username y password");
        }

    }

    //METODO DEL BOTON PARA VOLVER ATRAS
    public void atrasButtonOnAction(ActionEvent e) throws IOException {
        //Creamos una instancia del ViewMain y ubicamos el archivo para redirigir luego
        Parent root = FXMLLoader.load(getClass().getResource("/view/ViewMain.fxml"));

        Stage ventanaInicio = new Stage();
        ventanaInicio.setTitle("Inicio - Interfaz");
        ventanaInicio.getIcons().add(new Image("/images/PRGCA.png"));
        ventanaInicio.setScene(new Scene(root));

        //obtenemos la ventana actual para cerrarla
        Stage stage = (Stage) atrasButton.getScene().getWindow();
        stage.close();

        //Hacemos display de la interfaz inicio nuevamente
        ventanaInicio.show();
    }

    //METODO PARA VERIFICAR LOS DATOS (Evento en Interfaz)
    public void validarLogin() throws ClassNotFoundException, SQLException, IOException {
        ConexionBDD conexionActual = new ConexionBDD(); //Creamos una nueva conexion de la clase ConexionBDD (Objeto de esta clase con el constructor por defecto)
        Connection conectarBDD = (Connection) conexionActual.getConnection(); //Conectamos e inicializamos la BDD haciendo uso de un casteo

        //Definimos la 'consulta' o 'query' para buscar el IdCuenta del usuario y contraseña ingresados
        String verificarLogin = "SELECT IdCuenta, id_rol FROM cuentausuarios WHERE Username='" + usernameTextField.getText() + "' and Password='" + passwordPasswordField.getText() + "';";

        //Instanciamos de tipo statement para la consola
        Statement instancia = conectarBDD.createStatement();

        ResultSet resultadoQuery = instancia.executeQuery(verificarLogin);

        //CONDICIONAL PARA SABER SI SE ENCONTRO EL USUARIO O NO Y LLAMADA DEL METODO PARA REDIRIGIR EN FUNCION DEL ROL QUE TENGA
        if (resultadoQuery.next()) {  // Si se encontró un usuario, la consulta devolverá al menos una fila
            int idCuenta = resultadoQuery.getInt("IdCuenta");
            int idRol = resultadoQuery.getInt("id_rol");
            //Switch para saber que interfaz mostrar en funcion del rol que tenga
            switch (idRol) {
                case 1: // Administrador
                    System.out.println("Administrador");
                    displayAdmin();
                    break;
                case 2: // Entidad Ambiental
                    // Aquí cargamos la interfaz de entidad ambiental
                    System.out.println("Entidad");
                    displayEntidad();
                    break;
                case 3: // Ciudadano
                    // Aquí cargamos la interfaz de ciudadano
                    System.out.println("Ciudadano");
                    displayCiudadano();
                    break;
            }
        } else {
            // Si no se encontró un usuario, la consulta no devolverá filas
            JOptionPane.showConfirmDialog(null, "Usuario no encontrado. Verifica tus credenciales.");
        }

    }

    public void displayAdmin() throws IOException {
            // Aquí cargamos la interfaz del administrador

            //Creamos una instancia del LoginForm para redirigir luego
            Parent root = FXMLLoader.load(getClass().getResource("/view/AdminVista.fxml"));

            Stage PrincipalAdmin = new Stage();
            PrincipalAdmin.setTitle("Admin - Interfaz");
            PrincipalAdmin.getIcons().add(new Image("/images/PRGCA.png"));
            PrincipalAdmin.setScene(new Scene(root));

            //obtenemos la ventana actual para cerrarla
            Stage stage = (Stage) loginButton.getScene().getWindow();
            stage.close();

            //Hacemos display de la interfaz admin
            PrincipalAdmin.show();
    }

    public void displayEntidad() throws IOException {
        // Aquí cargamos la interfaz de la Entidad

        //Creamos una instancia del LoginForm para redirigir luego
        Parent root = FXMLLoader.load(getClass().getResource("/view/EntidadVista.fxml"));

        Stage PrincipalEntidad = new Stage();
        PrincipalEntidad.setTitle("Admin - Interfaz");
        PrincipalEntidad.getIcons().add(new Image("/images/PRGCA.png"));
        PrincipalEntidad.setScene(new Scene(root));

        //obtenemos la ventana actual para cerrarla
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();

        //Hacemos display de la interfaz admin
        PrincipalEntidad.show();
    }

    public void displayCiudadano() throws IOException {
        // Aquí cargamos la interfaz del Ciudadano

        //Creamos una instancia del LoginForm para redirigir luego
        Parent root = FXMLLoader.load(getClass().getResource("/view/CiudadanoVista.fxml"));

        Stage PrincipalCiudadano = new Stage();
        PrincipalCiudadano.setTitle("Admin - Interfaz");
        PrincipalCiudadano.getIcons().add(new Image("/images/PRGCA.png"));
        PrincipalCiudadano.setScene(new Scene(root));

        //obtenemos la ventana actual para cerrarla
        Stage stage = (Stage) loginButton.getScene().getWindow();
        stage.close();

        //Hacemos display de la interfaz admin
        PrincipalCiudadano.show();
    }

}
