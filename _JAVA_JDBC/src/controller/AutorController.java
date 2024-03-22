package controller;

import entity.Autor;
import model.AutorModel;

import javax.swing.*;

public class AutorController {

    public static void getAll() {
        AutorModel objMode = new AutorModel();

        String listAutores = "AUTORES LIST \n";

        for (Object iterador : objMode.findAll()) {
            Autor objAutor = (Autor) iterador;
            listAutores += objAutor.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listAutores);
    }

    public static String getAllString() {
        AutorModel objMode = new AutorModel();

        String listAutores = "AUTORES LIST \n";

        for (Object iterador : objMode.findAll()) {
            Autor objAutor = (Autor) iterador;
            listAutores += objAutor.toString() + "\n";
        }
        return listAutores;
    }

    public static void create() {
        AutorModel objMode = new AutorModel();

        //Pedimos datos al usuario
        String nombre = JOptionPane.showInputDialog("Ingrese Nombre del autor");
        String nacionalidad = JOptionPane.showInputDialog("Ingrese la nacionalidad de " + nombre);

        //Se crea una instancia de Autor

        Autor objAutor = new Autor();
        objAutor.setNombre(nombre);
        objAutor.setNacionalidad(nacionalidad);

        objAutor = (Autor) objMode.insert(objAutor);

        JOptionPane.showMessageDialog(null, objAutor.toString());
    }

    public static void delete() {
        AutorModel objMode = new AutorModel();
        String listAutores = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAutores + "\n Enter the Id the Autor to delete: "));

        Autor objAutor = objMode.findById(idDelete);
        int confirm = 1;

        if (objAutor == null) {
            JOptionPane.showMessageDialog(null, "Autor not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the Autor? \n" + objAutor.toString());

            if (confirm == 0) objMode.delete(objAutor);
        }
    }

    //método encontrar por Id
    public static void findById(){
        AutorModel objMode = new AutorModel();

        try{
            int opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del autor"));
            Autor autor = objMode.findById(opcion);
            JOptionPane.showMessageDialog(null,"Este es el autor: " + autor.toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Opcion no valida");
        }

    }

    public static void update(){
        //1. utilizar el modelo
        AutorModel objAutorModel = new AutorModel();

        String listaAutores = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listaAutores+ "\n Ingrese el Id del autor a editar"));

        Autor objAutor = objAutorModel.findById(idUpdate);

        //validamos que exista el autor
        if(objAutor == null){
            JOptionPane.showMessageDialog(null, "Coder not found");
        }else{
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del Autor", objAutor.getNombre());
            String nacionalidad = JOptionPane.showInputDialog(null, "Ingrese la nacionalidad", objAutor.getNacionalidad());

            objAutor.setNombre(nombre);
            objAutor.setNacionalidad(nacionalidad);

            objAutorModel.update(objAutor);
        }
    }
}
