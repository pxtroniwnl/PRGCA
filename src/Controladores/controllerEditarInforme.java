/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controladores;

import ConexionBaseDeDatos.ConexionBDD;
import Modelos.Barrio;
import Modelos.Contaminante;
import Modelos.Estado;
import Modelos.Localidad;
import Modelos.UnidadComunera;
import Modelos.Valoracion;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author ALEJANDRO PATRON
 */
public class controllerEditarInforme implements Initializable{

    //DECLARAMOS LOS NODOS DE LA INTERFAZ
    @FXML
    private ComboBox<Barrio> BarrioComboBox;

    @FXML
    private ComboBox<Contaminante> ContaminanteComboBox;

    @FXML
    private Button EdicionBttn;
    
    @FXML
    private Button AtrasBttn;
    
    @FXML
    private TextField ID_TextField;

    @FXML
    private ComboBox<Estado> EstadoComboBox;

    @FXML
    private ComboBox<Localidad> LocalidadComboBox;

    @FXML
    private ComboBox<UnidadComunera> UnidadComuneraComboBox;

    @FXML
    private ComboBox<Valoracion> ValoracionComboBox;

    //METODO PARA VOLVER ATRAS DE BOTON
    @FXML
    private void AtrasInformeOnAction(ActionEvent event) {
        Stage stage = (Stage) AtrasBttn.getScene().getWindow();
        stage.close();
    }

    //METODO PARA PRESIONAR EL BOTON EDITAR
    @FXML
    private void EdicionInformeOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        Contaminante selectedContam = (Contaminante) ContaminanteComboBox.getValue();
        Localidad selectedLocalidad = (Localidad) LocalidadComboBox.getValue();
        UnidadComunera selectedUnidad = (UnidadComunera) UnidadComuneraComboBox.getValue();
        Barrio selectedBarrio = (Barrio) BarrioComboBox.getValue();
        Valoracion selectedVal = (Valoracion) ValoracionComboBox.getValue();
        
        
            if(selectedContam == null || selectedLocalidad == null || selectedUnidad == null || selectedBarrio == null || selectedVal == null){
                JOptionPane.showMessageDialog(null, "Eliga un campo");
            }else{
                //METODO PARA EDITAR INFORME
                editarInformes();
            }
    }
    
    //METODO PARA ANIDADOS LOCALIDAD-UNIDAD
    @FXML
    private void localidadComboBoxOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        Localidad selectedLocalidad = LocalidadComboBox.getValue();
        if (selectedLocalidad != null) {
            // Borra los elementos actuales en UnidadComuneraComboBox y BarrioComboBox
            UnidadComuneraComboBox.getItems().clear();
            BarrioComboBox.getItems().clear();
            cargarUnidadesComuneras(selectedLocalidad.getNumeroLocalidad());
        }
    }    

    //METODO PARA ANIDADOS UNIDAD-BARRIOS
    @FXML
    private void unidadComuneraComboBoxOnAction(ActionEvent event) throws ClassNotFoundException, SQLException {
        // Carga los Barrios correspondientes a la Unidad Comunera seleccionada.
        UnidadComunera selectedUnidadComunera = UnidadComuneraComboBox.getValue();
        if (selectedUnidadComunera != null) {
            BarrioComboBox.getItems().clear();
            cargarBarrios(selectedUnidadComunera.getIdUnidad());
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
    
    //METODO PARA CARGAR VALORACION
    private void cargarValoracion(){
        Valoracion v1 = new Valoracion(1, "BAJA");
        Valoracion v2 = new Valoracion(2, "MEDIA");
        Valoracion v3 = new Valoracion(3, "ALTA");
        Valoracion v4 = new Valoracion(4, "CRITICA");
        
        
        ValoracionComboBox.getItems().addAll(v1,v2,v3,v4);
    }
    
    //METODO PARS CARGAR ESTADOS
    private void cargarEstados(){
        Estado e1 = new Estado(1, "NO ATENDIDO");
        Estado e2 = new Estado(2, "EN REVISION");
        Estado e3 = new Estado(3, "ATENDIDO");
        
        EstadoComboBox.getItems().addAll(e1,e2,e3);
    }

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

    private void cargarBarrios(int idUnidad) throws ClassNotFoundException, SQLException, SQLException {
        ConexionBDD conexionActual = new ConexionBDD(); //Creamos una nueva conexion de la clase ConexionBDD (Objeto de esta clase con el constructor por defecto)
        Connection conectarBDD = (Connection) conexionActual.getConnection(); //Conectamos e inicializamos la BDD haciendo uso de un casteo

        // Preparar la consulta SQL para obtener los barrios de la unidad comunera seleccionada
        String sql = "SELECT idBarrios, nombreBarrio FROM barrios WHERE unidad_id = ?";
        PreparedStatement pS = conectarBDD.prepareStatement(sql);
        pS.setInt(1, idUnidad);
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
    
    //METODO DEL UPDATE A LA BDD
    private void editarInformes() throws SQLException, ClassNotFoundException {
        ConexionBDD conexionActual = new ConexionBDD();
        Connection conectarBDD = (Connection) conexionActual.getConnection();

        String selectedContam = ContaminanteComboBox.getValue().getNombreContam();
        String selectedLocalidad = LocalidadComboBox.getValue().getNombreLocalidad();
        String selectedUnidad = UnidadComuneraComboBox.getValue().getNombreUnidad();
        String selectedBarrio = BarrioComboBox.getValue().getNombreBarrio();
        String selectedValoracion = ValoracionComboBox.getValue().getNombreValoracion();
        String selectedEstado = EstadoComboBox.getValue().getNombreEstado();
        int ID = Integer.parseInt(ID_TextField.getText());

        String consultaQuery = "UPDATE informes SET contaminante = ?, localidad = ?, unidad = ?, barrio = ?, valoracion = ?, estado = ? WHERE idInforme = ?";

        PreparedStatement preparedStatement = conectarBDD.prepareStatement(consultaQuery);
        preparedStatement.setString(1, selectedContam);
        preparedStatement.setString(2, selectedLocalidad);
        preparedStatement.setString(3, selectedUnidad);
        preparedStatement.setString(4, selectedBarrio);
        preparedStatement.setString(5, selectedValoracion);
        preparedStatement.setString(6, selectedEstado);
        preparedStatement.setInt(7, ID);

        int rQ = preparedStatement.executeUpdate();

        if (rQ > 0) {
            JOptionPane.showMessageDialog(null, "¡Informe Editado!");
            Stage stage = (Stage) EdicionBttn.getScene().getWindow();
            stage.close();
        } else {
            JOptionPane.showMessageDialog(null, "Informe no editado. Verifica tus credenciales.");
        }
}

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cargarContaminante();
        cargarLocalidades();
        cargarValoracion();
        cargarEstados();
    }
    
}
