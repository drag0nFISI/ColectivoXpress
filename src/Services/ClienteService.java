package Services;
import Modelos.Cliente;
import Datos.ClientesData;
import java.util.Scanner;
import java.util.HashMap;


public class ClienteService {
    //todas las cosas que podemos hacer sobre los clientes, es un gestor de los clientes
    static Scanner sc = new Scanner(System.in);

    public void registro_cliente(HashMap<String, String> datos){
        String nombres = datos.get("nombres");
        String apellidos = datos.get("apellidos");
        String telefono = datos.get("telefono");
        String dni = datos.get("dni");
        String distrito = datos.get("distrito");
        String fecha_nacimiento = datos.get("fecha_nacimiento");

        Cliente nuevo_cliente = new Cliente(
                nombres, apellidos, telefono, dni, fecha_nacimiento, distrito
        );

        ClientesData cd = new ClientesData();
        cd.guardar_cliente(nuevo_cliente);
    }

    public void mostrar_perfil(Cliente cliente){
        System.out.println("Nombre: "+cliente.nombres);
        System.out.println("Apellidos: "+cliente.apellidos);
        System.out.println("Telefono: "+cliente.telefono);
        System.out.println("DNI: "+cliente.dni);
        System.out.println("Fecha de Nacimiento: "+cliente.fecha_nacimiento);
        System.out.println("Distrito: "+cliente.distrito);
        System.out.println("Numero de viajes: "+cliente.numero_viajes);
    }
}
