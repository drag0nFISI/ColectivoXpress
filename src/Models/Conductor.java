package Models;

import java.util.ArrayList;
import java.util.List;

public class Conductor {
    public static int limite_dias_descanso;
    public String nombres;
    public String apellidos;
    public String telefono;
    public String dni;
    public String distrito;
    public String fecha_nacimiento;
    public String contrasena;
    public List<String> dias_descanso;

    public Conductor(String nombres, String apellidos, String telefono, String dni, String fecha_nacimiento, String distrito, String contrasena){
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.distrito = distrito;
        this.contrasena = contrasena;
        this.dias_descanso = new ArrayList<>();
    }

    public static int get_limite_dias_descanso(){
        return limite_dias_descanso;
    }

    public static void set_limite_dias_descanso(int nuevo_limite){
        limite_dias_descanso = nuevo_limite;
    }

    public String get_nombres(){
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
    public String get_distrito(){
        return this.distrito;
    }
    public String get_fecha_nacimiento(){
        return this.fecha_nacimiento;
    }
    public String get_contrasena(){
        return this.contrasena;
    }
    public List<String> get_dias_descanso(){
        return this.dias_descanso;
    }

    public boolean set_dias_descanso(List<String> dias_descanso){
        if(dias_descanso.size() > limite_dias_descanso){
            return false;
        }
        this.dias_descanso = dias_descanso;
        return true;
    }
}
