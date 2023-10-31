package Controladores;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class controllerViewMain {

    //Declaramos los componentes de la interfaz inicial
    @FXML
    private Button SignInButton;

    @FXML
    private Button SignUpButton;

    //METODO DEL BOTON PARA REFIRIGIR A LA INTERFAZ DEL LOGIN (Evento en Interfaz - On Action)
    public void SignInOnAction(ActionEvent e) throws IOException {

        //Creamos una instancia del LoginForm para redirigir luego
        Parent root = FXMLLoader.load(getClass().getResource("/view/loginForm.fxml"));

        Stage ventanaLogin = new Stage();
        ventanaLogin.initStyle(StageStyle.UNDECORATED); //PARA QUE APAREZCA SIN LA BARRA DE MINIMIZAR, CERRAR ETC...
        ventanaLogin.setResizable(false); //PARA QUE NO SE PUEDA CAMBIAR EL TAMAÑO DE LA INTERFAZ
        ventanaLogin.setScene(new Scene(root));

        //obtenemos la ventana actual para cerrarla
        Stage stage = (Stage) SignInButton.getScene().getWindow();
        stage.close();

        //Hacemos display de la interfaz login
        ventanaLogin.show();

    }

    //METODO DEL BOTON PARA REFIRIGIR A LA INTERFAZ DE REGISTRO (Evento en Interfaz - On Action)
    public void SignUpOnAction(ActionEvent ev) throws IOException {

        //Creamos una instancia del RegisterForm para redirigir luego
        Parent root = FXMLLoader.load(getClass().getResource("/view/RegisterForm.fxml"));

        Stage ventanaRegistro = new Stage();
        ventanaRegistro.initStyle(StageStyle.UNDECORATED); //PARA QUE APAREZCA SIN LA BARRA DE MINIMIZAR, CERRAR ETC...
        ventanaRegistro.setResizable(false); //PARA QUE NO SE PUEDA CAMBIAR EL TAMAÑO DE LA INTERFAZ
        ventanaRegistro.setScene(new Scene(root));

        //obtenemos la ventana actual para cerrarla
        Stage stage = (Stage) SignUpButton.getScene().getWindow();
        stage.close();

        //Hacemos display de la interfaz registro
        ventanaRegistro.show();

    }
}
