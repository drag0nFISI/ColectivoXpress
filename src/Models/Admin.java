package Models;

public class Admin {
    public String codigo;
    public String contrasena;

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
}
