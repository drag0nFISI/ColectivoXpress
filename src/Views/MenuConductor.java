package Views;

import Models.Conductor;
import Models.Ruta;
import Models.Viaje;
import Repository.RutaRepository;
import Repository.ViajeRepository;
import Services.ConductorService;

import javax.management.relation.RelationNotFoundException;
import java.time.LocalDate;
import java.util.*;

public class MenuConductor {

    static ConductorService cs = new ConductorService();
    static Scanner sc = new Scanner(System.in);

    public static void main(Conductor conductor) {
        MenuConductor mc = new MenuConductor();

        mc.comprobaciones_automaticas();
        mc.comprobar_viaje_actual(conductor);
           
        boolean stay = true;
        while(stay){

            System.out.println("------------ MENU DE CONDUCTOR ------------");
            System.out.println("Elija la opcion que desea: ");
            System.out.println("1. Ver mi perfil");
            System.out.println("2. Editar mi perfil");
            System.out.println("3. Editar mis dias de descanso");
            System.out.println("4. Ver viaje asignado");
            System.out.println("5. Volver");
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

                Viaje viaje = new Viaje(id, fecha_viaje.toString(), ruta_elegida, conductor, 0);

                vr.guardar_viaje(viaje);
                conductor.set_viaje_actual(viaje);
            }
        }
    }

    public void comprobaciones_automaticas(){
        HashMap<String, Object> configuraciones = cs.obtener_configuraciones();
        if(configuraciones ==null){
            return;
        }
        if(configuraciones.get("limite_dias_descanso") != null) {
            double limite = Double.parseDouble(configuraciones.get("limite_dias_descanso").toString());
            Conductor.set_limite_dias_descanso((int) limite);
        }
    }

    public void ver_perfil(Conductor conductor){
        cs.mostrar_perfil(conductor);
    }

    public void editar_perfil(Conductor conductor){
        HashMap<String, String> datos = new HashMap<>();
        System.out.println("\n----------- MODIFICAR PERFIL -----------");
        System.out.println("Si no quiere modificar algun apartado solo dejelo en blando y de ENTER");
        System.out.println("Nuevos Nombres: ");
        datos.put("nombres", sc.nextLine());
        System.out.println("Nuevos Apellidos: ");
        datos.put("apellidos", sc.nextLine());
        System.out.println("Nuevo telefono: ");
        datos.put("telefono", sc.nextLine());
        System.out.println("Nuevo distrito: ");
        datos.put("distrito", sc.nextLine());

        boolean exito = cs.editar_perfil(conductor, datos);
        if(exito){
            System.out.println("Cambios hechos correctamente...");
        } else{
            System.out.println("OCURRIO UN ERROR");
        }
    }

    public void editar_dias_descanso(Conductor conductor){
        System.out.println("\n---------- EDITAR DIAS DE DESCANSO -----------");
        System.out.println("ADVERTENCIA:");
        System.out.println("Asegurese de ingresar los dias de la semana en minusculas");
        System.out.println("ademas, evite usar tildes. Ej: lunes, martes, miercoles...");

        int limite_dias_descanso = Conductor.get_limite_dias_descanso();
        System.out.println("\nUsted puede ingresar "+limite_dias_descanso+" dias...");

        if(limite_dias_descanso==0){
            System.out.println("No se puede editar los dias de descanso");
            return;
        }

        List<String> dias = new ArrayList<>();
        for(int i = 0; i < limite_dias_descanso; i++){
            System.out.println("Dia "+(i+1)+": ");
            dias.add(sc.nextLine());
        }

        boolean exito = cs.editar_dias_descanso(conductor, dias);
        if(exito){
            System.out.println("SE EDITO CON EXITO LOS DIAS DE DESCANSO");
        } else {
            System.out.println("NO SE PUDO EDITAR LOS DIAS DE DESCANSO");
        }
    }

    public void ver_viaje_asignado(Conductor conductor){
        System.out.println("\n----------- VER VIAJE ASIGNADO -----------");
        if(conductor.get_viaje_actual() == null){
            System.out.println("No hay viaje asignado...");
        } else {
            Viaje viaje_actual = conductor.get_viaje_actual();
            System.out.println("ID: "+viaje_actual.get_id());
            System.out.println("Origen: "+viaje_actual.get_ruta().get_origen());
            System.out.println("Destino: "+viaje_actual.get_ruta().get_destino());
            System.out.println("Fecha de viaje: "+viaje_actual.get_fecha());
            System.out.println("Pasajeros: "+viaje_actual.get_pasajeros());
        }
    }
}
