package Vista;
import Modelos.Cliente;
import Services.ClienteService;

public class MenuPrincipal {
    //pseudo vista de menu principal
    public static void main(String[] args) {
        ClienteService consola = new ClienteService();
        Cliente cliente1 = consola.registro_cliente();
        consola.mostrar_perfil(cliente1);
    }


}
