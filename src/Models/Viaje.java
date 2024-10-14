package Models;

import Repository.ViajeRepository;

import java.util.List;

public class Viaje {

    public String id;
    public String fecha;
    public Ruta ruta;
    public int capacidad_pasajeros;
    public List<Pasajero> pasajeros;
    public Conductor conductor;

    public Viaje(String id, String fecha, Ruta ruta, Conductor conductor, int capacidad_pasajeros) {
        this.id = id;
        this.fecha = fecha;
        this.ruta = ruta;
        this.capacidad_pasajeros = capacidad_pasajeros;
        this.conductor = conductor;
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
    public Conductor get_conductor(){
        return this.conductor;
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
    public void set_conductor(Conductor nuevo_conductor){
        this.conductor = nuevo_conductor;
    }
    public void set_capacidad_pasajeros(int nueva_cantidad){
        this.capacidad_pasajeros = nueva_cantidad;
    }

    public boolean add_pasajero(Pasajero pasajero){
        if(this.pasajeros.size() >= this.capacidad_pasajeros){
            return false;
        }
        if(this.pasajeros.contains(pasajero)){
            return false;
        }
        this.pasajeros.add(pasajero);
        ViajeRepository vr = new ViajeRepository();
        return vr.editar_viaje(this);
    }

    public boolean delete_pasajero(Pasajero pasajero){
        if(this.pasajeros.size() == 0){
            return false;
        }
        if(this.pasajeros.contains(pasajero)){
            return false;
        }
        this.pasajeros.remove(pasajero);
        ViajeRepository vr = new ViajeRepository();
        return vr.editar_viaje(this);
    }


}
