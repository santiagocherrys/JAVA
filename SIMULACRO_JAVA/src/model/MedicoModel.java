package model;

import database.CRUD;
import database.ConfigDB;
import entity.Especialidad;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MedicoModel implements CRUD {

    @Override
    public Object insert(Object obj) {
        //1 Abrimos la conexión

        Connection objConnection = ConfigDB.openConnection();

        //2 Convertir el obj que llegó a Autor

        Medico objMedico = (Medico) obj;

        try {
            //3 Escribir el SQL
            String sql = "INSERT INTO medico(nombre, apellidos,id_especialidad) VALUES (?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //Asignar valor a los ? ?
            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellidos());
            objPrepare.setInt(3,objMedico.getId_especialidad());

            //6. Ejecutamos el Query
            objPrepare.execute();

            //Obtener los resultados con los id(llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //Iterar mientras haya un registro
            while(objRest.next()){
                objMedico.setId_medico(objRest.getInt(1));
                System.out.println("Si entro en el while");
            }

            JOptionPane.showMessageDialog(null, "Medico insertado correctamente");

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objMedico;
    }

    @Override
    public List<Object> findAll() {
        //Crear una lista para guardar lo que nos devuelve la base de datos
        List<Object> listMedico = new ArrayList<>();

        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        try{
            //Escribirmos el query en SQl
            String sql = "SELECT id_medico, medico.nombre, apellidos, especialidad.nombre FROM medico INNER JOIN especialidad ON medico.id_especialidad = especialidad.id_especialidad;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Ejecutar el query y obtener el resultado

            ResultSet objResult = objPrepare.executeQuery();

            //Mientras haya un resultado hacer

            while(objResult.next()){
                Medico objMedico = new Medico();

                objMedico.setId_medico(objResult.getInt("id_medico"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setEspecialidad_nombre(objResult.getString("especialidad.nombre"));


                listMedico.add(objMedico);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();
        return listMedico;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir a Autor

        Medico objMedico = (Medico) obj;

        //3. Variable para conocer el estado de la acción
        boolean isUpdated = false;

        try{
            //4. Crear la Sentencia SQL
            String sql = "UPDATE medico  SET nombre = ? , apellidos = ?, id_especialidad = ? WHERE id_medico = ?;";

            //5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Asignar valor a los parámetros del Query
            objPrepare.setString(1,objMedico.getNombre());
            objPrepare.setString(2,objMedico.getApellidos());
            objPrepare.setInt(3, objMedico.getId_especialidad());
            objPrepare.setInt(4, objMedico.getId_medico());


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
        Medico objMedico = (Medico) obj;

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //Variable de estado
        boolean isDeleted = false;

        try{
            //Escribir la sentencia SQL
            String sql = "DELETE FROM medico WHERE id_medico=?;";

            //Creamos el prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Dar valor al ?
            objPrepare.setInt(1,objMedico.getId_medico());

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

    public Medico findById(int id){
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();

        //Crear el autor que vamos a retornar
        Medico objMedico = null;

        try{
            String sql = "SELECT id_medico, medico.nombre, apellidos, medico.id_especialidad, especialidad.nombre FROM medico INNER JOIN especialidad ON medico.id_especialidad = especialidad.id_especialidad HAVING id_medico = ?;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objMedico = new Medico();
                objMedico.setId_medico(objResult.getInt("id_medico"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId_especialidad(objResult.getInt("medico.id_especialidad"));
                objMedico.setEspecialidad_nombre(objResult.getString("especialidad.nombre"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return objMedico;
    }

    public List<Medico> findMedicosByEspecialidad(int especialidad){
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();
        List<Medico> listaMedicos = new ArrayList<>();

        //Crear el autor que vamos a retornar
        Medico objMedico = null;

        try{
            String sql = "SELECT * FROM medico INNER JOIN especialidad ON medico.id_especialidad = especialidad.id_especialidad WHERE especialidad.id_especialidad=?;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,especialidad);

            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                objMedico = new Medico();
                objMedico.setId_medico(objResult.getInt("id_medico"));
                objMedico.setNombre(objResult.getString("medico.nombre"));
                objMedico.setApellidos(objResult.getString("apellidos"));
                objMedico.setId_especialidad(objResult.getInt("medico.id_especialidad"));
                objMedico.setEspecialidad_nombre(objResult.getString("especialidad.nombre"));

                listaMedicos.add(objMedico);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        return listaMedicos;

    }
}
