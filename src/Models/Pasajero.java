package Models;

import Repository.BoletoRepository;
import Repository.PasajeroRepository;
import Repository.ViajeRepository;
import java.util.List;

import java.util.Objects;
import java.util.Random;

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

    public Pasajero(String nombres, String apellidos, String telefono, String dni, String fecha_nacimiento, String distrito, String contrasena) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.distrito = distrito;
        this.contrasena = contrasena;
        this.viaje_actual = null;
        this.numero_viajes = 0;
    }

    public void editar_perfil(String nombres, String apellidos, String telefono, String distrito) {
        if (!Objects.equals(nombres, "")) {
            this.nombres = nombres;
        }
        if (!Objects.equals(apellidos, "")) {
            this.apellidos = apellidos;
        }
        if (!Objects.equals(telefono, "")) {
            this.telefono = telefono;
        }
        if (!Objects.equals(distrito, "")) {
            this.distrito = distrito;
        }
    }

    // Método para generar un boleto basado en el viaje
    public boolean generar_boleto(Viaje viaje) {

        if (viaje == null) {
            return false;
        }

        Random random = new Random();
        String id = "";

        BoletoRepository br = new BoletoRepository();

        // Genera ID random con 5 dígitos para el boleto y verifica su unicidad
        boolean encontrado = true;
        while (encontrado) {
            int id_aux = 10000 + random.nextInt(90000);
            id = String.format("%05d", id_aux);

            Boleto boleto_encontrado = br.buscar_boleto(id);

            if (boleto_encontrado == null) {
                encontrado = false;
            }
        }

        // Definir el método de pago y precio del boleto
        String metodoPago = "Tarjeta";  // Aquí puedes usar el método de pago que desees
        float precio = viaje.get_precio();  // Suponiendo que el viaje tiene un precio

        // Crear un nuevo boleto usando el constructor adecuado
        Boleto boleto = new Boleto(this, viaje, metodoPago, precio);

        // Guardar el boleto en el repositorio
        boolean exito = br.guardar_boleto(boleto);
        if (exito) {
            this.viaje_actual = viaje;  // Asignar el viaje actual al pasajero
            PasajeroRepository pr = new PasajeroRepository();
            pr.editar_cliente(this);  // Actualizar la información del pasajero
            viaje.add_pasajero(this);  // Añadir el pasajero al viaje
            ViajeRepository vr = new ViajeRepository();
            vr.editar_viaje(viaje);  // Actualizar la información del viaje
            return true;
        }
        return false;
    }

    // Métodos getters para obtener la información del pasajero
    public String get_nombres() {
        return this.nombres;
    }

    public String get_apellidos() {
        return this.apellidos;
    }

    public String get_telefono() {
        return this.telefono;
    }

    public String get_dni() {
        return this.dni;
    }

    public String get_fecha_nacimiento() {
        return this.fecha_nacimiento;
    }

    public String get_distrito() {
        return this.distrito;
    }

    public String get_contrasena() {
        return this.contrasena;
    }

    public int get_numero_viajes() {
        return this.numero_viajes;
    }

    // Método para obtener el ID del pasajero (en este caso, el DNI)
    public String get_id() {
        return this.dni;  // El DNI se considera como el identificador único del pasajero
    }
}
