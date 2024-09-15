package Modelos;

public class Cliente {
    String nombres;
    String apellidos;
    String telefono;
    String dni;
    String distrito;
    int edad;
    int numero_viajes;
    
    public Cliente(String nombres, String apellidos, String telefono, String dni, int edad, String distrito){
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.edad = edad;
        this.distrito = distrito;
        this.numero_viajes = 0;
    }
    
    public void comprar_asiento(){
        
    }
    
    
    
}
