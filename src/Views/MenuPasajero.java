package Views;
import Models.*;
import Repository.ConductorRepository;
import Repository.ViajeRepository;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

// Vista para interactuar con el usuario
public class MenuPasajero {
    static Scanner scanner = new Scanner(System.in);

    public static void main(Pasajero pasajero) {
        MenuPasajero menuPasajero = new MenuPasajero();

        while (true) {
            Consola.cls();
            Consola.dibujar_rectangulo(42, 7, 33, 10);
            Consola.gotoxy(35, 5);
            System.out.println("------------ MENU DE PASAJERO ------------");
            Consola.gotoxy(35, 8);
            System.out.println("Elija la opcion que desea: ");
            Consola.gotoxy(35, 10);
            System.out.println("1. Ver mi perfil");
            Consola.gotoxy(35, 11);
            System.out.println("2. Editar mi perfil");
            Consola.gotoxy(35, 12);
            System.out.println("3. Buscar Viajes");
            Consola.gotoxy(35, 13);
            System.out.println("4. Ver todas las rutas");
            Consola.gotoxy(35, 14);
            System.out.println("5. Ver todos los viajes disponibles");
            Consola.gotoxy(35, 15);
            System.out.println("6. Comprar boleto");
            Consola.gotoxy(35, 16);
            System.out.println("7. Ver detalles de mi ultimo boleto");
            Consola.gotoxy(35, 17);
            System.out.println("8. Salir");
            Consola.gotoxy(35, 19);
            System.out.print("Ingrese su opcion: ");
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
                    //buscarRutas();
                    menuPasajero.buscar_viajes();
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
                    Consola.cls();
                    Consola.dibujar_rectangulo(30, 8, 40, 5);
                    Consola.gotoxy(35, 10);
                    System.out.println("Saliendo del menú de pasajero...");
                    return;
                default:
                    Consola.gotoxy(35, 12);
                    System.out.println("Escoja una opcion valida...");
            }
        }
    }

    public void ver_perfil(Pasajero pasajero) {
        Consola.cls();
        Consola.dibujar_rectangulo(40, 10, 29, 5);
        Consola.gotoxy(35, 5);
        System.out.println("------------ MI PERFIL ------------");
        Consola.gotoxy(34, 7);
        System.out.printf("Nombre: %s\n", pasajero.get_nombres());
        Consola.gotoxy(34, 8);
        System.out.printf("Apellidos: %s\n", pasajero.get_apellidos());
        Consola.gotoxy(34, 9);
        System.out.printf("Correo: %s\n", pasajero.get_telefono());
        Consola.gotoxy(34, 10);
        System.out.printf("DNI: %s\n", pasajero.get_dni());
        Consola.gotoxy(34, 11);
        System.out.printf("Fecha de Nacimiento: %s\n", pasajero.get_fecha_nacimiento());
        Consola.gotoxy(34, 12);
        System.out.printf("Distrito: %s\n", pasajero.get_distrito());
        Consola.gotoxy(34, 13);
        System.out.printf("Numero de viajes: %d\n", pasajero.get_numero_viajes());
        Consola.gotoxy(30, 15);
        System.out.print("Presione ENTER para continuar...");
        scanner.nextLine();
    }


    public void ver_viajes_disponibles() {
        Consola.cls();
        Consola.gotoxy(35, 5);
        System.out.println("------------ VIAJES DISPONIBLES ------------");
        ViajeRepository vr = new ViajeRepository();
        List<Viaje> viajes = vr.obtener_viajes();

        ConductorRepository cr = new ConductorRepository();

        if (viajes == null) {
            Consola.gotoxy(22, 7);
            System.out.println("No hay viajes disponibles...");
            Consola.gotoxy(22, 9);
            System.out.print("Presione ENTER para continuar...");
            scanner.nextLine();
            return;
        }

        for (Viaje viaje : viajes) {
            int centerX = 44;
            String origenDestino = "Viaje " + viaje.get_ruta().get_origen() + " -> " + viaje.get_ruta().get_destino();
            int centeredPosition = centerX - (origenDestino.length() / 2);

            Consola.gotoxy(centeredPosition, 7);
            System.out.println("------------ " + origenDestino + " -----------");
            Consola.gotoxy(35, 9);
            System.out.println("ID del viaje: " + viaje.get_id());
            Consola.gotoxy(35, 10);
            System.out.println("Nombre del Conductor: " + cr.consultar_credenciales(viaje.get_dni_conductor(), "").get_nombres());
            Consola.gotoxy(35, 11);
            System.out.println("Fecha del viaje: " + viaje.get_fecha());
            Consola.gotoxy(35, 12);
            System.out.println("Precio: " + viaje.get_ruta().get_precio());
            Consola.gotoxy(35, 13);
            System.out.println("Precio oferta: " + viaje.get_ruta().get_precio_oferta());
        }
        Consola.gotoxy(35, 15);
        System.out.print("Presione ENTER para continuar...");
        scanner.nextLine();
    }

    public void comprar_boleto(Pasajero pasajero) {
        ver_viajes_disponibles();

        Consola.cls();
        Consola.gotoxy(35, 5);
        System.out.println("----------- COMPRAR BOLETO -----------");
        Consola.gotoxy(35, 7);
        System.out.print("Ingrese ID del viaje: ");
        String id_viaje = scanner.nextLine();

        ViajeRepository vr = new ViajeRepository();
        Viaje viaje = vr.obtener_viaje(id_viaje);

        if (viaje == null) {
            Consola.gotoxy(35, 9);
            System.out.println("Viaje no encontrado. Intente nuevamente.");
            Consola.gotoxy(35, 11);
            System.out.print("Presione ENTER para continuar...");
            scanner.nextLine();
            return;
        }

        if (pasajero == null) {
            Consola.gotoxy(35, 9);
            System.out.println("Error: Pasajero nulo.");
            Consola.gotoxy(35, 11);
            System.out.print("Presione ENTER para continuar...");
            scanner.nextLine();
            return;
        }
        if (viaje.get_ruta() == null) {
            Consola.gotoxy(35, 9);
            System.out.println("Error: La ruta del viaje es nula.");
            Consola.gotoxy(35, 11);
            System.out.print("Presione ENTER para continuar...");
            scanner.nextLine();
            return;
        }

        Consola.gotoxy(35, 9);
        System.out.println("La ruta elegida es: " + viaje.get_ruta().get_origen() + " -> " + viaje.get_ruta().get_destino());
        Consola.gotoxy(35, 11);
        System.out.println("A continuación se procederá con el pago...");
        Boleto boleto_creado = pasajero.generar_boleto(viaje);
        if (boleto_creado == null) {
            Consola.gotoxy(35, 13);
            System.out.println("No es posible comprar un boleto para este viaje actualmente");
            Consola.gotoxy(35, 15);
            System.out.print("Presione ENTER para continuar...");
            scanner.nextLine();
            return;
        }

        try {
            PagoMP.init();
            String descripcion = "Viaje " + viaje.get_ruta().get_origen() + " -> " + viaje.get_ruta().get_destino();
            float precio = viaje.get_ruta().get_precio();
            String correo = pasajero.get_telefono();
            Preference preference = PagoMP.crearPreferencia(boleto_creado.get_id(), descripcion, precio, correo);

            Consola.gotoxy(35, 17);
            System.out.println("Se le enviará a una página a completar el pago...");
            Consola.gotoxy(35, 19);
            System.out.println("Una vez culmine el pago, deberá regresar y presionar ENTER para confirmarlo");
            Thread.sleep(1000);

            PagoMP.redirigirAWeb(preference);
            boolean pago_exitoso = false;
            while (!pago_exitoso) {
                Consola.gotoxy(35, 21);
                System.out.println("En caso de querer cancelar la compra, escriba SALIR");
                Consola.gotoxy(35, 23);
                System.out.println("PRESIONE ENTER PARA VERIFICAR EL PAGO...");
                String aux = scanner.nextLine();
                if (aux.equals("SALIR")) {
                    Consola.gotoxy(35, 25);
                    System.out.println("Compra cancelada...");
                    return;
                }
                pago_exitoso = PagoMP.verificarEstadoPago(preference.getExternalReference());
                if (pago_exitoso) {
                    Consola.gotoxy(35, 25);
                    System.out.println("Transacción exitosa");
                    Consola.gotoxy(35, 27);
                    System.out.println("Espere un momento...");
                }
            }
        } catch (MPException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        if (pasajero.guardar_boleto(boleto_creado, viaje)) {
            Consola.gotoxy(35, 29);
            System.out.println("Boleto comprado exitosamente.");
        } else {
            Consola.gotoxy(35, 29);
            System.out.println("Error al comprar el boleto.");
        }
        Consola.gotoxy(35, 31);
        System.out.print("Presione ENTER para continuar...");
        scanner.nextLine();
    }

    public void ver_viaje_actual(Pasajero pasajero) {
        Consola.cls();
        Consola.dibujar_rectangulo(20, 3, 60, 25);
        Consola.gotoxy(35, 5);
        System.out.println("----------- VIAJE ACTUAL -----------");
        ViajeRepository vr = new ViajeRepository();
        Viaje viaje = vr.obtener_viaje(pasajero.id_viaje_actual);
        if (viaje == null) {
            Consola.gotoxy(22, 7);
            System.out.println("No ha comprado boleto para ningún viaje...");
            Consola.gotoxy(22, 9);
            System.out.print("Presione ENTER para continuar...");
            scanner.nextLine();
            return;
        }

        ConductorRepository cr = new ConductorRepository();

        System.out.print("\n------------ Viaje " + viaje.get_ruta().get_origen());
        System.out.println("-> " + viaje.get_ruta().get_destino() + " -------------");
        System.out.println("ID del viaje: " + viaje.get_id());
        System.out.println("Nombre del Conductor: " + cr.consultar_credenciales(viaje.get_dni_conductor(), "").get_nombres());
        System.out.println("Fecha del viaje: " + viaje.get_fecha());
        System.out.println("Precio: " + viaje.get_ruta().get_precio());
        System.out.println("Precio oferta: " + viaje.get_ruta().get_precio_oferta());
        Consola.gotoxy(22, 25);
        System.out.print("Presione ENTER para continuar...");
        scanner.nextLine();
    }

    public void editar_perfil(Pasajero pasajero) {
        HashMap<String, String> datos = new HashMap<>();
        Consola.cls();
        Consola.gotoxy(35, 5);
        System.out.println("----------- MODIFICAR PERFIL -----------");
        Consola.gotoxy(22, 7);
        System.out.println("Si no quiere modificar algun apartado solo dejelo en blanco y de ENTER");
        Consola.gotoxy(22, 9);
        System.out.print("Nuevos Nombres: ");
        datos.put("nombres", scanner.nextLine());
        Consola.gotoxy(22, 11);
        System.out.print("Nuevos Apellidos: ");
        datos.put("apellidos", scanner.nextLine());
        Consola.gotoxy(22, 13);
        System.out.print("Nuevo Correo: ");
        datos.put("telefono", scanner.nextLine());
        Consola.gotoxy(22, 15);
        System.out.print("Nuevo distrito: ");
        datos.put("distrito", scanner.nextLine());

        boolean exito = pasajero.editar_perfil(datos);
        if (exito) {
            Consola.gotoxy(22, 17);
            System.out.println("Cambios hechos satisfactoriamente...");
        } else {
            Consola.gotoxy(22, 17);
            System.out.println("OCURRIO UN ERROR");
        }
        Consola.gotoxy(22, 19);
        System.out.print("Presione ENTER para continuar...");
        scanner.nextLine();
    }

    private static void buscarRutas() {
        Consola.cls();
        Consola.gotoxy(35, 5);
        System.out.println("----------- BUSCAR RUTAS -----------");
        Consola.gotoxy(22, 7);
        System.out.print("Ingrese el origen: ");
        String origin = scanner.nextLine();
        Consola.gotoxy(22, 9);
        System.out.print("Ingrese el destino: ");
        String destination = scanner.nextLine();
        Ruta ruta = Ruta.buscar_ruta(origin, destination);
        if (ruta != null) {
            Consola.gotoxy(22, 11);
            System.out.println("Ruta encontrada: " + ruta.get_origen() + " -> " + ruta.get_destino());
        } else {
            Consola.gotoxy(22, 11);
            System.out.println("Ruta no encontrada.");
        }
        Consola.gotoxy(22, 13);
        System.out.print("Presione ENTER para continuar...");
        scanner.nextLine();
    }

    public void buscar_viajes(){
        Consola.cls();
        Consola.gotoxy(35, 5);
        System.out.println("----------- BUSCAR VIAJES -----------");
        Consola.gotoxy(15, 7);
        System.out.print("INGRESE SOLO LOS FILTROS DESEADOS, SI NO DESEA FILTRAR ORIGEN DEJELO VACIO, IGUAL CON DESTINO");
        Consola.gotoxy(22, 10);
        System.out.print("Origen: ");
        Consola.gotoxy(22, 11);
        System.out.print("Destino: ");


        Consola.gotoxy(30, 10);
        String origen = scanner.nextLine();
        Consola.gotoxy(31, 11);
        String destino = scanner.nextLine();

        List<Viaje> viajes = Viaje.buscar_viajes(origen, destino);

        int aux = 1;

        Consola.cls();

        for(Viaje viaje:viajes){
            Consola.dibujar_boton(7, 1, 5, 4+(2*aux), viaje.get_id());
            Consola.dibujar_boton(20, 1, 13, 4+(2*aux), viaje.get_ruta().get_origen());
            Consola.dibujar_boton(20, 1, 34, 4+(2*aux), viaje.get_ruta().get_destino());
            Consola.dibujar_boton(8, 1, 55, 4+(2*aux), Float.toString(viaje.get_ruta().get_precio()));
            String precio_oferta = "";
            if(viaje.get_ruta().get_precio_oferta()==0){
                precio_oferta = Float.toString(viaje.get_ruta().get_precio_oferta());
            }
            Consola.dibujar_boton(13, 1, 64, 4+(2*aux), precio_oferta);
            Consola.dibujar_boton(14, 1, 78, 4+(2*aux), viaje.get_dni_conductor());

            int asientos_restantes;
            if(viaje.get_pasajeros()!=null){
               asientos_restantes = viaje.get_capacidad_pasajeros() - viaje.get_pasajeros().size();
            } else {
                asientos_restantes = viaje.get_capacidad_pasajeros();
            }

            Consola.dibujar_boton(8, 1, 93, 4+(2*aux), Integer.toString(asientos_restantes));

            aux += 1;
        }
        Consola.dibujar_boton(7, 1, 5, 4, "ID");
        Consola.dibujar_boton(20, 1, 13, 4, "ORIGEN");
        Consola.dibujar_boton(20, 1, 34, 4, "DESTINO");
        Consola.dibujar_boton(8, 1, 55, 4, "PRECIO");
        Consola.dibujar_boton(13, 1, 64, 4, "PRECIO OFERTA");
        Consola.dibujar_boton(14, 1, 78, 4, "DNI CONDUCTOR");
        Consola.dibujar_boton(8, 1, 93, 4, "ASIENTOS");


        Consola.gotoxy(22, 13);
        System.out.print("Presione ENTER para continuar...");
        scanner.nextLine();

    }
}