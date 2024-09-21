package Views;

import Models.Admin;
import Models.Conductor;
import Models.Ruta;
import Services.ConductorService;
import Services.RutaService;

import java.util.HashMap;
import java.util.Scanner;


public class MenuAdmin {

    static Scanner sc = new Scanner(System.in);
    ConductorService cs = new ConductorService();
    RutaService rs = new RutaService();

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
            System.out.println("8. Volver");

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

        Conductor conductor = cs.conductor_por_dni(datos.get("dni"));
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

        boolean exito = cs.registrar_conductor(datos);
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

        Conductor conductor_existente = cs.conductor_por_dni(dni);

        if(conductor_existente == null){
            System.out.println("No hay conductor con este DNI");
            return;
        }

        HashMap<String, String> datos = new HashMap<>();

        System.out.println("\n------ Informacion actual ------");
        cs.mostrar_perfil(conductor_existente);
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

        boolean exito = cs.editar_perfil(conductor_existente, datos);
    }

    public void eliminar_conductor(){
        System.out.println("\n------------ ELIMINAR CONDUCTOR -----------");
        System.out.println("Ingrese el dni del conductor: ");
        String dni = sc.nextLine();

        boolean exito = cs.eliminar_conductor(dni);
        if(exito){
            System.out.println("CONDUCTOR ELIMINADO");
        } else {
            System.out.println("OCURRIO UN ERROR");
        }
    }

    public void agregar_ruta(){
        HashMap<String, String> datos = new HashMap<>();
        datos.put("origen", sc.nextLine());
        datos.put("destino", sc.nextLine());
        datos.put("precio", sc.nextLine());
        datos.put("tiempo_aproximado", sc.nextLine());
        rs.agregar_ruta(datos);
    }

    public void editar_ruta(){
        String origen = sc.nextLine();
        String destino = sc.nextLine();
        Ruta ruta = rs.buscar_ruta(origen, destino);
        if(ruta==null){
            System.out.println("ESTA RUTA NO EXISTE");
            return;
        }

        HashMap<String, String> datos = new HashMap<>();
        datos.put("precio", sc.nextLine());
        datos.put("tiempo_aproximado", sc.nextLine());
        boolean exito = rs.editar_ruta(ruta, datos);
        if(exito){
            System.out.println("CAMBIADO EXITOSAMENTE");
        } else {
            System.out.println("No se pudo cambiar");
        }
    }

    public void eliminar_ruta(){
        String origen;
        String destino;
        origen = sc.nextLine();
        destino = sc.nextLine();
        boolean exito = rs.eliminar_ruta(origen, destino);
        if(exito){
            System.out.println("Borrado exitosamente");
        } else {
            System.out.println("Lo siento, no se pudo eliminar");
        }
    }

    public void mostrar_rutas(){
        rs.mostrar_rutas();
    }
}
