package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Reservacion;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReservacionModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //Reservacion objReservacion = (Reservacion) obj;


        //1 Abrimos la conexión

        Connection objConnection = ConfigDB.openConnection();

        //Se convierte a Pasajero

        Reservacion objReservacion = (Reservacion) obj;

        try{
            //Se hace sentencia SQL

            objReservacion.imprimirTodo();

            String sql = "INSERT INTO reservacion(id_pasajero, id_vuelo, fecha_reservacion, asiento) VALUES(?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //Se le asigna valores a ? ?
            objPrepare.setInt(1,objReservacion.getId_pasajero());
            objPrepare.setInt(2,objReservacion.getId_vuelo());
            objPrepare.setString(3,objReservacion.getFecha_reservacion());
            objPrepare.setString(4,objReservacion.getAsiento());

            //Se ejecuta el Query
            objPrepare.execute();

            //Se obtiene las primary keys generadas
            ResultSet objRest = objPrepare.getGeneratedKeys();

            while (objRest.next()){
                objReservacion.setId_reservacion(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Reservación ingresado correctamente");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, "Error al insertar en DB " + e.getMessage());
        }

        //Se cierra la conexión
        ConfigDB.closeConnection();
        return objReservacion;
    }

    @Override
    public List<Object> findAll() {
        //Crear una lista para guardar lo que nos devuelve la base de datos
        List<Object> listReservacion = new ArrayList<>();

        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        try{
            //Escribirmos el query en SQl
            String sql = "SELECT id_reservacion, fecha_reservacion, asiento, nombre, apellido, destino, fecha_salida, hora_salida FROM reservacion INNER JOIN pasajero ON pasajero.id_pasajero = reservacion.id_pasajero\n" +
                    "INNER JOIN vuelo ON vuelo.id_vuelo = reservacion.id_vuelo;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Ejecutar el query y obtener el resultado

            ResultSet objResult = objPrepare.executeQuery();

            //Mientras haya un resultado hacer

            while(objResult.next()){
                Reservacion objReservacion = new Reservacion();

                objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));
                objReservacion.setPasajero_nombre(objResult.getString("nombre"));
                objReservacion.setPasajero_apellido(objResult.getString("apellido"));
                objReservacion.setVuelo_destino(objResult.getString("destino"));
                objReservacion.setVuelo_fecha_salida(objResult.getString("fecha_salida"));
                objReservacion.setVuelo_hora_salida(objResult.getString("hora_salida"));



                listReservacion.add(objReservacion);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();
        return listReservacion;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir a Reservacion

        Reservacion objReservacion = (Reservacion) obj;

        //3. Variable para conocer el estado de la acción
        boolean isUpdated = false;

        try{
            //4. Crear la Sentencia SQL
            String sql = "UPDATE reservacion  SET asiento = ? WHERE id_reservacion= ?;";

            //5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Asignar valor a los parámetros del Query
            objPrepare.setString(1,objReservacion.getAsiento());
            objPrepare.setInt(2,objReservacion.getId_reservacion());




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
        Reservacion objReservacion = (Reservacion) obj;

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //Variable de estado
        boolean isDeleted = false;

        try{
            //Escribir la sentencia SQL
            String sql = "DELETE FROM reservacion WHERE id_reservacion=?;";

            //Creamos el prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Dar valor al ?
            objPrepare.setInt(1,objReservacion.getId_reservacion());

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

    public Reservacion findById(int id){
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();

        //Crear el autor que vamos a retornar
        Reservacion objReservacion = null;

        try{
            String sql = "SELECT id_reservacion, reservacion.id_pasajero, reservacion.id_vuelo ,fecha_reservacion, asiento,nombre, apellido,destino, fecha_salida, hora_salida FROM reservacion INNER JOIN pasajero ON pasajero.id_pasajero = reservacion.id_pasajero\n" +
                        "INNER JOIN vuelo ON vuelo.id_vuelo = reservacion.id_vuelo WHERE id_reservacion = ?;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objReservacion = new Reservacion();

                objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
                objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                objReservacion.setAsiento(objResult.getString("asiento"));
                objReservacion.setPasajero_nombre(objResult.getString("nombre"));
                objReservacion.setPasajero_apellido(objResult.getString("apellido"));
                objReservacion.setVuelo_destino(objResult.getString("destino"));
                objReservacion.setVuelo_fecha_salida(objResult.getString("fecha_salida"));
                objReservacion.setVuelo_hora_salida(objResult.getString("hora_salida"));
                objReservacion.setId_pasajero(objResult.getInt("reservacion.id_pasajero"));
                objReservacion.setId_vuelo(objResult.getInt("reservacion.id_vuelo"));

            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return objReservacion;
    }

    public List<Reservacion> findReservacionesByvuelo(){
        VueloModel objVueloModel = new VueloModel();
        List<Object> listaVuelos = objVueloModel.findAll();
        List<Reservacion> listReservacion = new ArrayList<>();
        //Revisar si hay destinos

        if(listaVuelos.size()> 0){

            //Se hace selector para vuelo que elija destino
            ArrayList<String> unic = new ArrayList<>();



            int index = 0;
            for(Object iterador : listaVuelos){
                Vuelo vuelo = (Vuelo) iterador;
                //Buscar si el destino existe o no


                int index1 = Arrays.binarySearch(unic.toArray(), vuelo.getDestino());

                if (index1 < 0) {
                    //Se puede añadir destino
                    unic.add(vuelo.getDestino());
                    index++;
                } else {
                    //No se añade ya que existe
                }
            }

            String input = (String) JOptionPane.showInputDialog(null, "Escoja vuelo por Destino para filtrar...",
                    "destinos disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                    // default
                    // icon
                    unic.toArray(), // Array of choices
                    unic.toArray()[0]); // Initial choice

            int id_vuelo = 0;
            for(Object iterador : listaVuelos){
                Vuelo vuelo = (Vuelo) iterador;
                if(vuelo.getDestino().equals(input)){
                    id_vuelo = vuelo.getId_vuelo();
                }
            }
            //Abrir la conexion
            Connection objConnection = ConfigDB.openConnection();
            try{
                //Escribirmos el query en SQl
                String sql = "SELECT id_reservacion, reservacion.id_pasajero, reservacion.id_vuelo ,fecha_reservacion, asiento,nombre, apellido,destino, fecha_salida, hora_salida FROM reservacion INNER JOIN pasajero ON pasajero.id_pasajero = reservacion.id_pasajero\n" +
                        "INNER JOIN vuelo ON vuelo.id_vuelo = reservacion.id_vuelo WHERE reservacion.id_vuelo = ?;";

                PreparedStatement objPrepare = objConnection.prepareStatement(sql);

                objPrepare.setInt(1,id_vuelo);
                //Ejecutar el query y obtener el resultado



                ResultSet objResult = objPrepare.executeQuery();

                //Mientras haya un resultado hacer

                while(objResult.next()){
                    Reservacion objReservacion = new Reservacion();

                    objReservacion.setId_reservacion(objResult.getInt("id_reservacion"));
                    objReservacion.setFecha_reservacion(objResult.getString("fecha_reservacion"));
                    objReservacion.setAsiento(objResult.getString("asiento"));
                    objReservacion.setPasajero_nombre(objResult.getString("nombre"));
                    objReservacion.setPasajero_apellido(objResult.getString("apellido"));
                    objReservacion.setVuelo_destino(objResult.getString("destino"));
                    objReservacion.setVuelo_fecha_salida(objResult.getString("fecha_salida"));
                    objReservacion.setVuelo_hora_salida(objResult.getString("hora_salida"));
                    objReservacion.setId_pasajero(objResult.getInt("reservacion.id_pasajero"));
                    objReservacion.setId_vuelo(objResult.getInt("reservacion.id_vuelo"));



                    listReservacion.add(objReservacion);
                }
            }catch (SQLException error){
                JOptionPane.showMessageDialog(null,error.getMessage());
            }

            ConfigDB.closeConnection();
        }


        return listReservacion;
    }
}
