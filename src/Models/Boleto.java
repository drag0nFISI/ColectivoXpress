package Models;

import Repository.PasajeroRepository;
import Repository.ViajeRepository;

public class Boleto {

    public String dni_pasajero;
    public String id_viaje;
    private String metodoPago;
    private float precio;

    // Constructor que acepta un pasajero, viaje, método de pago y precio
    public Boleto(String dni_pasajero, String id_viaje, String metodoPago, float precio) {
        this.dni_pasajero = dni_pasajero;
        this.id_viaje = id_viaje;
        this.metodoPago = metodoPago;
        this.precio = precio;
    }

    public String get_dni_pasajero() {
        return dni_pasajero;
    }

    public String get_id_viaje() {
        return id_viaje;
    }

    public String get_metodoPago() {
        return metodoPago;
    }

    public float get_precio() {
        return precio;
    }

    public String get_id() {
        ViajeRepository vr = new ViajeRepository();
        Viaje viaje = vr.obtener_viaje(id_viaje);
        PasajeroRepository pr = new PasajeroRepository();
        Pasajero pasajero = pr.consultar_credenciales(dni_pasajero, "");
        return viaje.get_id() + "-" + pasajero.get_id();
    }
}

