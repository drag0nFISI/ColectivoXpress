package Services;

import Models.Ruta;
import Repository.RutaRepository;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class RutaService {

    RutaRepository rr = new RutaRepository();

    public boolean agregar_ruta(HashMap<String, String> datos){

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

    public boolean editar_ruta(Ruta ruta, HashMap<String, String> datos){

        boolean eliminar = rr.eliminar_ruta(ruta.origen, ruta.destino);
        if(!eliminar){
            System.out.println("no se pudo eliminar");
            return false;
        }

        ruta.set_tiempo_aproximado(datos.get("tiempo_aproximado"));
        ruta.set_precio(Float.parseFloat(datos.get("precio")));
        ruta.set_precio_oferta(Float.parseFloat(datos.get("precio_oferta")));

        boolean guardar = rr.guardar_ruta(ruta);
        if(!guardar){
            System.out.println("no se pudo guardar");
            return false;
        }

        return true;
    }

    public boolean eliminar_ruta(String origen, String destino){
        boolean exito = rr.eliminar_ruta(origen, destino);
        return exito;
    }

    public void mostrar_rutas(){
        HashMap<String, String> resultado = new HashMap<>();

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

    public Ruta buscar_ruta(String origen, String destino){
        return rr.buscar_ruta(origen, destino);
    }

}
