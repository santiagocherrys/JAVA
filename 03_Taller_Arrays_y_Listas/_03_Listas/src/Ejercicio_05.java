import javax.swing.*;
import java.util.ArrayList;


public class Ejercicio_05 {

    public static void main(String[] args) {


        /*Lista de Reproducción Musical: Imagina que estás creando una aplicación para
        gestionar listas de reproducción musicales. Cada canción es representada
        simplemente por su nombre. El programa debe permitir:*/

        /*-Añadir y remover canciones de la lista de reproducción.
        -Mostrar la canción actual y las siguientes en la lista.
        -Saltar a la siguiente canción.*/

        //Creamos nuestro arrayList para guardar las canciones

        ArrayList<String> playlist = new ArrayList<String>();

        String option = "";
        //Variable para guardar el indice de la canción actual
        int cancionActual = 0;

        do{
            option = JOptionPane.showInputDialog("PLAYLIST \n" +
                    "1. Añadir una canción a la playlist\n" +
                    "2.Remover una canción \n" +
                    "3.Mostar la canción actual y las siguientes en la lista\n" +
                    "4.Saltar a la siguiente canción\n" +
                    "5. Salir \n" +
                    "Ingrese una opcion: ");


            switch (option){
                case "1":
                    //Añadir una cancion a la playlist
                    int repeat = 0;

                    while(repeat == 0){
                        String nuevaCancion = JOptionPane.showInputDialog("Ingrese el nombre de la canción");

                        playlist.add(nuevaCancion.toLowerCase());

                        JOptionPane.showMessageDialog(null, nuevaCancion + " Agregada correctamente");

                        //ventana de confirmacion
                        repeat = JOptionPane.showConfirmDialog(null,"Deseas agregar otra canción?");
                    }

                    break;

                case "2":
                    //Remover una canción

                    String totalPlayList = "PLAYLIST \n";
                    int index = 1;


                    //Como el foreach en javascript
                    for (String temp : playlist){
                        totalPlayList += index + " " + temp+"\n";
                        index++;
                    }


                    int indiceEliminar = 0;
                    boolean valido = true;
                    String input = "";

                    do{
                        try{
                            valido= true;

                            input = JOptionPane.showInputDialog(
                                    totalPlayList + "\nIngrese el numero de la cancion que deseas eliminar");

                            //Esta validacion se hace ya que el cancel devuelve un null
                            if(input == null){
                                JOptionPane.showMessageDialog(null,"Hundiste cancel");
                                break;
                            }else{
                                indiceEliminar = Integer.parseInt(input);

                                //Validamos que el indice ingresado corresponda a algún item
                                if(indiceEliminar <= 0 || indiceEliminar > playlist.size()){
                                    JOptionPane.showMessageDialog(null,"Indice no valido");
                                }else{
                                    //Eliminar cancion
                                    JOptionPane.showMessageDialog(null, "Cancion " + "'"+playlist.get((indiceEliminar-1))+"'" + " eleminada correctamente ");
                                    playlist.remove((indiceEliminar-1));
                                }
                            }

                        }catch(Exception e){
                            indiceEliminar = 0;
                            JOptionPane.showMessageDialog(null,"Opción no valida catch");
                            valido = false;
                        }
                    }while(!valido);




                    break;
                case "3":
                    //Mostrar la canción actual y las siguientes en la lista

                    //Si la lista está vacia salir

                    if(playlist.isEmpty()){
                        JOptionPane.showMessageDialog(null,"La playlist está vacia");
                        break;
                    }
                        //Creamos la variable que guardará la información y la inicializamos con el indice de
                        String mensaje = "Canción Actual: \n" + playlist.get(cancionActual) +
                                "\n\n lista de reproducción: \n";

                        //Este ciclo itera desde una posición más de la canción actual hasta el final de la lista
                        for(int i= cancionActual; i < (playlist.size() -1); i++){
                            mensaje += playlist.get(i+1) + "\n";
                        }

                        //Finalmente imprimimos el mensaje
                        JOptionPane.showMessageDialog(null , mensaje);


                    break;
                case "4":
                    //Saltar a la siguiente canción

                    if(playlist.isEmpty()){
                        JOptionPane.showMessageDialog(null,"No hay canciones");
                        break;
                    }

                    //validación de que la canción esté en el rango
                    if((cancionActual + 1) < playlist.size()){
                        cancionActual++;
                    }else{
                        cancionActual = 0;
                    }

                    JOptionPane.showMessageDialog(null, "Reproducciendo canción: " + playlist.get(cancionActual));

                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Opción no valida");
                    break;



            }
        }while(!option.equals("5"));
    }
}
