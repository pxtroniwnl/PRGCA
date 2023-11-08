/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import ConexionBaseDeDatos.ConexionBDD;
import Modelos.Estado;
import Modelos.Informes;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;


/**
 *
 * @author ALEJANDRO PATRON
 */
public class controllerEntidadVista implements Initializable{
    
    String query;
    Connection conexion;
    PreparedStatement declaracionPreparada;
    ResultSet resultadoQuery;
    
    @FXML
    private Button btnMiPerfilEntidad;
    
    @FXML
    private BorderPane paneInformes;
    
    @FXML
    private BorderPane paneMiPerfilEntidad;
    
    //NODOS DE LA SUB-INTERFAZ VER INFORMES
    @FXML
    private Button btnCerrarInformes;
    
    @FXML
    private TextField idInformeTextField;
    
    @FXML
    private ComboBox<Estado> EstadoComboBox;
    
    @FXML
    private Button EditarBtn;
    
    @FXML
    private Button EstadoOnAction;
    
    @FXML
    private TableView<Informes> InformesTable;
    
    @FXML
    private TableColumn<Integer, ?> idCol;
    
    @FXML
    private TableColumn<String, ?> contamCol;
    
    @FXML
    private TableColumn<String, ?> locCol;
    
    @FXML
    private TableColumn<String, ?> unidadCol;
    
    @FXML
    private TableColumn<String, ?> barrioCol;
    
    @FXML
    private TableColumn<?, ?> valoracionCol;
    
    @FXML
    private TableColumn<String, ?> estadoCol;
    
    ObservableList<Informes> ListaInformes = FXCollections.observableArrayList();
    
    //MODOS DE LA SUB-INTERFAZ MI PERFIL
    @FXML
    private Button btnCerrarMiPerfil;
    
    @FXML
    private TextField idTextField;
    
    @FXML
    private TextField nombreTextField;
    
    @FXML
    private TextField usernameTextField;
    
    @FXML
    private TextField passTextField;
    
    @FXML
    private TextField correoTextField;
    
    @FXML
    private Button EditarMiPerfilBtn;
    
    //METODO PARA LA EDICION DEL PERFIL
    @FXML
    private void EditarMiPerfilOnAction(ActionEvent e) throws ClassNotFoundException, SQLException{
        String id = idTextField.getText();
        String Nombre = nombreTextField.getText();
        String Username = usernameTextField.getText();
        String Password = passTextField.getText();
        String Correo = correoTextField.getText();
        
            if(id.equals("") || Nombre.equals("") || Username.equals("") || Password.equals("") || 
                    Correo.equals("")){
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

        
        int ID = Integer.parseInt(idTextField.getText());
        String nombre_nvo = nombreTextField.getText();
        String username_nvo = usernameTextField.getText();
        String password_nvo = passTextField.getText();
        String descrip_nvo = "";
        String correo_nvo = correoTextField.getText();
        String direecion_nva = "";
        String tell_nvo = "";

        String consultaQuery = "UPDATE cuentausuarios SET NombreCompleto = ?, Username = ?, Password = ?, Direccion = ?, Telefono = ?, Descripcion = ?, correo = ?, id_rol = ? WHERE IdCuenta = ?";

        PreparedStatement preparedStatement = conectarBDD.prepareStatement(consultaQuery);
        preparedStatement.setString(1,nombre_nvo);
        preparedStatement.setString(2,username_nvo);
        preparedStatement.setString(3,password_nvo);
        preparedStatement.setString(4,direecion_nva);
        preparedStatement.setString(5,tell_nvo);
        preparedStatement.setString(6,descrip_nvo);
        preparedStatement.setString(7,correo_nvo);
        preparedStatement.setString(8,"2");
        preparedStatement.setInt(9, ID);
        

        int rQ = preparedStatement.executeUpdate();

        if (rQ > 0) {
            JOptionPane.showMessageDialog(null, "¡Usuario Editado!");
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no editado. Verifica tus credenciales.");
        }
    }
    
    
    //METODO PARA PASAR ADELANTE INFORMES
    @FXML
    private void VerInformesOnAction(ActionEvent e){
        paneInformes.toFront();
    }
    
    //METODO PARA PASAR ADELANTE MI PERFIL
    @FXML
    private void MiPerfilOnAction(ActionEvent e){
        paneMiPerfilEntidad.toFront();
    }
    
    //METODO PARS CARGAR ESTADOS
    private void cargarEstados(){
        Estado e1 = new Estado(1, "NO ATENDIDO");
        Estado e2 = new Estado(2, "EN REVISION");
        Estado e3 = new Estado(3, "ATENDIDO");
        
        EstadoComboBox.getItems().addAll(e1,e2,e3);
    }
    
    //CERRAR VENTANA INFORMES
    @FXML
    private void CerrarInformesOnAction(ActionEvent e){
        Stage stage = (Stage) btnCerrarInformes.getScene().getWindow();
        stage.close();
    }
    
    //METODO PARA CAMBIAR EL ESTADO
    @FXML
    private void EstadoOnAction(ActionEvent e) throws ClassNotFoundException, SQLException{
        String escritoID = idInformeTextField.getText();
        Estado selectedEstado = EstadoComboBox.getValue();
        
            if(selectedEstado == null || "".equals(escritoID)){
                JOptionPane.showMessageDialog(null, "Llene los campos");
            }else{
                //METODO PARA EDITAR INFORME
                editarEstado();
            }
    }
    
    //METODO DEL UPDATE DEL ESTADO
    private void editarEstado() throws ClassNotFoundException, SQLException{
        ConexionBDD conexionActual = new ConexionBDD();
        Connection conectarBDD = (Connection) conexionActual.getConnection();
        
        String selectedEstado = EstadoComboBox.getValue().getNombreEstado();
        int ID = Integer.parseInt(idInformeTextField.getText());

        String consultaQuery = "UPDATE informes SET estado = ? WHERE idInforme = ?";

        PreparedStatement preparedStatement = conectarBDD.prepareStatement(consultaQuery);
        preparedStatement.setString(1, selectedEstado);
        preparedStatement.setInt(2, ID);

        int rQ = preparedStatement.executeUpdate();

        if (rQ > 0) {
            JOptionPane.showMessageDialog(null, "¡Informe Editado!");
        } else {
            JOptionPane.showMessageDialog(null, "Informe no editado. Verifica tus credenciales.");
        }
    }
    
    //METODO PARA ACTUALIZAR LOS DATOS DEL TABLEVIEW POR CADA COLUMNA INFORMES
    @FXML
    private void ActualizarOnAction(ActionEvent evt) throws SQLException {
        ListaInformes.clear();

        query = "SELECT * FROM informes";
        declaracionPreparada = conexion.prepareStatement(query);
        resultadoQuery = declaracionPreparada.executeQuery();

        while (resultadoQuery.next()) {
            ListaInformes.add(new Informes(resultadoQuery.getInt("idInforme"),
                    resultadoQuery.getString("contaminante"),
                    resultadoQuery.getString("localidad"),
                    resultadoQuery.getString("unidad"),
                    resultadoQuery.getString("barrio"),
                    resultadoQuery.getString("valoracion"),
                    resultadoQuery.getString("estado")));

            InformesTable.setItems(ListaInformes);
        }
    }
    
    //METODO PARA CARGAR LOS DATOS AL TABLEVIEW POR CADA COLUMNA INFORMES
    private void cargarDatosInformes() throws ClassNotFoundException, SQLException {
        ConexionBDD miConexion1 = new ConexionBDD();
        conexion = miConexion1.getConnection();
        ActionEvent evt = null;
        ActualizarOnAction(evt);

        idCol.setCellValueFactory(new PropertyValueFactory<>("idInforme"));
        contamCol.setCellValueFactory(new PropertyValueFactory<>("contaminante"));
        locCol.setCellValueFactory(new PropertyValueFactory<>("localidad"));
        unidadCol.setCellValueFactory(new PropertyValueFactory<>("unidad"));
        barrioCol.setCellValueFactory(new PropertyValueFactory<>("barrio"));
        valoracionCol.setCellValueFactory(new PropertyValueFactory<>("valoracion"));
        estadoCol.setCellValueFactory(new PropertyValueFactory<>("estado"));
    }
    
    //CERRAR VENTANA MI PERFIL
    @FXML
    private void CerrarMiPerfilOnAction(ActionEvent e){
        Stage stage = (Stage) btnCerrarInformes.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarEstados();
        try {
            cargarDatosInformes();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(controllerEntidadVista.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
    
}
