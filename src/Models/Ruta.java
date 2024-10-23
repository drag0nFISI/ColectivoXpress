package Models;

import Repository.RutaRepository;
import Views.Consola;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Ruta {

    public String origen;
    public String destino;
    public String tiempo_aproximado;
    public float precio;
    public float precio_oferta;

    public static RutaRepository rr = new RutaRepository();

    public Ruta(){

    }

    public static boolean agregar_ruta(HashMap<String, String> datos){

        String origen = datos.get("origen");
        String destino = datos.get("destino");
        String tiempo_aproximado = datos.get("tiempo_aproximado");
        float precio;
        try{
            precio = Float.parseFloat(datos.get("precio"));
        } catch(Exception e) {
            return false;
        }

        Ruta nueva_ruta = new Ruta();
        nueva_ruta.set_origen(origen);
        nueva_ruta.set_destino(destino);
        nueva_ruta.set_tiempo_aproximado(tiempo_aproximado);
        nueva_ruta.set_precio(precio);

        boolean exito = rr.guardar_ruta(nueva_ruta);

        return exito;
    }

    public static boolean eliminar_ruta(String origen, String destino){
        boolean exito = rr.eliminar_ruta(origen, destino);
        return exito;
    }

    public static void mostrar_rutas(){

        List<Ruta> rutas = rr.obtener_rutas();
        Scanner sc = new Scanner(System.in);

        Consola.cls();

        Consola.gotoxy(55, 2);
        System.out.print("Rutas");

        int aux = 1;

        for(Ruta ruta:rutas){
            Consola.dibujar_boton(20, 1, 5, 4+(2*aux), ruta.get_origen());
            Consola.dibujar_boton(20, 1, 26, 4+(2*aux), ruta.get_destino());
            Consola.dibujar_boton(20, 1, 47, 4+(2*aux), ruta.get_tiempo_aproximado());
            Consola.dibujar_boton(20, 1, 68, 4+(2*aux), Float.toString(ruta.get_precio()));
            Consola.dibujar_boton(20, 1, 89, 4+(2*aux), Float.toString(ruta.get_precio_oferta()));

            aux += 1;
        }
        Consola.change_color(7, 18);
        Consola.dibujar_boton(20, 1, 5, 4, "Origen");
        Consola.dibujar_boton(20, 1, 26, 4, "Destino");
        Consola.dibujar_boton(20, 1, 47, 4, "Tiempo Aprox. Viaje");
        Consola.dibujar_boton(20, 1, 68, 4, "Precio Regular");
        Consola.dibujar_boton(20, 1, 89, 4, "Precio Oferta");
        Consola.change_color(0, 7);

        Consola.gotoxy(80, 2);
        System.out.print("Presione ENTER para continuar...");
        sc.nextLine();
    }

    public static Ruta buscar_ruta(String origen, String destino){
        return rr.buscar_ruta(origen, destino);
    }

    public boolean editar_ruta(HashMap<String, String> datos){

        boolean eliminar = rr.eliminar_ruta(this.origen, this.destino);
        if(!eliminar){
            return false;
        }

        if(!datos.get("tiempo_aproximado").isEmpty()){
            this.set_tiempo_aproximado(datos.get("tiempo_aproximado"));
        }
        if(!datos.get("precio").isEmpty()){
            this.set_precio(Float.parseFloat(datos.get("precio")));
        }
        if(!datos.get("precio_oferta").isEmpty()){
            this.set_precio_oferta(Float.parseFloat(datos.get("precio_oferta")));
        }

        return rr.guardar_ruta(this);
    }


    public String get_origen(){
        return this.origen;
    }
    public String get_destino(){
        return this.destino;
    }
    public String get_tiempo_aproximado(){
        return this.tiempo_aproximado;
    }
    public float get_precio(){
        return this.precio;
    }
    public float get_precio_oferta(){
        return this.precio_oferta;
    }

    public void set_origen(String origen){
        this.origen = origen;
    }
    public void set_destino(String destino){
        this.destino = destino;
    }
    public void set_tiempo_aproximado(String tiempo_aproximado){
        this.tiempo_aproximado = tiempo_aproximado;
    }
    public void set_precio(float precio){
        this.precio = precio;
    }
    public void set_precio_oferta(float precio_oferta){
        this.precio_oferta = precio_oferta;
    }
}
