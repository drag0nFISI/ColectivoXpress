package Models;

import java.util.Objects;
//EL OBJETO CLIENTE COMO TAL
public class Pasajero {
    public String nombres;
    public String apellidos;
    public String telefono;
    public String dni;
    public String distrito;
    public String fecha_nacimiento;
    public String contrasena;
    public int numero_viajes;
    
    public Pasajero(String nombres, String apellidos, String telefono, String dni, String fecha_nacimiento, String distrito, String contrasena){
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.distrito = distrito;
        this.contrasena = contrasena;
        this.numero_viajes = 0;
    }
    
    public void editar_perfil(String nombres, String apellidos, String telefono, String distrito){
        if(!Objects.equals(nombres, "")){
            this.nombres = nombres;
        }
        if(!Objects.equals(apellidos, "")){
            this.apellidos = apellidos;
        }
        if(!Objects.equals(telefono, "")){
            this.telefono = telefono;
        }
        if(!Objects.equals(distrito, "")){
            this.distrito = distrito;
        }
    }

    public String get_nombres() {
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
    public String get_fecha_nacimiento(){
        return this.fecha_nacimiento;
    }
    public String get_distrito(){
        return this.distrito;
    }
    public String get_contrasena(){
        return this.contrasena;
    }
    public int get_numero_viajes(){
        return this.numero_viajes;
    }
}
