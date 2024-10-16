package Models;

import Repository.AdminRepository;
import Repository.ConductorRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Conductor {
    public static int limite_dias_descanso;
    public String nombres;
    public String apellidos;
    public String telefono;
    public String dni;
    public String distrito;
    public String fecha_nacimiento;
    public String contrasena;
    public List<String> dias_descanso;
    public Viaje viaje_actual;

    public static ConductorRepository cr = new ConductorRepository();

    public Conductor(String nombres, String apellidos, String telefono, String dni, String fecha_nacimiento, String distrito, String contrasena){
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.distrito = distrito;
        this.contrasena = contrasena;
        this.dias_descanso = new ArrayList<>();
        this.viaje_actual = null;
    }

    public static int get_limite_dias_descanso(){
        return limite_dias_descanso;
    }

    public static void set_limite_dias_descanso(int nuevo_limite){
        limite_dias_descanso = nuevo_limite;
    }

    public Viaje get_viaje_actual(){
        return this.viaje_actual;
    }
    public void set_viaje_actual(Viaje viaje_actual){
        this.viaje_actual = viaje_actual;
    }

    public String get_nombres(){
        return this.nombres;
    }
    public String get_apellidos(){
        return this.apellidos;
    }
    public String get_telefono(){
        return this.telefono;
    }
    public String get_dni(){
        return this.dni;
    }
    public String get_distrito(){
        return this.distrito;
    }
    public String get_fecha_nacimiento(){
        return this.fecha_nacimiento;
    }
    public String get_contrasena(){
        return this.contrasena;
    }
    public List<String> get_dias_descanso(){
        return this.dias_descanso;
    }


    public static boolean editar_limite_dias_descanso(int nuevo_limite){
        AdminRepository ar = new AdminRepository();
        HashMap<String, Object> configuraciones;
        try{
            configuraciones = ar.obtener_configuraciones();
        } catch (Exception ex){
            return false;
        }

        if(configuraciones==null){
            return false;
        }

        configuraciones.put("limite_dias_descanso", nuevo_limite);

        boolean exito = ar.guardar_configuraciones(configuraciones);


        if(exito){
            return true;
        } else {
            return false;
        }
    }

    public static HashMap<String, Object> obtener_configuraciones(){
        AdminRepository ar = new AdminRepository();
        HashMap<String, Object> configuraciones;
        try{
            configuraciones = ar.obtener_configuraciones();
        } catch (Exception ex){
            return null;
        }
        return configuraciones;
    }

    public static Conductor login_conductor(String dni, String contrasena){
        Conductor conductor = cr.consultar_credenciales(dni, contrasena);
        return conductor;
    }

    public static boolean registrar_conductor(HashMap<String, String> datos){
        String nombres = datos.get("nombres");
        String apellidos = datos.get("apellidos");
        String telefono = datos.get("telefono");
        String dni = datos.get("dni");
        String distrito = datos.get("distrito");
        String fecha_nacimiento = datos.get("fecha_nacimiento");
        String contrasena = datos.get("contrasena");

        Conductor nuevo_conductor = new Conductor(
                nombres, apellidos, telefono, dni, fecha_nacimiento, distrito, contrasena
        );


        boolean exito = cr.guardar_conductor(nuevo_conductor);
        return exito;
    }

    public static Conductor conductor_por_dni(String dni){
        Conductor conductor = cr.consultar_credenciales(dni, "");
        return conductor;
    }

    public static boolean eliminar_conductor(String dni){
        boolean exito = cr.eliminar_conductor(dni);
        return exito;
    }

    public void mostrar_perfil(){
        System.out.println("Nombre: "+ this.nombres);
        System.out.println("Apellidos: "+ this.apellidos);
        System.out.println("Telefono: "+ this.telefono);
        System.out.println("DNI: "+ this.dni);
        System.out.println("Fecha de Nacimiento: "+ this.fecha_nacimiento);
        System.out.println("Distrito: "+ this.distrito);
        System.out.println("Dias de descanso: "+ this.dias_descanso);
    }

    public boolean editar_perfil(HashMap<String, String> datos){
        String nombres = datos.get("nombres");
        if(!nombres.equals("")){
            this.nombres = nombres;
        }
        String apellidos = datos.get("apellidos");
        if(!apellidos.equals("")){
            this.apellidos = apellidos;
        }
        String telefono = datos.get("telefono");
        if(!telefono.equals("")){
            this.telefono = telefono;
        }
        String distrito = datos.get("distrito");
        if(!distrito.equals("")){
            this.distrito = distrito;
        }

        boolean exito = cr.editar_conductor(this);

        return exito;
    }

    public boolean editar_dias_descanso(List<String> dias_descanso){
        boolean eliminar = cr.eliminar_conductor(this.dni);
        if(!eliminar){
            return false;
        }

        if(dias_descanso.size() > limite_dias_descanso){
            return false;
        }
        try{
            this.dias_descanso = dias_descanso;
        } catch (Exception ex){
            return false;
        }

        boolean guardar = cr.guardar_conductor(this);
        if (!guardar){
            return false;
        }
        return true;
    }
}
