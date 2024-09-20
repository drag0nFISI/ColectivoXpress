package Services;

import Models.Admin;
import Repository.AdminRepository;

import java.util.HashMap;

public class AdminService {

    AdminRepository ar = new AdminRepository();

    public boolean registrar_admin(String codigo, String contrasena){
        Admin nuevo_admin = new Admin(codigo, contrasena);

        boolean exito = ar.guardar_admin(nuevo_admin);
        return exito;
    }

    public Admin login_admin(String codigo, String contrasena){
        Admin admin = ar.consultar_credenciales(codigo, contrasena);
        return admin;
    }

}
