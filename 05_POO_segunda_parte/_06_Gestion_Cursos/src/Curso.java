import java.util.ArrayList;
import java.util.Scanner;

public class Curso {

    private String codigo;
    private String nombre;
    private ArrayList<Estudiante> listaEstudiantes;

    //El static no hay que instanciar la clase para poder acceder al atributo o método, es como una variable global
    private static  int  index = 1;

    public Curso(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.listaEstudiantes = new ArrayList<>();
    }

    public void agregarEstudiante(Scanner objScan){
        //limpia el buffer
        objScan.nextLine();

        System.out.println("Ingrese nombre del estudiante");
        String nombre = objScan.next();
        System.out.println("Ingrese email del estudiante");
        String email = objScan.next();


        Estudiante objEstudiante = new Estudiante(index,nombre,email);
        index++;

        this.listaEstudiantes.add(objEstudiante);
        System.out.println("Estudiante agregado correctamente");
    }

    public void listarEstudiantes(){
        System.out.println("Lista de estudiantes del Curso" .concat(this.nombre));

        if(this.listaEstudiantes.isEmpty()){
            System.out.println("No hay  estudiantes en el curso" .concat(this.nombre));
        }else{
            for(Estudiante temporal : this.listaEstudiantes){
                System.out.println(temporal.toString());
            }
        }
    }

    public void eliminarEstudiante(Scanner objScan){
        //Se lista estudiantes
        this.listarEstudiantes();

        System.out.println("Ingrese el id del estudiante a eliminar");
        int idEliminar = objScan.nextInt();

        //Eliminamos el estudiante si su ID coincide con el id que se desea eliminar
        boolean eliminado = this.listaEstudiantes.removeIf(estudiante -> estudiante.getId() == idEliminar);

        System.out.println(eliminado
        ?"Estudiante eliminado correctamente"
        :"No se pudo eliminar el estudiante");

    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public ArrayList<Estudiante> getListaEstudiantes() {
        return listaEstudiantes;
    }

    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiantes) {
        this.listaEstudiantes = listaEstudiantes;
    }

    @Override
    public String toString() {
        return "Curso{" +
                "codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", listaEstudiantes=" + listaEstudiantes +
                '}';
    }
}
