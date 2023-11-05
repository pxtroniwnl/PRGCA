
package ConexionBaseDeDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//CLASE PARA BDD
public class ConexionBDD {
    private Connection conexionBDD;
    
    //METODO PARA CONECTAR A LA BASE DE DATOS
    public Connection getConnection() throws ClassNotFoundException, SQLException{
        //Declaramos los parametros para el DriverManager y GetConnection
        String usuarioBDD="root";
        String passwBDD="prgca_user";
        String urlBDD = "jdbc:mysql://localhost:3306/prgca";
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        conexionBDD = DriverManager.getConnection(urlBDD,usuarioBDD,passwBDD);
     
        return conexionBDD;
    }
    
}
