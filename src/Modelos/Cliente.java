package Modelos;

import java.util.Objects;

public class Cliente {
    String nombres;
    String apellidos;
    String telefono;
    String dni;
    String distrito;
    String fecha_nacimiento;
    int numero_viajes;
    
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

    public void mostrar_perfil(){
        System.out.println("Nombre: "+this.nombres);
        System.out.println("Apellidos: "+this.apellidos);
        System.out.println("Telefono: "+this.telefono);
        System.out.println("DNI: "+this.dni);
        System.out.println("Fecha de Nacimiento: "+this.fecha_nacimiento);
        System.out.println("Distrito: "+this.distrito);
        System.out.println("Numero de viajes: "+this.numero_viajes);
    }
}
