/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import ConexionBaseDeDatos.ConexionBDD;
import Modelos.Rol;
import Modelos.Usuario;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
/**
 *
 * @author ALEJANDRO PATRON
 */
public class controllerAñadirUsuario implements Initializable{
    
    String query;
    Connection conexion;
    PreparedStatement declaracionPreparada;
    ResultSet resultadoQuery;
    Usuario usuario;
    
    //Declaramos los objetos del FXML 
    
    @FXML
    private ComboBox<Rol> rolComboBox;

    @FXML
    private Button atrasBttn;

    @FXML
    private TextField correoRegistrar;

    @FXML
    private TextField direccionRegistrar;

    @FXML
    private TextField idRegistrar;

    @FXML
    private TextField nameRegistrar;

    @FXML
    private PasswordField passwordRegistrar;

    @FXML
    private Button registrarBttn;

    @FXML
    private TextField telefonoRegistrar;

    @FXML
    private TextField usernameRegistrar;
    
    
    //METODO PARA VERIFICAR SI DEJO ESPACIOS SIN COMPLETAR
    @FXML
    private void crearUsuarioOnAction(ActionEvent e) throws ClassNotFoundException, SQLException {
        if (usernameRegistrar.getText().isBlank() && passwordRegistrar.getText().isBlank() && correoRegistrar.getText().isBlank()
                && telefonoRegistrar.getText().isBlank() && nameRegistrar.getText().isBlank() && idRegistrar.getText().isBlank()
                && direccionRegistrar.getText().isBlank()) {
            System.out.println("Por favor, complete todos los campos.");
        } else {
            // Obtener el rol seleccionado en el ComboBox
            Rol rolSeleccionado = rolComboBox.getValue();

            if (rolSeleccionado == null) {
                System.out.println("Por favor, seleccione un rol.");
            } else {
                System.out.println("Intentaste registrarte como " + rolSeleccionado.getNombre());
                añadirUsuario(); // Llama al método para validar el registro
            }
        }
    }

    //METODO PARA REGISTRO DEL USUARIO
    private void añadirUsuario() throws ClassNotFoundException, SQLException {
        ConexionBDD conexionActual = new ConexionBDD(); //Creamos una nueva conexion de la clase ConexionBDD (Objeto de esta clase con el constructor por defecto)
        Connection conectarBDD = (Connection) conexionActual.getConnection(); //Conectamos e inicializamos la BDD haciendo uso de un casteo
        
        
        Rol selectedRol = rolComboBox.getValue();
        int numeroRol = selectedRol.getNumero(); // Obtiene el número del rol seleccionado
        
        
        // Definimos la 'consulta' o 'query' para registrar nuevos registros en los respectivos campos de valor
        String consultaQuery = "INSERT INTO cuentausuarios (IdCuenta, NombreCompleto, Username, Password, Direccion, Telefono, Descripcion, correo, id_rol) "
                + "VALUES (" + idRegistrar.getText() + ",'" + nameRegistrar.getText() + "','" + usernameRegistrar.getText() + "','" + passwordRegistrar.getText() + "','" + direccionRegistrar.getText() + "',"
                + telefonoRegistrar.getText() + ",'','" + correoRegistrar.getText() + "', " + numeroRol + ")";

        //Instanciamos de tipo statement para la consola
        Statement instancia = (Statement) conectarBDD.createStatement();

        int resultadoQuery = instancia.executeUpdate(consultaQuery);

        //CONDICIONAL PARA SABER SI SE ENCONTRO EL USUARIO O NO (DEPENDIENDO DE LAS FILAS)
        if (resultadoQuery > 0) {
            // Si se encontró un usuario, la consulta devolverá al menos una fila
            JOptionPane.showConfirmDialog(null, "¡Usuario registrado!");
            Stage stage = (Stage) registrarBttn.getScene().getWindow();
            stage.close();
        } else {
            // Si no se encontró un usuario, la consulta no devolverá filas
            JOptionPane.showConfirmDialog(null, "Usuario no registrado. Verifica tus credenciales.");
        }
    }
    
    
    //METODO PARA VOLVER ATRAS
    @FXML
    private void atrasBttnOnAction(ActionEvent evt){
        Stage stage = (Stage) atrasBttn.getScene().getWindow();
        stage.close();
    }
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Rol administrador = new Rol(1, "Administrador");
        Rol entidadAmbiental = new Rol(2, "Entidad Ambiental");
        Rol ciudadano = new Rol(3, "Ciudadano");

        rolComboBox.getItems().addAll(administrador, entidadAmbiental, ciudadano);
        rolComboBox.setPromptText("Selecciona un rol:");
    }
    
    
    
}
