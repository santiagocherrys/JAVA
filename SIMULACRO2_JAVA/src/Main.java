import controller.AvionController;
import controller.PasajeroController;
import controller.ReservacionController;
import controller.VueloController;
import database.ConfigDB;
import entity.Avion;
import entity.Reservacion;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {


        //Menu
        String opcion = "";
        String opcion2 = "";

        do{
            opcion = JOptionPane.showInputDialog("""
                    Menu para manejo aerolinea
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
                            //Avion
                            AvionController.create();
                            break;
                        case "2":
                            //Pasajero
                            PasajeroController.create();
                            break;
                        case "3":
                            //vuelo
                            VueloController.create();
                            break;
                        case "4":
                            //reservacion
                            ReservacionController.create();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"Opción no valida");
                    }

                    break;
                case "2":
                    //Leer Registro
                    opcion2 = menu("Leer");

                    switch(opcion2){
                        case "1":
                            //Avion
                            AvionController.getAll();
                            break;
                        case "2":
                            //Pasajero
                            PasajeroController.getAll();
                            break;
                        case "3":
                            //vuelo
                            String busqueda = JOptionPane.showInputDialog("""
                                    Ingrese
                                    1. Listar todos los vuelos
                                    2. Listar vuelos por destino
                                    """);

                            switch(busqueda){
                                case "1":
                                    VueloController.getAll();
                                    break;
                                case "2":
                                    VueloController.buscarPorDestino();
                                    break;
                            }

                            break;
                        case "4":
                            //reservacion
                            ReservacionController.getAll();
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
                            //Avion
                            AvionController.update();
                            break;
                        case "2":
                            //Pasajero
                            PasajeroController.update();
                            break;
                        case "3":
                            //vuelo
                            VueloController.update();
                            break;
                        case "4":
                            //reservacion
                            ReservacionController.update();
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
                            //Avion
                            AvionController.delete();
                            break;
                        case "2":
                            //Pasajero
                            PasajeroController.delete();
                            break;
                        case "3":
                            //vuelo
                            VueloController.delete();
                            break;
                        case "4":
                            //reservacion
                            ReservacionController.delete();
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
                "1. Avion\n" +
                "2. Pasajero\n" +
                "3. Vuelo\n" +
                "4. Reservacion\n" +
                "ingrese su opcion";
        opcion = JOptionPane.showInputDialog(mensaje);

        return opcion;
    }
}