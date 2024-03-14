import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Estudiante {
    private String nombre;
    private ArrayList<Double> calificaciones;

    //Constructor
    public Estudiante(String nombre) {
        this.nombre = nombre;
        this.calificaciones = new ArrayList<>();
    }

    //seters and getters
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    public ArrayList<Double> getCalificaciones() {
        return calificaciones;
    }

    public void setCalificaciones(double calificacion) {
        this.calificaciones.add(calificacion);
    }

    public void calcularPromedio(){

        double promedio = 0;
        System.out.println("CALIFICACIONES");
        for(int i = 0; i < this.calificaciones.size();i++){
            promedio += this.calificaciones.get(i);
            System.out.print(this.calificaciones.get(i) + " ");
        }
        promedio /= this.calificaciones.size();


        System.out.println("\nEl promedio de las calificiones es: " + promedio);

    }

    public void addNuevaCalificacion(){
        Scanner leer = new Scanner(System.in);
        try{
            System.out.println("ingrese nota nueva\n");
            float nota = leer.nextFloat();

            this.calificaciones.add((double) nota);

        }catch(Exception e){
            System.out.println("valor invalido" + e);
        }
    }

    public void mostrarCalificaciones(){
        for(int i = 0; i < this.calificaciones.size();i++){
            System.out.print(this.calificaciones.get(i) + " ");
        }
    }
}
