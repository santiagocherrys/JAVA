package controller;

import entity.Avion;
import entity.Pasajero;
import model.AvionModel;
import model.PasajeroModel;

import javax.swing.*;

public class PasajeroController {
    public static void getAll(){
        PasajeroModel objMode = new PasajeroModel();

        String listPasajeros = "PASAJEROS LIST \n";

        for (Object iterador : objMode.findAll()) {
            Pasajero objAvion = (Pasajero) iterador;
            listPasajeros += objAvion.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listPasajeros);
    }

    public static String getAllString() {
        PasajeroModel objMode = new PasajeroModel();

        String listPasajero= "PASAJEROS LIST \n";

        for (Object iterador : objMode.findAll()) {
            Pasajero objPasajero = (Pasajero) iterador;
            listPasajero += objPasajero.toString() + "\n";
        }
        return listPasajero;
    }

    public static void create() {
        PasajeroModel objMode = new PasajeroModel();

        //Pedimos datos al usuario
        try {
            String nombre = JOptionPane.showInputDialog("Ingrese nombre del pasajero \n");
            String apellido = JOptionPane.showInputDialog("Ingrese apellidos del pasasjero \n");
            String documento_identidad = JOptionPane.showInputDialog("Ingrese documento identidad del paciente \n");

            //Se crea una instancia de Pasajero

            Pasajero objPasajero = new Pasajero();
            objPasajero.setNombre(nombre);
            objPasajero.setApellido(apellido);
            objPasajero.setDocumento_identidad(documento_identidad);


            objPasajero = (Pasajero) objMode.insert(objPasajero);

            JOptionPane.showMessageDialog(null, objPasajero.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error es " + e.getMessage());
        }
    }

        public static void delete() {
            PasajeroModel objMode = new PasajeroModel();
            String listPasajeros = getAllString();

            int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPasajeros + "\n Enter the Id el Pasajero to delete: "));

            Pasajero objPasajero = objMode.findById(idDelete);
            int confirm = 1;

            if (objPasajero == null) {
                JOptionPane.showMessageDialog(null, "Pasajero not found");
            } else {
                confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the Pasajero? \n" + objPasajero.toString());

                if (confirm == 0) objMode.delete(objPasajero);
            }
        }


    public static void update(){
        //1. utilizar el modelo
        PasajeroModel objPasajeroModel = new PasajeroModel();

        String listaPasajero = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listaPasajero+ "\n Ingrese el Id del Pasajero a editar"));

        Pasajero objPasajero = objPasajeroModel.findById(idUpdate);

        //validamos que exista el Pasajero
        if(objPasajero == null){
            JOptionPane.showMessageDialog(null, "Pasajero not found");
        }else{

            try{
                String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del Pasajero\n", objPasajero.getNombre());
                String apellido = JOptionPane.showInputDialog(null, "Ingrese los apellidos del pasajero\n", objPasajero.getApellido());
                String documento_identidad = JOptionPane.showInputDialog(null,"Ingrese documento de identidad del pasajero",objPasajero.getDocumento_identidad());


                objPasajero.setNombre(nombre);
                objPasajero.setApellido(apellido);
                objPasajero.setDocumento_identidad(documento_identidad);


                objPasajeroModel.update(objPasajero);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "El Error es " + e.getMessage());
            }

        }
    }


}
