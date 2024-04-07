package model;

import database.CRUD;
import database.ConfigDB;
import entity.Avion;
import entity.Pasajero;
import entity.Vuelo;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VueloModel implements CRUD {
    @Override
    public Object insert(Object obj) {
        //1 Abrimos la conexión

        Connection objConnection = ConfigDB.openConnection();

        //Se convierte a Pasajero

        Vuelo objVuelo = (Vuelo) obj;
        try{
            //Se hace sentencia SQL

            String sql = "INSERT INTO vuelo(destino, fecha_salida, hora_salida, id_avion) VALUES(?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //Se le asigna valores a ? ?
            objPrepare.setString(1,objVuelo.getDestino());
            objPrepare.setString(2,objVuelo.getFecha_salida());
            objPrepare.setString(3,objVuelo.getHora_salida());
            objPrepare.setInt(4,objVuelo.getId_avion());

            //Se ejecuta el Query
            objPrepare.execute();

            //Se obtiene las primary keys generadas
            ResultSet objRest = objPrepare.getGeneratedKeys();

            while (objRest.next()){
                objVuelo.setId_vuelo(objRest.getInt(1));
            }

            JOptionPane.showMessageDialog(null,"Vuelo ingresado correctamente");
        }catch (SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        //Se cierra la conexión
        ConfigDB.closeConnection();
        return objVuelo;
    }

    @Override
    public List<Object> findAll() {
        //Crear una lista para guardar lo que nos devuelve la base de datos
        List<Object> listVuelo = new ArrayList<>();

        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        try{
            //Escribirmos el query en SQl
            String sql = "SELECT id_vuelo, destino, fecha_salida, hora_salida, modelo, capacidad FROM vuelo INNER JOIN avion ON avion.id_avion = vuelo.id_avion;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Ejecutar el query y obtener el resultado

            ResultSet objResult = objPrepare.executeQuery();

            //Mientras haya un resultado hacer

            while(objResult.next()){
                Vuelo objVuelo = new Vuelo();

                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setAvion_setModelo(objResult.getString("modelo"));
                objVuelo.setAvion_capacidad(objResult.getInt("capacidad"));


                listVuelo.add(objVuelo);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();
        return listVuelo;
    }

    @Override
    public boolean update(Object obj) {
        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir a Vuelo

        Vuelo objVuelo = (Vuelo) obj;

        //3. Variable para conocer el estado de la acción
        boolean isUpdated = false;

        try{
            //4. Crear la Sentencia SQL
            String sql = "UPDATE vuelo  SET fecha_salida = ? WHERE id_vuelo= ?;";

            //5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Asignar valor a los parámetros del Query
            objPrepare.setString(1,objVuelo.getFecha_salida());
            objPrepare.setInt(2,objVuelo.getId_vuelo());




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
        Vuelo objVuelo = (Vuelo) obj;

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //Variable de estado
        boolean isDeleted = false;

        try{
            //Escribir la sentencia SQL
            String sql = "DELETE FROM vuelo WHERE id_vuelo=?;";

            //Creamos el prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Dar valor al ?
            objPrepare.setInt(1,objVuelo.getId_vuelo());

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

    public Vuelo findById(int id){
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();

        //Crear el autor que vamos a retornar
        Vuelo objVuelo = null;

        try{
            String sql = "SELECT id_vuelo, destino, fecha_salida, hora_salida, vuelo.id_avion, modelo, capacidad FROM vuelo INNER JOIN avion ON avion.id_avion = vuelo.id_avion WHERE id_vuelo = ?;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objVuelo = new Vuelo();
                objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                objVuelo.setDestino(objResult.getString("destino"));
                objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                objVuelo.setHora_salida(objResult.getString("hora_salida"));
                objVuelo.setId_avion(objResult.getInt("vuelo.id_avion"));
                objVuelo.setAvion_setModelo(objResult.getString("modelo"));
                objVuelo.setAvion_capacidad(objResult.getShort("capacidad"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return objVuelo;
    }

    public List<Vuelo> buscarPorDestino(){

        List<Object> listaVuelos = findAll();
        List<Vuelo> listVuelo = new ArrayList<>();
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

            String input = (String) JOptionPane.showInputDialog(null, "Escoja Destino para filtrar...",
                    "destinos disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                    // default
                    // icon
                    unic.toArray(), // Array of choices
                    unic.toArray()[0]); // Initial choice

            String destino = "";
            for(Object iterador : listaVuelos){
                Vuelo vuelo = (Vuelo) iterador;
                if(vuelo.getDestino().equals(input)){
                    destino = vuelo.getDestino();
                }
            }
            //Abrir la conexion
            Connection objConnection = ConfigDB.openConnection();
            try{
                //Escribirmos el query en SQl
                String sql = "SELECT id_vuelo, destino, fecha_salida, hora_salida FROM vuelo WHERE destino=?;";

                PreparedStatement objPrepare = objConnection.prepareStatement(sql);

                objPrepare.setString(1,destino);
                //Ejecutar el query y obtener el resultado



                ResultSet objResult = objPrepare.executeQuery();

                //Mientras haya un resultado hacer

                while(objResult.next()){
                    Vuelo objVuelo = new Vuelo();

                    objVuelo.setId_vuelo(objResult.getInt("id_vuelo"));
                    objVuelo.setDestino(objResult.getString("destino"));
                    objVuelo.setFecha_salida(objResult.getString("fecha_salida"));
                    objVuelo.setHora_salida(objResult.getString("hora_salida"));



                    listVuelo.add(objVuelo);
                }
            }catch (SQLException error){
                JOptionPane.showMessageDialog(null,error.getMessage());
            }

            ConfigDB.closeConnection();
        }


        return listVuelo;
    }
}
