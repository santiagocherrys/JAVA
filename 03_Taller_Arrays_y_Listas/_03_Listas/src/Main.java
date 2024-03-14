import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        /*Escribe un programa que utilice un array de tipo
        double para almacenar las calificaciones finales de 10 estudiantes en un curso. El
        programa debe calcular y mostrar el promedio de estas calificaciones.*/

        //Creamos el arreglo que guardará la lista
        double[] notasEstudiantes = new double[10];

        //Creamos nuestra instancia del objeto Scanner para poder obtener información desde la consola

        Scanner objScanner = new Scanner(System.in);

        double sumaTotal = 0;

        for(int i=0; i < 10 ;i++){
            System.out.println("Ingresa la nota del estudiante #" + (i+1));
            notasEstudiantes[i] = objScanner.nextDouble();
            sumaTotal += notasEstudiantes[i];
        }

        double promedio = sumaTotal/notasEstudiantes.length;

        System.out.println("El promedio general de todas las notas es " + promedio);
    }
}