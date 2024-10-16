package Views;
import Models.Ruta;
import Models.Viaje;
import Repository.ViajeRepository;
import Repository.BoletoRepository;
import Models.Pasajero;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import Models.Boleto;

// Vista para interactuar con el usuario
public class MenuPasajero {
    static Scanner scanner = new Scanner(System.in);

    public static void main(Pasajero pasajero) {
        MenuPasajero menuPasajero = new MenuPasajero();

        while (true) {
            System.out.println("------------ MENU DE PASAJERO ------------");
            System.out.println("Elija la opcion que desea: ");
            System.out.println("\t1. Ver mi perfil");
            System.out.println("\t2. Editar mi perfil");
            System.out.println("\t3. Buscar rutas");
            System.out.println("\t4. Ver todas las rutas disponibles");
            System.out.println("\t5. Ver viajes disponibles");
            System.out.println("\t6. Comprar boleto");
            System.out.println("\t7. Ver detalles de mi viaje actual");
            System.out.println("\t8. Salir");
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
                    Ruta.mostrar_rutas();
                    break;
                case 5:
                    menuPasajero.ver_viajes_disponibles();
                    break;
                case 6:
                    menuPasajero.comprar_boleto(pasajero);
                    break;
                case 7:
                    menuPasajero.ver_viaje_actual(pasajero);
                    break;
                case 8:
                    System.out.println("Saliendo del menú de pasajero...");
                    return;
                default:
                    System.out.println("Escoja una opcion valida...");
            }
        }
    }

    public void ver_perfil(Pasajero pasajero) {
        pasajero.mostrar_perfil();
    }

    public void ver_viajes_disponibles(){
        System.out.println("------------ VIAJES DISPONIBLES ------------");
        ViajeRepository vr = new ViajeRepository();
        List<Viaje> viajes = vr.obtener_viajes();
        if(viajes == null){
            System.out.println("No hay viajes disponibles...");
            return;
        }
        for (Viaje viaje : viajes) {
            System.out.print("\n------------ Viaje "+viaje.get_ruta().get_origen());
            System.out.println("-> "+viaje.get_ruta().get_destino()+" -------------");
            System.out.println("ID del viaje: "+viaje.get_id());
            System.out.println("Nombre del Conductor: "+viaje.get_conductor().get_nombres());
            System.out.println("Fecha del viaje: "+viaje.get_fecha());
            System.out.println("Precio: "+viaje.get_ruta().get_precio());
            System.out.println("Precio oferta: "+viaje.get_ruta().get_precio_oferta());
        }
    }

    public void comprar_boleto(Pasajero pasajero){
        System.out.println("\n----------- COMPRAR BOLETO -----------");
        System.out.println("Ingrese ID del viaje: ");
        String id_viaje = scanner.nextLine();

        ViajeRepository vr = new ViajeRepository();
        Viaje viaje = vr.obtener_viaje(id_viaje);


        if (viaje == null) {
            System.out.println("Viaje no encontrado. Intente nuevamente.");
            return;
        }


        System.out.println("La ruta elegida es: " + viaje.get_ruta().get_origen() + " -> " + viaje.get_ruta().get_destino());
        System.out.println("Elija un metodo de pago:"+ "\n\t1. Pago con Tarjeta "+"\n\t2. Pago en efectivo");
        int metodoPago = scanner.nextInt();
        scanner.nextLine();

        String metodoPagoStr = "";
        if (metodoPago == 1) {
            metodoPagoStr = "Tarjeta";
        } else if (metodoPago == 2) {
            metodoPagoStr = "Efectivo";
        } else {
            System.out.println("Opción inválida.");
            return;
        }
        if (pasajero == null) {
            System.out.println("Error: Pasajero nulo.");
            return;
        }
        if (viaje.get_ruta() == null) {
            System.out.println("Error: La ruta del viaje es nula.");
            return;
        }

        float precio = viaje.get_ruta().get_precio(); // Asegúrate de que esto no falle
        Boleto boleto = new Boleto(pasajero, viaje, metodoPagoStr, precio);
        BoletoRepository br = new BoletoRepository();
        if (pasajero.generar_boleto(viaje)) {

            System.out.println("Boleto comprado exitosamente.");
        } else {
            System.out.println("Error al comprar el boleto.");
        }
    }

    public void ver_viaje_actual(Pasajero pasajero){
        System.out.println("\n----------- VIAJE ACTUAL -----------");
        Viaje viaje = pasajero.viaje_actual;
        if(viaje== null){
            System.out.println("No ha comprado boleto para ningun viaje...");
            return;
        }
        System.out.print("\n------------ Viaje "+viaje.get_ruta().get_origen());
        System.out.println("-> "+viaje.get_ruta().get_destino()+" -------------");
        System.out.println("ID del viaje: "+viaje.get_id());
        System.out.println("Nombre del Conductor: "+viaje.get_conductor().get_nombres());
        System.out.println("Fecha del viaje: "+viaje.get_fecha());
        System.out.println("Precio: "+viaje.get_ruta().get_precio());
        System.out.println("Precio oferta: "+viaje.get_ruta().get_precio_oferta());
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

        boolean exito = pasajero.editar_perfil(datos);
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
        Ruta ruta = Ruta.buscar_ruta(origin, destination);
    }
}