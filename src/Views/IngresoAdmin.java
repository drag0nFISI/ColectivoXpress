package Views;

import Models.Admin;

import java.util.Scanner;

public class IngresoAdmin {

    Scanner sc = new Scanner(System.in);

    public static void inicio(){
        IngresoAdmin ia = new IngresoAdmin();
        ia.login_admin();
    }

    public void login_admin(){
        boolean stay = true;

        while(stay){

            Consola.cls();
            Consola.dibujar_rectangulo(42, 9, 20, 5);
            Consola.gotoxy(34, 5);
            System.out.print(" LOGIN DE ADMIN ");
            Consola.gotoxy(22, 7);
            System.out.print("Si desea salir deje ambos campos vacios");
            Consola.gotoxy(22, 10);
            System.out.print("Ingrese su Codigo: ");
            Consola.gotoxy(22, 12);
            System.out.print("Ingrese su Contrasena: ");

            Consola.gotoxy(41, 10);
            String codigo = sc.nextLine();
            Consola.gotoxy(45, 12);
            String contrasena = sc.nextLine();

            if(codigo.isEmpty() && contrasena.isEmpty()){
                return;
            }
            Admin admin = Admin.login_admin(codigo,contrasena);
            if(admin != null){
                stay = false;
                MenuAdmin.main(admin);
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
