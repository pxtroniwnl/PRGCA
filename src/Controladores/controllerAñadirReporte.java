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
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class controllerAñadirReporte implements Initializable {

    //AQUI SE TRABAJA CON COMBOBOX ANIDADOS ASI QUE ES DISTINTA LA FORMA DE AÑADIR INMFORMACION
    //DECLARAMOS LOS NODOS
    @FXML
    private ComboBox<Barrio> BarrioComboBox;

    @FXML
    private ComboBox<Contaminante> ContaminanteComboBox;

    @FXML
    private TextField DescripcionTextField;

    @FXML
    private TextField IDUsuarioTextField;

    @FXML
    private ComboBox<Localidad> LocalidadComboBox;

    @FXML
    private ComboBox<UnidadComunera> UnidadComuneraComboBox;

    @FXML
    private Button atrasBttn;

    @FXML
    private Button registrarBttn;

    //METODO PARA VOLVER ATRAS
    @FXML
    private void atrasBtnOnAction(ActionEvent e) {
        Stage stage = (Stage) atrasBttn.getScene().getWindow();
        stage.close();
    }

    //METODO PARA VERIFICAR SI DEJO ESPACIOS SIN COMPLETAR
    @FXML
    private void crearReporteOnAction(ActionEvent e) throws ClassNotFoundException, SQLException {
        if (IDUsuarioTextField.getText().isBlank() && DescripcionTextField.getText().isBlank()) {
            System.out.println("Por favor, complete todos los campos.");
        } else {
            Contaminante selectedContam = ContaminanteComboBox.getValue();
            Localidad selectedLocalidad = LocalidadComboBox.getValue();
            UnidadComunera selectedUnidad = UnidadComuneraComboBox.getValue();
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
        
        ContaminanteComboBox.getItems().addAll(c1,c2,c3,c4,c5,c6,c7,c8);
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
        UnidadComuneraComboBox.getItems().addAll(unidadesList);
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

    @FXML
    private void localidadComboBoxOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Localidad selectedLocalidad = LocalidadComboBox.getValue();
        if (selectedLocalidad != null) {
            // Borra los elementos actuales en UnidadComuneraComboBox y BarrioComboBox
            UnidadComuneraComboBox.getItems().clear();
            BarrioComboBox.getItems().clear();
            cargarUnidadesComuneras(selectedLocalidad.getNumeroLocalidad());
        }
    }

    @FXML
    private void unidadComuneraComboBoxOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        // Carga los Barrios correspondientes a la Unidad Comunera seleccionada.
        UnidadComunera selectedUnidadComunera = UnidadComuneraComboBox.getValue();
        if (selectedUnidadComunera != null) {
            BarrioComboBox.getItems().clear();
            cargarBarrios(selectedUnidadComunera.getIdUnidad());
        }
    }
    
    //METODO DEL INSERT A LA BASE DE DATOS
    private void añadirReporte() throws ClassNotFoundException, SQLException{
        ConexionBDD conexionActual = new ConexionBDD(); //Creamos una nueva conexion de la clase ConexionBDD (Objeto de esta clase con el constructor por defecto)
        Connection conectarBDD = (Connection) conexionActual.getConnection(); //Conectamos e inicializamos la BDD haciendo uso de un casteo
        
        String selectedContam = ContaminanteComboBox.getValue().getNombreContam(); //Obtiene el tipo de contaminacion elegido
        String selectedLocalidad = LocalidadComboBox.getValue().getNombreLocalidad(); //Obtiene la localidad elegida
        String selectedUnidad = UnidadComuneraComboBox.getValue().getNombreUnidad(); //Obtiene la unidad elegida
        String selectedBarrio = BarrioComboBox.getValue().getNombreBarrio(); //Obtiene el barrio elegio
        
        // Definimos la 'consulta' o 'query' para registrar nuevos registros en los respectivos campos de valor
        String consultaQuery = "INSERT INTO reportes (idUsuario, contaminante, localidad, unidad, barrio, Descripcion) "
            + "VALUES ('" + IDUsuarioTextField.getText() + "','" + selectedContam + "','" + selectedLocalidad 
            + "','" + selectedUnidad + "','" + selectedBarrio + "','" + DescripcionTextField.getText() + "')";
        
        //Instanciamos de tipo statement para la consola
        Statement instancia = (Statement) conectarBDD.createStatement();

        int rQ = instancia.executeUpdate(consultaQuery);

        //CONDICIONAL PARA SABER SI SE ENCONTRO EL USUARIO O NO (DEPENDIENDO DE LAS FILAS)
        if (rQ > 0) {
            // Si se encontró un usuario, la consulta devolverá al menos una fila
            JOptionPane.showMessageDialog(null, "¡Reporte Registrado!");
            Stage stage = (Stage) registrarBttn.getScene().getWindow();
            stage.close();
        } else {
            // Si no se encontró un usuario, la consulta no devolverá filas
            JOptionPane.showMessageDialog(null, "Usuario no registrado. Verifica tus credenciales.");
        }   
    }

    /**
     *
     * @param location
     * @param resources
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarLocalidades();
        cargarContaminante();
    }
    
}
