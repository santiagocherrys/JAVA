import javax.swing.*;
import java.util.ArrayList;

public class Ejercicio_4 {

    public static void main(String[] args) {

        //Planificador de viajes

        /*Escribe un programa para ayudar a planificar viajes por
        carretera. Los usuarios pueden ingresar varias ciudades que planean visitar en
        orden. Utiliza un arreglo de String para almacenar las ciudades. El programa debe
        ser capaz de:

        -Añadir y remover ciudades del itinerario.
        -Mostrar la lista completa de ciudades a visitar.
        -(Opcional) Calcular la distancia total del viaje asumiendo distancias ficticias entre
        ciudades consecutivas.*/

        String opcion = "";
        String ciudad = "";
        String listaCiudades = "";
        int index = 0;

        ArrayList<String> ciudades = new ArrayList<String>();

        do{
            opcion = JOptionPane.showInputDialog(null ,"Menu opciones:\n\n" +
                    "1. Añadir ciudades al itinerario\n" +
                    "2. Remover ciudades del itinerario\n" +
                    "3. Lista completa de ciudades a visitar\n" +
                    "4. Calcular distancia total del viaje\n" +
                    "5. Salir\n" +
                    "Ingrese opción:");

            switch(opcion){
                case "1":
                    //añadir ciudades
                    boolean bandera = true;
                    do{
                        ciudad = JOptionPane.showInputDialog("Ingrese la ciudad a visitar");
                        if(ciudad == null){
                            //Esto es cuando hunden cancel
                            break;
                        }else if(ciudad.equals("") ){
                            JOptionPane.showMessageDialog(null,"No se puede recibir un campo vacio");
                        }else{
                            //Agregar ciudad
                            ciudades.add(ciudad);
                            JOptionPane.showMessageDialog(null,"La ciudad agregada es " + ciudad);
                            bandera = false;
                        }
                    }while(bandera);
                    break;
                case "2":
                     //Remover ciudades del intinerario
                    //Se listan las ciudades
                    try{
                        listaCiudades = "Las Ciudades son\n";
                        index = 1;
                        for(String item : ciudades){
                            listaCiudades += index + " " + item + "\n";
                            index++;

                        }
                        int indice = Integer.parseInt(JOptionPane.showInputDialog(null,listaCiudades + "\n ingreseel indice de la cancion a eliminar"));

                        if(indice <= 0){
                            JOptionPane.showMessageDialog(null,"No se puede indices negativos o el indice 0");
                        }else if(indice <= ciudades.size()){
                            JOptionPane.showMessageDialog(null,"La ciudad removida es " + ciudades.get((indice-1)));
                            ciudades.remove((indice -1));
                        }else{
                            JOptionPane.showMessageDialog(null,"Fuera de rango");
                        }
                    }catch(Exception e){
                            JOptionPane.showMessageDialog(null,"No es una entrada valida");
                    }

                    break;
                case "3":
                    //Mostrar la lista de ciudades a visitar
                    listaCiudades = "Las Ciudades a visitar son\n\n";
                    for(String item : ciudades){
                        listaCiudades += item + "\n";
                    }

                    JOptionPane.showMessageDialog(null,listaCiudades);
                    break;
                case "4":
                    //Calcular distancia total de viaje
                    try{
                        int contador = 0;
                        for(int i=0; i < (ciudades.size()-1); i++){
                           contador += Integer.parseInt(JOptionPane.showInputDialog(null,"ingrese la distancia entre " +ciudades.get(i) + " y " + ciudades.get((i+1))));
                        }

                        JOptionPane.showMessageDialog(null,"la distancia total a recorrer es: " + contador);

                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null,"Dato no valido");
                    }

                    break;
                case "5":
                    JOptionPane.showMessageDialog(null,"Fin del programa");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opción no valida");
                    break;
            }

        }while(!opcion.equals("5"));
    }
}
