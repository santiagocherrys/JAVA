package controller;

import entity.Cita;
import entity.Especialidad;
import entity.Medico;
import entity.Paciente;
import model.CitaModel;
import model.EspecialidadModel;
import model.MedicoModel;
import model.PacienteModel;

import javax.swing.*;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class CitaController {
    public static void getAll() {
        CitaModel objMode = new CitaModel();

        String listCita = "CITAS LIST \n";

        for (Object iterador : objMode.findAll()) {
            Cita objCita = (Cita) iterador;
            listCita += objCita.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listCita);
    }

    public static String getAllString() {
        CitaModel objMode = new CitaModel();

        String listCitas= "Cita LIST \n";

        for (Object iterador : objMode.findAll()) {
            Cita objCita = (Cita) iterador;
            listCitas += objCita.toString() + "\n";
        }
        return listCitas;
    }

    public static void create(){
        CitaModel objMode = new CitaModel();

        PacienteModel objPacienteModel = new PacienteModel();
        MedicoModel objMedicoModel = new MedicoModel();



        //Pedimos datos al usuario
        try{
            //preguntamos si existe paciente para poder crear Cita
            if(objPacienteModel.findAll().size()>0){

                //Se despliega menu para escoger pacientes
                List<Object> listaPacientes = objPacienteModel.findAll();

                //Se hace selector para Cita
                String[] choices = new String[listaPacientes.size()];

                int index = 0;
                for(Object iterador : listaPacientes){
                    Paciente paciente = (Paciente) iterador;
                    System.out.println("Este es el paciente " + paciente.getNombre() + " y su id " + paciente.getId_paciente());
                    choices[index] = paciente.getNombre() + " " + paciente.getApellidos();
                    index++;
                }

                String input = (String) JOptionPane.showInputDialog(null, "Escoja Paciente para la cita...",
                        "Paciente disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                        // default
                        // icon
                        choices, // Array of choices
                        choices[0]); // Initial choice


                Cita objCita = new Cita();
                int id_pacient = 0;
                for(Object iterador : listaPacientes){
                    Paciente paciente = (Paciente) iterador;
                    String nombre_completo = paciente.getNombre() + " " + paciente.getApellidos() ;
                    if(nombre_completo.equals(input)){
                        id_pacient = paciente.getId_paciente();
                        objCita.setPaciente_nombre(paciente.getNombre());
                        objCita.setPaciente_apellidos(paciente.getApellidos());
                    }
                }

                //Se despliega menu para medicos
                List<Object> listaMedicos = objMedicoModel.findAll();

                //Se hace selector para Cita
                String[] choices2 = new String[listaMedicos.size()];

                int index2 = 0;
                for(Object iterador : listaMedicos){
                    Medico medico = (Medico) iterador;
                    System.out.println("Este es el medico " + medico.getNombre() + " y su id " + medico.getId_medico());
                    choices2[index2] = medico.getNombre() + " " + medico.getApellidos();
                    index2++;
                }

                String input2 = (String) JOptionPane.showInputDialog(null, "Escoja Medico para la cita...",
                        "Medico disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                        // default
                        // icon
                        choices2, // Array of choices
                        choices2[0]); // Initial choice



                int id_medic = 0;
                for(Object iterador : listaMedicos){
                    Medico medico = (Medico) iterador;
                    String nombre_completo = medico.getNombre() + " " + medico.getApellidos() ;
                    if(nombre_completo.equals(input2)){
                        id_medic = medico.getId_medico();
                        objCita.setMedico_nombre(medico.getNombre());
                        objCita.setMedico_apellidos(medico.getApellidos());
                    }
                }

                Date fecha_cita = new SimpleDateFormat("yyyy-MM-dd").parse(JOptionPane.showInputDialog("Ingrese fecha de la cita, recuerde YYYY-MM-DD \n"));

                //Formato hora
                String inputFormat = "HH:mm:ss";


                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
                String hora_cita = JOptionPane.showInputDialog("Ingrese la hora de la Cita, recuerde HH:MM horario militar")+ ":00";
                LocalTime time = LocalTime.parse(hora_cita, inputFormatter);

                Time h_cita = Time.valueOf(time);

                String motivo = JOptionPane.showInputDialog("Ingrese el motivo por el cual el paciente agenda cita");


                //Se crea una instancia de Cita


                objCita.setId_paciente(id_pacient);
                objCita.setId_medico(id_medic);
                objCita.setFecha_cita(fecha_cita);
                objCita.setHora_cita(h_cita);
                objCita.setMotivo(motivo);


                objCita = (Cita) objMode.insert(objCita);

                JOptionPane.showMessageDialog(null, objCita.toString());
            }else{
                JOptionPane.showMessageDialog(null,"Tiene que crear un paciente para poder añadirle cita");
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error es " + e.getMessage());
        }

    }

    public static void delete() {
        CitaModel objMode = new CitaModel();
        String listCitas = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listCitas + "\n Entre el id de la cita a borrar: "));

        Cita objCita = objMode.findById(idDelete);
        int confirm = 1;

        if (objCita == null) {
            JOptionPane.showMessageDialog(null, "Cita not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete la cita? \n" + objCita.toString());

            if (confirm == 0) objMode.delete(objCita);
        }
    }

    public static void update(){
        //1. utilizar el modelo
        CitaModel objCitaModel = new CitaModel();

        PacienteModel objPacienteModel = new PacienteModel();
        MedicoModel objMedicoModel = new MedicoModel();

        //Se despliega men
        List<Object> listaPacientes = objPacienteModel.findAll();
        List<Object> listaMedicos = objMedicoModel.findAll();

        String listaCitas = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listaCitas+ "\n Ingrese el Id de la cita a editar"));

        Cita objCita = objCitaModel.findById(idUpdate);

        //validamos que exista el autor
        if(objCita == null){
            JOptionPane.showMessageDialog(null, "Cita no encontrada not found");
        }else{

            try{
                //Se hace selector para paciente
                String[] choices = new String[listaPacientes.size()];

                int index = 0;
                int selector = 0;

                for(Object iterador : listaPacientes){
                    Paciente paciente = (Paciente) iterador;
                    System.out.println("Este es el paciente " + paciente.getNombre() + " y su id " + paciente.getId_paciente());
                    choices[index] = paciente.getNombre() + " " + paciente.getApellidos();

                    System.out.println("paciente id: " + paciente.getId_paciente() + "id_paciente desde Cita "+ objCita.getId_cita());
                    if(paciente.getId_paciente() == objCita.getId_cita()){
                        selector = index;
                        System.out.println("selector " + selector);
                    }
                    index++;
                }
                String input = (String) JOptionPane.showInputDialog(null, "Escoja Paciente...",
                        "Paciente disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                        // default
                        // icon
                        choices, // Array of choices
                        choices[selector]); // Initial choice

                int id_pacient = 0;
                for(Object iterador : listaPacientes){
                    Paciente paciente = (Paciente) iterador;
                    String nombre_completo = paciente.getNombre() + " " + paciente.getApellidos();
                    if(nombre_completo.equals(input)){
                        id_pacient = paciente.getId_paciente();
                    }
                }

                //Se hace selector para medico
                String[] choices2 = new String[listaMedicos.size()];

                int index2 = 0;
                int selector2 = 0;

                for(Object iterador : listaMedicos){
                    Medico medico = (Medico) iterador;
                    System.out.println("Este es el medico " + medico.getNombre() + " y su id " + medico.getId_medico());
                    choices2[index2] = medico.getNombre() + " " + medico.getApellidos();

                    if(medico.getId_medico() == objCita.getId_medico()){
                        selector2 = index2;
                    }
                    index2++;
                }
                String input2 = (String) JOptionPane.showInputDialog(null, "Escoja Medico...",
                        "Medicos disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                        // default
                        // icon
                        choices2, // Array of choices
                        choices2[selector2]); // Initial choice

                int id_medic = 0;
                for(Object iterador : listaMedicos){
                    Medico medico = (Medico) iterador;
                    String nombre_completo = medico.getNombre() + " " + medico.getApellidos();
                    if(nombre_completo.equals(input2)){
                        id_medic = medico.getId_medico();
                    }
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd");
                Date fecha_cita = new SimpleDateFormat("yyyy-MM-dd").parse(JOptionPane.showInputDialog(null,"Ingrese fecha cita paciente recuerde YYYY-MM-DD\n",  dateFormat.format(objCita.getFecha_cita())));

                //Formato hora
                String inputFormat = "HH:mm:ss";
                DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern(inputFormat);
                String hora_cita = JOptionPane.showInputDialog("Ingrese la hora de la Cita, recuerde HH:MM horario militar" + ":00", objCita.getHora_cita().toString());
                LocalTime time = LocalTime.parse(hora_cita, inputFormatter);

                Time h_cita = Time.valueOf(time);
                String motivo = JOptionPane.showInputDialog(null, "Ingrese el motivo de la consulta\n", objCita.getMotivo());

                objCita.setId_paciente(id_pacient);
                objCita.setId_medico(id_medic);
                objCita.setFecha_cita(fecha_cita);
                objCita.setHora_cita(h_cita);
                objCita.setMotivo(motivo);


                objCitaModel.update(objCita);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "El Error es " + e.getMessage());
            }

        }
    }


    public static void buscarPorFecha(){
        //1. utilizar el modelo
        CitaModel objCitaModel = new CitaModel();

        try{
            Date fecha_cita = new SimpleDateFormat("yyyy-MM-dd").parse(JOptionPane.showInputDialog("Ingrese fecha de la cita, recuerde YYYY-MM-DD \n"));

            //Chequea si hay resultado
            if(objCitaModel.buscarPorFecha(fecha_cita).size()>0){
                String listCita = "CITAS LIST \n";

                for (Cita iterador : objCitaModel.buscarPorFecha(fecha_cita)) {

                    listCita += iterador.toString() + "\n";
                }
                JOptionPane.showMessageDialog(null, listCita);
            }else{
                JOptionPane.showMessageDialog(null, "No se tiene registros de esta fecha");
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error " + e.getMessage());
        }







    }


}
