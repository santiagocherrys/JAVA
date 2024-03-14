import java.util.ArrayList;
import java.util.Scanner;

public class GestionEmpleados {
    private ArrayList<EmpleadoPermanente> listaEmpleadoPermanente;
    private ArrayList<EmpleadoTemporal> listaEmpleadoTemporal;

    public static int permanentIndex = 1;
    public static int temporalIndex = 1;

    public GestionEmpleados(){
        this.listaEmpleadoPermanente = new ArrayList<>();
        this.listaEmpleadoTemporal = new ArrayList<>();
    }

    public void addEmpleado(boolean tipo){
        Scanner objScan = new Scanner(System.in);
        //Se piden datos
        try{
            System.out.println("Ingrese el nombre del empleado permanente");
            String nombre = objScan.nextLine();
            System.out.println("Ingrese la edad del empleado " + nombre);
            int edad = objScan.nextInt();
            System.out.println("Ingrese el salario que se gana " + nombre);
            double salario = objScan.nextDouble();
            if(tipo){
                //true Empleado permanente
                EmpleadoPermanente empleado = new EmpleadoPermanente(nombre,edad,permanentIndex,salario);
                permanentIndex++;
                this.listaEmpleadoPermanente.add(empleado);
            }else{
                //false Empleado temporal
                EmpleadoTemporal empleado = new EmpleadoTemporal(nombre,edad,temporalIndex,salario);
                temporalIndex++;
                this.listaEmpleadoTemporal.add(empleado);
            }
        }catch(Exception e){
            System.out.println("opción no válida");
        }

    }

    public void deleteEmpleado(boolean tipo){
        int id;
        Scanner objScan = new Scanner(System.in);
        if(tipo){
            //borrar empleado permanente
            if(this.listaEmpleadoPermanente.isEmpty()){
                System.out.println("no hay empleados permanentes");
            }else{
                //listar
                this.listarEmpleados(true);

                try{
                    System.out.println("ingrese el id del empleado a eliminar");
                    id = objScan.nextInt();
                    if(this.listaEmpleadoPermanente.removeIf(empleado -> empleado.getIdEmpleado() == id )){
                        System.out.println("Empleado eliminado con exito");
                    }else{
                        System.out.println("Error al eliminar");
                    }
                }catch(Exception e){
                    System.out.println("Valor invalido");
                }


            }
        }else{
            //borrar empleado temporal
            if(this.listaEmpleadoTemporal.isEmpty()){
                System.out.println("No hay empleados temporales");
            }else{
                //listar
                this.listarEmpleados(false);

                try{
                    System.out.println("ingrese el id del empleado a eliminar");
                    id = objScan.nextInt();
                    if(this.listaEmpleadoTemporal.removeIf(empleado -> empleado.getIdEmpleado() == id )){
                        System.out.println("Empleado eliminado con exito");
                    }else{
                        System.out.println("Error al eliminar");
                    }
                }catch(Exception e){
                    System.out.println("Valor invalido");
                }
            }
        }
    }

    public void listarEmpleados(boolean tipo){
        if(tipo){
            //listar permanentes
            if(this.listaEmpleadoPermanente.isEmpty()){
                System.out.println("No hay empleados permanentes");
            }else{
                for(EmpleadoPermanente iterador : this.listaEmpleadoPermanente){
                    System.out.println(iterador.toString());
                }
            }

        }else{
            //listar temporales
            if(this.listaEmpleadoTemporal.isEmpty()){
                System.out.println("No hay empleados temporales");
            }else{
                for(EmpleadoTemporal iterador: this.listaEmpleadoTemporal){
                    System.out.println(iterador.toString());
                }
            }
        }
    }
}
