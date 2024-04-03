import controller.CitaController;
import controller.EspecialidadController;
import controller.MedicoController;
import controller.PacienteController;
import database.ConfigDB;
import entity.Paciente;

import javax.swing.*;
import java.io.ObjectInputFilter;

public class Main {
    public static void main(String[] args) {

        /*System.out.println("prueba conexión");
        ConfigDB.openConnection();
        ConfigDB.closeConnection();*/

        //Menu
        String opcion = "";
        String opcion2 = "";

        do{
            opcion = JOptionPane.showInputDialog("""
                    Menu para manejo hospital
                    1. Crear Registro
                    2. Leer Registro
                    3. Actualizar Registro
                    4. Eliminar Registro
                    5. Salir
                    Ingrese su opción:
                    """);

            switch (opcion){
                case "1":
                    //Crear Registro
                    opcion2 = menu("Crear");

                    switch(opcion2){
                        case "1":
                            //Especialidad
                            EspecialidadController.create();
                            break;
                        case "2":
                            //Medico
                            MedicoController.create();
                            break;
                        case "3":
                            //Paciente
                            PacienteController.create();
                            break;
                        case "4":
                            //Cita
                            CitaController.create();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"Opción no valida");
                    }

                    break;
                case "2":
                    //Leer Registro
                    opcion2 = menu("Leer");
                    String choice = "";

                    switch(opcion2){
                        case "1":
                            //Especialidad
                            EspecialidadController.getAll();
                            break;
                        case "2":
                            //Medico

                            choice = JOptionPane.showInputDialog("""
                                    1. Leer todas los medicos
                                    2. Buscar medicos por especialidad
                                    """);

                            switch (choice){
                                case "1":
                                    MedicoController.getAll();
                                    break;
                                case "2":
                                    MedicoController.findMedicosByEspecialidad();
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null,"Opcion no valida");
                            }
                            break;
                        case "3":
                            //Paciente
                            choice = JOptionPane.showInputDialog("""
                                    1. Leer todas los pacientes
                                    2. Buscar paciente por documento identidad
                                    """);

                            switch (choice){
                                case "1":
                                    PacienteController.getAll();
                                    break;
                                case "2":
                                    PacienteController.buscarPorTarIdentidad();
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null,"Opcion no valida");
                            }

                            break;
                        case "4":
                            //Cita
                            choice = JOptionPane.showInputDialog("""
                                    1. Leer todas las citas
                                    2. Buscar citas por fecha
                                    """);

                            switch (choice){
                                case "1":
                                    CitaController.getAll();
                                    break;
                                case "2":
                                    CitaController.buscarPorFecha();
                                    break;
                                default:
                                    JOptionPane.showMessageDialog(null,"Opcion no valida");
                            }

                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"Opción no valida");
                    }

                    break;
                case "3":
                    //Actualizar Registro
                    opcion2 = menu("Actualizar");

                    switch(opcion2){
                        case "1":
                            //Especialidad
                            EspecialidadController.update();
                            break;
                        case "2":
                            //Medico
                            MedicoController.update();
                            break;
                        case "3":
                            //Paciente
                            PacienteController.update();
                            break;
                        case "4":
                            //Cita
                            CitaController.update();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"Opción no valida");
                    }

                    break;
                case "4":
                    //Eliminar Registros
                    opcion2 = menu("Eliminar");

                    switch(opcion2){
                        case "1":
                            //Especialidad
                            EspecialidadController.delete();
                            break;
                        case "2":
                            //Medico
                            MedicoController.delete();
                            break;
                        case "3":
                            //Paciente
                            PacienteController.delete();
                            break;
                        case "4":
                            //Cita
                            CitaController.delete();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"Opción no valida");
                    }

                    break;
                case "5":
                    //Salir
                    JOptionPane.showMessageDialog(null, "Gracias por su visita, vuelva pronto");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion no valida");

            }
        }while(!opcion.equals("5"));
    }

    public static String menu(String texto){
        String opcion = "";
        String mensaje = texto + " Registro en:\n" +
                "1. Especialidad\n" +
                "2. Medico\n" +
                "3. Paciente\n" +
                "4. Cita\n" +
                "ingrese su opcion";
        opcion = JOptionPane.showInputDialog(mensaje);

        return opcion;
    }
}