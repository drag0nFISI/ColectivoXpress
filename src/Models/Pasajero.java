package Models;

import Repository.BoletoRepository;
import Repository.PasajeroRepository;
import Repository.ViajeRepository;

import java.util.List;
import java.util.Objects;
import java.util.Random;

//EL OBJETO CLIENTE COMO TAL
public class Pasajero {
    public String nombres;
    public String apellidos;
    public String telefono;
    public String dni;
    public String distrito;
    public String fecha_nacimiento;
    public String contrasena;
    public Viaje viaje_actual;
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


    public boolean generar_boleto(Viaje viaje){

        if(viaje==null){
            return false;
        }

        Random random = new Random();
        String id = "";

        BoletoRepository br = new BoletoRepository();

        //Genera ID random con 5 digitos para boleto... realiza comprobacion
        boolean encontrado = true;
        while(encontrado){
            int id_aux = 10000 + random.nextInt(90000);
            id = String.format("%05d", id_aux);

            Boleto boleto_encontrado = br.buscar_boleto(id);

            if(boleto_encontrado == null){
                encontrado = false;
            }
        }
        Boleto boleto = new Boleto(id, viaje, this.get_dni());
        boolean exito = br.guardar_boleto(boleto);
        if(exito){
            this.viaje_actual = viaje;
            PasajeroRepository pr = new PasajeroRepository();
            pr.editar_cliente(this);
            viaje.add_pasajero(this);
            ViajeRepository vr = new ViajeRepository();
            vr.editar_viaje(viaje);
            return true;
        }
        return false;
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
