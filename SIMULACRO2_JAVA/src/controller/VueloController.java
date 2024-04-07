package controller;

import entity.Avion;
import entity.Pasajero;
import entity.Vuelo;
import model.AvionModel;
import model.PasajeroModel;
import model.VueloModel;

import javax.swing.*;
import javax.swing.border.SoftBevelBorder;
import java.util.ArrayList;
import java.util.List;

public class VueloController {

    public static void getAll(){
        VueloModel objMode = new VueloModel();

        String listVuelos = "VUELOS LIST \n";

        for (Object iterador : objMode.findAll()) {
            Vuelo objVuelo = (Vuelo) iterador;
            listVuelos += objVuelo.imprimirTodo() + "\n";
        }
        JOptionPane.showMessageDialog(null, listVuelos);
    }

    public static String getAllString() {
        VueloModel objMode = new VueloModel();

        String listVuelos= "VUELOS LIST \n";

        for (Object iterador : objMode.findAll()) {
            Vuelo objVuelo = (Vuelo) iterador;
            listVuelos += objVuelo.imprimirTodo() + "\n";
        }
        return listVuelos;
    }

    public static void create() {
        VueloModel objMode = new VueloModel();

        AvionModel objAvionModel = new AvionModel();



        //Pedimos datos al usuario
        try{
            //preguntamos si existe avion
            if(objAvionModel.findAll().size()>0){
                String destino = JOptionPane.showInputDialog("Ingrese Nombre del destino \n");
                String fecha_salida = JOptionPane.showInputDialog("Ingrese fecha salida recuerde 'yyyy-mm-dd' \n");
                String hora_salida = JOptionPane.showInputDialog("Ingrese hora de salida recuerde 'HH:MM' formato militar");


                //Se despliega menu Aviones
                List<Object> listaAviones = objAvionModel.findAll();

                //Se hace selector para vuelo que elija avion
                String[] choices = new String[listaAviones.size()];

                int index = 0;
                for(Object iterador : listaAviones){
                    Avion avion = (Avion) iterador;
                    System.out.println("Este es el avion " + avion.getModelo() + " y su capacidad " + avion.getCapacidad());
                    choices[index] = avion.getModelo();
                    index++;
                }

                String input = (String) JOptionPane.showInputDialog(null, "Escoja Avion para el vuelo...",
                        "Aviones disponibles", JOptionPane.QUESTION_MESSAGE, null, // Use
                        // default
                        // icon
                        choices, // Array of choices
                        choices[0]); // Initial choice


                Vuelo objVuelo = new Vuelo();
                int id_avion = 0;
                for(Object iterador : listaAviones){
                    Avion avion = (Avion) iterador;
                    if(avion.getModelo().equals(input)){
                        id_avion = avion.getId_avion();
                        objVuelo.setId_avion(id_avion);
                    }
                }


                //Se añade los otros valores


                objVuelo.setDestino(destino);
                objVuelo.setFecha_salida(fecha_salida);
                objVuelo.setHora_salida(hora_salida);

                System.out.println("Estos son los datos del vuelo " + objVuelo.toString() + " id_avion " + objVuelo.getId_avion());
                objVuelo = (Vuelo) objMode.insert(objVuelo);

                JOptionPane.showMessageDialog(null, objVuelo.toString());
            }else{
                JOptionPane.showMessageDialog(null,"Tiene que crear algun avion para poder añadirle al vuelo su avion");
            }


        }catch (Exception e){
            JOptionPane.showMessageDialog(null, "Error es " + e.getMessage());
        }

    }

    public static void update(){
        //1. utilizar el modelo
        VueloModel objVueloModel = new VueloModel();


        String listaVuelos = getAllString();

        int idUpdate = Integer.parseInt(JOptionPane.showInputDialog(listaVuelos+ "\n Ingrese el Id del medico a editar"));

        Vuelo objVuelo = objVueloModel.findById(idUpdate);

        //validamos que exista el Vuelo
        if(objVuelo == null){
            JOptionPane.showMessageDialog(null, "Vuelo not found");
        }else{

            try{
                String fecha_salida = JOptionPane.showInputDialog(null, "Ingrese la fecha del vuelo a modificar\n", objVuelo.getFecha_salida());





                objVuelo.setFecha_salida(fecha_salida);



                objVueloModel.update(objVuelo);
            }catch (Exception e){
                JOptionPane.showMessageDialog(null, "El Error es " + e.getMessage());
            }

        }
    }

    public static void delete() {
        VueloModel objMode = new VueloModel();
        String listVuelos = getAllString();

        int idDelete = Integer.parseInt(JOptionPane.showInputDialog(listVuelos + "\n Entre el id del vuelo a borrar: "));

        Vuelo objVuelo = objMode.findById(idDelete);
        int confirm = 1;

        if (objVuelo == null) {
            JOptionPane.showMessageDialog(null, "Medico not found");
        } else {
            confirm = JOptionPane.showConfirmDialog(null, "Are you sure want to delete vuelo? \n" + objVuelo.toString());

            if (confirm == 0) objMode.delete(objVuelo);
        }
    }

    public static void buscarPorDestino(){
        //Listar destinos
        VueloModel objVueloModel = new VueloModel();
        List<Vuelo> listaVuelos = objVueloModel.buscarPorDestino();
        if(listaVuelos.isEmpty()){
            JOptionPane.showMessageDialog(null,"No vuelos disponibles, cree un vuelo");
        }else{
            String imprimir = "Estos son los vuelos asociados al destino \n";

            for(Vuelo vuelo: listaVuelos){
                imprimir += vuelo.toString() + "\n";
            }
            JOptionPane.showMessageDialog(null, imprimir);
        }

    }


}
