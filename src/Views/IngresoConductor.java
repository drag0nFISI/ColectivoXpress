package Views;

import Models.Conductor;

import java.util.Scanner;

public class IngresoConductor {

    static Scanner sc = new Scanner(System.in);

    public static void inicio(){
        IngresoConductor ic = new IngresoConductor();
        ic.login_conductor();
    }

    public void login_conductor(){
        boolean stay = true;
        while (stay) {

            Consola.cls();
            Consola.dibujar_rectangulo(42, 9, 20, 5);
            Consola.gotoxy(32, 5);
            System.out.print(" LOGIN DE CONDUCTOR ");
            Consola.gotoxy(22, 7);
            System.out.print("Si desea salir deje ambos campos vacios");
            Consola.gotoxy(22, 10);
            System.out.print("Ingrese su DNI: ");
            Consola.gotoxy(22, 12);
            System.out.print("Ingrese su Contrasena: ");

            Consola.gotoxy(38, 10);
            String dni = sc.nextLine();
            Consola.gotoxy(45, 12);
            String contrasena = sc.nextLine();

            if(dni.isEmpty() && contrasena.isEmpty()) {
                return;
            }
            Conductor conductor = Conductor.login_conductor(dni,contrasena);
            if(conductor !=null){
                stay = false;
                MenuConductor.main(conductor);
            } else {
                Consola.gotoxy(33, 16);
                System.out.print("Error al logearse");
                Consola.gotoxy(23, 17);
                System.out.print("Presione ENTER para volver a intentarlo");
                sc.nextLine();
            }
        }
    }
}
