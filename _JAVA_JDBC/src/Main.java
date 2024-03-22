import controller.AutorController;
import controller.LibroController;
import database.ConfigDB;
import entity.Libro;
import model.AutorModel;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {

        String option = "";
        String option2 = "";

        do{
            option = JOptionPane.showInputDialog("""
                    Menu opciones libreria
                    1. Crear Registro
                    2. Consultar
                    3. Actualizar
                    4. Eliminar
                    5. Consultas avanzadas
                    6. Salir
                    """);

            if (!option.equals("6")){
                if(!option.equals("5")) {
                    option2 = JOptionPane.showInputDialog("""
                            Que deseas Modificar:
                            1. Autor
                            2. Libro
                            """);
                }
            }

            switch (option){
                case "1":
                    //Crear registro
                    if(option2.equals("1")){
                        AutorController.create();
                    }else{
                        LibroController.create();
                    }
                    break;
                case "2":
                    //Consultar
                    String option3 = "";
                    if(option2.equals("1")) {
                        option3 = JOptionPane.showInputDialog("""
                                1. Listar todos los autores
                                2. Buscar autor por ID
                                Ingrese opción:
                                """);

                        switch (option3){
                            case "1":
                                //Listar todos los autores
                                AutorController.getAll();
                                break;
                            case "2":
                                //Buscar autor por ID
                                AutorController.findById();
                                break;
                            default:
                                JOptionPane.showMessageDialog(null,"Opcion incorrecta");
                                break;
                        }

                    }else{
                        option3 = JOptionPane.showInputDialog("""
                                1. Mostrar todo los libros
                                2. Buscar libro por ID
                                3. Buscar libro por titulo
                                4. Buscar libro por autor
                                Ingrese la opción:
                                """);

                        switch(option3){
                            case "1":
                                //Mostrar todos los libros
                                LibroController.getAll();
                                break;
                            case "2":
                                //Buscar libro por ID
                                LibroController.findById();
                                break;
                            case "3":
                                //Buscar libro por titulo
                                System.out.println("Entro a titulo");
                                LibroController.findByTitle();
                                break;
                            case "4":
                                //Buscar libro por autor
                                LibroController.findByAutor();
                                break;
                            default:
                                JOptionPane.showMessageDialog(null,"Opcion incorrecta");
                                break;

                        }

                    }
                    break;
                case "3":
                    //Actualizar
                    if(option2.equals("1")){
                        AutorController.update();
                    }else{
                        LibroController.update();
                    }
                    break;
                case "4":
                    //Eliminar
                    if(option2.equals("1")){
                        AutorController.delete();
                    }else{
                        LibroController.delete();
                    }
                    break;
                case "5":
                    //Consulta avanzadas
                    String buscador = JOptionPane.showInputDialog("""
                            1. Buscar por ID de autor muestre sus libros
                            2. Buscar toda la información por Id de autor de ambas tablas
                            3. Mostrar toda la información de todas las tablas
                            """);
                    switch (buscador){
                        case "1":
                            //Buscar por ID de autor
                            LibroController.findByIdAutor();
                            break;
                        case "2":
                            //Desplegar toda la información
                            LibroController.findAlldata();
                            break;
                        case "3":
                            //Mostrar toda la información de todas las tablas
                            LibroController.showAll();
                            break;
                        default:
                            JOptionPane.showMessageDialog(null,"opcion no valida");
                    }
                    break;
                case "6":
                    //salir
                    JOptionPane.showMessageDialog(null,"Fin del programa, vuelva pronto");
                    break;
                default:
                    JOptionPane.showMessageDialog(null,"Opcion no valida");
            }
        }while(!option.equals("6"));
    }
}