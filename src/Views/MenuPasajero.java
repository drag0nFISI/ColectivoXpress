package Views;
import java.util.HashMap;
import java.util.Scanner;

import Services.PasajeroService;
import Models.Pasajero;

public class MenuPasajero {

    static Scanner sc = new Scanner(System.in);
    static PasajeroService cs = new PasajeroService();

    public static void main(Pasajero pasajero) {
        MenuPasajero mp = new MenuPasajero();

        while(true){

            System.out.println("------------ MENU DE PASAJERO ------------");
            System.out.println("Elija la opcion que desea: ");
            System.out.println("1. Ver mi perfil");
            System.out.println("2. Editar mi perfil");
            int aux = sc.nextInt();
            sc.nextLine();
            switch (aux){
                case 1:
                    mp.ver_perfil(pasajero);
                    break;
                case 2:
                    mp.editar_perfil(pasajero);
                    break;
                default:
                    System.out.println("Escoja una opcion valida...");
            }
        }
    }

    public void ver_perfil(Pasajero pasajero){
        cs.mostrar_perfil(pasajero);
    }

    public void editar_perfil(Pasajero pasajero){
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

        boolean exito = cs.editar_perfil(pasajero, datos);
        if(exito){
            System.out.println("Cambios hechos satisfactoriamente...");
        } else {
            System.out.println("OCURRIO UN ERROR");
        }
    }

}
