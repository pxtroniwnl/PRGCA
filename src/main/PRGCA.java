package main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author ALEJANDRO PATRON
 */
public class PRGCA extends Application {
    
    //Declaracion de variables para el start()
    private double xOffset = 0;
    private double yOffset = 0;

    /**
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/ViewMain.fxml"));

        Scene scene = new Scene(root);
        
        stage.initStyle(StageStyle.UNDECORATED); //PARA QUE APAREZCA SIN LA BARRA DE MINIMIZAR, CERRAR ETC...
        stage.setResizable(false); //PARA QUE NO SE PUEDA CAMBIAR EL TAMAÑO DE LA INTERFAZ
        
        // Listener para permitir mover la ventana desde el borde superior
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });
        
        stage.setScene(scene);
        stage.getIcons().add(new Image("/images/PRGCA COLOR VERDE.png"));
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
