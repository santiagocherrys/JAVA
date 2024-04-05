package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Pasajero;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PasajeroModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //1 Abrimos la conexión

        Connection objConnection = ConfigDB.openConnection();

        //Se convierte a Pasajero

        Pasajero objPasajero = (Pasajero) obj;
        try{
            //Se hace sentencia SQL

            String sql = "INSERT INTO pasajero(nombre, apellido, documento_identidad) VALUES(?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //Se le asigna valores a ? ?
            objPrepare.setString(1,objPasajero.getNombre());
            objPrepare.setString(2,objPasajero.getApellido());
            objPrepare.setString(3,objPasajero.getDocumento_identidad());

            //Se ejecuta el Query
            objPrepare.execute();

            //Se obtiene las primary keys generadas
            ResultSet objRest = objPrepare.getGeneratedKeys();

            while (objRest.next()){
                objPasajero.setId_pasajero(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Pasajero ingresado correctamente");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //Se cierra la conexión
        ConfigDB.closeConnection();
        return objPasajero;
    }

    @Override
    public List<Object> findAll() {
        //Crear una lista para guardar lo que nos devuelve la base de datos
        List<Object> listPasajero = new ArrayList<>();

        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        try{
            //Escribirmos el query en SQl
            String sql = "SELECT * FROM pasajero;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Ejecutar el query y obtener el resultado

            ResultSet objResult = objPrepare.executeQuery();

            //Mientras haya un resultado hacer

            while(objResult.next()){
                Pasajero objPasajero = new Pasajero();

                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));


                listPasajero.add(objPasajero);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();
        return listPasajero;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir a Autor

        Pasajero objPasajero = (Pasajero) obj;

        //3. Variable para conocer el estado de la acción
        boolean isUpdated = false;

        try{
            //4. Crear la Sentencia SQL
            String sql = "UPDATE pasajero  SET nombre = ? , apellido = ?, documento_identidad = ? WHERE id_pasajero= ?;";

            //5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Asignar valor a los parámetros del Query
            objPrepare.setString(1,objPasajero.getNombre());
            objPrepare.setString(2,objPasajero.getApellido());
            objPrepare.setString(3,objPasajero.getDocumento_identidad());
            objPrepare.setInt(4,objPasajero.getId_pasajero());



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
    public boolean delete(Object obj) {
        //Convertir el objeto a la Entidad
        Pasajero objPasajero = (Pasajero) obj;

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //Variable de estado
        boolean isDeleted = false;

        try{
            //Escribir la sentencia SQL
            String sql = "DELETE FROM pasajero WHERE id_pasajero=?;";

            //Creamos el prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Dar valor al ?
            objPrepare.setInt(1,objPasajero.getId_pasajero());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows >0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The  delete was successful. ");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }

    public Pasajero findById(int id){
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();

        //Crear el autor que vamos a retornar
        Pasajero objPasajero = null;

        try{
            String sql = "SELECT * FROM pasajero WHERE id_pasajero = ?;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objPasajero = new Pasajero();
                objPasajero.setId_pasajero(objResult.getInt("id_pasajero"));
                objPasajero.setNombre(objResult.getString("nombre"));
                objPasajero.setApellido(objResult.getString("apellido"));
                objPasajero.setDocumento_identidad(objResult.getString("documento_identidad"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return objPasajero;
    }
}
