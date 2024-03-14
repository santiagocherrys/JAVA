import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        GestionEmpleados objGestionE = new GestionEmpleados();
        Scanner objScan = new Scanner(System.in);
        String opcion = "";
        int option = 0;

        do{
            System.out.println("""
                    MENU
                    1. Añadir empleado
                    2. Eliminar empleado
                    3. Mostrar empleado
                    4. Salir
                    Elegir opcion:
                    """);
                opcion = objScan.next();

                switch(opcion){
                    case "1":

                        //Añadir empleado
                        try{
                            System.out.println("""
                                1. Añadir Empleado permanente
                                2. Añadir Empleado Temporal
                                """);
                            option = objScan.nextInt();
                            if(option == 1){
                                //Add empleado permanente
                                objGestionE.addEmpleado(true);
                            }else if(option ==2){
                                //Add empleado temporal
                                objGestionE.addEmpleado(false);
                            }else{
                                System.out.println("Opcion no válida");
                            }
                        }catch(Exception e){
                            System.out.println("Valor inválido");
                        }

                        break;
                    case "2":
                        //Eliminar empleado
                        try{
                            System.out.println("""
                                    1. Eliminar Empleado Permanente
                                    2. Eliminar Empleado Temporal
                                    """);
                            option = objScan.nextInt();
                            if(option == 1){
                                //Eliminar empleado permanente
                                objGestionE.deleteEmpleado(true);
                            }else if(option ==2){
                                //Eliminar empleado temporal
                                objGestionE.deleteEmpleado(false);
                            }else{
                                System.out.println("Opcion no válida");
                            }


                        }catch(Exception e){
                            System.out.println("Opcion no valida");
                        }
                        break;
                    case "3":
                        //Mostrar empleados
                        try{
                            System.out.println("""
                                    1. Mostrar Empleado Permanente
                                    2. Mostrar Empleado Temporal
                                    """);
                            option = objScan.nextInt();
                            if(option == 1){
                                //Eliminar empleado permanente
                                objGestionE.listarEmpleados(true);
                            }else if(option ==2){
                                //Eliminar empleado temporal
                                objGestionE.listarEmpleados(false);
                            }else{
                                System.out.println("Opcion no válida");
                            }


                        }catch(Exception e){
                            System.out.println("Opcion no valida");
                        }
                        break;
                    case "4":
                        //Salir
                        System.out.println("Fin del programa, buen día");
                        break;
                    default:
                        System.out.println("opcion no valida");
                }

        }while(!opcion.equals("4"));




    }
}