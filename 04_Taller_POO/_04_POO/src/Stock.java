import java.util.Scanner;

public class Stock {
    private int id;
    private String descripcion;
    private int cantidad;

    //Constructor
    public Stock(int id, String descripcion, int cantidad) {
        this.id = id;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
    }

    //Metodos setters y getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    //toString

    @Override
    public String toString() {
        return "Stock{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", cantidad=" + cantidad +
                '}';
    }

    //metodos
    //añadir productos del stock

    public void addProductoToStock(){
        Scanner objStock = new Scanner(System.in);
        try{
            System.out.println("ingrese la cantidad de " + this.descripcion + " a añadir");
            int addCantidad = objStock.nextInt();
            this.cantidad += addCantidad;
            System.out.println("La cantidad nueva de " + this.descripcion + " es " + this.cantidad);
        }catch(Exception e){
            System.out.println("opcion no válida");
        }
    }

    public void removeProductoFromStock(){
        Scanner objStock = new Scanner(System.in);
        System.out.println("La cantidad que hay de " + this.descripcion + " es: "+ this.cantidad);

        try{
            System.out.println("Ingrese la cantidad a retirar");
            int retirar = objStock.nextInt();
            if(retirar > this.cantidad){
                System.out.println("No se puede retirar una cantidad mayor al stock");
            }else{
                this.cantidad -= retirar;
                System.out.println("La nueva cantidad en stock de " + this.descripcion + " es: " + this.cantidad);
            }
        }catch(Exception e){
            System.out.println("opción no válida");
        }

    }

    public void cantidadActual(){
        System.out.println("La cantidad actual de " + this.descripcion + " es: " + this.cantidad);
    }
}
