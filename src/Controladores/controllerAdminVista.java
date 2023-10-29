
package Controladores;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class controllerAdminVista implements Initializable{
    
    
    //DECLARAMOS LO QUE TENGAMOS EN LA INTERFAZ
    @FXML
    private Button btnInformes;

    @FXML
    private Button btnReportes;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUsuarios;
    
    @FXML
    private Button btnCerrar;
    
    
    //METODO PARA EL CLICK DE LOS BOTONES DE LA INTERFAZ ADMIN
    @FXML
    private void clickBotones(ActionEvent event){
        if(event.getSource() == btnUsuarios){
            
        }else if(event.getSource() == btnReportes){
            
        }else if(event.getSource() == btnInformes){
            
        }else if(event.getSource() == btnCerrar){
            System.exit(0);
        }else if(event.getSource() == btnSearch){
            
        }
    }
    

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
