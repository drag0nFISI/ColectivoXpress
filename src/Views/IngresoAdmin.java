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

            System.out.println("\n----------------LOGIN DE ADMIN-------------");
            System.out.println("PARA VOLVER ATRAS DEJE VACIOS LOS CAMPOS DE CODIGO Y CONTRASENA");
            System.out.print("Ingrese su codigo: ");
            String codigo = sc.nextLine();
            System.out.print("\nIngrese su contrasena: ");
            String contrasena = sc.nextLine();

            if(codigo.equals("") && contrasena.equals("")){
                return;
            }
            Admin admin = Admin.login_admin(codigo,contrasena);
            if(admin != null){
                System.out.println("\nLogeado Correctamente");
                stay = false;
                MenuAdmin.main(admin);
            } else {
                System.out.println("\nError al logearse");
            }
        }
    }

}
