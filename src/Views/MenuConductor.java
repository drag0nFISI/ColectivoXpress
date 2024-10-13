package Views;

import Models.Conductor;
import Services.ConductorService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import java.util.HashMap;

public class MenuConductor {

    static ConductorService cs = new ConductorService();
    static Scanner sc = new Scanner(System.in);

    public static void main(Conductor conductor) {
        MenuConductor mc = new MenuConductor();

        mc.comprobaciones_automaticas();
           
        boolean stay = true;
        while(stay){

            System.out.println("------------ MENU DE CONDUCTOR ------------");
            System.out.println("Elija la opcion que desea: ");
            System.out.println("1. Ver mi perfil");
            System.out.println("2. Editar mi perfil");
            System.out.println("3. Editar mis dias de descanso");
            System.out.println("4. Volver");
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
                    stay = false;
                    break;
                default:
                    System.out.println("Escoja una opcion valida...");
            }
        }
    }

    public void comprobaciones_automaticas(){
        HashMap<String, Object> configuraciones = cs.obtener_configuraciones();
        if(configuraciones ==null){
            return;
        }
        if(configuraciones.get("limite_dias_descanso") != null){
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
}
