package Views;

import Models.Pasajero;

import java.util.HashMap;
import java.util.Scanner;
import Services.PasajeroService;

public class IngresoPasajero {

    static Scanner sc = new Scanner(System.in);
    static PasajeroService cs = new PasajeroService();

    public static void inicio() {
        IngresoPasajero mc = new IngresoPasajero();
        int aux=0;

        boolean stay = true;

        while(stay){
            System.out.println("\n----------- INGRESO DE PASAJERO ---------");
            System.out.println("Seleccione lo que desea hacer: ");
            System.out.println("1. Registrarme");
            System.out.println("2. Login");
            System.out.println("3. Salir");
            System.out.print("Ingrese su opcion: ");
            aux = sc.nextInt();
            sc.nextLine();
            switch (aux){
                case 1:
                    mc.registrar_cliente();
                    break;
                case 2:
                    mc.login_cliente();
                    break;
                case 3:
                    stay = false;
                    break;
                default:
                    System.out.println("VALOR NO VALIDO");
                    break;
            }
        }
    }

    public void registrar_cliente(){
        HashMap<String, String> datos = new HashMap<String, String>();
        System.out.println("\n--------------BIENVENIDO AL REGISTRO DE PASAJERO------------");
        System.out.println("Ingrese su nombre: ");
        datos.put("nombres", sc.nextLine());
        System.out.println("Ingrese su apellido: ");
        datos.put("apellidos", sc.nextLine());
        System.out.println("Ingrese su gmail: ");
        datos.put("telefono", sc.nextLine());
        System.out.println("Ingrese su contrasena: ");
        datos.put("contrasena", sc.nextLine());
        System.out.println("Ingrese su DNI: ");
        datos.put("dni", sc.nextLine());

        Pasajero pasajero_existente = cs.cliente_por_dni(datos.get("dni"));
        if(pasajero_existente !=null){
            System.out.println("DNI ya registrado....");
            return;
        }
        System.out.println("Ingrese su fecha de nacimiento: ");
        datos.put("fecha_nacimiento", sc.nextLine());
        System.out.println("Ingrese su distrito: ");
        datos.put("distrito", sc.nextLine());

        boolean exito = cs.registro_cliente(datos);

        if(exito){
            System.out.println("Se ha registrado el usuario con exito");
        } else {
            System.out.println("No se ha podido registrar al usuario");
        }
        datos = null;
    }

    public void login_cliente(){
        System.out.println("\n----------------LOGIN DE PASAJERO-------------");
        System.out.print("Ingrese su DNI: ");
        String dni = sc.nextLine();
        System.out.print("\nIngrese su contrasena: ");
        String contrasena = sc.nextLine();
        Pasajero pasajero = cs.login_cliente(dni,contrasena);
        if(pasajero !=null){
            System.out.println("\nLogeado Correctamente");
            MenuPasajero.main(pasajero);
        } else {
            System.out.println("\nError al logearse");
        }
    }
}
