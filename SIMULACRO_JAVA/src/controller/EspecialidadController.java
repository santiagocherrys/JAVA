package controller;

import entity.Especialidad;
import model.EspecialidadModel;

import javax.swing.*;

public class EspecialidadController {

    public static void getAll() {
        EspecialidadModel objMode = new EspecialidadModel();

        String listEspecialidad = "ESPECIALIDAD LIST \n";

        for (Object iterador : objMode.findAll()) {
            Especialidad objEspecialidad = (Especialidad) iterador;
            listEspecialidad += objEspecialidad.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listEspecialidad);
    }

    public static String getAllString() {
        EspecialidadModel objMode = new EspecialidadModel();

        String listEspecialidad = "ESPECIALIDAD LIST \n";

        for (Object iterador : objMode.findAll()) {
            Especialidad objEspecialidad = (Especialidad) iterador;
            listEspecialidad += objEspecialidad.toString() + "\n";
        }
        return listEspecialidad;
    }

    public static void create(){
        EspecialidadModel objMode = new EspecialidadModel();

        //Pedimos datos al usuario
        String nombre = JOptionPane.showInputDialog("Ingrese Nombre de la especialidad");
        String descripcion = JOptionPane.showInputDialog("Ingrese la descripcion de " + nombre);

        //Se crea una instancia de Autor

        Especialidad objEspecialidad = new Especialidad();
        objEspecialidad.setNombre(nombre);
        objEspecialidad.setDescripcion(descripcion);

        objEspecialidad = (Especialidad) objMode.insert(objEspecialidad);

        JOptionPane.showMessageDialog(null, objEspecialidad.toString());
    }

    public static void delete() {
        EspecialidadModel objMode = new EspecialidadModel();
        String listEspecialidad = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listEspecialidad + "\n Ingrese el id de la especialidad que quiere borrar: "));

        Especialidad objEspecialidad = objMode.findById(idDelete);
        int confirm = 1;

        if (objEspecialidad == null) {
            JOptionPane.showMessageDialog(null, "Especialidad no encontrada");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Está seguro que desea borrar la especialidad? \n" + objEspecialidad.toString());

            if (confirm == 0) objMode.delete(objEspecialidad);
        }
    }

    //método encontrar por Id
    public static void findById(){
        EspecialidadModel objMode = new EspecialidadModel();

        try{
            int opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id de la especialidad"));
            Especialidad especialidad = objMode.findById(opcion);
            JOptionPane.showMessageDialog(null,"Este es la especialidad: " + especialidad.toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Opcion no valida");
        }

    }

    public static void update(){
        //1. utilizar el modelo
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();

        String listaEspecialidades = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listaEspecialidades+ "\n Ingrese el Id de la especialidad a editar"));

        Especialidad objEspecialidad = objEspecialidadModel.findById(idUpdate);

        //validamos que exista el autor
        if(objEspecialidad == null){
            JOptionPane.showMessageDialog(null, "Especialidad no encontrada");
        }else{
            String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre de la especialidad", objEspecialidad.getNombre());
            String descripcion = JOptionPane.showInputDialog(null, "Ingrese la descripcion", objEspecialidad.getDescripcion());

            objEspecialidad.setNombre(nombre);
            objEspecialidad.setDescripcion(descripcion);

            objEspecialidadModel.update(objEspecialidad);
        }
    }





}
