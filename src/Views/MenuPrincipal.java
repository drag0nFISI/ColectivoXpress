package Views;

import java.util.Scanner;

//pseudo vista de menu principal
public class MenuPrincipal {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            Consola.change_color(0, 7);

            int aux = 0;
            boolean permitido = false;

            Consola.cls();
            Consola.dibujar_rectangulo(34, 7, 20, 5);
            Consola.gotoxy(23, 5);
            System.out.print(" BIENVENIDO AL MENU PRINCIPAL ");
            Consola.gotoxy(22, 7);
            System.out.print("1. Soy pasajero");
            Consola.gotoxy(22, 8);
            System.out.print("2. Soy conductor");
            Consola.gotoxy(22, 9);
            System.out.print("3. Soy administrador");

            while (!permitido) {
                try {
                    Consola.gotoxy(22, 11);
                    System.out.print("Ingrese su opcion: ");
                    aux = sc.nextInt();
                    permitido = true;
                } catch (Exception e) {
                    Consola.gotoxy(41, 11);
                    System.out.print("            ");
                    Consola.gotoxy(25, 14);
                    System.out.print("Opcion no valida");
                    sc.nextLine();
                }
            }

            switch (aux) {
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
            Consola.reset_color();
        }
    }
}
