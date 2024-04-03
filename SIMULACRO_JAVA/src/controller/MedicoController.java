package controller;

import entity.Especialidad;
import entity.Medico;
import entity.Paciente;
import model.EspecialidadModel;
import model.MedicoModel;
import model.PacienteModel;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MedicoController {
    public static void getAll() {
        MedicoModel objMode = new MedicoModel();

        String listMedicos = "MEDICOS LIST \n";

        for (Object iterador : objMode.findAll()) {
            Medico objMedico = (Medico) iterador;
            listMedicos += objMedico.toString() + "\n";
        }
        JOptionPane.showMessageDialog(null, listMedicos);
    }

    public static String getAllString() {
        MedicoModel objMode = new MedicoModel();

        String listMedicos= "MEDICOS LIST \n";

        for (Object iterador : objMode.findAll()) {
            Medico objMedico = (Medico) iterador;
            listMedicos += objMedico.toString() + "\n";
        }
        return listMedicos;
    }

    public static void create() {
        MedicoModel objMode = new MedicoModel();

        EspecialidadModel objEspecialidadModel = new EspecialidadModel();



        //Pedimos datos al usuario
        try{
            //preguntamos si existe especialidad
            if(objEspecialidadModel.findAll().size()>0){
                String nombre = JOptionPane.showInputDialog("Ingrese Nombre del medico \n");
                String apellidos = JOptionPane.showInputDialog("Ingrese apellidos del medico \n");


                //Se despliega menu especialidad
                List<Object> listaEspecialidades = objEspecialidadModel.findAll();

                //Se hace selector para Medimos
                String[] choices = new String[listaEspecialidades.size()];

                int index = 0;
                for(Object iterador : listaEspecialidades){
                    Especialidad especialidad = (Especialidad) iterador;
                    System.out.println("Este es el medico " + especialidad.getNombre() + " y su id " + especialidad.getId_especialidad());
                    choices[index] = especialidad.getNombre();
                    index++;
                }

                String input = (String) JOptionPane.showInputDialog(null, "Escoja Especialidad...",
                        "Especialidades disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                        // default
                        // icon
                        choices, // Array of choices
                        choices[0]); // Initial choice


                Medico objMedico = new Medico();
                int id_espec = 0;
                for(Object iterador : listaEspecialidades){
                    Especialidad especialidad = (Especialidad) iterador;
                    if(especialidad.getNombre().equals(input)){
                        id_espec = especialidad.getId_especialidad();
                        objMedico.setEspecialidad_nombre(input);
                    }
                }


                //Se crea una instancia de Medico


                objMedico.setNombre(nombre);
                objMedico.setApellidos(apellidos);
                objMedico.setId_especialidad(id_espec);


                objMedico = (Medico) objMode.insert(objMedico);

                JOptionPane.showMessageDialog(null, objMedico.toString());
            }else{
                JOptionPane.showMessageDialog(null,"Tiene que crear alguna especialidad para poder añadirle al  medico su especialidad");
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error es " + e.getMessage());
        }

    }

    public static void delete() {
        MedicoModel objMode = new MedicoModel();
        String listMedicos = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listMedicos + "\n Entre el id del médico a borrar: "));

        Medico objMedico = objMode.findById(idDelete);
        int confirm = 1;

        if (objMedico == null) {
            JOptionPane.showMessageDialog(null, "Medico not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete el médico? \n" + objMedico.toString());

            if (confirm == 0) objMode.delete(objMedico);
        }
    }

    //método encontrar por Id
    public static void findById(){
        MedicoModel objMode = new MedicoModel();

        try{
            int opcion = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el id del Medico"));
            Medico medico = objMode.findById(opcion);
            JOptionPane.showMessageDialog(null,"Este es el medico: " + medico.toString());
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,"Opcion no valida");
        }

    }

    public static void update(){
        //1. utilizar el modelo
        MedicoModel objMedicoModel = new MedicoModel();
        EspecialidadModel objEspecialidadModel = new EspecialidadModel();

        String listaMedicos = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listaMedicos+ "\n Ingrese el Id del medico a editar"));

        Medico objMedico = objMedicoModel.findById(idUpdate);

        //validamos que exista el autor
        if(objMedico == null){
            JOptionPane.showMessageDialog(null, "Medico not found");
        }else{

            try{
                String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del Medico\n", objMedico.getNombre());
                String apellidos = JOptionPane.showInputDialog(null, "Ingrese los apellidos del Medico\n", objMedico.getApellidos());


                //Se despliega menu especialidad
                List<Object> listaEspecialidades = objEspecialidadModel.findAll();

                //Se hace selector
                String[] choices = new String[listaEspecialidades.size()];

                int index = 0;
                int selector = 0;

                for(Object iterador : listaEspecialidades){
                    Especialidad especialidad = (Especialidad) iterador;
                    System.out.println("Este es la especialidad " + especialidad.getNombre() + " y su id " + especialidad.getId_especialidad());
                    choices[index] = especialidad.getNombre();
                    System.out.println("id_especialidad " +especialidad.getId_especialidad() + " Medico_id_especialidad " + objMedico.getId_especialidad());
                    if(especialidad.getId_especialidad() == objMedico.getId_especialidad()){
                        selector = index;
                    }
                    index++;
                }
                String input = (String) JOptionPane.showInputDialog(null, "Escoja Medico...",
                        "Medicos disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                        // default
                        // icon
                        choices, // Array of choices
                        choices[selector]); // Initial choice

                int id_espec = 0;
                for(Object iterador : listaEspecialidades){
                    Especialidad especialidad = (Especialidad) iterador;
                    if(especialidad.getNombre().equals(input)){
                        id_espec = especialidad.getId_especialidad();
                    }
                }

                objMedico.setNombre(nombre);
                objMedico.setApellidos(apellidos);
                objMedico.setId_especialidad(id_espec);


                objMedicoModel.update(objMedico);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "El Error es " + e.getMessage());
            }

        }
    }

    public static void findMedicosByEspecialidad(){

        MedicoModel objMedicoModel = new MedicoModel();

        EspecialidadModel objEspecialidadModel = new EspecialidadModel();

        //Se despliega menu especialidad
        List<Object> listaEspecialidades = objEspecialidadModel.findAll();

        //Se hace selector para Medimos
        String[] choices = new String[listaEspecialidades.size()];

        int index = 0;
        for(Object iterador : listaEspecialidades){
            Especialidad especialidad = (Especialidad) iterador;
            System.out.println("Este es el medico " + especialidad.getNombre() + " y su id " + especialidad.getId_especialidad());
            choices[index] = especialidad.getNombre();
            index++;
        }

        String input = (String) JOptionPane.showInputDialog(null, "Escoja Especialidad...",
                "Especialidades disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                // default
                // icon
                choices, // Array of choices
                choices[0]); // Initial choice

        Medico objMedico = new Medico();
        int id_espec = 0;
        for(Object iterador : listaEspecialidades){
            Especialidad especialidad = (Especialidad) iterador;
            if(especialidad.getNombre().equals(input)){
                id_espec = especialidad.getId_especialidad();
                objMedico.setEspecialidad_nombre(input);
            }
        }



        //Revisamos si hay datos

        if(objMedicoModel.findMedicosByEspecialidad(id_espec).size() >0){
            String listMedicos = "MEDICOS LIST \n";

            for (Medico iterador : objMedicoModel.findMedicosByEspecialidad(id_espec)) {

                listMedicos += iterador.toStringEspecialidad() + "\n";
            }
            JOptionPane.showMessageDialog(null, listMedicos);
        }else{
            JOptionPane.showMessageDialog(null,"No se encuentra ningun registro con esa cedula");
        }




    }



}
