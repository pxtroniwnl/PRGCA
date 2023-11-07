/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import ConexionBaseDeDatos.ConexionBDD;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class controllerEliminarInforme implements Initializable {

    Connection conexion = null;
    PreparedStatement statement;
    ConexionBDD conexionActual = new ConexionBDD();

    //DECLARAMOS LOS NODOS QUE SE USARAN EN LA INTERFAZ
    @FXML
    private Button CerrarBtn;

    @FXML
    private Button EliminarBtn;

    @FXML
    private TextField ID_Eliminar;

    //METODO CUANDO SE LE CLICK AL BOROPN DE CERRAR VENTANA
    @FXML
    private void CerrarPopUp(ActionEvent event) {
        Stage stage = (Stage) CerrarBtn.getScene().getWindow();
        stage.close();
    }

    //METODO PARA CUANDOI SE LE DE CLICK AL BOTON ELIMINAR
    @FXML
    private void EliminarOnAction(ActionEvent event) throws SQLException {
        int idInformeAEliminar = Integer.parseInt(ID_Eliminar.getText()); // Convierte el texto a entero
        String query = "DELETE FROM informes WHERE IdInforme = ?";

        statement = conexion.prepareStatement(query);
        statement.setInt(1, idInformeAEliminar);

        int filasAfectadas = statement.executeUpdate();

        if (filasAfectadas > 0) {
            JOptionPane.showMessageDialog(null, "Informe eleminado exitosamente");
        } else {
            JOptionPane.showMessageDialog(null, "ID del informe no encontrado");
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            conexion = conexionActual.getConnection();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(controllerEliminarInforme.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
