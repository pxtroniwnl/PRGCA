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
import Modelos.Reporte;
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

public class controllerAdminVista implements Initializable {

    //Declaracion de variables para permitir que la ventana se pueda mover sin redimensionarlo
    private double xOffset = 0;
    private double yOffset = 0;

    private int posicionUsuarioEnTabla;

    String query;
    Connection conexion;
    PreparedStatement declaracionPreparada;
    ResultSet resultadoQuery;
    Usuario usuario;
    Reporte reporte;

    //NODOS DE LA SUB-INTERFAZ DE USUARIO 
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

    //NODOS DE LA SUB-INTERFAZ DE REPORTES
    @FXML
    private Button btnCerrarReportes;

    @FXML
    private BorderPane paneReportes;

    @FXML
    private Button CrearReporteBtn;

    @FXML
    private Button ActualizarBtnReportes;

    @FXML
    private TableView<Reporte> reportesTable;

    @FXML
    private TableColumn<Reporte, Integer> idReporteCol;

    @FXML
    private TableColumn<Reporte, Integer> idUsuarioCol;

    @FXML
    private TableColumn<Reporte, Integer> LocalidadCol;

    @FXML
    private TableColumn<Reporte, Integer> UnidadComunCol;

    @FXML
    private TableColumn<Reporte, Integer> contaminanteCol;

    @FXML
    private TableColumn<Reporte, Integer> BarrioCol;

    @FXML
    private TableColumn<Reporte, String> DescripcionCol;

    ObservableList<Reporte> ListaReportes = FXCollections.observableArrayList();
    
    
    //METODO PARA CREAR REPORTES ADMIN
    @FXML
    private void CrearReporteBtnOnAction(ActionEvent e) throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("/view/AdminVista_AddReporte.fxml"));
        
        Scene scene = new Scene(root);
        
        Stage popupAñadirReporte = new Stage();
        
        popupAñadirReporte.initStyle(StageStyle.UNDECORATED); //PARA QUE APAREZCA SIN LA BARRA DE MINIMIZAR, CERRAR ETC...
        popupAñadirReporte.setResizable(false); //PARA QUE NO SE PUEDA CAMBIAR EL TAMAÑO DE LA INTERFAZ

        // Listener para permitir mover la ventana desde el borde superior
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            popupAñadirReporte.setX(event.getScreenX() - xOffset);
            popupAñadirReporte.setY(event.getScreenY() - yOffset);
        });

        popupAñadirReporte.setScene(scene);
        popupAñadirReporte.getIcons().add(new Image("/images/PRGCA COLOR VERDE.png"));
        popupAñadirReporte.show();
    }
    
    //METODO PARA CARGAR LOS DATOS AL TABLEVIEW POR CADA COLUMNA REPORTES
    private void cargarDatosReportes() throws ClassNotFoundException, SQLException {
        ConexionBDD miConexion1 = new ConexionBDD();
        conexion = miConexion1.getConnection();
        ActionEvent evt = null;
        ActualizarTablaReportes(evt);

        idReporteCol.setCellValueFactory(new PropertyValueFactory<>("idReportes"));
        idUsuarioCol.setCellValueFactory(new PropertyValueFactory<>("idUsuario"));
        contaminanteCol.setCellValueFactory(new PropertyValueFactory<>("contaminante"));
        LocalidadCol.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        UnidadComunCol.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        BarrioCol.setCellValueFactory(new PropertyValueFactory<>("barrio"));
        DescripcionCol.setCellValueFactory(new PropertyValueFactory<>("Descripcion"));
        
    }

    //METODO PARA ACTUALIZAR LOS DATOS DEL TABLEVIEW POR CADA COLUMNA REPORTES
    @FXML
    private void ActualizarTablaReportes(ActionEvent evt) throws SQLException {
        ListaReportes.clear();

        query = "SELECT * FROM reportes";
        declaracionPreparada = conexion.prepareStatement(query);
        resultadoQuery = declaracionPreparada.executeQuery();

        while (resultadoQuery.next()) {
            ListaReportes.add(new Reporte(resultadoQuery.getInt("idReportes"),
                    resultadoQuery.getInt("idUsuario"),
                    resultadoQuery.getString("contaminante"),
                    resultadoQuery.getString("localidad"),
                    resultadoQuery.getString("unidad"),
                    resultadoQuery.getString("barrio"),
                    resultadoQuery.getString("Descripcion")));

            reportesTable.setItems(ListaReportes);
        }
    }
    
    //METODO PARA CERRAR VENTANA REPORTES
    @FXML
    private void btnCerrarReportesAction(ActionEvent evt){
        Stage stage = (Stage) btnCerrarReportes.getScene().getWindow();
        stage.close();
    }

    //METODO PARA CARGAR LOS DATOS AL TABLEVIEW POR CADA COLUMNA USUARIO
    private void cargarDatos() throws ClassNotFoundException, SQLException {
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
    private void btnUsuariosAction(ActionEvent e) {
        paneUsuarios.toFront();
    }

    //METODO PARA QUE PASE ADELANTE EL APARTADO DE REPORTES
    @FXML
    private void btnReportesOnAction(ActionEvent evt) {
        paneReportes.toFront();
    }

    //METODO PARA CERRAR VENTANA USUARIO
    @FXML
    private void btnCerrarUsuariosAction(ActionEvent evt) {
        Stage stage = (Stage) btnCerrarUsuarios.getScene().getWindow();
        stage.close();
    }

    //METODO PARA ACTUALIZAR TABLEVIEW USUARIO
    @FXML
    private void ActualizarTabla(ActionEvent evt) throws SQLException {
        ListaUsuarios.clear();

        query = "SELECT * FROM cuentausuarios";
        declaracionPreparada = conexion.prepareStatement(query);
        resultadoQuery = declaracionPreparada.executeQuery();

        while (resultadoQuery.next()) {
            ListaUsuarios.add(new Usuario(resultadoQuery.getInt("IdCuenta"),
                    resultadoQuery.getString("NombreCompleto"),
                    resultadoQuery.getString("Username"),
                    resultadoQuery.getString("Password"),
                    resultadoQuery.getString("correo"),
                    resultadoQuery.getInt("id_rol")));

            UsuariosTable.setItems(ListaUsuarios);
        }

    }

    //METODO PARA ELIMINAR USUARIO
    @FXML
    private void BorrarUsuario(ActionEvent evt) throws IOException {
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

    //METODO PARA AÑADIR Y/O CREAR UN NUEVO USUARIO
    @FXML
    private void CrearUsuarioBtnOnAction(ActionEvent evt) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/AdminVista_AddUsuario_1.fxml"));

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
            try {
                cargarDatos();
            } catch (ClassNotFoundException | SQLException ex) {
                Logger.getLogger(controllerAdminVista.class.getName()).log(Level.SEVERE, null, ex);
            }

            cargarDatosReportes();

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(controllerAdminVista.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
