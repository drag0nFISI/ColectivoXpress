package Vista;
import Modelos.Cliente;
import Services.ClienteService;
import java.util.HashMap;
import java.util.Scanner;

//pseudo vista de menu principal
public class MenuPrincipal {

    static Scanner sc = new Scanner(System.in);
    static ClienteService cs = new ClienteService();

    public static void main(String[] args) {
        registrar_cliente();
    }

    public static void registrar_cliente(){
        HashMap<String, String> datos = new HashMap<String, String>();

        System.out.println("Ingrese su nombre: ");
        datos.put("nombres", sc.nextLine());
        System.out.println("Ingrese su apellido: ");
        datos.put("apellidos", sc.nextLine());
        System.out.println("Ingrese su telefono: ");
        datos.put("telefono", sc.nextLine());
        System.out.println("Ingrese su DNI: ");
        datos.put("dni", sc.nextLine());
        System.out.println("Ingrese su fecha de nacimiento: ");
        datos.put("fecha_nacimiento", sc.nextLine());
        System.out.println("Ingrese su distrito: ");
        datos.put("distrito", sc.nextLine());
        cs.registro_cliente(datos);
        datos = null;
    }


}
