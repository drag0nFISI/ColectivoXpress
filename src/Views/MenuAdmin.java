package Views;

import Models.Admin;
import Models.Conductor;
import Models.Ruta;

import java.util.HashMap;
import java.util.Scanner;


public class MenuAdmin {

    static Scanner sc = new Scanner(System.in);

    public static void main(Admin admin) {
        MenuAdmin ma = new MenuAdmin();
        boolean stay = true;
        while (stay){

            System.out.println("-------------- MENU ADMIN --------------");
            System.out.println("Que desea hacer...");
            System.out.println("1. Agregar un conductor");
            System.out.println("2. Eliminar conductor");
            System.out.println("3. Editar conductor");
            System.out.println("4. Agregar ruta");
            System.out.println("5. Editar ruta");
            System.out.println("6. Eliminar ruta");
            System.out.println("7. Mostrar rutas");
            System.out.println("8. Editar configuraciones");
            System.out.println("9. Volver");

            int aux = sc.nextInt();
            sc.nextLine();
            switch (aux){
                case 1:
                    ma.agregar_conductor();
                    break;
                case 2:
                    ma.eliminar_conductor();
                    break;
                case 3:
                    ma.editar_conductor();
                    break;
                case 4:
                    ma.agregar_ruta();
                    break;
                case 5:
                    ma.editar_ruta();
                    break;
                case 6:
                    ma.eliminar_ruta();
                    break;
                case 7:
                    ma.mostrar_rutas();
                    break;
                case 8:
                    ma.editar_configuraciones();
                    break;
                case 9:
                    stay = false;
                    break;
                default:
                    System.out.println("Escoja una opcion valida...");
            }
        }
    }

    public void agregar_conductor(){
        HashMap<String, String> datos = new HashMap<>();
        System.out.println("\n------------ AGREGAR UN CONDUCTOR ------------");
        System.out.println("Ingrese los nombres: ");
        datos.put("nombres", sc.nextLine());
        System.out.println("Ingrese los apellidos: ");
        datos.put("apellidos", sc.nextLine());
        System.out.println("Ingrese el telefono: ");
        datos.put("telefono", sc.nextLine());
        System.out.println("Ingrese el dni: ");
        datos.put("dni", sc.nextLine());

        Conductor conductor = Conductor.conductor_por_dni(datos.get("dni"));
        if(conductor!=null){
            System.out.println("Este dni ya esta registrado");
            return;
        }

        System.out.println("Ingrese la fecha de nacimiento: ");
        datos.put("fecha_nacimiento", sc.nextLine());
        System.out.println("Ingrese el distrito de residencia: ");
        datos.put("distrito", sc.nextLine());
        System.out.println("Ingrese la contrasena");
        datos.put("contrasena", sc.nextLine());

        boolean exito = Conductor.registrar_conductor(datos);
        if(exito){
            System.out.println("SE REGISTRO CORRECTAMENTE AL CONDUCTOR");
        } else {
            System.out.println("OCURRIO UN ERROR");
        }
    }

    public void editar_conductor(){
        System.out.println("\n---------- EDITAR CONDUCTOR -----------");
        System.out.println("Ingrese el dni del conductor: ");
        String dni = sc.nextLine();

        Conductor conductor_existente = Conductor.conductor_por_dni(dni);

        if(conductor_existente == null){
            System.out.println("No hay conductor con este DNI");
            return;
        }

        HashMap<String, String> datos = new HashMap<>();

        System.out.println("\n------ Informacion actual ------");
        conductor_existente.mostrar_perfil();
        System.out.println("\n------ Modificaciones --------");
        System.out.println("Si no desea modificar algun campo solo presione ENTER");
        System.out.print("Nuevos nombres: ");
        datos.put("nombres", sc.nextLine());
        System.out.print("Nuevos Apellidos: ");
        datos.put("apellidos", sc.nextLine());
        System.out.print("Nuevo telefono: ");
        datos.put("telefono", sc.nextLine());
        System.out.print("Nuevo distrito: ");
        datos.put("distrito", sc.nextLine());

        boolean exito = conductor_existente.editar_perfil(datos);
    }

    public void eliminar_conductor(){
        System.out.println("\n------------ ELIMINAR CONDUCTOR -----------");
        System.out.println("Ingrese el dni del conductor: ");
        String dni = sc.nextLine();

        boolean exito = Conductor.eliminar_conductor(dni);
        if(exito){
            System.out.println("CONDUCTOR ELIMINADO");
        } else {
            System.out.println("OCURRIO UN ERROR");
        }
    }

    public void agregar_ruta(){
        HashMap<String, String> datos = new HashMap<>();
        System.out.println("\n---------- AGREGAR RUTA ----------");
        System.out.print("Origen: ");
        datos.put("origen", sc.nextLine());
        System.out.print("Destino: ");
        datos.put("destino", sc.nextLine());

        Ruta ruta_existente = Ruta.buscar_ruta(datos.get("origen"), datos.get("destino"));
        if(ruta_existente!= null){
            System.out.println("Ya existe esta ruta...");
            return;
        }

        System.out.print("Precio: ");
        datos.put("precio", sc.nextLine());
        System.out.print("Tiempo aproximado de viaje: ");
        datos.put("tiempo_aproximado", sc.nextLine());
        boolean exito = Ruta.agregar_ruta(datos);
        if(exito){
            System.out.println("SE AÑADIO LA RUTA CON EXITO\n");
        } else {
            System.out.println("OCURRIO UN ERROR, NO SE PUDO ANADIR LA RUTA\n");
        }
    }

    public void editar_ruta(){
        System.out.println("\n-------------- EDITAR RUTA --------------");
        System.out.println("Ingrese los datos de la ruta que quiere editar...");
        System.out.print("Origen: ");
        String origen = sc.nextLine();
        System.out.print("Destino: ");
        String destino = sc.nextLine();
        Ruta ruta = Ruta.buscar_ruta(origen, destino);
        if(ruta==null){
            System.out.println("ESTA RUTA NO EXISTE...");
            return;
        }

        System.out.println("\nInserte los datos a cambiar, si no quiere modificar\nun apartado solo presione ENTER...");

        HashMap<String, String> datos = new HashMap<>();
        System.out.print("Precio Regular: ");
        datos.put("precio", sc.nextLine());
        System.out.println("Precio de oferta: ");
        datos.put("precio_oferta", sc.nextLine());
        System.out.print("Tiempo aproximado de viaje: ");
        datos.put("tiempo_aproximado", sc.nextLine());

        boolean exito = ruta.editar_ruta(datos);
        if(exito){
            System.out.println("CAMBIADO EXITOSAMENTE");
        } else {
            System.out.println("NO SE PUDO EDITAR LA RUTA");
        }
    }

    public void eliminar_ruta(){
        String origen;
        String destino;

        System.out.println("\n---------- ELIMINAR RUTA ----------");
        System.out.println("Ingrese los datos de la ruta a eliminar: ");
        System.out.print("Origen: ");
        origen = sc.nextLine();
        System.out.print("Destino: ");
        destino = sc.nextLine();

        boolean exito = Ruta.eliminar_ruta(origen, destino);
        if(exito){
            System.out.println("RUTA ELIMINADA CON EXITO");
        } else {
            System.out.println("NO SE PUDO ELIMINAR LA RUTA");
        }
    }

    public void mostrar_rutas(){
        Ruta.mostrar_rutas();
    }

    public void editar_configuraciones(){
        System.out.println("\n-------------- EDITAR CONFIGURACIONES --------------");
        System.out.println("Editar numero de dias de descanso de trabajadores: ");
        System.out.println("Insertar cantidad de dias: ");
        int limite_dias_descanso = sc.nextInt();
        boolean exito = Conductor.editar_limite_dias_descanso(limite_dias_descanso);
        if(exito){
            System.out.println("Limite de dias de descanso cambiado con exito");
        } else {
            System.out.println("NO SE PUDO CAMBIAR EL LIMITE");
        }
    }
}
