package Views;

import Models.Admin;
import Models.Conductor;
import Services.AdminService;

import java.util.Scanner;

public class IngresoAdmin {

    Scanner sc = new Scanner(System.in);
    AdminService as = new AdminService();

    public static void inicio(){
        IngresoAdmin ia = new IngresoAdmin();
        ia.login_admin();
    }

    public void login_admin(){
        System.out.println("\n----------------LOGIN DE ADMIN-------------");
        System.out.print("Ingrese su codigo: ");
        String codigo = sc.nextLine();
        System.out.print("\nIngrese su contrasena: ");
        String contrasena = sc.nextLine();
        Admin admin = as.login_admin(codigo,contrasena);
        if(admin !=null){
            System.out.println("\nLogeado Correctamente");
            MenuAdmin.main(admin);
        } else {
            System.out.println("\nError al logearse");
        }
    }

}
