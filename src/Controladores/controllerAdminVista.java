package Controladores;

import Modelos.Usuario;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Connection;
import ConexionBaseDeDatos.ConexionBDD;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class controllerAdminVista implements Initializable{
    
    String query;
    Connection conexion;
    PreparedStatement declaracionPreparada;
    ResultSet resultadoQuery;
    Usuario usuario;
    
    ObservableList<Usuario> ListaUsuarios = FXCollections.observableArrayList();
    
    
    @FXML
    private Button ActualizarBtn;

    @FXML
    private Button CrearUsuarioBtn;

    @FXML
    private TableView<Usuario> UsuariosTable;
    
    @FXML
    private TableColumn<Usuario, Integer> idUsuario;
    
    @FXML
    private TableColumn<Usuario, String> NombreUsuario;
    
    @FXML
    private TableColumn<Usuario, String> UsernameUsuario;
    
    @FXML
    private TableColumn<Usuario, String> PasswordUsuario;
    
    @FXML
    private TableColumn<Usuario, String> CorreoUsuario;
    
    @FXML
    private TableColumn<Usuario, Integer> id_rolUsuario;

    @FXML
    private Button btnCerrar4;

    @FXML
    private Button btnInformes;

    @FXML
    private Button btnReportes;

    @FXML
    private Button btnUsuarios;

    @FXML
    private BorderPane paneUsuarios;

    //METODO PARA CARGAR LOS DATOS AL TABLEVIEW POR CADA COLUMNA
    private void cargarDatos() throws ClassNotFoundException, SQLException{
        ConexionBDD miConexion = new ConexionBDD();
        conexion = miConexion.getConnection();
        ActualizarTabla();
        
        idUsuario.setCellValueFactory(new PropertyValueFactory<>("IdCuenta"));
        NombreUsuario.setCellValueFactory(new PropertyValueFactory<>("NombreCompleto"));
        UsernameUsuario.setCellValueFactory(new PropertyValueFactory<>("Username"));
        PasswordUsuario.setCellValueFactory(new PropertyValueFactory<>("Password"));
        CorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correo"));
        id_rolUsuario.setCellValueFactory(new PropertyValueFactory<>("id_rol"));
        
                
    }
    
    
    //METODO PARA ACTUALIZAR TABLEVIEW
    @FXML
    private void ActualizarTabla() throws SQLException{
        ListaUsuarios.clear();
        
        query = "SELECT * FROM cuentausuarios";
        declaracionPreparada = conexion.prepareStatement(query);
        resultadoQuery = declaracionPreparada.executeQuery();
        
        while(resultadoQuery.next()){
            ListaUsuarios.add(new Usuario(resultadoQuery.getInt("IdCuenta"),
                    resultadoQuery.getString("NombreCompleto"),
                    resultadoQuery.getString("Username"),
                    resultadoQuery.getString("Password"),
                    resultadoQuery.getString("correo"),
                    resultadoQuery.getInt("id_rol")));
            
            UsuariosTable.setItems(ListaUsuarios);
        }
    
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cargarDatos();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(controllerAdminVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}

    
