package Views;
import Models.PagoMP;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;

import java.util.Scanner;

//pseudo vista de menu principal
public class MenuPrincipal {

    static Scanner sc = new Scanner(System.in);



    public static void test(String titulo, String descripcion, String correo, double precio){
        try {
            // Inicializar Mercado Pago
            PagoMP.init();

            // Crear la preferencia de pago
            Preference preference = PagoMP.crearPreferencia(titulo, descripcion, precio, correo);

            // Redirigir al usuario a Mercado Pago
            PagoMP.redirigirAWeb(preference);
            System.out.println("PRESIONE ENTER PARA VERIFICAR EL PAGO");
            sc.nextLine();
            sc.nextLine();
            PagoMP.verificarEstadoPago(preference.getExternalReference());
        } catch (MPException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        while (true) {

            int aux = 0;
            boolean permitido = false;

            System.out.println("\033[2J");
            System.out.println("\n---------- BIENVENIDO AL MENU PRINCIPAL --------");
            System.out.println("\t1. Soy pasajero");
            System.out.println("\t2. Soy conductor");
            System.out.println("\t3. Soy administrador");

            while (!permitido) {
                try {
                    System.out.print("Ingrese su opcion: ");
                    aux = sc.nextInt();
                    permitido = true;
                } catch (Exception e) {
                    System.out.println("Opcion no valida");
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
        }
    }
}
