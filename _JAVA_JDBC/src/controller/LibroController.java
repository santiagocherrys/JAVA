package controller;

import entity.Autor;
import entity.Libro;
import model.AutorModel;
import model.LibroModel;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class LibroController{

    public static void getAll() {
        LibroModel objMode = new LibroModel();

        String listLibros = "LIBRO LIST \n";

        for (Object iterador : objMode.findAll()) {
            Libro objLibro = (Libro) iterador;
            listLibros += objLibro.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listLibros);
    }

    public static String getAllString() {
        LibroModel objMode = new LibroModel();

        String listLibros = "LIBRO LIST \n";

        for (Object iterador : objMode.findAll()) {
            Libro objLibro = (Libro) iterador;
            listLibros += objLibro.toString() + "\n";
        }
        return listLibros;
    }

    public static void create() {
        LibroModel objMode = new LibroModel();
        AutorModel objModeAutor = new AutorModel();
        List<Object> listaAutores = objModeAutor.findAll();




        //Pedimos datos al usuario
        String titulo = JOptionPane.showInputDialog("Ingrese titulo del libro");
        int anio_publicacion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el año de publicacion del " + titulo));
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del libro " + titulo));

        //Datos id_autor
        //Se hace selector para autores
        String[] choices = new String[listaAutores.size()];

        int index = 0;
        for(Object iterador : listaAutores){
            Autor autor = (Autor) iterador;
            System.out.println("Este es el autor " + autor.getNombre() + " y su id " + autor.getId());
            choices[index] = autor.getNombre();
            index++;
        }
        String input = (String) JOptionPane.showInputDialog(null, "Escoja Autor...",
                "Autores disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                // default
                // icon
                choices, // Array of choices
                choices[0]); // Initial choice

        int id_autor = 0;
        for(Object iterador : listaAutores){
            Autor autor = (Autor) iterador;
            if(autor.getNombre().equals(input)){
                id_autor = autor.getId();
            }
        }

        //Se crea una instancia de Autor

        Libro objLibro = new Libro();
        objLibro.setTitulo(titulo);
        objLibro.setAnio_publicacion(anio_publicacion);
        objLibro.setPrecio(precio);
        objLibro.setId_autor(id_autor);

        objLibro = (Libro) objMode.insert(objLibro);

        JOptionPane.showMessageDialog(null, objLibro.toString());
    }

    public static void delete() {
        LibroModel objMode = new LibroModel();
        String listLibros = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listLibros + "\n Enter the Id the coder to delete: "));

        Libro objLibro = objMode.findById(idDelete);
        int confirm = 1;

        if (objLibro == null) {
            JOptionPane.showMessageDialog(null, "Libro not found");
        }else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the coder? \n" + objLibro.toString());

            if (confirm == 0) objMode.delete(objLibro);
        }
    }

    //método encontrar por Id
    public static void findById(){
        LibroModel objMode = new LibroModel();

        try{
            int opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del libro"));
            Libro libro = objMode.findById(opcion);
            JOptionPane.showMessageDialog(null,"Este es el libro: " + libro.toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Opcion no valida");
        }

    }

    //Mètodo para encontrar por ID de autor

    public static void findByIdAutor(){
        LibroModel objModel = new LibroModel();
        String listaAutores = AutorController.getAllString();
        List<Libro> listaLibro = new ArrayList<>();

        try{
            int id = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del autor para filtrar libros" + listaAutores));
            listaLibro = objModel.findByIdAutor(id);
            String resultado = "Los libros filtrados son \n";
            for(Libro iterador: listaLibro){
                resultado += iterador.imprimirPorAutor() + "\n";
            }
            JOptionPane.showMessageDialog(null,resultado);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Opcion no valida " + e.getMessage());
        }


    }

    //método para encontrar por Titulo

    public static void findByTitle(){
        String title = JOptionPane.showInputDialog("Ingrese el titulo a buscar del libro");
        LibroModel objMode = new LibroModel();

        String librosTitulo = "ESTOS SON LOS TITULOS\n";

        if(!objMode.findByTitle(title).isEmpty()){
            for(Libro iterador: objMode.findByTitle(title)){
                librosTitulo += iterador.toString() +"\n";
            }
            JOptionPane.showMessageDialog(null,librosTitulo);
        }else{
            JOptionPane.showMessageDialog(null,"No hay conicidencias");
        }

    }

    public static void findByAutor(){
        LibroModel objMode = new LibroModel();
        AutorModel objModeAutor = new AutorModel();
        List<Object> listaAutores = objModeAutor.findAll();

        //Se hace selector para autores
        String[] choices = new String[listaAutores.size()];

        int index = 0;
        for(Object iterador : listaAutores){
            Autor autor = (Autor) iterador;
            System.out.println("Este es el autor " + autor.getNombre() + " y su id " + autor.getId());
            choices[index] = autor.getNombre();
            index++;
        }
        String input = (String) JOptionPane.showInputDialog(null, "Escoja Autor...",
                "Autores disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                // default
                // icon
                choices, // Array of choices
                choices[0]); // Initial choice

        int id_autor = 0;
        for(Object iterador : listaAutores){
            Autor autor = (Autor) iterador;
            if(autor.getNombre().equals(input)){
                id_autor = autor.getId();
            }
        }

        String librosAutores = "ESTOS SON LOS TITULOS del Autor" +input + "\n";

        if(!objMode.findByAutor(id_autor).isEmpty()){
            for(Libro iterador: objMode.findByAutor(id_autor)){
                librosAutores += iterador.toString() +"\n";
            }
            JOptionPane.showMessageDialog(null,librosAutores);
        }else{
            JOptionPane.showMessageDialog(null,"No hay conicidencias");
        }

    }

    public static void update(){
        //1. utilizar el modelo
        LibroModel objLibroModel = new LibroModel();
        AutorModel objModeAutor = new AutorModel();
        String listaAutores = getAllString();
        List<Object> lista_Autores = objModeAutor.findAll();


        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listaAutores+ "\n Ingrese el Id del libro a editar"));

        Libro objLibro = objLibroModel.findById(idUpdate);

        //validamos que exista el autor
        if(objLibro == null){
            JOptionPane.showMessageDialog(null, "Coder not found");
        }else{
            String titulo = JOptionPane.showInputDialog(null, "Ingrese el titulo  del libro", objLibro.getTitulo());
            int anio_publicacion = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el año de publicacion", objLibro.getAnio_publicacion()));
            double precio = Double.parseDouble(JOptionPane.showInputDialog(null,"Ingrese el precio del libro",objLibro.getPrecio()));


            //Se hace selector para autores
            String[] choices = new String[lista_Autores.size()];

            int index = 0;
            int selector = 0;

            for(Object iterador : lista_Autores){
                Autor autor = (Autor) iterador;
                System.out.println("Este es el autor " + autor.getNombre() + " y su id " + autor.getId());
                choices[index] = autor.getNombre();
                if(autor.getId() == objLibro.getId_autor()){
                    selector = index;
                }
                index++;
            }
            String input = (String) JOptionPane.showInputDialog(null, "Escoja Autor...",
                    "Autores disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                    // default
                    // icon
                    choices, // Array of choices
                    choices[selector]); // Initial choice

            int id_autor = 0;
            for(Object iterador : lista_Autores){
                Autor autor = (Autor) iterador;
                if(autor.getNombre().equals(input)){
                    id_autor = autor.getId();
                }
            }


            objLibro.setTitulo(titulo);
            objLibro.setAnio_publicacion(anio_publicacion);
            objLibro.setPrecio(precio);
            objLibro.setId_autor(id_autor);

            objLibroModel.update(objLibro);
        }
    }

    public static void findAlldata(){
        LibroModel objModel =  new LibroModel();
        int id = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el id a desplegar toda la información"));

        if(!objModel.findAlldata(id).isEmpty()){
            String resultado = "Esto es toda la información \n";
            for(Libro iterador: objModel.findAlldata(id)){
                resultado += iterador.imprimirTodo() + "\n";
            }
            JOptionPane.showMessageDialog(null,resultado);
        }else{
            JOptionPane.showMessageDialog(null,"Dato invalido");
        }

    }

    public static void showAll(){
        LibroModel objModel =  new LibroModel();


            String resultado = "Esto es toda la información \n";
            for(Libro iterador: objModel.showAll()){
                resultado += iterador.imprimirTodo() + "\n";
            }
            JOptionPane.showMessageDialog(null,resultado);

    }


}
