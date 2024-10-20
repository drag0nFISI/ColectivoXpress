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

        Consola.dibujar_rectangulo(40, 9, 4, 11);
        Consola.gotoxy(15, 11);
        System.out.print(" INFORMACION ACTUAL ");
        Consola.gotoxy(6, 13);
        System.out.print("DNI: " + conductor_existente.get_dni());
        Consola.gotoxy(6, 14);
        System.out.print("Nombres: " + conductor_existente.get_nombres());
        Consola.gotoxy(6, 15);
        System.out.print("Apellidos: " + conductor_existente.get_apellidos());
        Consola.gotoxy(6, 16);
        System.out.print("Telefono: " + conductor_existente.get_telefono());
        Consola.gotoxy(6, 17);
        System.out.println("Fecha de Nacimiento: " + conductor_existente.get_fecha_nacimiento());
        Consola.gotoxy(6, 18);
        System.out.print("Distrito: " + conductor_existente.get_distrito());
        Consola.gotoxy(6, 19);
        System.out.print("Dias de descanso: ");
        for(String dia: conductor_existente.get_dias_descanso()){
            System.out.print(dia + ", ");
        }

        Consola.dibujar_rectangulo(50, 8, 52, 11);
        Consola.gotoxy(70, 11);
        System.out.print(" MODIFICACIONES ");
        Consola.gotoxy(54, 13);
        System.out.print("Si no desea modificar algun campo presione ENTER");

        Consola.gotoxy(54, 15);
        System.out.print("Nuevos nombres: ");
        Consola.gotoxy(54, 16);
        System.out.print("Nuevos Apellidos: ");
        Consola.gotoxy(54, 17);
        System.out.print("Nuevo telefono: ");
        Consola.gotoxy(54, 18);
        System.out.print("Nuevo distrito: ");

        Consola.gotoxy(70, 15);
        datos.put("nombres", sc.nextLine());
        Consola.gotoxy(72, 16);
        datos.put("apellidos", sc.nextLine());
        Consola.gotoxy(70, 17);
        datos.put("telefono", sc.nextLine());
        Consola.gotoxy(70, 18);
        datos.put("distrito", sc.nextLine());

        boolean exito = conductor_existente.editar_perfil(datos);

        if(!exito){
            Consola.gotoxy(35, 23);
            System.out.print("No se ha podido modificar el perfil");
            Consola.gotoxy(32, 24);
            System.out.print("Presione ENTER para continuar");
            sc.nextLine();
            return;
        }

        Consola.gotoxy(32, 23);
        System.out.print("Perfil modificado exitosamente");
        Consola.gotoxy(32, 24);
        System.out.print("Presione ENTER para continuar");
        sc.nextLine();
    }

    public void eliminar_conductor(){

        Consola.cls();
        Consola.dibujar_rectangulo(40, 3, 25, 5);
        Consola.gotoxy(36, 5);
        System.out.print(" ELIMINAR CONDUCTOR ");
        Consola.gotoxy(27, 7);
        System.out.print("DNI: ");
        Consola.gotoxy(32, 7);
        String dni = sc.nextLine();

        boolean exito = Conductor.eliminar_conductor(dni);
        if(exito){
            Consola.gotoxy(35, 11);
            System.out.print("CONDUCTOR ELIMINADO");
        } else {
            Consola.gotoxy(28, 11);
            System.out.print("NO SE PUDO ELIMINAR AL CONDUCTOR");
        }
        Consola.gotoxy(30, 12);
        System.out.print("Presione ENTER para continuar");
        sc.nextLine();
    }

    public void agregar_ruta(){
        HashMap<String, String> datos = new HashMap<>();

        Consola.cls();
        Consola.dibujar_rectangulo(42, 6, 20, 5);
        Consola.gotoxy(32, 5);
        System.out.print(" AGREGAR UNA RUTA ");
        Consola.gotoxy(22, 7);
        System.out.print("Origen: ");
        Consola.gotoxy(22, 8);
        System.out.print("Destino: ");
        Consola.gotoxy(22, 9);
        System.out.print("Precio: ");
        Consola.gotoxy(22, 10);
        System.out.print("Tiempo aprox. viaje: ");

        Consola.gotoxy(30, 7);
        datos.put("origen", sc.nextLine());
        Consola.gotoxy(31, 8);
        datos.put("destino", sc.nextLine());

        Ruta ruta_existente = Ruta.buscar_ruta(datos.get("origen"), datos.get("destino"));
        if(ruta_existente!= null){
            System.out.println("Ya existe esta ruta...");
            return;
        }

        Consola.gotoxy(30, 9);
        datos.put("precio", sc.nextLine());
        Consola.gotoxy(43, 10);
        datos.put("tiempo_aproximado", sc.nextLine());

        boolean exito = Ruta.agregar_ruta(datos);
        if(exito){
            Consola.gotoxy(28, 14);
            System.out.print("SE AÑADIO LA RUTA CON EXITO");
        } else {
            Consola.gotoxy(20, 14);
            System.out.print("OCURRIO UN ERROR, NO SE PUDO ANADIR LA RUTA");
        }
        Consola.gotoxy(27, 15);
        System.out.print("Presione ENTER para continuar");
        sc.nextLine();
    }

    public void editar_ruta(){

        Consola.cls();
        Consola.dibujar_rectangulo(42, 4, 20, 5);
        Consola.gotoxy(34, 5);
        System.out.print(" EDITAR RUTA ");
        Consola.gotoxy(22, 7);
        System.out.print("Origen: ");
        Consola.gotoxy(22, 8);
        System.out.print("Destino: ");

        Consola.gotoxy(30, 7);
        String origen = sc.nextLine();
        Consola.gotoxy(31, 8);
        String destino = sc.nextLine();

        Ruta ruta = Ruta.buscar_ruta(origen, destino);
        if(ruta==null){
            Consola.gotoxy(30, 12);
            System.out.print("ESTA RUTA NO EXISTE...");
            Consola.gotoxy(26, 13);
            System.out.print("Presione ENTER para continuar");
            sc.nextLine();
            return;
        }

        Consola.dibujar_rectangulo(40, 5, 4, 11);
        Consola.gotoxy(15, 11);
        System.out.print(" INFORMACION ACTUAL ");
        Consola.gotoxy(6, 13);
        System.out.print("Precio Regular: " + ruta.get_precio());
        Consola.gotoxy(6, 14);
        System.out.print("Precio Oferta: " + ruta.get_precio_oferta());
        Consola.gotoxy(6, 15);
        System.out.print("Tiempo Aprox. Viaje: " + ruta.get_tiempo_aproximado());

        Consola.dibujar_rectangulo(50, 8, 52, 11);
        Consola.gotoxy(70, 11);
        System.out.print(" MODIFICACIONES ");
        Consola.gotoxy(54, 13);
        System.out.print("Si no desea modificar algun campo presione ENTER");

        HashMap<String, String> datos = new HashMap<>();

        Consola.gotoxy(54, 15);
        System.out.print("Nuevo Precio Regular: ");
        Consola.gotoxy(54, 16);
        System.out.print("Nuevo Precio Oferta: ");
        Consola.gotoxy(54, 17);
        System.out.print("Nuevo Tiempo Aprox. Viaje: ");

        Consola.gotoxy(76, 15);
        datos.put("precio", sc.nextLine());
        Consola.gotoxy(75, 16);
        datos.put("precio_oferta", sc.nextLine());
        Consola.gotoxy(81, 17);
        datos.put("tiempo_aproximado", sc.nextLine());

        boolean exito = ruta.editar_ruta(datos);

        if(!exito){
            Consola.gotoxy(36, 23);
            System.out.print("No se ha podido modificar la ruta");
            Consola.gotoxy(32, 24);
            System.out.print("Presione ENTER para continuar");
            sc.nextLine();
            return;
        }

        Consola.gotoxy(33, 23);
        System.out.print("Ruta modificada exitosamente");
        Consola.gotoxy(32, 24);
        System.out.print("Presione ENTER para continuar");
        sc.nextLine();
    }

    public void eliminar_ruta(){
        Consola.cls();
        Consola.dibujar_rectangulo(42, 6, 20, 5);
        Consola.gotoxy(33, 5);
        System.out.print(" ELIMINAR RUTA ");
        Consola.gotoxy(22, 7);
        System.out.print("Ingrese los datos de la ruta a eliminar");
        Consola.gotoxy(22, 9);
        System.out.print("Origen: ");
        Consola.gotoxy(22, 10);
        System.out.print("Destino: ");

        Consola.gotoxy(30, 9);
        String origen = sc.nextLine();
        Consola.gotoxy(31, 10);
        String destino = sc.nextLine();

        boolean exito = Ruta.eliminar_ruta(origen, destino);
        if(exito){
            Consola.gotoxy(29, 14);
            System.out.print("RUTA ELIMINADA CON EXITO");
        } else {
            Consola.gotoxy(27, 14);
            System.out.print("NO SE PUDO ELIMINAR LA RUTA");
        }
        Consola.gotoxy(26, 15);
        System.out.print("Presione ENTER para continuar");
        sc.nextLine();
    }

    public void mostrar_rutas(){
        Ruta.mostrar_rutas();
    }

    public void editar_configuraciones(){

        Consola.cls();
        Consola.dibujar_rectangulo(50, 5, 20, 5);
        Consola.gotoxy(33, 5);
        System.out.print(" EDITAR CONFIGURACIONES ");
        Consola.gotoxy(22, 7);
        System.out.print("Editar numero de dias de descanso de conductores");
        Consola.gotoxy(22, 9);
        System.out.print("Insertar cantidad de dias: ");

        int limite_dias_descanso;

        while(true){
            try{
                Consola.gotoxy(49, 9);
                limite_dias_descanso = sc.nextInt();
                break;
            } catch (Exception e) {
                sc.nextLine();
                Consola.gotoxy(35, 13);
                System.out.print("Ingrese un valor valido");
                Consola.gotoxy(32, 14);
                System.out.print("Presione ENTER para continuar...");
                sc.nextLine();
                Consola.gotoxy(49, 9);
                System.out.print("                ");
                Consola.gotoxy(35, 13);
                System.out.print("                       ");
                Consola.gotoxy(32, 14);
                System.out.print("                                ");
            }
        }

        boolean exito = Conductor.editar_limite_dias_descanso(limite_dias_descanso);
        sc.nextLine();
        if(exito){
            Consola.gotoxy(27, 13);
            System.out.print("Limite de dias de descanso cambiado con exito");
        } else {
            Consola.gotoxy(35, 13);
            System.out.print("NO SE PUDO CAMBIAR EL LIMITE");
        }
        Consola.gotoxy(32, 14);
        System.out.print("Presione ENTER para continuar...");
        sc.nextLine();
    }
}
