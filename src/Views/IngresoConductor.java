package Views;

import Models.Conductor;
import Models.Pasajero;
import java.util.Scanner;
import Repository.ConductorRepository;
import Services.ConductorService;

public class IngresoConductor {

    static Scanner sc = new Scanner(System.in);
    static ConductorService cs = new ConductorService();

    public static void inicio(){
        IngresoConductor ic = new IngresoConductor();
        ic.login_conductor();
    }

    public void login_conductor(){
        System.out.println("\n----------------LOGIN DE CONDUCTOR-------------");
        System.out.print("Ingrese su DNI: ");
        String dni = sc.nextLine();
        System.out.print("\nIngrese su contrasena: ");
        String contrasena = sc.nextLine();
        Conductor conductor = cs.login_conductor(dni,contrasena);
        if(conductor !=null){
            System.out.println("\nLogeado Correctamente");
            MenuConductor.main(conductor);
        } else {
            System.out.println("\nError al logearse");
        }
    }
}
