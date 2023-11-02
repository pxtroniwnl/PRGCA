package Controladores;

import ConexionBaseDeDatos.ConexionBDD;
import java.io.IOException;
import java.sql.Statement;
import java.sql.ResultSet;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import java.sql.SQLException;
import java.sql.Connection;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

public class controllerLogin {
    
    //Declaracion de variables para permitir que la ventana se pueda mover sin redimensionarlo
    private double xOffset = 0;
    private double yOffset = 0;
    
    //Declaramos los componentes con los que trabajaremos de la Inferfaz Login
    @FXML
    private Button atrasButton;

    @FXML
    private BorderPane brdrpaneLogin;

    @FXML
    private Button loginButton;

    @FXML
    private PasswordField passwordPasswordField;

    @FXML
    private TextField usernameTextField;

    //METODO DEL BOTON PARA LOGEARSE Y VERIFICAR QUE NO ESTEN VACIOS LOS CAMPOS DE TEXTO(Evento en Interfaz)
    public void loginButtonOnAction(ActionEvent e) throws ClassNotFoundException, SQLException, IOException {

        //Vereficamos si los campos de texto estan vacios
        if (usernameTextField.getText().isBlank() == false && passwordPasswordField.getText().isBlank() == false) {
            System.out.println("Intentaste iniciar sesion");
            validarLogin(); //Llamamos el metodo para validar el login
        } else {
            System.out.println("Porfavor ingrese username y password");
        }

    }

    //METODO DEL BOTON PARA VOLVER ATRAS
    public void atrasButtonOnAction(ActionEvent e) throws IOException {
        //Creamos una instancia del ViewMain y ubicamos el archivo para redirigir luego
        Parent root = FXMLLoader.load(getClass().getResource("/view/ViewMain.fxml"));

        Scene scene = new Scene(root);
        
        Stage VentanaInicio = new Stage();
        
        VentanaInicio.initStyle(StageStyle.UNDECORATED); //PARA QUE APAREZCA SIN LA BARRA DE MINIMIZAR, CERRAR ETC...
        VentanaInicio.setResizable(false); //PARA QUE NO SE PUEDA CAMBIAR EL TAMAÑO DE LA INTERFAZ
        
        // Listener para permitir mover la ventana desde el borde superior
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            VentanaInicio.setX(event.getScreenX() - xOffset);
            VentanaInicio.setY(event.getScreenY() - yOffset);
        });
        
        //obtenemos la ventana actual para cerrarla
        Stage stage = (Stage) atrasButton.getScene().getWindow();
        stage.close();
        
        VentanaInicio.setScene(scene);
        VentanaInicio.getIcons().add(new Image("/images/PRGCA COLOR VERDE.png"));
        VentanaInicio.show();
    }

    //METODO PARA VERIFICAR LOS DATOS (Evento en Interfaz)
    public void validarLogin() throws ClassNotFoundException, SQLException, IOException {
        ConexionBDD conexionActual = new ConexionBDD(); //Creamos una nueva conexion de la clase ConexionBDD (Objeto de esta clase con el constructor por defecto)
        Connection conectarBDD = (Connection) conexionActual.getConnection(); //Conectamos e inicializamos la BDD haciendo uso de un casteo

        //Definimos la 'consulta' o 'query' para buscar el IdCuenta del usuario y contraseña ingresados
        String verificarLogin = "SELECT IdCuenta, id_rol FROM cuentausuarios WHERE Username='" + usernameTextField.getText() + "' and Password='" + passwordPasswordField.getText() + "';";

        //Instanciamos de tipo statement para la consola
        Statement instancia = conectarBDD.createStatement();

        ResultSet resultadoQuery = instancia.executeQuery(verificarLogin);

        //CONDICIONAL PARA SABER SI SE ENCONTRO EL USUARIO O NO Y LLAMADA DEL METODO PARA REDIRIGIR EN FUNCION DEL ROL QUE TENGA
        if (resultadoQuery.next()) {  // Si se encontró un usuario, la consulta devolverá al menos una fila
            int idCuenta = resultadoQuery.getInt("IdCuenta");
            int idRol = resultadoQuery.getInt("id_rol");
            //Switch para saber que interfaz mostrar en funcion del rol que tenga
            switch (idRol) {
                case 1: // Administrador
                    System.out.println("Administrador");
                    // Aquí cargamos la interfaz del administrador

                    //Creamos una instancia del AdminVista para redirigir luego
                    Parent rootAdmin = FXMLLoader.load(getClass().getResource("/view/AdminVista.fxml"));
                    
                    Stage PrincipalAdmin = new Stage();
                    PrincipalAdmin.initStyle(StageStyle.UNDECORATED); //PARA QUE APAREZCA SIN LA BARRA DE MINIMIZAR, CERRAR ETC...
                    PrincipalAdmin.setResizable(false); //PARA QUE NO SE PUEDA CAMBIAR EL TAMAÑO DE LA INTERFAZ
                    
                    // Listener para permitir mover la ventana desde el borde superior con el setResizable en False
                    rootAdmin.setOnMousePressed(event -> {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    });

                    rootAdmin.setOnMouseDragged(event -> {
                        PrincipalAdmin.setX(event.getScreenX() - xOffset);
                        PrincipalAdmin.setY(event.getScreenY() - yOffset);
                    });
                    
                    PrincipalAdmin.setScene(new Scene(rootAdmin));
                    
                    //obtenemos la ventana actual para cerrarla
                    Stage stage = (Stage) loginButton.getScene().getWindow();
                    stage.close();

                    //Hacemos display de la interfaz admin
                    PrincipalAdmin.getIcons().add(new Image("/images/PRGCA COLOR VERDE.png"));
                    PrincipalAdmin.show();
                    break;

                case 2: // Entidad Ambiental
                    // Aquí cargamos la interfaz de entidad ambiental
                    System.out.println("Entidad");
                    // Aquí cargamos la interfaz de la Entidad

                    //Creamos una instancia del EntidadVista para redirigir luego
                    Parent rootEntidad = FXMLLoader.load(getClass().getResource("/view/EntidadVista.fxml"));

                    Stage PrincipalEntidad = new Stage();
                    PrincipalEntidad.initStyle(StageStyle.UNDECORATED); //PARA QUE APAREZCA SIN LA BARRA DE MINIMIZAR, CERRAR ETC...
                    PrincipalEntidad.setResizable(false); //PARA QUE NO SE PUEDA CAMBIAR EL TAMAÑO DE LA INTERFAZ
                    
                    // Listener para permitir mover la ventana desde el borde superior con el setResizable en False
                    rootEntidad.setOnMousePressed(event -> {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    });

                    rootEntidad.setOnMouseDragged(event -> {
                        PrincipalEntidad.setX(event.getScreenX() - xOffset);
                        PrincipalEntidad.setY(event.getScreenY() - yOffset);
                    });
                    
                    PrincipalEntidad.setScene(new Scene(rootEntidad));

                    //obtenemos la ventana actual para cerrarla
                    Stage stageActual =  (Stage) loginButton.getScene().getWindow();
                    stageActual.close();

                    //Hacemos display de la interfaz admin
                    PrincipalEntidad.getIcons().add(new Image("/images/PRGCA COLOR VERDE.png"));
                    PrincipalEntidad.show();
                    break;
                    
                case 3: // Ciudadano
                    // Aquí cargamos la interfaz de ciudadano
                    System.out.println("Ciudadano");
                    // Aquí cargamos la interfaz del Ciudadano

                    //Creamos una instancia del CiudadanoVista para redirigir luego
                    Parent root = FXMLLoader.load(getClass().getResource("/view/CiudadanoVista.fxml"));

                    Stage PrincipalCiudadano = new Stage();
                    PrincipalCiudadano.initStyle(StageStyle.UNDECORATED); //PARA QUE APAREZCA SIN LA BARRA DE MINIMIZAR, CERRAR ETC...
                    PrincipalCiudadano.setResizable(false); //PARA QUE NO SE PUEDA CAMBIAR EL TAMAÑO DE LA INTERFAZ
                    
                    // Listener para permitir mover la ventana desde el borde superior con el setResizable en False
                    root.setOnMousePressed(event -> {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    });

                    root.setOnMouseDragged(event -> {
                        PrincipalCiudadano.setX(event.getScreenX() - xOffset);
                        PrincipalCiudadano.setY(event.getScreenY() - yOffset);
                    });
                    
                    PrincipalCiudadano.setScene(new Scene(root));

                    //obtenemos la ventana actual para cerrarla
                    Stage stageActual2 = (Stage) loginButton.getScene().getWindow();
                    stageActual2.close();

                    //Hacemos display de la interfaz admin
                    PrincipalCiudadano.getIcons().add(new Image("/images/PRGCA COLOR VERDE.png"));
                    PrincipalCiudadano.show();
                    break;
            }
        } else {
            // Si no se encontró un usuario, la consulta no devolverá filas
            JOptionPane.showConfirmDialog(null, "Usuario no encontrado. Verifica tus credenciales.");
        }

    }

}
