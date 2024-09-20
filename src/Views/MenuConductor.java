package Views;

import Models.Conductor;
import Services.ConductorService;
import java.util.Scanner;

import java.util.HashMap;

public class MenuConductor {

    static ConductorService cs = new ConductorService();
    static Scanner sc = new Scanner(System.in);

    public static void main(Conductor conductor) {
        MenuConductor mc = new MenuConductor();

        while(true){

            System.out.println("------------ MENU DE CONDUCTOR ------------");
            System.out.println("Elija la opcion que desea: ");
            System.out.println("1. Ver mi perfil");
            System.out.println("2. Editar mi perfil");
            int aux = sc.nextInt();
            sc.nextLine();
            switch (aux){
                case 1:
                    mc.ver_perfil(conductor);
                    break;
                case 2:
                    mc.editar_perfil(conductor);
                    break;
                default:
                    System.out.println("Escoja una opcion valida...");
            }
        }
    }

    public void ver_perfil(Conductor conductor){
        cs.mostrar_perfil(conductor);
    }

    public void editar_perfil(Conductor conductor){
        HashMap<String, String> datos = new HashMap<>();
        System.out.println("\n----------- MODIFICAR PERFIL -----------");
        System.out.println("Si no quiere modificar algun apartado solo dejelo en blando y de ENTER");
        System.out.println("Nuevos Nombres: ");
        datos.put("nombres", sc.nextLine());
        System.out.println("Nuevos Apellidos: ");
        datos.put("apellidos", sc.nextLine());
        System.out.println("Nuevo telefono: ");
        datos.put("telefono", sc.nextLine());
        System.out.println("Nuevo distrito: ");
        datos.put("distrito", sc.nextLine());

        boolean exito = cs.editar_perfil(conductor, datos);
        if(exito){
            System.out.println("Cambios hechos correctamente...");
        } else{
            System.out.println("OCURRIO UN ERROR");
        }
    }
}
