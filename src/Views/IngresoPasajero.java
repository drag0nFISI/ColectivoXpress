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

        Consola.cls();
        sc.nextLine();
        Consola.dibujar_rectangulo(36, 9, 20, 5);
        Consola.gotoxy(28, 5);
        System.out.print(" REGISTRO DE PASAJERO ");
        Consola.gotoxy(22, 7);
        System.out.print("DNI: ");
        Consola.gotoxy(22, 8);
        System.out.print("Nombre: ");
        Consola.gotoxy(22, 9);
        System.out.print("Apellido: ");
        Consola.gotoxy(22, 10);
        System.out.print("Correo: ");
        Consola.gotoxy(22, 11);
        System.out.print("Contrasena: ");
        Consola.gotoxy(22, 12);
        System.out.print("Fecha de nacimiento: ");
        Consola.gotoxy(22, 13);
        System.out.print("Distrito: ");

        Consola.gotoxy(27, 7);
        datos.put("dni", sc.nextLine());

        Pasajero pasajero_existente = Pasajero.cliente_por_dni(datos.get("dni"));
        if(pasajero_existente !=null){
            Consola.gotoxy(29, 17);
            System.out.print("DNI ya registrado...");
            Consola.gotoxy(24, 18);
            System.out.print("Presione ENTER para continuar");
            sc.nextLine();
            return;
        }

        Consola.gotoxy(30, 8);
        datos.put("nombres", sc.nextLine());
        Consola.gotoxy(32, 9);
        datos.put("apellidos", sc.nextLine());
        Consola.gotoxy(30, 10);
        datos.put("telefono", sc.nextLine());
        Consola.gotoxy(34, 11);
        datos.put("contrasena", sc.nextLine());
        Consola.gotoxy(43, 12);
        datos.put("fecha_nacimiento", sc.nextLine());
        Consola.gotoxy(32, 13);
        datos.put("distrito", sc.nextLine());

        boolean exito = Pasajero.registro_cliente(datos);

        if(exito){
            Consola.gotoxy(20, 17);
            System.out.print("Se ha registrado el usuario con exito");
        } else {
            Consola.gotoxy(21, 17);
            System.out.print("No se ha podido registrar al usuario");
        }
        Consola.gotoxy(24, 18);
        System.out.print("Presione ENTER para continuar");
        sc.nextLine();
    }

    public void login_cliente(){

        Consola.cls();
        sc.nextLine();
        Consola.dibujar_rectangulo(42, 7, 20, 5);
        Consola.gotoxy(32, 5);
        System.out.print(" LOGIN DE PASAJERO ");
        Consola.gotoxy(22, 7);
        System.out.print("Si desea salir deje ambos campos vacios");
        Consola.gotoxy(22, 9);
        System.out.print("Ingrese su DNI: ");
        Consola.gotoxy(22, 11);
        System.out.print("Ingrese su Contrasena: ");

        Consola.gotoxy(38, 9);
        String dni = sc.nextLine();
        Consola.gotoxy(45, 11);
        String contrasena = sc.nextLine();

        if(dni.isEmpty() && contrasena.isEmpty()) {
            return;
        }

        Pasajero pasajero = Pasajero.login_cliente(dni,contrasena);
        if(pasajero !=null){
            MenuPasajero.main(pasajero);
        } else {
            Consola.gotoxy(27, 15);
            System.out.print("No se ha podido iniciar sesión");
            Consola.gotoxy(27, 16);
            System.out.print("Presione ENTER para continuar");
            sc.nextLine();
        }
    }
}
