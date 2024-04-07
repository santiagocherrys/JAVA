package controller;

import entity.Avion;
import model.AvionModel;

import javax.swing.*;

public class AvionController {
    public static void getAll() {
        AvionModel objMode = new AvionModel();

        String listAviones = "AVIONES LIST \n";

        for (Object iterador : objMode.findAll()) {
            Avion objAvion = (Avion) iterador;
            listAviones += objAvion.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listAviones);
    }

    public static String getAllString() {
        AvionModel objMode = new AvionModel();

        String listAviones= "AVIONES LIST \n";

        for (Object iterador : objMode.findAll()) {
            Avion objAvion = (Avion) iterador;
            listAviones += objAvion.toString() + "\n";
        }
        return listAviones;
    }

    public static void create() {
        AvionModel objMode = new AvionModel();

        //Pedimos datos al usuario
        try{
            String modelo = JOptionPane.showInputDialog("Ingrese Modelo Avión \n");
            int capacidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la capacidad en sillas que tiene el avion \n"));

            //Se crea una instancia de Avion

            Avion objAvion = new Avion();
            objAvion.setModelo(modelo);
            objAvion.setCapacidad(capacidad);


            objAvion = (Avion) objMode.insert(objAvion);

            JOptionPane.showMessageDialog(null, objAvion.toString());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error es " + e.getMessage());
        }

    }

    public static void delete() {
        AvionModel objMode = new AvionModel();
        String listAviones = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listAviones + "\n Enter the Id del avion to delete: "));

        Avion objAvion = objMode.findById(idDelete);
        int confirm = 1;

        if (objAvion == null) {
            JOptionPane.showMessageDialog(null, "Avion not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the Avion? \n" + objAvion.toString());

            if (confirm == 0) objMode.delete(objAvion);
        }
    }

    public static void update(){
        //1. utilizar el modelo
        AvionModel objAvionModel = new AvionModel();

        String listaAviones = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listaAviones+ "\n Ingrese el Id del avion a editar"));

        Avion objAvion = objAvionModel.findById(idUpdate);

        //validamos que exista el autor
        if(objAvion == null){
            JOptionPane.showMessageDialog(null, "Avion not found");
        }else{

            try{
                String modelo = JOptionPane.showInputDialog(null, "Ingrese el modelo del Avion\n", objAvion.getModelo());
                int capacidad = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la capacidad del avion( #asientos)\n", Integer.toString(objAvion.getCapacidad())));


                objAvion.setModelo(modelo);
                objAvion.setCapacidad(capacidad);


                objAvionModel.update(objAvion);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "El Error es " + e.getMessage());
            }

        }
    }
}
