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
import javax.swing.JOptionPane;

public class controllerLogin{

    //Declaramos los componentes con los que trabajaremos de la Inferfaz Login
    @FXML
    private Button atrasButton;

    @FXML
    private TextField usernameTextField;

    @FXML
    private PasswordField passwordPasswordField;

    //METODO DEL BOTON PARA LOGEARSE Y VERIFICAR QUE NO ESTEN VACIOS LOS CAMPOS DE TEXTO(Evento en Interfaz)
    public void loginButtonOnAction(ActionEvent e) throws ClassNotFoundException, SQLException {

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
    public void validarLogin() throws ClassNotFoundException, SQLException {
        ConexionBDD conexionActual = new ConexionBDD(); //Creamos una nueva conexion de la clase ConexionBDD (Objeto de esta clase con el constructor por defecto)
        Connection conectarBDD = (Connection) conexionActual.getConnection(); //Conectamos e inicializamos la BDD haciendo uso de un casteo

        //Definimos la 'consulta' o 'query' para buscar el IdCuenta del usuario y contraseña ingresados
        String verificarLogin = "SELECT IdCuenta FROM cuentausuarios WHERE Username='" + usernameTextField.getText() + "' and Password='" + passwordPasswordField.getText() + "';";

        //Instanciamos de tipo statement para la consola
        Statement instancia = conectarBDD.createStatement();

        ResultSet resultadoQuery = instancia.executeQuery(verificarLogin);

        //CONDICIONAL PARA SABER SI SE ENCONTRO EL USUARIO O NO
        if (resultadoQuery.next()) {
            // Si se encontró un usuario, la consulta devolverá al menos una fila
            JOptionPane.showConfirmDialog(null, "¡Usuario encontrado!");
        } else {
            // Si no se encontró un usuario, la consulta no devolverá filas
            JOptionPane.showConfirmDialog(null, "Usuario no encontrado. Verifica tus credenciales.");
        }

    }

}
