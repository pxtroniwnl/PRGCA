/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import ConexionBaseDeDatos.ConexionBDD;
import Modelos.Barrio;
import Modelos.Contaminante;
import Modelos.Localidad;
import Modelos.UnidadComunera;
import Modelos.Valoracion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class controllerCiudadanoVista {
    
    //NODOS
    @FXML
    private Button btnCerrarMiPerfil;

    @FXML
    private Button btnCerrarReportes;

    @FXML
    private Button btnReportes;

    @FXML
    private Button btnMiPerfil;

    @FXML
    private BorderPane paneMiPerfil;

    @FXML
    private BorderPane paneReportes;
    
    
    
    //NODOS MI PERFIL
    @FXML
    private TextField Id_TextField;
    
    @FXML
    private TextField Nombre_TextField;
    
    @FXML
    private TextField Username_TextField;
    
    @FXML
    private TextField password_TextField;
    
    @FXML
    private TextField descrip_TextField;
    
    @FXML
    private TextField correo_TextField;
    
    @FXML
    private TextField direccion_Field;
    
    @FXML
    private TextField telefono_TextField;
    
    @FXML
    private Button EditarMiPerfilButton;
    
    //METODO PARA OPRIMIR EL BOTON EDITAR MI PERFIL  
    @FXML
    private void EditarMiPerfilOnAction(ActionEvent e) throws ClassNotFoundException, SQLException{
        String id = Id_TextField.getText();
        String Nombre = Nombre_TextField.getText();
        String Username = Username_TextField.getText();
        String Password = password_TextField.getText();
        String Descrip = descrip_TextField.getText();
        String Correo = correo_TextField.getText();
        String Direccion = direccion_Field.getText();
        String Telefono = telefono_TextField.getText();
        
            if(id.equals("") || Nombre.equals("") || Username.equals("") || Password.equals("") || Descrip.equals("") || 
                    Correo.equals("") || Direccion.equals("") || Telefono.equals("")){
                JOptionPane.showMessageDialog(null, "Rellene los campo");
            }else{
                //METODO PARA EDITAR MI PERFIL
                editarMiPerfil();
            }
    }
    
    //METODO DEL UPDATE A LA BDD
    private void editarMiPerfil() throws ClassNotFoundException, SQLException{
        ConexionBDD conexionActual = new ConexionBDD();
        Connection conectarBDD = (Connection) conexionActual.getConnection();

        
        int ID = Integer.parseInt(Id_TextField.getText());
        String nombre_nvo = Nombre_TextField.getText();
        String username_nvo = Username_TextField.getText();
        String password_nvo = password_TextField.getText();
        String descrip_nvo = descrip_TextField.getText();
        String correo_nvo = correo_TextField.getText();
        String direecion_nva = direccion_Field.getText();
        String tell_nvo = telefono_TextField.getText();

        String consultaQuery = "UPDATE cuentausuarios SET NombreCompleto = ?, Username = ?, Password = ?, Direccion = ?, Telefono = ?, Descripcion = ?, correo = ?, id_rol = ? WHERE IdCuenta = ?";

        PreparedStatement preparedStatement = conectarBDD.prepareStatement(consultaQuery);
        preparedStatement.setString(1,nombre_nvo);
        preparedStatement.setString(2,username_nvo);
        preparedStatement.setString(3,password_nvo);
        preparedStatement.setString(4,direecion_nva);
        preparedStatement.setString(5,tell_nvo);
        preparedStatement.setString(6,descrip_nvo);
        preparedStatement.setString(7,correo_nvo);
        preparedStatement.setString(8,"3");
        preparedStatement.setInt(9, ID);
        

        int rQ = preparedStatement.executeUpdate();

        if (rQ > 0) {
            JOptionPane.showMessageDialog(null, "¡Usuario Editado!");
            Stage stage = (Stage) EditarMiPerfilButton.getScene().getWindow();
            stage.close();
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no editado. Verifica tus credenciales.");
        }
    }
    
    
    
    //METODO PARA ECHAR ADELANTE APARTADO REPORTES
    @FXML
    private void ReportesOnAction(ActionEvent event) {
        paneReportes.toFront();
    }
    
    //METODO PARA ECHAR ADELANTE APARTADO MIPERFIL
    @FXML
    private void MiPerfilOnAction(ActionEvent event){
        paneMiPerfil.toFront();
    }
    
    //METODO PARA CERRAR VENTANA - Reportes
    @FXML
    private void CerrarReportesOnAction(ActionEvent e){
        Stage stage = (Stage) btnCerrarReportes.getScene().getWindow();
        stage.close();
    }
    
    //METODO PARA CERRAR VENTANA - Mi Perfil
    @FXML
    private void CerrarMiPerfilOnAction(ActionEvent e){
        Stage stage = (Stage) btnCerrarReportes.getScene().getWindow();
        stage.close();
    }
}
