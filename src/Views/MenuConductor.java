package Views;

import Models.Conductor;
import Models.Ruta;
import Models.Viaje;
import Repository.RutaRepository;
import Repository.ViajeRepository;

import java.time.LocalDate;
import java.util.*;

public class MenuConductor {

    static Scanner sc = new Scanner(System.in);

    public static void main(Conductor conductor) {
        MenuConductor mc = new MenuConductor();

        mc.comprobaciones_automaticas();
        mc.comprobar_viaje_actual(conductor);

        boolean stay = true;
        while(stay){
            Consola.cls();
            Consola.dibujar_rectangulo(42, 7, 33, 7);
            Consola.gotoxy(35, 5);
            System.out.println("------------ MENU DE CONDUCTOR ------------");
            Consola.gotoxy(35, 8);
            System.out.println("Elija la opcion que desea: ");
            Consola.gotoxy(35, 10);
            System.out.println("1. Ver mi perfil");
            Consola.gotoxy(35, 11);
            System.out.println("2. Editar mi perfil");
            Consola.gotoxy(35, 12);
            System.out.println("3. Editar mis dias de descanso");
            Consola.gotoxy(35, 13);
            System.out.println("4. Ver viaje asignado");
            Consola.gotoxy(35, 14);
            System.out.println("5. Volver");
            Consola.gotoxy(35, 16);
            System.out.print("Ingrese su opcion: ");
            int aux = sc.nextInt();
            sc.nextLine();
            switch (aux){
                case 1:
                    mc.ver_perfil(conductor);
                    break;
                case 2:
                    mc.editar_perfil(conductor);
                    break;
                case 3:
                    mc.editar_dias_descanso(conductor);
                    break;
                case 4:
                    mc.ver_viaje_asignado(conductor);
                    break;
                case 5:
                    stay = false;
                    break;
                default:
                    Consola.gotoxy(35, 18);
                    System.out.println("Escoja una opcion valida...");
            }
        }
    }

    public void comprobar_viaje_actual(Conductor conductor){
        if(conductor.get_viaje_actual() == null){
            RutaRepository rr = new RutaRepository();
            List<Ruta> rutas = rr.obtener_rutas();
            if(rutas == null){
                return;
            }
            ViajeRepository vr = new ViajeRepository();
            List<Viaje> viajes = vr.obtener_viajes();

            Ruta ruta_elegida = null;

            int contador = 0;
            int aux2 = 0;

            for(Ruta ruta:rutas){
                int aux = 0;
                aux2 += 1;
                if(viajes != null){
                    for(Viaje viaje:viajes){
                        if(ruta.get_origen().equals(viaje.get_ruta().get_origen()) && ruta.get_destino().equals(viaje.get_ruta().get_destino())){
                            aux++;
                        }
                    }
                }
                if(aux2 == 1){
                    contador = aux;
                }
                if(aux <= contador){
                    contador = aux;
                    ruta_elegida = ruta;
                }
            }

            if(ruta_elegida != null){
                Random random = new Random();
                String id = "";
                //Genera ID random con 5 digitos para Viaje... realiza comprobacion
                boolean encontrado = true;
                while(encontrado){
                    int id_aux = 10000 + random.nextInt(90000);
                    id = String.format("%05d", id_aux);

                    if(viajes != null){
                        for(Viaje viaje:viajes){
                            if(viaje.get_id().equals(id)){
                                encontrado = true;
                                break;
                            }else{
                                encontrado = false;
                            }
                        }
                    } else {
                        encontrado = false;
                    }
                }

                LocalDate fecha_actual = LocalDate.now();
                LocalDate fecha_viaje = fecha_actual.plusDays(1);

                Viaje viaje = new Viaje(id, fecha_viaje.toString(), ruta_elegida, conductor.get_dni());

                vr.guardar_viaje(viaje);
                conductor.set_viaje_actual(viaje);
            }
        }
    }

    public void comprobaciones_automaticas(){
        HashMap<String, Object> configuraciones = Conductor.obtener_configuraciones();
        if(configuraciones ==null){
            return;
        }
        if(configuraciones.get("limite_dias_descanso") != null) {
            double limite = Double.parseDouble(configuraciones.get("limite_dias_descanso").toString());
            Conductor.set_limite_dias_descanso((int) limite);
        }
    }

    public void ver_perfil(Conductor conductor){
        Consola.cls();
        Consola.dibujar_rectangulo(42, 9, 33, 5);
        Consola.gotoxy(35, 5);
        System.out.println("------------ MI PERFIL ------------");
        conductor.mostrar_perfil();
        Consola.gotoxy(35, 15);
        System.out.print("Presione ENTER para continuar...");
        sc.nextLine();
    }

    public void editar_perfil(Conductor conductor){
        HashMap<String, String> datos = new HashMap<>();
        Consola.cls();
        Consola.dibujar_rectangulo(72, 12, 33, 7);
        Consola.gotoxy(35, 5);
        System.out.println("----------------------- MODIFICAR PERFIL -----------------------");
        Consola.gotoxy(35, 7);
        System.out.println("Si no quiere modificar algun apartado solo dejelo en blanco y de ENTER");
        Consola.gotoxy(35, 9);
        System.out.print("Nuevos Nombres: ");
        datos.put("nombres", sc.nextLine());
        Consola.gotoxy(35, 11);
        System.out.print("Nuevos Apellidos: ");
        datos.put("apellidos", sc.nextLine());
        Consola.gotoxy(35, 13);
        System.out.print("Nuevo telefono: ");
        datos.put("telefono", sc.nextLine());
        Consola.gotoxy(35, 15);
        System.out.print("Nuevo distrito: ");
        datos.put("distrito", sc.nextLine());

        boolean exito = conductor.editar_perfil(datos);
        if(exito){
            Consola.gotoxy(35, 17);
            System.out.println("Cambios hechos correctamente...");
        } else{
            Consola.gotoxy(35, 17);
            System.out.println("OCURRIO UN ERROR");
        }
        Consola.gotoxy(35, 19);
        System.out.print("Presione ENTER para continuar...");
        sc.nextLine();
    }

    public void editar_dias_descanso(Conductor conductor){
        Consola.cls();
        Consola.dibujar_rectangulo(62, 8, 33, 7);
        Consola.gotoxy(35, 5);
        System.out.println("---------------- EDITAR DIAS DE DESCANSO ------------------");
        Consola.gotoxy(35, 7);
        System.out.println("ADVERTENCIA:");
        Consola.gotoxy(35, 8);
        System.out.println("Asegurese de ingresar los dias de la semana en minusculas");
        Consola.gotoxy(35, 9);
        System.out.println("ademas, evite usar tildes. Ej: lunes, martes, miercoles...");

        int limite_dias_descanso = Conductor.get_limite_dias_descanso();
        Consola.gotoxy(35, 12);
        System.out.println("Usted puede ingresar "+limite_dias_descanso+" dias...");

        if(limite_dias_descanso==0){
            Consola.gotoxy(35, 13);
            System.out.println("No se puede editar los dias de descanso");
            Consola.gotoxy(35, 15);
            System.out.print("Presione ENTER para continuar...");
            sc.nextLine();
            return;
        }

        List<String> dias = new ArrayList<>();
        for(int i = 0; i < limite_dias_descanso; i++){
            Consola.gotoxy(35, 13 + i);
            System.out.print("Dia "+(i+1)+": ");
            dias.add(sc.nextLine());
        }

        boolean exito = conductor.editar_dias_descanso(dias);
        if(exito){
            Consola.gotoxy(35, 18);
            System.out.println("SE EDITO CON EXITO LOS DIAS DE DESCANSO");
        } else {
            Consola.gotoxy(35, 18);
            System.out.println("NO SE PUDO EDITAR LOS DIAS DE DESCANSO");
        }
        Consola.gotoxy(35, 19);
        System.out.print("Presione ENTER para continuar...");
        sc.nextLine();
    }

    public void ver_viaje_asignado(Conductor conductor){
        Consola.cls();
        Consola.dibujar_rectangulo(42, 8, 33, 7);
        Consola.gotoxy(35, 5);
        System.out.println("----------- VER VIAJE ASIGNADO -----------");
        if(conductor.get_viaje_actual() == null){
            Consola.gotoxy(35, 7);
            System.out.println("No hay viaje asignado...");
        } else {
            Viaje viaje_actual = conductor.get_viaje_actual();
            Consola.gotoxy(35, 8);
            System.out.println("ID: "+viaje_actual.get_id());
            Consola.gotoxy(35, 9);
            System.out.println("Origen: "+viaje_actual.get_ruta().get_origen());
            Consola.gotoxy(35, 10);
            System.out.println("Destino: "+viaje_actual.get_ruta().get_destino());
            Consola.gotoxy(35, 11);
            System.out.println("Fecha de viaje: "+viaje_actual.get_fecha());
            Consola.gotoxy(35, 12);
            System.out.println("Pasajeros: "+viaje_actual.get_pasajeros());
        }
        Consola.gotoxy(35, 14);
        System.out.print("Presione ENTER para continuar...");
        sc.nextLine();
    }
}

