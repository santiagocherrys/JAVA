package model;

import database.CRUD;
import database.ConfigDB;
import entity.Autor;

import javax.swing.*;
import java.io.ObjectInputFilter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AutorModel implements CRUD {

    @Override
    public Object insert(Object obj) {

        //1 Abrimos la conexión

        Connection objConnection = ConfigDB.openConnection();

        //2 Convertir el obj que llegó a Autor

        Autor objAutor = (Autor) obj;

        try {
            //3 Escribir el SQL
            String sql = "INSERT INTO autores(nombre, nacionalidad) VALUES (?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //Asignar valor a los ? ?
            objPrepare.setString(1,objAutor.getNombre());
            objPrepare.setString(2,objAutor.getNacionalidad());

            //6. Ejecutamos el Query
            objPrepare.execute();

            //Obtener los resultados con los id(llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //Iterar mientras haya un registro
            while(objRest.next()){
                objAutor.setId(objRest.getInt(1));
                System.out.println("Si entro en el while");
            }

            JOptionPane.showMessageDialog(null, "Autor insertado correctamente");

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objAutor;
    }

    @Override
    public List<Object> findAll() {
        //Crear una lista para guardar lo que nos devuelve la base de datos
        List<Object> listAutor = new ArrayList<>();

        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        try{
            //Escribirmos el query en SQl
            String sql = "SELECT * FROM autores;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Ejecutar el query y obtener el resultado

            ResultSet objResult = objPrepare.executeQuery();

            //Mientras haya un resultado hacer

            while(objResult.next()){
                Autor objAutor = new Autor();

                objAutor.setId(objResult.getInt("id"));
                objAutor.setNombre(objResult.getString("nombre"));
                objAutor.setNacionalidad(objResult.getString("nacionalidad"));

                listAutor.add(objAutor);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();
        return listAutor;
    }

    @Override
    public boolean update(Object obj) {

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir a Autor

        Autor objAutor = (Autor) obj;

        //3. Variable para conocer el estado de la acción
        boolean isUpdated = false;

        try{
            //4. Crear la Sentencia SQL
            String sql = "UPDATE autores  SET nombre = ? , nacionalidad = ? WHERE id = ?;";

            //5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Asignar valor a los parámetros del Query
            objPrepare.setString(1,objAutor.getNombre());
            objPrepare.setString(2,objAutor.getNacionalidad());
            objPrepare.setInt(3, objAutor.getId());

            int totalRowAffected = objPrepare.executeUpdate();

            if(totalRowAffected > 0){
                isUpdated = true;
                JOptionPane.showMessageDialog(null,"The update was successful. ");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }finally{
            ConfigDB.closeConnection();
        }
        return isUpdated;
    }

    @Override
    public boolean delete(Object obj){
        //Convertir el objeto a la Entidad
        Autor objAutor = (Autor) obj;

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //Variable de estado
        boolean isDeleted = false;

        try{
            //Escribir la sentencia SQL
            String sql = "DELETE FROM autores WHERE id=?;";

            //Creamos el prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Dar valor al ?
            objPrepare.setInt(1,objAutor.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows >0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The  updatewas successful. ");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }

    public Autor findById(int id){
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();

        //Crear el autor que vamos a retornar
        Autor objAutor = null;

        try{
            String sql = "SELECT * FROM autores WHERE id = ?;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objAutor = new Autor();
                objAutor.setId(objResult.getInt("id"));
                objAutor.setNombre(objResult.getString("nombre"));
                objAutor.setNacionalidad(objResult.getString("nacionalidad"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return objAutor;
    }
}
