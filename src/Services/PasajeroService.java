package Services;
import Models.Pasajero;
import Repository.PasajeroRepository;

import java.util.Scanner;
import java.util.HashMap;


public class PasajeroService {
    //todas las cosas que podemos hacer sobre los clientes, es un gestor de los clientes
    static Scanner sc = new Scanner(System.in);
    PasajeroRepository pr = new PasajeroRepository();

    public boolean registro_cliente(HashMap<String, String> datos){
        String nombres = datos.get("nombres");
        String apellidos = datos.get("apellidos");
        String telefono = datos.get("telefono");
        String dni = datos.get("dni");
        String distrito = datos.get("distrito");
        String fecha_nacimiento = datos.get("fecha_nacimiento");
        String contrasena = datos.get("contrasena");

        Pasajero nuevo_pasajero = new Pasajero(
                nombres, apellidos, telefono, dni, fecha_nacimiento, distrito, contrasena
        );


        boolean exito = pr.guardar_cliente(nuevo_pasajero);
        return exito;
    }

    public Pasajero login_cliente(String dni, String contrasena){
        Pasajero pasajero = pr.consultar_credenciales(dni, contrasena);
        return pasajero;
    }

    public Pasajero cliente_por_dni(String dni){
        Pasajero pasajero = pr.consultar_credenciales(dni, "");
        return pasajero;
    }

    public void mostrar_perfil(Pasajero pasajero){
        System.out.println("Nombre: "+ pasajero.nombres);
        System.out.println("Apellidos: "+ pasajero.apellidos);
        System.out.println("Telefono: "+ pasajero.telefono);
        System.out.println("DNI: "+ pasajero.dni);
        System.out.println("Fecha de Nacimiento: "+ pasajero.fecha_nacimiento);
        System.out.println("Distrito: "+ pasajero.distrito);
        System.out.println("Numero de viajes: "+ pasajero.numero_viajes);
    }

    public boolean editar_perfil(Pasajero pasajero, HashMap<String, String> datos){
        String nombres = datos.get("nombres");
        if(!nombres.equals("")){
            pasajero.nombres = nombres;
        }
        String apellidos = datos.get("apellidos");
        if(!apellidos.equals("")){
            pasajero.apellidos = apellidos;
        }
        String telefono = datos.get("telefono");
        if(!telefono.equals("")){
            pasajero.telefono = telefono;
        }
        String distrito = datos.get("distrito");
        if(!distrito.equals("")){
            pasajero.distrito = distrito;
        }

        boolean exito = pr.editar_cliente(pasajero);

        return exito;
    }
}
