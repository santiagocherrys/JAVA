import java.util.Scanner;

public class BuscadorMayorMenor {

    public static void main(String[] args) {

        /*Buscador de Número Mayor y Menor: Utiliza un array de int para almacenar 5
        números enteros ingresados por el usuario (puedes usar la clase Scanner para la
        entrada de datos). El programa debe encontrar y mostrar el número mayor y el
        menor de la lista.*/

        Scanner leer = new Scanner(System.in);

        int[] arreglo = new int[5];
        int mayor = -999999;

        int menor= 999999;

        for(int i = 0; i < 5 ; i++){
            System.out.println("Ingrese el numero " + (i+1));
            int lectura = leer.nextInt();
            arreglo[i]=lectura;
            if(lectura > mayor){
                mayor = lectura;
            }

            if(lectura < menor){
                menor = lectura;
            }

        }

        System.out.println("Estos son los numeros ingresados");

        for(int i= 0; i < 5; i++){
            System.out.print(arreglo[i] + " ");
        }

        System.out.println("\n El numero mayor es: " + mayor + " el numero menor es: " + menor );


    }
}

