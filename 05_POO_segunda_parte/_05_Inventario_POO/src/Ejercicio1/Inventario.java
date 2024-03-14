import java.util.ArrayList;

public class Inventario {

    private ArrayList<ProductoEspecifico> listaProductos;

    public Inventario(){

        this.listaProductos = new ArrayList<>();
    }

    public ArrayList<ProductoEspecifico> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<ProductoEspecifico> listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void agregarProducto(ProductoEspecifico producto){
        this.listaProductos.add(producto);
    }

    public boolean eliminarProducto(int id){
        return this.listaProductos.removeIf(producto -> producto.getId() == id);
    }

    public void listarProductos(){
        for(ProductoEspecifico iterador : this.listaProductos){
            System.out.println(iterador.toString());
        }
    }

    public ProductoEspecifico buscarPorNombre(String nombreBuscar){
        for(ProductoEspecifico iterador: this.listaProductos){

            //Lo pone todo en minuscula y compara
            if(iterador.getNombre().equalsIgnoreCase(nombreBuscar)){
                return iterador;
            }
        }

        //Si no encontró coincidenca
        return null;
    }

    public String buscarPorCategoria(String categoriaBuscar){
        String busqueda = "";
        for(ProductoEspecifico iterador: this.listaProductos){

            //Lo pone todo en minuscula y compara
            if(iterador.getCategoria().equalsIgnoreCase(categoriaBuscar)){
                busqueda += iterador.toString() + "\n";
            }
        }

        return busqueda;


    }
}
