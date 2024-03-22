package model;

import database.CRUD;
import database.ConfigDB;
import entity.Autor;
import entity.Libro;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LibroModel implements CRUD {
    @Override
    public Object insert(Object obj) {

        //1 Abrimos la conexión

        Connection objConnection = ConfigDB.openConnection();

        //2 Convertir el obj que llegó a Autor

        Libro objLibro = (Libro) obj;

        try {
            //3 Escribir el SQL
            String sql = "INSERT INTO libro(titulo, anio_publicacion, precio, id_autor) VALUES (?,?,?,?);";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            //Asignar valor a los ? ?
            objPrepare.setString(1,objLibro.getTitulo());
            objPrepare.setInt(2,objLibro.getAnio_publicacion());
            objPrepare.setDouble(3,objLibro.getPrecio());
            objPrepare.setInt(4,objLibro.getId_autor());

            //6. Ejecutamos el Query
            objPrepare.execute();

            //Obtener los resultados con los id(llaves generadas)
            ResultSet objRest = objPrepare.getGeneratedKeys();

            //Iterar mientras haya un registro
            while(objRest.next()){
                objLibro.setId(objRest.getInt(1));
                System.out.println("Si entro en el while");
            }

            JOptionPane.showMessageDialog(null, "Libro insertado correctamente");

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        ConfigDB.closeConnection();
        return objLibro;

    }

    @Override
    public List<Object> findAll() {
        //Crear una lista para guardar lo que nos devuelve la base de datos
        List<Object> listLibro = new ArrayList<>();

        //Abrir la conexion
        Connection objConnection = ConfigDB.openConnection();

        try{
            //Escribirmos el query en SQl
            String sql = "SELECT * FROM libro;";

            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Ejecutar el query y obtener el resultado

            ResultSet objResult = objPrepare.executeQuery();

            //Mientras haya un resultado hacer

            while(objResult.next()){
                Libro objLibro = new Libro();

                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getString("titulo"));
                objLibro.setAnio_publicacion(objResult.getInt("anio_publicacion"));
                objLibro.setPrecio(objResult.getDouble("precio"));
                objLibro.setId_autor(objResult.getInt("id_autor"));

                listLibro.add(objLibro);
            }
        }catch (SQLException error){
            JOptionPane.showMessageDialog(null,error.getMessage());
        }

        ConfigDB.closeConnection();
        return listLibro;
    }

    @Override
    public boolean update(Object obj) {

        //1. Abrir la conexión
        Connection objConnection = ConfigDB.openConnection();

        //2. Convertir a libro

        Libro objLibro = (Libro) obj;

        //3. Variable para conocer el estado de la acción
        boolean isUpdated = false;

        try{
            //4. Crear la Sentencia SQL
            String sql = "UPDATE libro  SET titulo = ? , anio_publicacion = ?, precio = ?, id_autor = ?  WHERE id = ?;";

            //5. Crear el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //6. Asignar valor a los parámetros del Query
            objPrepare.setString(1,objLibro.getTitulo());
            objPrepare.setInt(2,objLibro.getAnio_publicacion());
            objPrepare.setDouble(3, objLibro.getPrecio());
            objPrepare.setInt(4,objLibro.getId_autor());
            objPrepare.setInt(5, objLibro.getId());

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
        Libro objLibro = (Libro) obj;

        //Abrimos la conexión
        Connection objConnection = ConfigDB.openConnection();

        //Variable de estado
        boolean isDeleted = false;

        try{
            //Escribir la sentencia SQL
            String sql = "DELETE FROM libro WHERE id=?;";

            //Creamos el prepare statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            //Dar valor al ?
            objPrepare.setInt(1,objLibro.getId());

            int totalAffectedRows = objPrepare.executeUpdate();

            if(totalAffectedRows >0){
                isDeleted = true;
                JOptionPane.showMessageDialog(null,"The  update was successful. ");
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        ConfigDB.closeConnection();
        return isDeleted;
    }

    public Libro findById(int id){
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();

        //Crear el libro que vamos a retornar
        Libro objLibro = null;

        try{
            String sql = "SELECT * FROM libro WHERE id = ?;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            if(objResult.next()){
                objLibro = new Libro();
                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getString("titulo"));
                objLibro.setAnio_publicacion(objResult.getInt("anio_publicacion"));
                objLibro.setPrecio(objResult.getDouble("precio"));
                objLibro.setId_autor(objResult.getInt("id_autor"));
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return objLibro;
    }

    public List<Libro> findByIdAutor(int id){
        List<Libro> listaLibros = new ArrayList<>();
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();

        //Crear el libro que vamos a retornar
        Libro objLibro = null;

        try{
            String sql = "SELECT titulo, precio, anio_publicacion FROM libro INNER JOIN autores ON libro.id_autor = autores.id WHERE autores.id = ?;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1,id);

            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                objLibro = new Libro();
                objLibro.setTitulo(objResult.getString("titulo"));
                objLibro.setAnio_publicacion(objResult.getInt("anio_publicacion"));
                objLibro.setPrecio(objResult.getDouble("precio"));

                listaLibros.add(objLibro);
                System.out.println(objLibro.imprimirPorAutor());
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return listaLibros;
    }

    public List<Libro> findByTitle(String title){
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();

        //Crear el libro que vamos a retornar
        List<Libro> listaLibro = new ArrayList<>();

        try{
            String sql = "SELECT * FROM libro WHERE titulo like ?;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            System.out.println("%"+title+"%");
            objPrepare.setString(1,"%"+title+"%");

            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Libro objLibro = new Libro();
                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getString("titulo"));
                objLibro.setAnio_publicacion(objResult.getInt("anio_publicacion"));
                objLibro.setPrecio(objResult.getDouble("precio"));
                objLibro.setId_autor(objResult.getInt("id_autor"));

                listaLibro.add(objLibro);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return listaLibro;

    }

    public List<Libro> findByAutor(int autorId){
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();

        //Crear el libro que vamos a retornar
        List<Libro> listaLibro = new ArrayList<>();

        try{
            String sql = "SELECT * FROM libro WHERE id_autor=?;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);

            objPrepare.setInt(1, autorId);

            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Libro objLibro = new Libro();
                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getString("titulo"));
                objLibro.setAnio_publicacion(objResult.getInt("anio_publicacion"));
                objLibro.setPrecio(objResult.getDouble("precio"));
                objLibro.setId_autor(objResult.getInt("id_autor"));

                listaLibro.add(objLibro);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return listaLibro;
    }

    public List<Libro> findAlldata(int id){
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();

        //Crear el libro que vamos a retornar
        List<Libro> listaLibro = new ArrayList<>();

        try{
            String sql = "SELECT * FROM libro INNER JOIN autores ON libro.id_autor = autores.id WHERE autores.id = ?;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);
            objPrepare.setInt(1, id);

            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Libro objLibro = new Libro();
                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getString("titulo"));
                objLibro.setAnio_publicacion(objResult.getInt("anio_publicacion"));
                objLibro.setPrecio(objResult.getDouble("precio"));
                objLibro.setId_autor(objResult.getInt("id_autor"));
                objLibro.setIdSuper(objResult.getInt("id"));
                objLibro.setNombre(objResult.getString("nombre"));
                objLibro.setNacionalidad(objResult.getString("nacionalidad"));

                listaLibro.add(objLibro);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return listaLibro;

    }

    public List<Libro> showAll(){
        //Abrimos conexión
        Connection objConnection = ConfigDB.openConnection();

        //Crear el libro que vamos a retornar
        List<Libro> listaLibro = new ArrayList<>();

        try{
            String sql = "SELECT * FROM libro INNER JOIN autores ON libro.id_autor = autores.id ;";

            //Preparamos el statement
            PreparedStatement objPrepare = objConnection.prepareStatement(sql);


            //6. Ejecutamos a Query
            ResultSet objResult = objPrepare.executeQuery();

            while(objResult.next()){
                Libro objLibro = new Libro();
                objLibro.setId(objResult.getInt("id"));
                objLibro.setTitulo(objResult.getString("titulo"));
                objLibro.setAnio_publicacion(objResult.getInt("anio_publicacion"));
                objLibro.setPrecio(objResult.getDouble("precio"));
                objLibro.setId_autor(objResult.getInt("id_autor"));
                objLibro.setIdSuper(objResult.getInt("id"));
                objLibro.setNombre(objResult.getString("nombre"));
                objLibro.setNacionalidad(objResult.getString("nacionalidad"));

                listaLibro.add(objLibro);
            }
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        //Cerrar la conexión
        ConfigDB.closeConnection();
        return listaLibro;

    }


 }
