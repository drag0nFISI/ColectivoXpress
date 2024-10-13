package Views;
import Models.Ruta;
import Services.RutaService;
import Services.PasajeroService;
import Models.Pasajero;
import java.util.HashMap;
import java.util.Scanner;

// Vista para interactuar con el usuario
public class MenuPasajero {
    static Scanner scanner = new Scanner(System.in);
    static RutaService rutaService = new RutaService();
    static PasajeroService pasajeroService = new PasajeroService();

    public static void main(Pasajero pasajero) {
        MenuPasajero menuPasajero = new MenuPasajero();

        while (true) {
            System.out.println("------------ MENU DE PASAJERO ------------");
            System.out.println("Elija la opcion que desea: ");
            System.out.println("1. Ver mi perfil");
            System.out.println("2. Editar mi perfil");
            System.out.println("3. Buscar rutas");
            System.out.println("4. Ver todas las rutas disponibles");
            System.out.println("5. Salir");
            int aux = scanner.nextInt();
            scanner.nextLine();
            switch (aux) {
                case 1:
                    menuPasajero.ver_perfil(pasajero);
                    break;
                case 2:
                    menuPasajero.editar_perfil(pasajero);
                    break;
                case 3:
                    buscarRutas();
                    break;
                case 4:
                    rutaService.mostrar_rutas();
                    break;
                case 5:
                    System.out.println("Saliendo del menú de pasajero...");
                    return;
                default:
                    System.out.println("Escoja una opcion valida...");
            }
        }
    }

    public void ver_perfil(Pasajero pasajero) {
        pasajeroService.mostrar_perfil(pasajero);
    }

    public void editar_perfil(Pasajero pasajero) {
        HashMap<String, String> datos = new HashMap<>();
        System.out.println("\n----------- MODIFICAR PERFIL -----------");
        System.out.println("Si no quiere modificar algun apartado solo dejelo en blanco y de ENTER");
        System.out.println("Nuevos Nombres: ");
        datos.put("nombres", scanner.nextLine());
        System.out.println("Nuevos Apellidos: ");
        datos.put("apellidos", scanner.nextLine());
        System.out.println("Nuevo telefono: ");
        datos.put("telefono", scanner.nextLine());
        System.out.println("Nuevo distrito: ");
        datos.put("distrito", scanner.nextLine());

        boolean exito = pasajeroService.editar_perfil(pasajero, datos);
        if (exito) {
            System.out.println("Cambios hechos satisfactoriamente...");
        } else {
            System.out.println("OCURRIO UN ERROR");
        }
    }

    private static void buscarRutas() {
        System.out.print("Ingrese el origen: ");
        String origin = scanner.nextLine();
        System.out.print("Ingrese el destino: ");
        String destination = scanner.nextLine();
        Ruta ruta = rutaService.buscar_ruta(origin, destination);
    }
}