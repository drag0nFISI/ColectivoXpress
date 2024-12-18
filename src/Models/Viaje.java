package Models;

import Repository.ViajeRepository;
import java.util.ArrayList;
import java.util.List;

public class Viaje {

    public String id;
    public String fecha;
    public Ruta ruta;
    public int capacidad_pasajeros;
    public List<Pasajero> pasajeros;
    public String dni_conductor;

    static ViajeRepository vr = new ViajeRepository();

    public Viaje(String id, String fecha, Ruta ruta, String dni_conductor) {
        this.id = id;
        this.fecha = fecha;
        this.ruta = ruta;
        this.capacidad_pasajeros = 10;
        this.dni_conductor = dni_conductor;
        this.pasajeros = new ArrayList<>();
    }


    public String get_id(){
        return this.id;
    }
    public String get_fecha(){
        return this.fecha;
    }
    public Ruta get_ruta(){
        return this.ruta;
    }
    public List<Pasajero> get_pasajeros(){
        return this.pasajeros;
    }
    public String get_dni_conductor(){
        return this.dni_conductor;
    }
    public int get_capacidad_pasajeros(){
        return this.capacidad_pasajeros;
    }

    public void set_id(String nuevo_id){
        this.id = nuevo_id;
    }
    public void set_fecha(String nueva_fecha){
        this.fecha = nueva_fecha;
    }
    public void set_ruta(Ruta nueva_ruta){
        this.ruta = nueva_ruta;
    }
    public void set_conductor(String nuevo_dni_conductor){
        this.dni_conductor = nuevo_dni_conductor;
    }
    public void set_capacidad_pasajeros(int nueva_cantidad){
        this.capacidad_pasajeros = nueva_cantidad;
    }

    public boolean add_pasajero(Pasajero pasajero){
        if(this.pasajeros == null){
            this.pasajeros = new ArrayList<>();
        }
        if(this.pasajeros.size() >= this.capacidad_pasajeros){
            return false;
        }
        if(this.pasajeros.contains(pasajero)){
            return false;
        }
        this.pasajeros.add(pasajero);

        return vr.editar_viaje(this);
    }

    public boolean delete_pasajero(Pasajero pasajero){
        if(this.pasajeros.isEmpty()){
            return false;
        }
        if(this.pasajeros.contains(pasajero)){
            return false;
        }
        this.pasajeros.remove(pasajero);

        return vr.editar_viaje(this);
    }

    public static List<Viaje> buscar_viajes(String origen, String destino){
        List<Viaje> viajes = new ArrayList<>();
        List<Viaje> total_viajes = vr.obtener_viajes();

        for(Viaje viaje:total_viajes){
            int aux1 = 1;
            int aux2 = 1;
            if(!origen.isEmpty()){
                if(!viaje.get_ruta().get_origen().equals(origen)){
                    aux1 = 0;
                }
            }
            if(!destino.isEmpty()){
                if(!viaje.get_ruta().get_destino().equals(destino)){
                    aux2 = 0;
                }
            }
            if(aux1*aux2 == 1){
                viajes.add(viaje);
            }
        }

        return viajes;
    }
}
