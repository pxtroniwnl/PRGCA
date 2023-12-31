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
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class controllerCiudadanoVista implements Initializable{
    
    //NODOS


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
    private Button btnCerrarMiPerfil;
    
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
    
    //NODOS REPORTES
    
    //AQUI SE TRABAJA CON COMBOBOX ANIDADOS ASI QUE ES DISTINTA LA FORMA DE AÑADIR INMFORMACION
    //DECLARAMOS LOS NODOS
    
    @FXML
    private TextField idTextField;
    
    @FXML
    private TextField DescripTextField;
    
    @FXML
    private ComboBox<Contaminante> ContamComboBox;
    
    @FXML
    private ComboBox<Localidad> LocalidadComboBox;
    
    @FXML
    private ComboBox<UnidadComunera> UnidadComboBox;
    
    @FXML
    private ComboBox<Barrio> BarrioComboBox;
    
    @FXML
    private Button btnCerrarReportes;
    
    @FXML
    private Button crearReporteBtn;
    
    
    //METODO PARA CERRAR REPORTES
    @FXML
    private void CerrarReportesOnAction(ActionEvent e){
        Stage stage = (Stage) btnCerrarReportes.getScene().getWindow();
        stage.close();
    }
    
    //METODO PARA CREAR REPORTES
    @FXML
    private void crearReporteOnAction(ActionEvent e) throws ClassNotFoundException, SQLException{
        if (idTextField.getText().isBlank() && DescripTextField.getText().isBlank()) {
            JOptionPane.showMessageDialog(null, "Complete los campos de texto");
        } else {
            Contaminante selectedContam = ContamComboBox.getValue();
            Localidad selectedLocalidad = LocalidadComboBox.getValue();
            UnidadComunera selectedUnidad = UnidadComboBox.getValue();
            Barrio selectedBarrio = BarrioComboBox.getValue();
            
            if(selectedContam == null || selectedLocalidad == null || selectedUnidad == null || selectedBarrio == null){
                JOptionPane.showMessageDialog(null, "Eliga un campo");
            }else{
                //METODO PARA AÑADIR REPORTES
                añadirReporte();
            }
        }
    }
    
    //METODO PARA CARGAR LOS CONTAMINANTES
    private void cargarContaminante(){
        Contaminante c1 = new Contaminante(1, "Contaminacion del Aire");
        Contaminante c2 = new Contaminante(2, "Contaminacion del Agua");
        Contaminante c3 = new Contaminante(3, "Contaminacion del Suelo");
        Contaminante c4 = new Contaminante(4, "Contaminacion Acustica");
        Contaminante c5 = new Contaminante(5, "Contaminacion Luminica");
        Contaminante c6 = new Contaminante(6, "Contaminacion Visual");
        Contaminante c7 = new Contaminante(7, "Contaminacion Termica");
        Contaminante c8 = new Contaminante(8, "Contaminacion de Fauna");
        
        ContamComboBox.getItems().addAll(c1,c2,c3,c4,c5,c6,c7,c8);
    }
    
    //METODO PARA CARGAR LOCALIDADES
    private void cargarLocalidades(){
        Localidad loc1 = new Localidad(1,"Historica y del Caribe Norte");
        Localidad loc2 = new Localidad(2,"De la Virgen y Turistica");
        Localidad loc3 = new Localidad(3,"Industrial y de la Bahia");

        LocalidadComboBox.getItems().addAll(loc1, loc2, loc3);
    }
    
    //METODO PARA CARGAR UNIDADES COMUNERAS
    private void cargarUnidadesComuneras(int localidadID) throws ClassNotFoundException, SQLException {
        ConexionBDD conexionActual = new ConexionBDD(); //Creamos una nueva conexion de la clase ConexionBDD (Objeto de esta clase con el constructor por defecto)
        Connection conectarBDD = (Connection) conexionActual.getConnection(); //Conectamos e inicializamos la BDD haciendo uso de un casteo

        //Preparar la consulta SQL  para obtener Localidades
        String sql = "SELECT idUnidad, nombreUnidad FROM unidadescomuneras WHERE localidad_id = ?";
        PreparedStatement pS = conectarBDD.prepareStatement(sql);
        pS.setInt(1, localidadID);
        ResultSet rS = pS.executeQuery();

        //Lista para las Unidades Comuneras
        ObservableList<UnidadComunera> unidadesList = FXCollections.observableArrayList();

        while (rS.next()) {
            int id = rS.getInt("idUnidad");
            String nombre = rS.getString("nombreUnidad");
            UnidadComunera unidad = new UnidadComunera(id, nombre);
            unidadesList.add(unidad);
        }
        UnidadComboBox.getItems().addAll(unidadesList);
    }
    
    //METODO PARA CARGAR BARRIOS
    private void cargarBarrios(int unidadComuneraID) throws SQLException, ClassNotFoundException {
        ConexionBDD conexionActual = new ConexionBDD(); //Creamos una nueva conexion de la clase ConexionBDD (Objeto de esta clase con el constructor por defecto)
        Connection conectarBDD = (Connection) conexionActual.getConnection(); //Conectamos e inicializamos la BDD haciendo uso de un casteo

        // Preparar la consulta SQL para obtener los barrios de la unidad comunera seleccionada
        String sql = "SELECT idBarrios, nombreBarrio FROM barrios WHERE unidad_id = ?";
        PreparedStatement pS = conectarBDD.prepareStatement(sql);
        pS.setInt(1, unidadComuneraID);
        ResultSet rS = pS.executeQuery();

        ObservableList<Barrio> barriosList = FXCollections.observableArrayList();

        while (rS.next()) {
            int id = rS.getInt("idBarrios");
            String nombre = rS.getString("nombreBarrio");
            Barrio barrio = new Barrio(id, nombre);
            barriosList.add(barrio);
        }

        BarrioComboBox.setItems(barriosList);
    }
    
    //METODOS DE LOS COMBOBOX ANIDADOS
    @FXML
    private void localidadComboBoxOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Localidad selectedLocalidad = LocalidadComboBox.getValue();
        if (selectedLocalidad != null) {
            // Borra los elementos actuales en UnidadComuneraComboBox y BarrioComboBox
            UnidadComboBox.getItems().clear();
            BarrioComboBox.getItems().clear();
            cargarUnidadesComuneras(selectedLocalidad.getNumeroLocalidad());
        }
    }
    
    //METODOS DE LOS COMBOBOX ANIDADOS
    @FXML
    private void unidadComuneraComboBoxOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        // Carga los Barrios correspondientes a la Unidad Comunera seleccionada.
        UnidadComunera selectedUnidadComunera = UnidadComboBox.getValue();
        if (selectedUnidadComunera != null) {
            BarrioComboBox.getItems().clear();
            cargarBarrios(selectedUnidadComunera.getIdUnidad());
        }
    }
    
    //METODO DEL INSERT A LA BASE DE DATOS
    private void añadirReporte() throws ClassNotFoundException, SQLException{
        ConexionBDD conexionActual = new ConexionBDD(); //Creamos una nueva conexion de la clase ConexionBDD (Objeto de esta clase con el constructor por defecto)
        Connection conectarBDD = (Connection) conexionActual.getConnection(); //Conectamos e inicializamos la BDD haciendo uso de un casteo
        
        String selectedContam = ContamComboBox.getValue().getNombreContam(); //Obtiene el tipo de contaminacion elegido
        String selectedLocalidad = LocalidadComboBox.getValue().getNombreLocalidad(); //Obtiene la localidad elegida
        String selectedUnidad = UnidadComboBox.getValue().getNombreUnidad(); //Obtiene la unidad elegida
        String selectedBarrio = BarrioComboBox.getValue().getNombreBarrio(); //Obtiene el barrio elegio
        
        // Definimos la 'consulta' o 'query' para registrar nuevos registros en los respectivos campos de valor
        String consultaQuery = "INSERT INTO reportes (idUsuario, contaminante, localidad, unidad, barrio, Descripcion) "
            + "VALUES ('" + idTextField.getText() + "','" + selectedContam + "','" + selectedLocalidad 
            + "','" + selectedUnidad + "','" + selectedBarrio + "','" + DescripTextField.getText() + "')";
        
        //Instanciamos de tipo statement para la consola
        Statement instancia = (Statement) conectarBDD.createStatement();

        int rQ = instancia.executeUpdate(consultaQuery);

        //CONDICIONAL PARA SABER SI SE ENCONTRO EL USUARIO O NO (DEPENDIENDO DE LAS FILAS)
        if (rQ > 0) {
            // Si se encontró un usuario, la consulta devolverá al menos una fila
            JOptionPane.showMessageDialog(null, "¡Reporte Registrado!");
        } else {
            // Si no se encontró un usuario, la consulta no devolverá filas
            JOptionPane.showMessageDialog(null, "Reporte no registrado. Verifica tus credenciales.");
        }   
    }
    
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
    
    
    //METODO PARA CERRAR VENTANA - Mi Perfil
    @FXML
    private void CerrarMiPerfilOnAction(ActionEvent e){
        Stage stage = (Stage) btnCerrarReportes.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarLocalidades();
        cargarContaminante();
    }
}
