/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import ConexionBaseDeDatos.ConexionBDD;
import Modelos.Barrio;
import Modelos.Contaminante;
import Modelos.Localidad;
import Modelos.Rol;
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
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
            System.out.println("Ningun espacio en blanco");
            //Llamar al metodo que Inserta los datos
        }
    }

    //METODO PARA CARGAR LOCALIDADES
    private void cargarLocalidades() throws ClassNotFoundException, SQLException {
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
            cargarUnidadesComuneras(selectedLocalidad.getNumeroLocalidad());

        }
    }

    @FXML
    private void unidadComuneraComboBoxOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        // Carga los Barrios correspondientes a la Unidad Comunera seleccionada.
        UnidadComunera selectedUnidadComunera = UnidadComuneraComboBox.getValue();
        if (selectedUnidadComunera != null) {
            cargarBarrios(selectedUnidadComunera.getIdUnidad());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cargarLocalidades();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(controllerAñadirReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        LocalidadComboBox.setOnAction(event -> {
            try {
                this.localidadComboBoxOnAction(event);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(controllerAñadirReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        UnidadComuneraComboBox.setOnAction(event -> {
            try {
                this.unidadComuneraComboBoxOnAction(event);
            } catch (SQLException | ClassNotFoundException ex) {
                Logger.getLogger(controllerAñadirReporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }
}
