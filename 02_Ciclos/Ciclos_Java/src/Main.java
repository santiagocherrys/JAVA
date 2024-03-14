import javax.swing.*;
import java.rmi.server.ExportException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        //Ejercicio 3
        /*Tabla de Multiplicar*/
        /*for(int i=1; i<=10; i++){
            System.out.println("Tabla del " + i);

            for(int j=1; j<=10;j++){
                System.out.println(i + "x" + j + " = " + (i*j));
            }
            System.out.println("");
        }*/


        //Ejercicio 9 Sistema de menu interactivo
        /*String option = "";
        double saldo = 0;

        do{
            option = JOptionPane.showInputDialog(null, "MENU DE OPCIONES\n" +
                    "1. Ver saldo \n" +
                    "2. Depositar dinero\n" +
                    "3. Retirar dinero\n" +
                    "4. salir\n\n" +
                    "Ingresa una opcion: ");

            switch(option){
                case "1":

                    //Ver el saldo
                    JOptionPane.showMessageDialog(null, "Tu saldo es de: " + saldo);
                    break;
                case "2":
                    //Ingresar saldo
                    String dinero = JOptionPane.showInputDialog(null, "ingrese la cantidad de dinero a depositar");



                    try{
                        //Convertir o castear de String a double
                        double dineroGuardar = Double.parseDouble(dinero);

                        saldo += dineroGuardar;
                        JOptionPane.showMessageDialog(null, "Dinero depositado correctamente");
                    }catch (Exception error){
                        JOptionPane.showMessageDialog(null , "Caracteres no válido");
                    }
                    break;

                case "3":
                        //Retirar dinero

                    try{
                         double retiro = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese la catidad a Retirar"));

                         //Validar si tiene saldo disponible
                         if(retiro > saldo){
                             JOptionPane.showMessageDialog(null, "Saldo insuficiente");
                         }else {
                             saldo -= retiro;
                             JOptionPane.showMessageDialog(null, "Dinero retirado correctamente, tu saldo es: " + saldo);
                         }
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null , "Caracteres no válido");
                    }
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Esta no es la opcion");
                break;
            }

        }while(!option.equals("4"));*/

        //Ejercicio 1 Contador simple

        /*System.out.println("Este programa cuenta del 1 al 10");
        for(int i=1; i <=10 ;i++){
            System.out.println(i);
        }

        System.out.println("Gracias por utilizar el programa");*/

        //Ejercicio 2 Suma de numeros

        /*System.out.println("Este programa suma los numeros del 1 al 100");
        int contador = 0;
        int suma = 0;
        while(contador != 100){
            System.out.println(contador);
            contador++;
            suma += contador;
        }
        System.out.println("La suma de los numeros del 1 al 100 es: " + suma);*/

        //Ejercicio 4 Suma de numeros Pares del 1 al 100

        /*int suma = 0;
        for(int i = 2; i<=100; i+=2){
            suma += i;

        }
        System.out.println("La suma de los numeros pares del 1 al 100 pares es: " + suma);*/

        //Ejercicio 5 Validacion de entrada de usuario

        /*Scanner leer = new Scanner(System.in);

        boolean flag = true;
        int edad= 0;
        do{
            try{
                System.out.println("Ingrese su edad");
                edad = leer.nextInt();




                if((edad > 0) && (edad <=120) ){
                    System.out.println("Su edad es: " + edad + " dato correcto");
                    System.out.println("Fin del programa");
                    flag = false;
                }else{
                    System.out.println("edad invalida, está por fuera del rango");
                }
            }catch(Exception e){
                System.out.println("Solo entradas numericas, vuelva a intentarlo");

                *//*OJO SI NO SE PONE ESTA LINEA SE QUEDA EN UN CICLO INFINITO*//*
                leer.nextInt();
            }

        }while(flag);*/

        //Ejercicio 6 numeros primos
        /*Scanner leer = new Scanner(System.in);

        System.out.println("Este programa le dice si un numero es primo");



            int cont = 0;
            int num = 0;
            while(num != 1){
                try{
                    System.out.println("ingrese el numero, 1 para salir");
                    num = leer.nextInt();

                    for(int i = 1; i<= num; i++ ){
                        if(num % i == 0){
                            cont++;
                        }

                        if(cont >3){
                            break;
                        }
                    }

                    if(num ==1){
                        break;
                    }

                    if(cont == 2){
                        System.out.println("El número " + num + " es un número primo");
                    }else{
                        System.out.println("El número " + num + " No es número primo");
                    }
                    cont = 0;
                }catch (Exception e){
                    System.out.println("Ingrese dato entero númerico");
                    leer.nextLine();
                }

            }*/

        //Ejercicio 7 Juego de adivinanzas
        /*System.out.println("Este es un juego de adivinanzas, donde  tiene que adivinar numero aleatorio");
        String option = "";
        int cont = 0;
        int adivinar = 0;
        int numJug = 0;
        boolean flag = true;
        do{
            try{
                if(cont == 0){
                    option = JOptionPane.showInputDialog(null, "MENU DE OPCIONES\n" +
                            "1. Ingrese el maximo numero para jugar en el rango del 1 al maximo numero \n" +
                            "0. Salir\n\n" +
                            "Ingresa una opcion: ");

                    cont = 1;

                    switch (option){
                        case "1":
                            int num = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese el máximo numero"));

                            adivinar = (int)(Math.random()*num+1);
                            break;
                        case "0":
                            //salir
                            flag = false;
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"opcion no válida");
                            cont = 0;
                            break;
                    }
                }
            }catch (Exception e){
                System.out.println("Tiene que ingresar una opcion numerica");
            }

            if(flag != false){
                numJug = Integer.parseInt(JOptionPane.showInputDialog(null ,"Ingrese numeroa Adivinar"));

                if(numJug == adivinar){
                    JOptionPane.showMessageDialog(null,"Has ganado, el nùmero que adivinaste es el " + adivinar);
                    flag = false;
                }else{
                    JOptionPane.showMessageDialog(null,"Intentalo de nuevo");
                }
            }
        }while(flag);*/

        //Ejercicio 8 Calculo de interes compuesto
        String option = "";



        double capitalInicial = 0, tasaInteres = 0;
        int numeroAnios = 0;
        boolean flag = true;
        while(flag){
            option = JOptionPane.showInputDialog(null, "Este programa le calcula el interes compuesto\n" +
                    "1. Ingrese los datos \n" +
                    "2. Mostrar datos ingresador\n" +
                    "3. Calcular interes compuesto\n"+
                    "4. Salir\n\n" +
                    "Ingresa una opcion: ");

            switch (option){
                case "1":
                    //Ingresar datos
                    try{
                        capitalInicial = Float.parseFloat(JOptionPane.showInputDialog(null,"Ingrese capital inicial"));
                        tasaInteres = Float.parseFloat(JOptionPane.showInputDialog(null,"Ingrese tasa interes- Recuerde que es entre 0 a 1 en decimal"));
                        numeroAnios = Integer.parseInt(JOptionPane.showInputDialog(null,"Ingrese numero años, recuerde que es un numero entero"));
                    }catch (Exception e){
                        JOptionPane.showMessageDialog(null,"Se ha equivocado, recuerde que son datos numericos y el año es entero");
                    }

                    break;
                case "2":
                    //Mostrar datos
                    JOptionPane.showMessageDialog(null,"Estos son los datos: \n" +
                            "El capital inicial es " + capitalInicial + " tasa interes " + tasaInteres + " numero de Años " + numeroAnios);

                    break;

                case "3":
                    //Calcular  interes
                    double interesCompuesto = 0;
                    for(int i = 1; i<=numeroAnios ; i++){
                        if(i == 1){
                            interesCompuesto = (1+tasaInteres);
                        }else{
                            interesCompuesto = interesCompuesto * (1+tasaInteres);
                        }

                        System.out.println("Interes compuesto " + i + " es " + interesCompuesto);
                    }
                    interesCompuesto *= capitalInicial;

                    JOptionPane.showMessageDialog(null,"El interes compuesto es: " + interesCompuesto);
                    break;
                case "4":
                    //Salir
                    flag = false;
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Se ha equivocado, valor invalido");
                    break;
            }
        }










    }
}