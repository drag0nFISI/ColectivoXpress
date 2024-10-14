package Models;

public class Boleto {

    private Pasajero pasajero;
    private Viaje viaje;
    private String metodoPago;
    private float precio;

    // Constructor que acepta un pasajero, viaje, método de pago y precio
    public Boleto(Pasajero pasajero, Viaje viaje, String metodoPago, float precio) {
        this.pasajero = pasajero;
        this.viaje = viaje;
        this.metodoPago = metodoPago;
        this.precio = precio;
    }

    public Pasajero get_pasajero() {
        return pasajero;
    }

    public Viaje get_viaje() {
        return viaje;
    }

    public String get_metodoPago() {
        return metodoPago;
    }

    public float get_precio() {
        return precio;
    }

    public String get_id() {
        return viaje.get_id() + "-" + pasajero.get_id();
    }
}

