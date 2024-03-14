import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        //instancias
        Scanner objScan = new Scanner(System.in);

        GestionCurso objGestion = new GestionCurso();

        int option = 0;
        do{
            System.out.println("""
                    MENU DE OPCIONES
                    1. Administrar Estudiantes
                    2. Administrar Cursos
                    3. Salir
                    
                    Ingrese una opcion
                    """);
            option = objScan.nextInt();

            switch(option){
                case 1:
                    //Administrar Estudiantes
                    int option3 = 0;
                    do{
                        System.out.println("""
                                MENU DE ESTUDIANTES
                                1. Agregar estudiante a un curso
                                2. Listar todos los estudiantes de un curso
                                3. Eliminar estudiante de un curso
                                4. Salir
                                
                                Ingrese su opción:
                                """);

                        option3 = objScan.nextInt();

                        switch (option3){
                            case 1:
                                //Agregar estudiante a un curso
                                objGestion.listarTodoslosCursos();
                                System.out.println("Ingresa el código dle curso donde ingresaras el nuevo estudiante");
                                String codigo = objScan.next();

                                Curso objCurso = objGestion.buscarCursoPorCodigo(codigo);

                                if(objCurso == null){
                                    System.out.println("El código ingresado no es valido");
                                }else{
                                    objCurso.agregarEstudiante(objScan);
                                }
                                break;
                            case 2:
                                //Listar todos los estudiantes de un curso
                                objGestion.listarTodoslosCursos();
                                System.out.println("Ingresa el código dle curso donde ingresaras el nuevo estudiante");
                                codigo = objScan.next();

                                objCurso = objGestion.buscarCursoPorCodigo(codigo);

                                if(objCurso == null){
                                    System.out.println("El código ingresado no es valido");
                                }else{
                                    objCurso.listarEstudiantes();
                                }

                                break;
                            case 3:
                                //Eliminar estudiante de un curso
                                //Solucion profe

                                objGestion.listarTodoslosCursos();

                                System.out.println("Ingresa el código del curso donde deseas eliminar el estudiante");
                                codigo = objScan.next();

                                Curso objcurso = objGestion.buscarCursoPorCodigo(codigo);

                                if(objcurso == null){
                                    System.out.println("El código ingresado no coincide con ningún curso");
                                }else{
                                    objcurso.eliminarEstudiante(objScan);
                                }






                                //Solucion mia
                                /*System.out.println("Estos son los cursos");
                                objGestion.listarTodoslosCursos();
                                System.out.println("ingrese el código del curso");
                                String idCurso = objScan.next();
                                //Se recorre los cursos para encontrar el indicado
                                for(Curso iterador : objGestion.getListaCursos() ){
                                    if(iterador.getCodigo().equalsIgnoreCase(idCurso)){
                                        iterador.eliminarEstudiante(objScan);
                                    }
                                }*/
                                break;
                        }


                    }while(option3 !=4);

                    break;
                case 2:
                    //Administrar Cursos

                    int option2 = 0;

                    do{
                        System.out.println("""
                                MENU de CURSOS
                                1. Agregar curso
                                2. Listar cursos.
                                3. Buscar por código
                                4. Salir
                                
                                Ingrese una opcion
                                """);
                        option2 = objScan.nextInt();

                        switch(option2){
                            case 1:
                                //Agregar curso
                                objGestion.agregarCurso(objScan);
                                break;
                            case 2:
                                //Listar cursos
                                objGestion.listarTodoslosCursos();
                                break;
                            case 3:
                                //Buscar por código
                                System.out.println("Ingresa el código del curso");
                                String codigo = objScan.next();

                                Curso objCurso = objGestion.buscarCursoPorCodigo(codigo);
                                if(objCurso == null){
                                    System.out.println("No existe ningun curso con este código");
                                }else{
                                    System.out.println(objCurso.toString());

                                }
                                break;
                            case 4:
                                //Salir
                                break;
                        }
                    }while(option2 !=4);
                    break;
                case 3:
                    break;

            }

        }while(option != 3);




    }
}
