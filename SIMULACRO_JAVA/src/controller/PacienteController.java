package controller;

import entity.Cita;
import entity.Paciente;
import model.PacienteModel;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class PacienteController {

    public static void getAll() {
        PacienteModel objMode = new PacienteModel();

        String listPacientes = "PACIENTES LIST \n";

        for (Object iterador : objMode.findAll()) {
            Paciente objPaciente = (Paciente) iterador;
            listPacientes += objPaciente.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listPacientes);
    }

    public static String getAllString() {
        PacienteModel objMode = new PacienteModel();

        String listPacientes= "PACIENTES LIST \n";

        for (Object iterador : objMode.findAll()) {
            Paciente objPaciente = (Paciente) iterador;
            listPacientes += objPaciente.toString() + "\n";
        }
        return listPacientes;
    }

    public static void create() {
        PacienteModel objMode = new PacienteModel();

        //Pedimos datos al usuario
        try{
            String nombre = JOptionPane.showInputDialog("Ingrese Nombre del paciente \n");
            String apellidos = JOptionPane.showInputDialog("Ingrese apellidos del paciente \n");
            Date fecha_nacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(JOptionPane.showInputDialog("Ingrese fecha de nacimiento recuerde que es  YYYY-MM-DD \n"));
            String documento_identidad = JOptionPane.showInputDialog("Ingreese documento de identidad");
            //Se crea una instancia de Paciente

            Paciente objPaciente = new Paciente();
            objPaciente.setNombre(nombre);
            objPaciente.setApellidos(apellidos);
            objPaciente.setFecha_nacimiento(fecha_nacimiento);
            objPaciente.setDocumento_identidad(documento_identidad);

            objPaciente = (Paciente) objMode.insert(objPaciente);

            JOptionPane.showMessageDialog(null, objPaciente.toString());
        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error es " + e.getMessage());
        }

    }

    public static void delete() {
        PacienteModel objMode = new PacienteModel();
        String listPacientes = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listPacientes + "\n Enter the Id the Paciente to delete: "));

        Paciente objPaciente = objMode.findById(idDelete);
        int confirm = 1;

        if (objPaciente == null) {
            JOptionPane.showMessageDialog(null, "Paciente not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete the Paciente? \n" + objPaciente.toString());

            if (confirm == 0) objMode.delete(objPaciente);
        }
    }

    //método encontrar por Id
    public static void findById(){
        PacienteModel objMode = new PacienteModel();

        try{
            int opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del Paciente"));
            Paciente paciente = objMode.findById(opcion);
            JOptionPane.showMessageDialog(null,"Este es el paciente: " + paciente.toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Opcion no valida");
        }

    }

    public static void update(){
        //1. utilizar el modelo
        PacienteModel objPacienteModel = new PacienteModel();

        String listaPaciente = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listaPaciente+ "\n Ingrese el Id del paciente a editar"));

        Paciente objPaciente = objPacienteModel.findById(idUpdate);

        //validamos que exista el autor
        if(objPaciente == null){
            JOptionPane.showMessageDialog(null, "Paciente not found");
        }else{

            try{
                String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del Paciente\n", objPaciente.getNombre());
                String apellidos = JOptionPane.showInputDialog(null, "Ingrese los apellidos del paciente\n", objPaciente.getApellidos());
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
                Date fecha_nacimiento = new SimpleDateFormat("yyyy-MM-dd").parse(JOptionPane.showInputDialog(null,"Ingrese fecha nacimiento paciente recuerde YYYY-MM-DD\n",  dateFormat.format(objPaciente.getFecha_nacimiento())));
                String documento_identidad = JOptionPane.showInputDialog(null, "Ingrese el documento de identidad del paciente", objPaciente.getDocumento_identidad());

                objPaciente.setNombre(nombre);
                objPaciente.setApellidos(apellidos);
                objPaciente.setFecha_nacimiento(fecha_nacimiento);
                objPaciente.setDocumento_identidad(documento_identidad);

                objPacienteModel.update(objPaciente);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "El Error es " + e.getMessage());
            }

        }
    }

    public static void buscarPorTarIdentidad(){
        PacienteModel objPacienteModel = new PacienteModel();

        String opcion = JOptionPane.showInputDialog(null,"Ingrese el documento a buscar");

        //Revisamos si hay datos

        if(objPacienteModel.buscarPorTarIdentidad(opcion).size() >0){
            String listPaciente = "PACIENTES LIST \n";

            for (Paciente iterador : objPacienteModel.buscarPorTarIdentidad(opcion)) {

                listPaciente += iterador.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, listPaciente);
        }else{
            JOptionPane.showMessageDialog(null,"No se encuentra ningun registro con esa cedula");
        }
    }





}
