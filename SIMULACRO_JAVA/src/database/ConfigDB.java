package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigDB {
    //Tiene el estado de la conexión
    public static Connection objConnection = null;

    //Conectar a la base de datos
    public static Connection openConnection(){

        try{
           //Se llama el driver
           Class.forName("com.mysql.cj.jdbc.Driver");

           //Se crea coneción local
            String url = "jdbc:mysql://localhost:3306/hospital_simulacro";
            String user = "root";
            String password = "Rlwl2023.";
            //String password = "";

            //Se hace casting para que devuelva la conexión de tipo conexión

            objConnection = DriverManager.getConnection(url,user,password);
            System.out.println("Me conecté perfectamente!!!!!!");

        }catch (ClassNotFoundException error){
            System.out.println("ERROR >> Driver no Instalado " + error.getMessage());
        }catch (SQLException error){
            System.out.println("ERROR >> error al conectar con la base de datos " + error.getMessage());
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
