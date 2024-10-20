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

            Consola.cls();
            Consola.dibujar_rectangulo(42, 13, 20, 5);
            Consola.gotoxy(36, 5);
            System.out.print(" MENU ADMIN ");
            Consola.gotoxy(22, 7);
            System.out.print("1. Agregar un Conductor");
            Consola.gotoxy(22, 8);
            System.out.print("2. Eliminar Conductor");
            Consola.gotoxy(22, 9);
            System.out.print("3. Editar Conductor");
            Consola.gotoxy(22, 10);
            System.out.print("4. Agregar Ruta");
            Consola.gotoxy(22, 11);
            System.out.print("5. Editar Ruta");
            Consola.gotoxy(22, 12);
            System.out.print("6. Eliminar Ruta");
            Consola.gotoxy(22, 13);
            System.out.print("7. Mostrar Rutas");
            Consola.gotoxy(22, 14);
            System.out.print("8. Editar Configuraciones");
            Consola.gotoxy(22, 15);
            System.out.print("9. Volver");

            Consola.gotoxy(22, 17);
            System.out.println("Ingrese su opcion: ");

            Consola.gotoxy(41, 17);

            int aux = 0;

            try{
                aux = sc.nextInt();
            } catch (Exception e){
                Consola.gotoxy(30, 20);
                System.out.print("Escoja una opcion valida...");
            }
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
                    Consola.gotoxy(30, 20);
                    System.out.print("Escoja una opcion valida...");
                    Consola.gotoxy(27, 21);
                    System.out.print("Presione ENTER para continuar...");
                    sc.nextLine();
            }
        }
    }

    public void agregar_conductor(){
        HashMap<String, String> datos = new HashMap<>();

        Consola.cls();
        Consola.dibujar_rectangulo(42, 9, 20, 5);
        Consola.gotoxy(31, 5);
        System.out.print(" AGREGAR UN CONDUCTOR ");
        Consola.gotoxy(22, 7);
        System.out.print("DNI: ");
        Consola.gotoxy(22, 8);
        System.out.print("Nombres: ");
        Consola.gotoxy(22, 9);
        System.out.print("Apellidos: ");
        Consola.gotoxy(22, 10);
        System.out.print("Telefono: ");
        Consola.gotoxy(22, 11);
        System.out.print("Fecha de Nacimiento: ");
        Consola.gotoxy(22, 12);
        System.out.print("Distrito: ");
        Consola.gotoxy(22, 13);
        System.out.print("Contrasena: ");

        Consola.gotoxy(27, 7);
        datos.put("dni", sc.nextLine());

        Conductor conductor = Conductor.conductor_por_dni(datos.get("dni"));
        if(conductor!=null){
            Consola.gotoxy(19, 17);
            System.out.print("Ya existe un conductor registrado con este DNI");
            Consola.gotoxy(26, 18);
            System.out.print("Presione ENTER para continuar");
            sc.nextLine();
            return;
        }

        Consola.gotoxy(31, 8);
        datos.put("nombres", sc.nextLine());
        Consola.gotoxy(33, 9);
        datos.put("apellidos", sc.nextLine());
        Consola.gotoxy(32, 10);
        datos.put("telefono", sc.nextLine());
        Consola.gotoxy(43, 11);
        datos.put("fecha_nacimiento", sc.nextLine());
        Consola.gotoxy(32, 12);
        datos.put("distrito", sc.nextLine());
        Consola.gotoxy(35, 13);
        datos.put("contrasena", sc.nextLine());

        boolean exito = Conductor.registrar_conductor(datos);

        Consola.gotoxy(20, 17);
        if(exito){
            System.out.print("Se ha registrado el conductor con exito");
        } else {
            System.out.println("No se ha podido registrar al usuario");
        }
        Consola.gotoxy(26, 18);
        System.out.print("Presione ENTER para continuar");
        sc.nextLine();
    }

    public void editar_conductor(){

        Consola.cls();
        Consola.dibujar_rectangulo(40, 3, 25, 5);
        Consola.gotoxy(37, 5);
        System.out.print(" EDITAR CONDUCTOR ");
        Consola.gotoxy(27, 7);
        System.out.print("DNI: ");
        Consola.gotoxy(32, 7);
        String dni = sc.nextLine();

        Conductor conductor_existente = Conductor.conductor_por_dni(dni);

        if(conductor_existente == null){
            Consola.gotoxy(31, 11);
            System.out.print("No hay conductor con este DNI");
            Consola.gotoxy(31, 12);
            System.out.print("Presione ENTER para continuar");
            sc.nextLine();
            return;
        }


        HashMap<String, String> datos = new HashMap<>();

        Consola.dibujar_rectangulo(40, 9, 14, 11);
        Consola.gotoxy(25, 11);
        System.out.print(" INFORMACION ACTUAL ");
        Consola.gotoxy(16, 13);
        System.out.print("DNI: " + conductor_existente.get_dni());
        Consola.gotoxy(16, 14);
        System.out.print("Nombres: " + conductor_existente.get_nombres());
        Consola.gotoxy(16, 15);
        System.out.print("Apellidos: " + conductor_existente.get_apellidos());
        Consola.gotoxy(16, 16);
        System.out.print("Telefono: " + conductor_existente.get_telefono());
        Consola.gotoxy(16, 17);
        System.out.println("Fecha de Nacimiento: " + conductor_existente.get_fecha_nacimiento());
        Consola.gotoxy(16, 18);
        System.out.print("Distrito: " + conductor_existente.get_distrito());
        Consola.gotoxy(16, 19);
        System.out.print("Dias de descanso: ");
        for(String dia: conductor_existente.get_dias_descanso()){
            System.out.print(dia + ", ");
        }

        Consola.dibujar_rectangulo(50, 8, 62, 11);
        Consola.gotoxy(80, 11);
        System.out.print(" MODIFICACIONES ");
        Consola.gotoxy(64, 13);
        System.out.print("Si no desea modificar algun campo presione ENTER");

        Consola.gotoxy(64, 15);
        System.out.print("Nuevos nombres: ");
        Consola.gotoxy(64, 16);
        System.out.print("Nuevos Apellidos: ");
        Consola.gotoxy(64, 17);
        System.out.print("Nuevo telefono: ");
        Consola.gotoxy(64, 18);
        System.out.print("Nuevo distrito: ");

        Consola.gotoxy(80, 15);
        datos.put("nombres", sc.nextLine());
        Consola.gotoxy(82, 16);
        datos.put("apellidos", sc.nextLine());
        Consola.gotoxy(80, 17);
        datos.put("telefono", sc.nextLine());
        Consola.gotoxy(80, 18);
        datos.put("distrito", sc.nextLine());

        boolean exito = conductor_existente.editar_perfil(datos);

        if(!exito){
            Consola.gotoxy(45, 23);
            System.out.print("No se ha podido modificar el perfil");
            Consola.gotoxy(42, 24);
            System.out.print("Presione ENTER para continuar");
            sc.nextLine();
            return;
        }

        Consola.gotoxy(42, 23);
        System.out.print("Perfil modificado exitosamente");
        Consola.gotoxy(42, 24);
        System.out.print("Presione ENTER para continuar");
        sc.nextLine();
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
