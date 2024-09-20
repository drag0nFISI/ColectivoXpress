package Views;

import Models.Admin;
import Models.Conductor;
import Services.ConductorService;

import java.util.HashMap;
import java.util.Scanner;


public class MenuAdmin {

    static Scanner sc = new Scanner(System.in);
    ConductorService cs = new ConductorService();

    public static void main(Admin admin) {
        MenuAdmin ma = new MenuAdmin();
        boolean stay = true;
        while (stay){

            System.out.println("-------------- MENU ADMIN --------------");
            System.out.println("Que desea hacer...");
            System.out.println("1. Agregar un conductor");
            System.out.println("2. Eliminar conductor");
            System.out.println("3. Volver");

            int aux = sc.nextInt();
            sc.nextLine();
            switch (aux){
                case 1:
                    ma.agregar_conductor();
                    break;
                case 2:
                    ma.eliminar_conductor();
                    break;
                case 3:
                    stay = false;
                    break;
                default:
                    System.out.println("Escoja una opcion valida...");
            }
        }
    }

    public void agregar_conductor(){
        HashMap<String, String> datos = new HashMap<>();
        System.out.println("------------ AGREGAR UN CONDUCTOR ------------");
        System.out.println("Ingrese los nombres: ");
        datos.put("nombres", sc.nextLine());
        System.out.println("Ingrese los apellidos: ");
        datos.put("apellidos", sc.nextLine());
        System.out.println("Ingrese el telefono: ");
        datos.put("telefono", sc.nextLine());
        System.out.println("Ingrese el dni: ");
        datos.put("dni", sc.nextLine());

        Conductor conductor = cs.conductor_por_dni(datos.get("dni"));
        if(conductor!=null){
            System.out.println("Este dni ya esta registrado");
            return;
        }

        System.out.println("Ingrese la fecha de nacimiento: ");
        datos.put("fecha_nacimiento", sc.nextLine());
        System.out.println("Ingrese el distrito de residencia: ");
        datos.put("distrito", sc.nextLine());
        System.out.println("Ingrese la contrasena");
        datos.put("contrasena", sc.nextLine());

        boolean exito = cs.registrar_conductor(datos);
        if(exito){
            System.out.println("SE REGISTRO CORRECTAMENTE AL CONDUCTOR");
        } else {
            System.out.println("OCURRIO UN ERROR");
        }
    }

    public void eliminar_conductor(){
        System.out.println("------------ ELIMINAR CONDUCTOR -----------");
        System.out.println("Ingrese el dni del conductor: ");
        String dni = sc.nextLine();

        boolean exito = cs.eliminar_conductor(dni);
        if(exito){
            System.out.println("CONDUCTOR ELIMINADO");
        } else {
            System.out.println("OCURRIO UN ERROR");
        }
    }
}
