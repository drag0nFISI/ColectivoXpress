package Views;
import Services.PasajeroService;

import java.io.IOException;
import java.util.Scanner;

//pseudo vista de menu principal
public class MenuPrincipal {

    static Scanner sc = new Scanner(System.in);
    static PasajeroService cs = new PasajeroService();

    public static void main(String[] args) {
        while(true){

            int aux = 0;
            boolean permitido = false;

            System.out.println("\n---------- BIENVENIDO AL MENU PRINCIPAL --------");
            System.out.println("1. Soy pasajero");
            System.out.println("2. Soy conductor");
            System.out.println("3. Soy administrador");

            while(!permitido){
                try{
                    System.out.print("Ingrese su opcion: ");
                    aux = sc.nextInt();
                    permitido = true;
                } catch(Exception e){
                    System.out.println("Opcion no valida");
                }
            }

            switch (aux){
                case 1:
                    IngresoPasajero.inicio();
                    break;
                case 2:
                    IngresoConductor.inicio();
                    break;
                case 3:
                    IngresoAdmin.inicio();
                    break;
                default:
                    System.out.println("Opcion no valida");
                    break;
            }
        }
    }
}
