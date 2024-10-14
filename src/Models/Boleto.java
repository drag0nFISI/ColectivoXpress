package Models;

public class Boleto {
    public String id;
    public Viaje viaje;
    public String dni_pasajero;

    public Boleto(String id, Viaje viaje, String dni_pasajero) {
        this.id = id;
        this.viaje = viaje;
        this.dni_pasajero = dni_pasajero;
    }

    public String get_id() {
        return id;
    }

    public String get_dni_pasajero() {
        return dni_pasajero;
    }

    public Viaje get_viaje() {
        return viaje;
    }

}
