package model;

import database.CRUD;
import database.ConfigDB;
import entity.Cita;
import entity.Medico;
import entity.Paciente;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CitaModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //1 Abrimos la conexión

        Connection objConnection = ConfigDB.openConnection();

        //2 Convertir el obj que llegó a Cita

        Cita objCita = (Cita) obj;

        try {
            //3 Escribir el SQL
            String sql = "INSERT INTO cita(id_paciente,id_medico, fecha_cita, hora_cita, motivo) VALUES (?,?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //Asignar valor a los ? ?
            objPrepare.setInt(1,objCita.getId_paciente());
            objPrepare.setInt(2,objCita.getId_medico());
            objPrepare.setDate(3,new java.sql.Date(objCita.getFecha_cita().getTime()));
            objPrepare.setTime(4,objCita.getHora_cita());
            objPrepare.setString(5, objCita.getMotivo());

            //6. Ejecutamos el Query
            objPrepare.execute();

            //Obtener los resultados con los id(llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //Iterar mientras haya un registro
            while(objRest.next()){
                objCita.setId_cita(objRest.getInt(1));
                System.out.println("Si entro en el while");
            }

            JOptionPane.showMessageDialog(null, "Cita insertado correctamente");

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objCita;
    }

    @Override
    public List<Object> findAll() {

        //Crear una lista para guardar lo que nos devuelve la base de datos
        List<Object> listCita = new ArrayList<>();

        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        try{
            //Escribirmos el query en SQl
            String sql = "SELECT id_cita, cita.fecha_cita, cita.hora_cita, cita.motivo, paciente.nombre, paciente.apellidos, medico.nombre , medico.apellidos FROM cita INNER JOIN paciente ON cita.id_paciente = paciente.id_paciente \n" +
                    "INNER JOIN medico ON cita.id_medico = medico.id_medico;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Ejecutar el query y obtener el resultado

            ResultSet objResult = objPrepare.executeQuery();

            //Mientras haya un resultado hacer

            while(objResult.next()){
                Cita objCita = new Cita();

                objCita.setId_cita(objResult.getInt("id_cita"));
                objCita.setFecha_cita(objResult.getDate("cita.fecha_cita"));
                objCita.setHora_cita(objResult.getTime("cita.hora_cita"));
                objCita.setMotivo(objResult.getString("motivo"));
                objCita.setPaciente_nombre(objResult.getString("paciente.nombre"));
                objCita.setPaciente_apellidos(objResult.getString("paciente.apellidos"));
                objCita.setMedico_nombre(objResult.getString("medico.nombre"));
                objCita.setMedico_apellidos(objResult.getString("medico.apellidos"));



                listCita.add(objCita);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();
        return listCita;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir a Cita

        Cita objCita = (Cita) obj;

        //3. Variable para conocer el estado de la acción
        boolean isUpdated = false;

        try{
            //4. Crear la Sentencia SQL
            String sql = "UPDATE cita  SET id_paciente = ? , id_medico = ?, fecha_cita =?, hora_cita = ?, motivo = ? WHERE id_cita= ?;";

            //5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Asignar valor a los parámetros del Query
            objPrepare.setInt(1,objCita.getId_paciente());
            objPrepare.setInt(2,objCita.getId_medico());
            objPrepare.setDate(3,new java.sql.Date(objCita.getFecha_cita().getTime()));
            objPrepare.setTime(4, objCita.getHora_cita());
            objPrepare.setString(5,objCita.getMotivo());
            objPrepare.setInt(6,objCita.getId_cita());

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
        Cita objCita = (Cita) obj;

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //Variable de estado
        boolean isDeleted = false;

        try{
            //Escribir la sentencia SQL
            String sql = "DELETE FROM cita WHERE id_cita=?;";

            //Creamos el prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Dar valor al ?
            objPrepare.setInt(1,objCita.getId_cita());

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

    public Cita findById(int id){
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();

        //Crear el autor que vamos a retornar
        Cita objCita = null;

        try{
                String sql = "SELECT cita.id_cita, cita.fecha_cita, cita.hora_cita, cita.motivo, paciente.id_paciente, paciente.nombre, paciente.apellidos, medico.id_medico, medico.nombre , medico.apellidos FROM cita INNER JOIN paciente ON cita.id_paciente = paciente.id_paciente \n" +
                        "INNER JOIN medico ON cita.id_medico = medico.id_medico WHERE id_cita = ?;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objCita = new Cita();
                objCita.setId_cita(objResult.getInt("cita.id_cita"));
                objCita.setFecha_cita(objResult.getDate("cita.fecha_cita"));
                objCita.setHora_cita(objResult.getTime("cita.hora_cita"));
                objCita.setMotivo(objResult.getString("cita.motivo"));
                objCita.setId_paciente(objResult.getInt("paciente.id_paciente"));
                objCita.setPaciente_nombre(objResult.getString("paciente.nombre"));
                objCita.setPaciente_apellidos(objResult.getString("paciente.apellidos"));
                objCita.setId_medico(objResult.getInt("medico.id_medico"));
                objCita.setMedico_nombre(objResult.getString("medico.nombre"));
                objCita.setMedico_apellidos(objResult.getString("medico.apellidos"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return objCita;
    }

    public List<Cita> buscarPorFecha(Date fecha){
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();

        List<Cita> listCitas = new ArrayList<>();

        //Crear el autor que vamos a retornar
        Cita objCita = null;

        try{
            String sql = "SELECT cita.id_cita, cita.fecha_cita, cita.hora_cita, cita.motivo, paciente.id_paciente, paciente.nombre, paciente.apellidos, medico.id_medico, medico.nombre , medico.apellidos FROM cita INNER JOIN paciente ON cita.id_paciente = paciente.id_paciente \n" +
                    "INNER JOIN medico ON cita.id_medico = medico.id_medico WHERE cita.fecha_cita = ?;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setDate(1,new java.sql.Date(fecha.getTime()));


            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                objCita = new Cita();
                objCita.setId_cita(objResult.getInt("cita.id_cita"));
                objCita.setFecha_cita(objResult.getDate("cita.fecha_cita"));
                objCita.setHora_cita(objResult.getTime("cita.hora_cita"));
                objCita.setMotivo(objResult.getString("cita.motivo"));
                objCita.setId_paciente(objResult.getInt("paciente.id_paciente"));
                objCita.setPaciente_nombre(objResult.getString("paciente.nombre"));
                objCita.setPaciente_apellidos(objResult.getString("paciente.apellidos"));
                objCita.setId_medico(objResult.getInt("medico.id_medico"));
                objCita.setMedico_nombre(objResult.getString("medico.nombre"));
                objCita.setMedico_apellidos(objResult.getString("medico.apellidos"));

                listCitas.add(objCita);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return listCitas;

    }


}
