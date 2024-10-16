package Models;

import Repository.AdminRepository;

public class Admin {
    public String codigo;
    public String contrasena;

    public static AdminRepository ar = new AdminRepository();

    public Admin(String codigo, String contrasena){
        this.codigo = codigo;
        this.contrasena = contrasena;
    }

    public String get_codigo(){
        return this.codigo;
    }

    public String get_contrasena(){
        return this.contrasena;
    }

    public static boolean registrar_admin(String codigo, String contrasena){
        Admin nuevo_admin = new Admin(codigo, contrasena);

        boolean exito = ar.guardar_admin(nuevo_admin);
        return exito;
    }

    public static Admin login_admin(String codigo, String contrasena){
        Admin admin = ar.consultar_credenciales(codigo, contrasena);
        return admin;
    }
}
