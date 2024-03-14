public class ProductoEspecifico extends Producto{

    private String categoria;
    private String marca;

    public ProductoEspecifico(int id, String nombre, double precio, String categoria, String marca) {
        super(id, nombre, precio);
        this.categoria = categoria;
        this.marca = marca;
    }

    //getter y setters

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    //toString


    @Override
    public String toString(){
        return super.toString() +
                " categoria='" + categoria + '\'' +
                ", marca='" + marca + '\'' +
                '}';
    }

    /*public void imprimirTodo(){
        System.out.println("id " + this.getId() +"\n nombre " + this.getNombre() + "\n precio " + this.getPrecio() + "\n categoria " + this.categoria + "\n marca " + this.marca);
    }*/
}
