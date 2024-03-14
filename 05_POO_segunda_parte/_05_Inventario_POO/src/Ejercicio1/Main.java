import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        /*1. Agregar
        * 2. Eliminar
        * 3. Buscar por nombre
        * 4. Buscar por categoria
        * 5. Listar inventario
        * 6. Salir*/

        String option = "";
        int index = 0;
        //Se crea el objeto inventario
        Inventario objInventario = new Inventario();
        do{
            option = JOptionPane.showInputDialog(null,"Menu:\n" +
                    "1. Agregar\n" +
                    "2. Eliminar\n" +
                    "3. Buscar por nombre\n " +
                    "4. Buscar por categoria\n" +
                    "5. Listar inventario\n" +
                    "6. Salir\n\n" +
                    "Ingresa opcion:");

                    switch(option){
                        case "1":
                            //Agregar
                            try{

                                //Pedir datos
                                String nombreProd = JOptionPane.showInputDialog(null,"Ingrese el nombre del producto");
                                double precio = Double.parseDouble(JOptionPane.showInputDialog(null,"ingrese el precio de " +nombreProd));
                                String categoria = JOptionPane.showInputDialog(null,"ingrese el nombre de la categoria");
                                String marca = JOptionPane.showInputDialog(null,"Ingrese la marca del producto " + nombreProd);

                                //Añadir productos
                                ProductoEspecifico objProducto = new ProductoEspecifico(index, nombreProd, precio, categoria, marca);
                                objInventario.agregarProducto(objProducto);
                                index++;
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null, "dato erroneo");
                            }


                            break;
                        case "2":
                            //Eliminar

                            try{
                                //listar los elementos
                                int i =0;
                                int opcion;
                                String lista = "Lista de producto:\n";
                                for(ProductoEspecifico producto : objInventario.getListaProductos()){
                                    i++;
                                    lista += i + " "+ producto.getNombre() + "\n";
                                }

                                opcion = Integer.parseInt(JOptionPane.showInputDialog(null, lista + "Escoja el indice del producto a eliminar"));

                                if(opcion > i){
                                    JOptionPane.showMessageDialog(null,"Se ha pasado del rango");
                                }else{
                                    objInventario.eliminarProducto((opcion-1));
                                }
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null,"dato invalido");
                            }


                            break;
                        case "3":
                            //Buscar por nombre;
                            String nombreBuscar = "";
                            nombreBuscar = JOptionPane.showInputDialog(null,"Ingrese el nombre a buscar en el inventario");
                            ProductoEspecifico busqueda = objInventario.buscarPorNombre(nombreBuscar);
                            if(busqueda == null){
                                JOptionPane.showMessageDialog(null,"El producto no está en inventario");
                            }else{
                                JOptionPane.showMessageDialog(null, "Resultado de la busqueda \n " + busqueda.toString());
                            }
                            break;
                        case "4":
                            //Buscar por categoria
                            String categoriaBuscar = "";
                            String resultado = "";
                            categoriaBuscar = JOptionPane.showInputDialog(null,"Ingrese la categoria a buscar en el inventario");
                            resultado = objInventario.buscarPorCategoria(categoriaBuscar);
                            if(resultado.isEmpty()){
                                JOptionPane.showMessageDialog(null,"El producto no está en inventario");
                            }else{
                                JOptionPane.showMessageDialog(null, "Resultado de la busqueda \n " + resultado);
                            }
                            break;
                        case "5":
                            //Listar inventario
                            String lista = "Lista de inventario:\n";
                            for(ProductoEspecifico producto : objInventario.getListaProductos()){
                                lista += producto.toString() + "\n";
                            }

                            JOptionPane.showMessageDialog(null, lista );

                            break;
                        case "6":
                            //Salir
                            JOptionPane.showMessageDialog(null, "Fin del programa");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Opcion no valida");
                            break;

                    }

        }while(!option.equals("6"));



    }
}