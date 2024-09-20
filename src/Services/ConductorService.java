package Services;

import Models.Conductor;
import Repository.ConductorRepository;

import java.util.HashMap;

public class ConductorService {

    static ConductorRepository cr = new ConductorRepository();

    public boolean registrar_conductor(HashMap<String, String> datos){
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

    public Conductor login_conductor(String dni, String contrasena){
        Conductor conductor = cr.consultar_credenciales(dni, contrasena);
        return conductor;
    }

    public Conductor conductor_por_dni(String dni){
        Conductor conductor = cr.consultar_credenciales(dni, "");
        return conductor;
    }

    public void mostrar_perfil(Conductor conductor){
        System.out.println("Nombre: "+ conductor.nombres);
        System.out.println("Apellidos: "+ conductor.apellidos);
        System.out.println("Telefono: "+ conductor.telefono);
        System.out.println("DNI: "+ conductor.dni);
        System.out.println("Fecha de Nacimiento: "+ conductor.fecha_nacimiento);
        System.out.println("Distrito: "+ conductor.distrito);
        System.out.println("Dias de descanso: "+ conductor.dias_descanso);
    }

    public boolean eliminar_conductor(String dni){
        boolean exito = cr.eliminar_conductor(dni);
        return exito;
    }

    public boolean editar_perfil(Conductor conductor, HashMap<String, String> datos){
        String nombres = datos.get("nombres");
        if(!nombres.equals("")){
            conductor.nombres = nombres;
        }
        String apellidos = datos.get("apellidos");
        if(!apellidos.equals("")){
            conductor.apellidos = apellidos;
        }
        String telefono = datos.get("telefono");
        if(!telefono.equals("")){
            conductor.telefono = telefono;
        }
        String distrito = datos.get("distrito");
        if(!distrito.equals("")){
            conductor.distrito = distrito;
        }

        boolean exito = cr.editar_conductor(conductor);

        return exito;
    }
}
