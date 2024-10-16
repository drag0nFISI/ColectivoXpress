package Models;

import Repository.RutaRepository;

import java.util.HashMap;
import java.util.List;

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

        System.out.println("\n--------- RUTAS ----------");
        for(Ruta ruta:rutas){
            System.out.println("---------------------------");
            System.out.println("Origen: "+ruta.origen);
            System.out.println("Destino: "+ruta.destino);
            System.out.println("Tiempo aproximado: "+ruta.tiempo_aproximado);
            System.out.println("Precio: "+ruta.precio);
            System.out.println("Precio Oferta: "+ruta.precio_oferta);
        }
    }

    public static Ruta buscar_ruta(String origen, String destino){
        return rr.buscar_ruta(origen, destino);
    }

    public boolean editar_ruta(HashMap<String, String> datos){

        boolean eliminar = rr.eliminar_ruta(this.origen, this.destino);
        if(!eliminar){
            System.out.println("no se pudo eliminar");
            return false;
        }

        this.set_tiempo_aproximado(datos.get("tiempo_aproximado"));
        this.set_precio(Float.parseFloat(datos.get("precio")));
        this.set_precio_oferta(Float.parseFloat(datos.get("precio_oferta")));

        boolean guardar = rr.guardar_ruta(this);
        if(!guardar){
            System.out.println("no se pudo guardar");
            return false;
        }

        return true;
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
