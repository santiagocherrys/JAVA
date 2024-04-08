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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
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
        ReservacionModel objReservacionModel = new ReservacionModel();

        PasajeroModel objPasajeroModel = new PasajeroModel();
        VueloModel objVueloModel = new VueloModel();

        List<Reservacion> listaReservaciones = new ArrayList<>();


        //Pedimos datos al usuario
        try{
            //preguntamos si existe pasajero
            if(objPasajeroModel.findAll().size()>0){

                //Se despliega menu Pasajeros
                List<Object> listaPasajeros = objPasajeroModel.findAll();

                //Se hace selector para pasajero que elija pasajero
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
                int vuelo_capacidad = 0;
                System.out.println("tamaño lista vuelo " + listaVuelos.size());
                for(Object iterador : listaVuelos){
                    Vuelo vuelo = (Vuelo) iterador;
                    System.out.println("vuelo " + vuelo.getDestino() + " Vuelo de usuario " + input2);
                    if(vuelo.getDestino().equals(input2)){
                        id_vuelo = vuelo.getId_vuelo();
                        vuelo_capacidad = vuelo.getAvion_capacidad();
                        objReservacion.setId_vuelo(id_vuelo);
                        System.out.println("INGRESE EN VUELO y este es id_vuelo " + id_vuelo);
                    }
                }

                //Se chequea si el vuelo aun tiene capacidad
                listaReservaciones = objReservacionModel.findReservacionesByvuelo(id_vuelo);

                JOptionPane.showMessageDialog(null, "Esta es el tamaño lista de un vuelo " + listaReservaciones.size() + " este es vuelo capacidad " + vuelo_capacidad);
                if(listaReservaciones.size() >= vuelo_capacidad){
                    JOptionPane.showMessageDialog(null,"Ha pasado la capacidad del avion");
                }else{
                    //Se añade los otros valores
                    String fecha_reservacion = JOptionPane.showInputDialog("Ingrese la fecha de la reservaciòn recuerde 'yyyy-mm-dd'");

                    //rectificar los asientos

                    //int asiento = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el asiento del 1 al 3"));
                    final String[] asiento = {""};

                    //Se rectifica si está en el rango
                    if(listaReservaciones.size() < vuelo_capacidad){
                        final boolean[] bandera = {true};

                        //Arreglo para capturar los asientos de las reservaciones
                        String[] b = new String[listaReservaciones.size()];
                        int i=0, i2=0;
                        for(Reservacion iterador: listaReservaciones){
                            b[i2]= iterador.getAsiento();
                            i2++;

                            /*if(iterador.getAsiento().equals(Integer.toString(asiento))){
                                JOptionPane.showMessageDialog(null,"Asiento no disponible");
                                bandera[0] = false;
                                break;
                            }*/
                        }

                        //Se organiza arreglo
                        Arrays.sort(b);
                        String[] arrayTemp = new String[vuelo_capacidad];
                        //buscar en el arreglo temporal
                        int j=0;

                        //Convertir array a list para utilizar indexof
                        List<String> list = Arrays.asList(b);

                        //completar arreglo
                        int k=0;

                        //Array completo con el tamaño de los asientos
                        String[] arrayComp = new String[vuelo_capacidad];
                        for(k=0;k<vuelo_capacidad;k++){

                            if(k < listaReservaciones.size()){
                                arrayComp[k]=b[k];
                            }else{
                                arrayComp[k]= Integer.toString(k+1);
                            }

                        }


                        for(j=0; j<vuelo_capacidad;j++){
                            if(list.indexOf(Integer.toString((j+1))) !=-1){
                                arrayTemp[j]=arrayComp[j];
                            }else{
                                arrayTemp[j]= Integer.toString((j+1));
                            }
                        }

                        JPanel panel = new JPanel();
                        JFrame frame =new JFrame("Escoja uno de los asientos que estan disponibles");

                        JButton[] buttons = new JButton[vuelo_capacidad];
                        panel=new JPanel(new GridLayout(1,vuelo_capacidad));

                        final String[] choice = {""};
                        final boolean[] banderita = {true};
                        for(i = 0; i < buttons.length; i++) {
                            buttons[i] = new JButton(arrayTemp[i]);
                            buttons[i].setSize(200, 200);
                            buttons[i].setActionCommand(arrayTemp[i]);

                            //List contiene los asientos previos en la base de datos y este if deshabilita los botones de los asientos que ya estan en la base de datos
                            if(!(list.indexOf(arrayTemp[i]) < 0)){
                                buttons[i].setEnabled(false);
                            }
                            buttons[i].addActionListener(new ActionListener(){

                                public void actionPerformed(ActionEvent e) {
                                    choice[0] = e.getActionCommand();
                                    JOptionPane.showMessageDialog(null, "You have clicked: "+ choice[0]);
                                    asiento[0] = choice[0];

                                    //if(!choice[0].isEmpty()){
                                        JOptionPane.showMessageDialog(null ,"Entro a X");
                                        banderita[0] = false;
                                        frame.setVisible(false);
                                    //}
                                }
                            });




                            panel.add(buttons[i]);
                        }

                        frame.add(panel);
                        frame.pack();
                        frame.setVisible(true);

                        //Este While hace que el recuadro de escoger asientos permanezca activo mientras no se escoja ningun asiento
                        while(banderita[0]){
                            System.out.println("A la espera de que escojan asiento\n");
                        }

                        //asientos

                        /*JOptionPane.showMessageDialog(null,"asientos "+ Arrays.toString(b));
                        JOptionPane.showMessageDialog(null,"Asientos reales " +Arrays.toString(arrayTemp));*/

                        if(bandera[0]){
                            objReservacion.setAsiento(asiento[0]);
                            JOptionPane.showMessageDialog(null, "Asiento agregado correctamente");
                            objReservacion.setFecha_reservacion(fecha_reservacion);

                            objReservacion = (Reservacion) objReservacionModel.insert(objReservacion);

                            JOptionPane.showMessageDialog(null, objReservacion.toString());
                        }

                    }else{
                        JOptionPane.showMessageDialog(null, "Fuera del rango del avion");
                    }



                }



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

    public static void findReservacionesByvuelo(){
        //Listar reservacion por vuelo
        ReservacionModel objReservacionModel = new ReservacionModel();
        List<Reservacion> listaReservaciones = objReservacionModel.findReservacionesByvuelo();
        if(listaReservaciones.isEmpty()){
            JOptionPane.showMessageDialog(null,"No hay reservas asociadas a ese vuelo, cree una reserva");
        }else{
            String imprimir = "Estos son las reservaciones asociados al destino \n";

            for(Reservacion reservacion: listaReservaciones){
                imprimir += reservacion.imprimirTodo() + "\n";
            }
            JOptionPane.showMessageDialog(null, imprimir);
        }
    }
}
