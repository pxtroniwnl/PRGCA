/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package Controladores;

import ConexionBaseDeDatos.ConexionBDD;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * FXML Controller class
 *
 * @author ALEJANDRO PATRON
 */
public class controllerEliminarUsuario implements Initializable {
    
        Connection conexion = null;
        PreparedStatement statement;
        ConexionBDD conexionActual = new ConexionBDD();
        
    
    @FXML
    private Button CerrarBtn;

    @FXML
    private Button EliminarBtn;

    @FXML
    private TextField ID_Eliminar;

    @FXML
    private void CerrarPopUp(ActionEvent event) {
        
        
        Stage stage = (Stage) CerrarBtn.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void EliminarOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        
        
        int idUsuarioAEliminar = Integer.parseInt(ID_Eliminar.getText()); // Convierte el texto a entero
        String query = "DELETE FROM cuentausuarios WHERE IdCuenta = ?";
        
        statement = conexion.prepareStatement(query);
        statement.setInt(1, idUsuarioAEliminar);
        
        int filasAfectadas = statement.executeUpdate();

        if (filasAfectadas > 0) {
            System.out.println("Usuario eliminado exitosamente.");
        } else {
            System.out.println("No se encontró ningún usuario con el ID especificado.");
        }
        
        
    }
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
            try {
                conexion = conexionActual.getConnection();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(controllerEliminarUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }
    }    
    
}
