package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {

    //Tiene el estado de la conexion
    public static Connection objConnection = null;

    //Conectar a la base de datos
    public static Connection openConnection(){

        try{
            //Se llama al driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            //Se crea conexión remota
            String url = "jdbc:mysql://brpap9tzeo21fo8noi2d-mysql.services.clever-cloud.com:3306/brpap9tzeo21fo8noi2d";
            String user = "uhffe4tbcrd50m7g";
            String password = "k4m38CQhzJjA8POWQ8RC";


            //Se crea variables de conexion local para computadores Riwi

            /*String url = "jdbc:mysql://localhost:3306/libreria";
            String user = "root";
            String password = "Rlwl2023.";
            //String password = ""; Para mi computador personal*/


            //Se hace casting para que devuelva la conexión de tipo conexión

            objConnection = DriverManager.getConnection(url,user,password);
            System.out.println("Me conecté perfectamente!!!!!!");
        }catch (ClassNotFoundException error){
            System.out.println("ERROR >> Driver no Instalado " + error.getMessage());
        }catch (SQLException error){
            System.out.println("ERROR >> error al conectar con la base de datos" + error.getMessage());
        }
        return objConnection;
    }

    //Método para finalizar una conexión
    public static void closeConnection(){
        try{
            if(objConnection != null) objConnection.close();
            System.out.println("Se finalizó la conexión con exito");
        }catch (SQLException e){
            System.out.println("Error: " + e.getMessage());
        }

    }

}
