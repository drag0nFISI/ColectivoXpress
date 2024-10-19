package Views;

import Models.Pasajero;

import java.util.HashMap;
import java.util.Scanner;

public class IngresoPasajero {

    static Scanner sc = new Scanner(System.in);

    public static void inicio() {
        IngresoPasajero mc = new IngresoPasajero();
        int aux=0;

        boolean stay = true;

        while(stay){
            Consola.cls();

            Consola.dibujar_rectangulo(31, 7, 20, 5);
            Consola.gotoxy(26, 5);
            System.out.print(" INGRESO DE PASAJERO ");
            Consola.gotoxy(22, 7);
            System.out.print("1. Registrarme");
            Consola.gotoxy(22, 8);
            System.out.print("2. Login");
            Consola.gotoxy(22, 9);
            System.out.print("3. Salir");

            boolean permitido = false;
            while (!permitido) {
                try {
                    Consola.gotoxy(22, 11);
                    System.out.print("Ingrese su opcion: ");
                    aux = sc.nextInt();
                    permitido = true;
                } catch (Exception e) {
                    Consola.gotoxy(41, 11);
                    System.out.print("           ");
                    Consola.gotoxy(26, 14);
                    System.out.print("Opcion no valida");
                    sc.nextLine();
                }
            }

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

        Pasajero pasajero_existente = Pasajero.cliente_por_dni(datos.get("dni"));
        if(pasajero_existente !=null){
            System.out.println("DNI ya registrado....");
            return;
        }
        System.out.println("Ingrese su fecha de nacimiento: ");
        datos.put("fecha_nacimiento", sc.nextLine());
        System.out.println("Ingrese su distrito: ");
        datos.put("distrito", sc.nextLine());

        boolean exito = Pasajero.registro_cliente(datos);

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
        Pasajero pasajero = Pasajero.login_cliente(dni,contrasena);
        if(pasajero !=null){
            System.out.println("\nLogeado Correctamente");
            MenuPasajero.main(pasajero);
        } else {
            System.out.println("\nError al logearse");
        }
    }
}
