package Modelos;

import java.util.Objects;
//EL OBJETO CLIENTE COMO TAL
public class Cliente {
    public String nombres;
    public String apellidos;
    public String telefono;
    public String dni;
    public String distrito;
    public String fecha_nacimiento;
    public int numero_viajes;
    
    public Cliente(String nombres, String apellidos, String telefono, String dni, String fecha_nacimiento, String distrito){
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.distrito = distrito;
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

}
