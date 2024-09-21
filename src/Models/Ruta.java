package Models;

public class Ruta {

    public String origen;
    public String destino;
    public String tiempo_aproximado;
    public float precio;
    public float oferta_descuento;

    public Ruta(){

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
    public float get_oferta_descuento(){
        return this.oferta_descuento;
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
    public void set_oferta_descuento(float oferta_descuento){
        this.oferta_descuento = oferta_descuento;
    }
}
