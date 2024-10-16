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

            System.out.println("\n----------------LOGIN DE CONDUCTOR-------------");
            System.out.println("PARA VOLVER ATRAS DEJE VACIOS LOS CAMPOS DE DNI Y CONTRASENA");
            System.out.print("Ingrese su DNI: ");
            String dni = sc.nextLine();
            System.out.print("\nIngrese su contrasena: ");
            String contrasena = sc.nextLine();
            if(dni.equals("") && contrasena.equals("")) {
                stay = false;
                return;
            }
            Conductor conductor = Conductor.login_conductor(dni,contrasena);
            if(conductor !=null){
                System.out.println("\nLogeado Correctamente");
                MenuConductor.main(conductor);
            } else {
                System.out.println("\nError al logearse");
            }
        }
    }
}
