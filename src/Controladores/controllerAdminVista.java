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
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class controllerAdminVista implements Initializable{
    
    //Declaracion de variables para permitir que la ventana se pueda mover sin redimensionarlo
    private double xOffset = 0;
    private double yOffset = 0;
    
    private int posicionUsuarioEnTabla;
    
    String query;
    Connection conexion;
    PreparedStatement declaracionPreparada;
    ResultSet resultadoQuery;
    Usuario usuario;
    
    ObservableList<Usuario> ListaUsuarios = FXCollections.observableArrayList();
    
    
    @FXML
    private Button ActualizarBtn;
    
    @FXML
    private Button BorrarUsuarioBtn;

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
    private Button btnCerrarUsuarios;

    @FXML
    private Button btnInformes;

    @FXML
    private Button btnReportes;

    @FXML
    private Button btnUsuarios;

    @FXML
    private BorderPane paneUsuarios;
    
    @FXML
    private Button BorrarUsuairoBtn;

    //METODO PARA CARGAR LOS DATOS AL TABLEVIEW POR CADA COLUMNA
    private void cargarDatos() throws ClassNotFoundException, SQLException{
        ConexionBDD miConexion = new ConexionBDD();
        conexion = miConexion.getConnection();
        ActionEvent evt = null;
        ActualizarTabla(evt);
        
        idUsuario.setCellValueFactory(new PropertyValueFactory<>("IdCuenta"));
        NombreUsuario.setCellValueFactory(new PropertyValueFactory<>("NombreCompleto"));
        UsernameUsuario.setCellValueFactory(new PropertyValueFactory<>("Username"));
        PasswordUsuario.setCellValueFactory(new PropertyValueFactory<>("Password"));
        CorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correo"));
        id_rolUsuario.setCellValueFactory(new PropertyValueFactory<>("id_rol"));
        
                
    }
    
    //METODO PARA QUE PASE ADELANTE EL APARTADO DE USUARIOS
    @FXML
    private void btnUsuariosAction(ActionEvent e){
        paneUsuarios.toFront();
    }
    
    //METODO PARA CERRAR VENTANA
    @FXML
    private void btnCerrarUsuariosAction(ActionEvent evt){
        Stage stage = (Stage) btnCerrarUsuarios.getScene().getWindow();
        stage.close();
    }
    
    //METODO PARA ACTUALIZAR TABLEVIEW
    @FXML
    private void ActualizarTabla(ActionEvent evt) throws SQLException{
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
    
    @FXML
    private void BorrarUsuario(ActionEvent evt) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/AdminVista_EliminarUsuario.fxml"));

        Scene scene = new Scene(root);
        
        Stage popupEliminarUsuario = new Stage();
        
        popupEliminarUsuario.initStyle(StageStyle.UNDECORATED); //PARA QUE APAREZCA SIN LA BARRA DE MINIMIZAR, CERRAR ETC...
        popupEliminarUsuario.setResizable(false); //PARA QUE NO SE PUEDA CAMBIAR EL TAMAÑO DE LA INTERFAZ
        
        // Listener para permitir mover la ventana desde el borde superior
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            popupEliminarUsuario.setX(event.getScreenX() - xOffset);
            popupEliminarUsuario.setY(event.getScreenY() - yOffset);
        });
        
        popupEliminarUsuario.setScene(scene);
        popupEliminarUsuario.getIcons().add(new Image("/images/PRGCA COLOR VERDE.png"));
        popupEliminarUsuario.show();
    }
    
            
    //Evento si se le da al boton Añadir Usuario
    @FXML
    private void CrearUsuarioBtnOnAction(ActionEvent evt) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/AdminVista_AddUsuario.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage popupAñadirUsuario = new Stage();
        
        popupAñadirUsuario.initStyle(StageStyle.UNDECORATED); //PARA QUE APAREZCA SIN LA BARRA DE MINIMIZAR, CERRAR ETC...
        popupAñadirUsuario.setResizable(false); //PARA QUE NO SE PUEDA CAMBIAR EL TAMAÑO DE LA INTERFAZ
        
        // Listener para permitir mover la ventana desde el borde superior con el setResizable en False
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            popupAñadirUsuario.setX(event.getScreenX() - xOffset);
            popupAñadirUsuario.setY(event.getScreenY() - yOffset);
        });
        
        popupAñadirUsuario.setScene(scene);
        
        //Hacemos display de la interfaz
        popupAñadirUsuario.getIcons().add(new Image("/images/PRGCA.png"));
        popupAñadirUsuario.show();
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

    
