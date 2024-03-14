import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner objScan = new Scanner(System.in);

        //Ejercicio 1 Calculadora Básica

        /*System.out.println("Este programa le suma, resta, multiplica y divide dos numeros");

        System.out.println("Ingrese el número 1 ");
        float num1 = objScan.nextFloat();

        System.out.println("Ingrese el número 2 ");
        float num2 = objScan.nextFloat();

        System.out.println("Resultados");
        System.out.println("Suma " + num1 + " + " + num2 + " = " + (num1+num2));
        System.out.println("Resta " + num1 + " - " + num2 + " = " + (num1-num2));
        System.out.println("Multiplicacion " + num1 + " * " + num2 + " = " + (num1*num2));
        System.out.println("División " + num1 + " / " + num2 + " = " + (num1/num2));*/

        //Ejercicio 2 Verificador de Edad

        /*System.out.println("Este programa le dice si es mayor o menor de edad");

        System.out.println("Ingrese su edad por favor");
        int edad = objScan.nextInt();

        if(edad >= 18){
            System.out.println("Usted es mayor de edad");
        }else{
            System.out.println("Usted es menor de edad");
        }*/



        //Ejercicio 3 Conversor de unidades

        /*System.out.println("Este programa convierte de kilometros a millas");
        // 1 milla es igual a 1.60934

        System.out.println("Ingrese el numero de kilometros a convertir:");
        float kilometros = objScan.nextFloat();

        System.out.println("Los " + kilometros + " kilometros  equivalen a " + (kilometros/1.60934) + " millas");*/


        //Ejercicio 4 Calculadora de Indice de masa Corporal
        /*System.out.println("Este programa calcula el indice de masa corporal(IMC)");

        System.out.println("Ingrese su peso en kilogramos ");
        float peso = objScan.nextFloat();

        System.out.println("Ingrese su altura en metros");
        float altura = objScan.nextFloat();

        System.out.println("Su indice de masa corporal es: " + (peso/(altura*altura)));*/


        //Ejercicio 5 Clasificador de Números

        /*System.out.println("Programa que pide un número y muestra si el número es positivo, negativo o cero," +
                "Ademas si es par o impar");

        System.out.println("Por favor ingrese el número");
        float numero = objScan.nextFloat();

        if(numero > 0){
            System.out.println("El número es positivo");
        } else if (numero == 0) {
            System.out.println("El numero es cero");
        }else{
            System.out.println("El número es negativo");
        }

        if((numero % 2) == 0){
            System.out.println("El número es par");
        }else{
            System.out.println("El número es impar");
        }*/

        //Ejercicio 6 Calculadora de Días del Mes

        /*System.out.println("Este programa te dice cuantos días tiene un mes");
        System.out.println("Ingresa por favor el número del mes");

        int mes = objScan.nextInt();

        switch (mes){
            case 1:
                System.out.println("Enero tiene 31 días");
                break;
            case 2:
                System.out.println("Febrero tiene 28 días");
                break;
            case 3:
                System.out.println("Marzo tiene 31 días");
                break;
            case 4:
                System.out.println("Abril tiene 30 días");
                break;
            case 5:
                System.out.println("Mayo tiene 31 días");
                break;
            case 6:
                System.out.println("Junio tiene 30 días");
                break;
            case 7:
                System.out.println("Julio tiene 31 días");
                break;
            case 8:
                System.out.println("Agosto tiene 31 días");
                break;
            case 9:
                System.out.println("Septiembre tiene 30 días");
                break;
            case 10:
                System.out.println("Octubre tiene 31 días");
                break;
            case 11:
                System.out.println("Noviembre tiene 30 días");
                break;
            case 12:
                System.out.println("Diciembre tiene 31 días");
                break;
            default:
                System.out.println("El número ingresado no corresponde a un mes");
                break;

        }*/

        //Ejercicio 7 Menu interactivo

        /*System.out.println("Este programa simula una calculadora");

        boolean salir = true;
        float numero1 = 0;
        float numero2 = 0;
        while(salir){

            int option = menu(objScan);
            if(option >=1 && option <=4){
                System.out.println("ingrese numero 1");
                numero1 = objScan.nextFloat();
                System.out.println("ingrese numero 2");
                numero2 = objScan.nextFloat();
            }


            switch(option){
                case 1:
                    //Suma
                    System.out.println("La suma de " + numero1 + " + " + numero2 + " es " + (numero1 + numero2));
                    break;
                case 2:
                    //Resta
                    System.out.println("La resta de " + numero1 + " - " + numero2 + " es " + (numero1 - numero2));
                    break;
                case 3:
                    //Multiplicacion
                    System.out.println("La multiplicacion de " + numero1 + " * " + numero2 + " es " + (numero1 * numero2));
                    break;
                case 4:
                    //division
                    System.out.println("La división de " + numero1 + " / " + numero2 + " es " + (numero1 / numero2));
                    break;
                case 5:
                    //Salir
                    System.out.println("Ha Finalizado el programa");
                    salir = false;
                    break;
                default:
                    //Opcion no valida
                    System.out.println("Opcion no valida, ingrese opcion correcta");
                    break;

            }
        }*/

        //Ejercicio 8 Calculo de promedio

        /*System.out.println("Este programa recibe 3 calificaciones del 0 al 10 de rango y calcula promedio" +
                "para ver si un alumno aprueba o no una materia");

        System.out.println("Ingrese la nota 1");
        float nota1 = objScan.nextFloat();
        System.out.println("Ingrese la nota 2");
        float nota2 = objScan.nextFloat();
        System.out.println("Ingrese la nota 3");
        float nota3 = objScan.nextFloat();

        float promedioNota = (nota1 + nota2 + nota3)/3;

        if(promedioNota >=6){
            System.out.println("El estudiante aprueba la materia con promedio " + promedioNota);
        }else {
            System.out.println("El estudiante NO aprueba ya que sacó " + promedioNota);
        }*/

        //Ejercicio 9

        /*System.out.println("Este programa le calcula si un año es bisiesto");

        System.out.println("Ingrese el año:");
        int anio = objScan.nextInt();

        if((anio % 4) == 0 && (anio % 100) != 0){
            System.out.println("El año es bisiesto");
        }else if((anio % 100) == 0 && (anio % 400) == 0){
            System.out.println("El año es bisiesto");
        }else if(anio % 4 == 0){
            System.out.println("Es un año bisiesto");
        }else{
            System.out.println("No es un año bisiesto");
        }*/

        //Ejercicio 10

        /*System.out.println("Este programa le calcula cuanto dejar de propina de acuerdo al total de la cuenta y el" +
                " porcentaje de propina que se quiere dejar");

        System.out.println("Ingrese el total de la cuenta");
        float cuenta = objScan.nextFloat();
        System.out.println("Ingrese el porcentaje de propina que quiere dar(rango de 0% a 100%)");
        float porcentaje = objScan.nextFloat();

        System.out.println("La propina a dar es " + (cuenta*(porcentaje/100)));*/

        //ejercicio 11 Ordenando tres numeros
        /*System.out.println("Este programa pide 3 numeros y los organiza de menor a mayor");

        System.out.println("ingrese numero 1");
        float num1 = objScan.nextFloat();
        System.out.println("ingrese numero 2");
        float num2 = objScan.nextFloat();
        System.out.println("ingrese numero 3");
        float num3 = objScan.nextFloat();
        float maxNum, minNum, medNum;
        if((num1 >num2) && (num1 > num3)){
            maxNum = num1;
            if(num2 > num3){
                minNum = num3;
                medNum = num2;
            }else{
                minNum = num2;
                medNum = num3;
            }
        }else if((num2 >num1) && (num2 > num3)){
            maxNum = num2;
            if(num1 > num3){
                minNum = num3;
                medNum = num1;
            }else{
                minNum = num1;
                medNum = num3;
            }
        }else{
            maxNum = num3;
            if(num1 > num2){
                minNum = num2;
                medNum= num1;
            }else{
                minNum = num1;
                medNum = num2;
            }
        }

        System.out.println("El numero maximo es: " + maxNum + " el numero medio " + medNum + " y el menor " + minNum);*/

        //ejercicio 12 Generador de horoscopo

        /*System.out.println("Este programa le dice que signo de acuerdo al mes y al día");

        System.out.println("Ingrese el mes en que nació");
        int mes = objScan.nextInt();
        System.out.println("Ingrese el día en que nació");
        int dia = objScan.nextInt();

        switch (mes){
            case 1:
                //Enero
                if(dia <=19){
                    System.out.println("Eres Capricornio");
                }else{
                    System.out.println("Eres Acuario");
                }
                break;
            case 2:
                //Febrero
                if(dia <=18){
                    System.out.println("Eres Acuario");
                }else{
                    System.out.println("Eres Piscis");
                }
                break;
            case 3:
                //Marzo
                if(dia <= 20){
                    System.out.println("Eres Piscis");
                }else{
                    System.out.println("Eres Aries");
                }
                break;
            case 4:
                //Abril
                if(dia <=19){
                    System.out.println("Eres Aries");
                }else{
                    System.out.println("Eres Tauro");
                }
                break;
            case 5:
                //Mayo
               if(dia <= 20){
                   System.out.println("Eres Tauro");
               }else{
                   System.out.println("Eres Géminis");
               }
                break;
            case 6:
                //Junio
                if(dia <=20){
                    System.out.println("Eres Géminis");
                }else{
                    System.out.println("Eres Cancer");
                }
                break;
            case 7:
                //Julio
                if(dia <=22){
                    System.out.println("Eres Cancer");
                }else{
                    System.out.println("Eres Leo");
                }
                break;
            case 8:
                //Agosto
                if(dia <= 22){
                    System.out.println("Eres Leo");
                }else{
                    System.out.println("Eres Virgo");
                }
                break;
            case 9:
                //Septiembre
                if(dia <= 22){
                    System.out.println("Eres Virgo");
                }else{
                    System.out.println("Eres Libra");
                }
                break;
            case 10:
                //Octubre
                if(dia <=22){
                    System.out.println("Eres Libra");
                }else{
                    System.out.println("Eres Escorpio");
                }
                break;
            case 11:
                //Noviembre
                if(dia <= 21){
                    System.out.println("Eres Escorpio");
                }else{
                    System.out.println("Eres Sagitario");
                }
                break;
            case 12:
                //Diciembre
                if(dia <= 21 ){
                    System.out.println("Eres Sagitario");
                }else{
                    System.out.println("Eres Capricornio");
                }
                break;
            default:
                System.out.println("Mes incorrecto");
                break;

        }*/

        //ejercicio 13 Calculadora de tarifa de taxi

        /*System.out.println("Este programa le calcula cuanta plata debe pagar en el taxi");

        System.out.println("Ingrese el valor de la mínima o la tarifa base");
        float minima = objScan.nextFloat();
        System.out.println("Ingrese valor tarifa adicional por kilometro");
        float kilometro = objScan.nextFloat();
        System.out.println("Ingrese la distancia total recorrida en kilometros");
        float kilometros = objScan.nextFloat();

        System.out.println("La tarifa a pagar es: " + (minima+(kilometro*kilometros)));*/

        //Ejercicio 14 Convertidor de temperatura

        /*System.out.println("Este programa convierte de Centigrados a Fahrenheit o viceversa");

        System.out.println("1 -> C a F ");
        System.out.println("2 -> F a C ");
        int opcion = objScan.nextInt();
        float result;
        switch (opcion){
            case 1:
                System.out.println("Ingrese temperatura en Centigrados");
                float centigrados = objScan.nextFloat();
                result = (centigrados*9)/5+32;
                System.out.println("La temperatura en Fahrenheit es: " + result);
                break;
            case 2:
                System.out.println("Ingrese temperatura en Fahrenheit");
                float fahren = objScan.nextFloat();
                result = (fahren-32)*((float) 5 /9);
                System.out.println("La temperatura en Fahrenheit es: " + result);
                break;

            default:
                System.out.println("opcion no valida");*/

        //Ejercicio 15 Sistema de Calificaciones

        System.out.println("Programa de calificaciones");
        System.out.println("Ingrese la calificacion, recuerde que es de (0-100)");
        float calificacion = objScan.nextFloat();

        if(calificacion >= 90 && calificacion <= 100){
            System.out.println("Su calificacion es A");
        }else if(calificacion >= 80 && calificacion <= 89){
            System.out.println("Su calificacion es B");
        }else if(calificacion >= 70 && calificacion <= 79){
            System.out.println("Su calificacion es C");
        }else if(calificacion >= 60 && calificacion <= 69){
            System.out.println("Su calificacion es D");
        }else if(calificacion >= 50 && calificacion <= 59){
            System.out.println("Su calificacion es F");
        }else{
        System.out.println("Pongase las pilas, calificacion baja");
        
        }

    }




    static int menu(Scanner opcion) {
        System.out.println("--------------Menu---------------");
        System.out.println("| 1. Sumar                      |");
        System.out.println("| 2. Restar                     |");
        System.out.println("| 3. Multiplicar                |");
        System.out.println("| 4. Dividir                    |");
        System.out.println("| 5. Salir                      |");
        System.out.println("|-------------------------------|");

        System.out.println("Ingrese la opcion deseada: ");
        int opc = opcion.nextInt();
        return opc;
    }
}