import java.util.Scanner;

public class EmpleadoPermanente  extends Empleado{

    //constructor

    public EmpleadoPermanente(String nombre, int edad, int idEmpleado, double salario) {
        super(nombre, edad, idEmpleado, salario);
    }

    @Override
    public String toString() {
        return "EmpleadoPermanente{ " + super.toString() + " }";
    }
}
