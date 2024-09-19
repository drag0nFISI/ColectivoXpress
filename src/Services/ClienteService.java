package Services;
import Modelos.Cliente;
import java.util.Scanner;

public class ClienteService {
    //todas las cosas que podemos hacer sobre los clientes, es un gestor de los clientes
    static Scanner sc = new Scanner(System.in);

    public Cliente registro_cliente(){
        String nombres;
        String apellidos;
        String telefono;
        String dni;
        String distrito;
        String fecha_nacimiento;

        System.out.println("Ingrese su nombre: ");
        nombres = sc.nextLine();
        System.out.println("Ingrese sus apellidos: ");
        apellidos = sc.nextLine();
        System.out.println("Ingrese su telefono: ");
        telefono = sc.nextLine();
        System.out.println("Ingrese su dni: ");
        dni = sc.nextLine();
        System.out.println("Ingrese su distrito: ");
        distrito = sc.nextLine();
        System.out.println("Ingrese su fecha de nacimiento: ");
        fecha_nacimiento = sc.nextLine();

        Cliente nuevo_cliente = new Cliente(
                nombres, apellidos, telefono, dni, distrito, fecha_nacimiento
        );

        return nuevo_cliente;
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
