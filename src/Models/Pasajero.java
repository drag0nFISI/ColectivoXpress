package Models;

import Repository.BoletoRepository;
import Repository.ConductorRepository;
import Repository.PasajeroRepository;
import Repository.ViajeRepository;

import java.util.HashMap;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

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
    public String id_viaje_actual;
    public int numero_viajes;

    public static PasajeroRepository pr = new PasajeroRepository();
    public static BoletoRepository br = new BoletoRepository();

    public Pasajero(String nombres, String apellidos, String telefono, String dni, String fecha_nacimiento, String distrito, String contrasena) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.dni = dni;
        this.fecha_nacimiento = fecha_nacimiento;
        this.distrito = distrito;
        this.contrasena = contrasena;
        this.id_viaje_actual = null;
        this.numero_viajes = 0;
    }

    public static boolean registro_cliente(HashMap<String, String> datos){
        String nombres = datos.get("nombres");
        String apellidos = datos.get("apellidos");
        String telefono = datos.get("telefono");
        String dni = datos.get("dni");
        String distrito = datos.get("distrito");
        String fecha_nacimiento = datos.get("fecha_nacimiento");
        String contrasena = datos.get("contrasena");

        Pasajero nuevo_pasajero = new Pasajero(
                nombres, apellidos, telefono, dni, fecha_nacimiento, distrito, contrasena
        );


        boolean exito = pr.guardar_cliente(nuevo_pasajero);
        return exito;
    }

    public static Pasajero login_cliente(String dni, String contrasena){
        Pasajero pasajero = pr.consultar_credenciales(dni, contrasena);
        return pasajero;
    }

    public static Pasajero cliente_por_dni(String dni){
        Pasajero pasajero = pr.consultar_credenciales(dni, "");
        return pasajero;
    }

    public static void enviarCorreo(String nombreCliente, String nombreConductor, String nombreRuta,
                                    String precio, String fechaSalida, String codigoTicket, String emailTo) {

        String emailFrom = "colectivoxpress@gmail.com";
        String passwordFrom = "yaca tceq ggdi xtnf";
        String subject = "Detalles de tu compra";

        String template =
                "<body style='font-size: 2.2vw;'>" +
                        "<div style='width: 50%; margin: 0 auto;'>" +
                        "<div style='text-align: center; background-color: #161616; padding-top: 15px; padding-bottom: 15px;'>" +
                        "<h1 style='color: #e7c049; display: inline; font-size: 1.5em;'>Colectivo</h1>" +
                        "<h1 style='color: #c94d65; display: inline; font-size: 1.5em;'>Xpress</h1>" +
                        "</div>" +

                        "<div style='text-align: left; padding: 15px; background-color: #ffffff; font-size: 0.4em;'>" +
                        "<p>Hola, <strong>" + nombreCliente + "</strong>,</p>" +
                        "<p>Gracias por tu compra. Aquí están los detalles de tu viaje:</p>" +

                        "<ul>" +
                        "<li><strong>Código de Ticket:</strong> " + codigoTicket + "</li>" +
                        "<li><strong>Conductor:</strong> " + nombreConductor + "</li>" +
                        "<li><strong>Ruta:</strong> " + nombreRuta + "</li>" +
                        "<li><strong>Precio pagado:</strong> $" + precio + "</li>" +
                        "<li><strong>Fecha de salida:</strong> " + fechaSalida + "</li>" +
                        "</ul>" +

                        "<p>A continuación, te dejamos 6 recomendaciones para tener un viaje seguro:</p>" +

                        "<ol>" +
                        "<li>No se olvide su identificación personal.</li>" +
                        "<li>Usa siempre el cinturón de seguridad.</li>" +
                        "<li>Procura llegar con tiempo.</li>" +
                        "<li>Sigue las indicaciones del conductor.</li>" +
                        "<li>No distraigas al conductor mientras maneja.</li>" +
                        "<li style='color: red;'>Si viaja con un menor, tener la documentación respectiva.</li>" +
                        "</ol>" +

                        "<p><a href='https://drive.google.com/file/d/1dSKyUJcgZqWjH_hRL7mE29eAVpdaQIsF/view?usp=sharing' target='_blank'>TyC</a></p>" +
                        "</div>" +

                        "<div style='text-align: center; background-color: #161616; padding-top: 15px; padding-bottom: 15px;'>" +
                        "<h1 style='color: #ffffff; display: inline; font-size: 0.7em;'>¡Gracias por su compra!</h1>" +
                        "</div>" +
                        "</div>" +
                        "</body>";

        Properties mProperties = new Properties();
        mProperties.put("mail.smtp.host", "smtp.gmail.com");
        mProperties.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        mProperties.setProperty("mail.smtp.starttls.enable", "true");
        mProperties.setProperty("mail.smtp.port", "587");
        mProperties.setProperty("mail.smtp.user", emailFrom);
        mProperties.setProperty("mail.smtp.ssl.protocols", "TLSv1.2");
        mProperties.setProperty("mail.smtp.auth", "true");

        Session mSession = Session.getDefaultInstance(mProperties);

        try {
            MimeMessage mCorreo = new MimeMessage(mSession);
            mCorreo.setFrom(new InternetAddress(emailFrom));
            mCorreo.addRecipient(Message.RecipientType.TO, new InternetAddress(emailTo));
            mCorreo.setSubject(subject);
            mCorreo.setContent(template, "text/html");

            Transport mTransport = mSession.getTransport("smtp");
            mTransport.connect(emailFrom, passwordFrom);
            mTransport.sendMessage(mCorreo, mCorreo.getRecipients(Message.RecipientType.TO));
            mTransport.close();

            System.out.println("Correo enviado exitosamente.");
        } catch (Exception e) {
            System.out.println("Error al enviar el correo: " + e.getMessage());
        }
    }

    public void mostrar_perfil(){
        System.out.println("Nombre: "+ this.nombres);
        System.out.println("Apellidos: "+ this.apellidos);
        System.out.println("Correo : "+ this.telefono);
        System.out.println("DNI: "+ this.dni);
        System.out.println("Fecha de Nacimiento: "+ this.fecha_nacimiento);
        System.out.println("Distrito: "+ this.distrito);
        System.out.println("Numero de viajes: "+ this.numero_viajes);
    }

    public boolean editar_perfil(HashMap<String, String> datos){
        String nombres = datos.get("nombres");
        if(!nombres.equals("")){
            this.nombres = nombres;
        }
        String apellidos = datos.get("apellidos");
        if(!apellidos.equals("")){
            this.apellidos = apellidos;
        }
        String telefono = datos.get("telefono");
        if(!telefono.equals("")){
            this.telefono = telefono;
        }
        String distrito = datos.get("distrito");
        if(!distrito.equals("")){
            this.distrito = distrito;
        }

        boolean exito = pr.editar_cliente(this);

        return exito;
    }

    // Método para generar un boleto basado en el viaje
    public Boleto generar_boleto(Viaje viaje) {

        if (viaje == null) {
            return null;
        }

        if(viaje.get_capacidad_pasajeros() == viaje.get_pasajeros().size()){
            return null;
        }

        Random random = new Random();
        String id = "";


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

        float precio = viaje.get_ruta().get_precio();

        // Crear un nuevo boleto usando el constructor adecuado
        Boleto boleto = new Boleto(this.get_dni(), viaje.get_id(), "MercadoPago", precio);
        return boleto;
    }

    public boolean guardar_boleto(Boleto boleto, Viaje viaje){
        boolean exito = br.guardar_boleto(boleto);
        if (exito) {
            this.id_viaje_actual = viaje.get_id();

            PasajeroRepository pr = new PasajeroRepository();
            pr.editar_cliente(this);
            viaje.add_pasajero(this);

            ViajeRepository vr = new ViajeRepository();
            vr.editar_viaje(viaje);

            ConductorRepository cr = new ConductorRepository();
            Conductor conductor = cr.consultar_credenciales(viaje.get_dni_conductor(), "");

            Pasajero.enviarCorreo(this.get_nombres(), conductor.get_nombres(),
                    viaje.get_ruta().get_origen()+" -> "+viaje.get_ruta().get_destino(),
                    Float.toString(viaje.get_ruta().get_precio()), viaje.get_fecha(), boleto.get_id(), this.telefono);
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
