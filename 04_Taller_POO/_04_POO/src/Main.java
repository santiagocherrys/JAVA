import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

    /*Libro objLibro = new Libro("Cien años de soledad","Gabriel Garcia", "01-05-1967",true);

        System.out.println(objLibro.getTitulo());
        objLibro.setTitulo("El rastro de tu sangre en la nieve");
        System.out.println(objLibro.getTitulo());

        System.out.println(objLibro.toString());

        objLibro.cambiarEstado();

        System.out.println(objLibro);

        //Objeto empleado
        Scanner objScaner =  new Scanner(System.in);
        int id=0;

        System.out.println("Ingrese el nombre del empleado");
        String nombre = objScaner.nextLine();

        System.out.println("Ingrese el cargo del empleado: ");
        String cargo = objScaner.nextLine();

        System.out.println("Ingrese el salario del empleado: ");
        double salario =  objScaner.nextDouble();

        id++;*/


        /*Empleado objEmpleado = new Empleado(nombre,cargo,salario,id);

        System.out.println(objEmpleado.toString());

        System.out.println("El salario es: " + objEmpleado.getSalario());

        System.out.println("Por tu buen desempleño te vamos a subir tu salario");
        objEmpleado.aumentarSalario(10);

        System.out.println("Tu nuevo salario es "+ objEmpleado.getSalario());

        objEmpleado.aumentarSalario(0.2);

        System.out.println("Tu nuevo salario es "+ objEmpleado.getSalario());*/

        //Ejercicio Cuenta Bancaria

        /*CuentaBancaria objCuenta = new CuentaBancaria("Santiago Echeverry",5000);

        System.out.println(objCuenta.toString());

        //balance
        System.out.println("su balance es: " + objCuenta.getBalance());

        //depositar dinero
        objCuenta.depositarDinero();

        //retirar dinero
        objCuenta.retirarDinero();*/

        //Ejercicio Stock

        /*Stock stock = new Stock(1,"arroz",5);

        stock.addProductoToStock();

        stock.removeProductoFromStock();
        stock.cantidadActual();*/

        //Ejercicio calificaciones

        Estudiante estudiante = new Estudiante("Santiago Echeverry");

        //añadir calificaciones

        estudiante.setCalificaciones(4.5);
        estudiante.setCalificaciones(5);
        estudiante.setCalificaciones(3.5);

        estudiante.calcularPromedio();
        estudiante.addNuevaCalificacion();
        estudiante.mostrarCalificaciones();
        estudiante.calcularPromedio();




    }
}