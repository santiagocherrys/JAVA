package controller;

import entity.Avion;
import entity.Pasajero;
import entity.Reservacion;
import entity.Vuelo;
import model.AvionModel;
import model.PasajeroModel;
import model.ReservacionModel;
import model.VueloModel;

import javax.swing.*;
import java.util.List;

public class ReservacionController {
    public static void getAll(){
        ReservacionModel objMode = new ReservacionModel();

        String listReservacion = "RESERVACION LIST \n";

        for (Object iterador : objMode.findAll()) {
            Reservacion objReservacion = (Reservacion) iterador;
            listReservacion += objReservacion.imprimirTodo() + "\n";
        }
        JOptionPane.showMessageDialog(null, listReservacion);
    }

    public static String getAllString() {
        ReservacionModel objMode = new ReservacionModel();

        String listReservacion= "RESERVACION LIST \n";

        for (Object iterador : objMode.findAll()) {
            Reservacion objReservacion = (Reservacion) iterador;
            listReservacion += objReservacion.imprimirTodo() + "\n";
        }
        return listReservacion;
    }

    public static void create(){
        ReservacionModel objMode = new ReservacionModel();

        PasajeroModel objPasajeroModel = new PasajeroModel();
        VueloModel objVueloModel = new VueloModel();


        //Pedimos datos al usuario
        try{
            //preguntamos si existe pasajero
            if(objPasajeroModel.findAll().size()>0){

                //Se despliega menu Pasajeros
                List<Object> listaPasajeros = objPasajeroModel.findAll();

                //Se hace selector para pasajero que elija avion
                String[] choices = new String[listaPasajeros.size()];

                int index = 0;
                for(Object iterador : listaPasajeros){
                    Pasajero pasajero = (Pasajero) iterador;
                    System.out.println("Este es el pasajero " + pasajero.getNombre());
                    choices[index] = pasajero.getNombre() + " " + pasajero.getApellido();
                    index++;
                }

                String input = (String) JOptionPane.showInputDialog(null, "Escoja Pasajero para la reservacion...",
                        "Aviones disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                        // default
                        // icon
                        choices, // Array of choices
                        choices[0]); // Initial choice


                Reservacion objReservacion = new Reservacion();
                int id_pasajero = 0;
                for(Object iterador : listaPasajeros){
                    Pasajero pasajero = (Pasajero) iterador;
                    String nombreCompleto = pasajero.getNombre() + " " + pasajero.getApellido();
                    if(nombreCompleto.equals(input)){
                        id_pasajero = pasajero.getId_pasajero();
                        objReservacion.setId_pasajero(id_pasajero);
                        System.out.println("ESTE ES EL ID DE PASAJERO " + id_pasajero);
                    }
                }

                //Se despliega menu Vuelos
                List<Object> listaVuelos = objVueloModel.findAll();

                //Se hace selector para pasajero que elija avion
                String[] choices2 = new String[listaVuelos.size()];

                int index2 = 0;
                for(Object iterador : listaVuelos){
                    Vuelo vuelo = (Vuelo) iterador;
                    System.out.println("Este es el vuelo " + vuelo.getDestino());
                    choices2[index2] = vuelo.getDestino();
                    index2++;
                }

                String input2 = (String) JOptionPane.showInputDialog(null, "Escoja el Vuelo para la reservacion...",
                        "Vuelos disponibles hacia", JOptionPane.QUESTION_MESSAGE, null, // Use
                        // default
                        // icon
                        choices2, // Array of choices
                        choices2[0]); // Initial choice



                int id_vuelo = 0;
                System.out.println("tamaño lista vuelo " + listaVuelos.size());
                for(Object iterador : listaVuelos){
                    Vuelo vuelo = (Vuelo) iterador;
                    System.out.println("vuelo " + vuelo.getDestino() + " Vuelo de usuario " + input2);
                    if(vuelo.getDestino().equals(input2)){
                        id_vuelo = vuelo.getId_vuelo();
                        objReservacion.setId_vuelo(id_vuelo);
                        System.out.println("INGRESE EN VUELO y este es id_vuelo " + id_vuelo);
                    }
                }

                //Se añade los otros valores
                String fecha_reservacion = JOptionPane.showInputDialog("Ingrese la fecha de la reservaciòn recuerde 'yyyy-mm-dd'");
                String asiento = JOptionPane.showInputDialog("Ingrese el asiento");


                objReservacion.setFecha_reservacion(fecha_reservacion);
                objReservacion.setAsiento(asiento);



                objReservacion = (Reservacion) objMode.insert(objReservacion);

                JOptionPane.showMessageDialog(null, objReservacion.toString());
            }else{
                JOptionPane.showMessageDialog(null,"Tiene que crear algun pasajero para poder añadirle una reservaciòn");
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error es " + e.getMessage());
        }

    }

    public static void update(){
        //1. utilizar el modelo
        ReservacionModel objReservacionModel = new ReservacionModel();


        String listaReservaciones = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listaReservaciones+ "\n Ingrese el Id del vuelo a editar"));

        Reservacion objReservacion = objReservacionModel.findById(idUpdate);

        //validamos que exista el Vuelo
        if(objReservacion == null){
            JOptionPane.showMessageDialog(null, "Vuelo not found");
        }else{

            try{
                String asiento = JOptionPane.showInputDialog(null, "Ingrese el valor nuevo del asiento\n", objReservacion.getAsiento());





                objReservacion.setAsiento(asiento);



                objReservacionModel.update(objReservacion);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "El Error es " + e.getMessage());
            }

        }
    }

    public static void delete() {
        ReservacionModel objMode = new ReservacionModel();
        String listReservacion = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listReservacion + "\n Entre el id de la reservacion a borrar: "));

        Reservacion objReservacion = objMode.findById(idDelete);
        int confirm = 1;

        if (objReservacion == null) {
            JOptionPane.showMessageDialog(null, "Reservacion not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete Reservacion? \n" + objReservacion.imprimirTodo());

            if (confirm == 0) objMode.delete(objReservacion);
        }
    }
}
