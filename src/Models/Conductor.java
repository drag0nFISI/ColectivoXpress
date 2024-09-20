package Models;

public class Conductor {
    public String nombres;
    public String apellidos;
    public String telefono;
    public String dni;
    public String distrito;
    public String fecha_nacimiento;
    public String contrasena;
    public String[] dias_descanso;

    public Conductor(String nombres, String apellidos, String telefono, String dni, String fecha_nacimiento, String distrito, String contrasena){
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.distrito = distrito;
        this.contrasena = contrasena;
        this.dias_descanso = new String[0];
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
    public String[] get_dias_descanso(){
        return this.dias_descanso;
    }
}
