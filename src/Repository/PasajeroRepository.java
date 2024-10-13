package Repository;
import Models.Pasajero;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//ACA VA A ESTAR LA CONEXION A LA PSEUDO BASE DE DATOS / JSON / XML / CSV

public class PasajeroRepository {
    public boolean guardar_cliente(Pasajero nuevo_pasajero){

        List<Pasajero> pasajeros = null;

        try (Reader reader = new FileReader("src/resources/pasajeros.json")) {
            Type listType = new TypeToken<ArrayList<Pasajero>>(){}.getType();
            Gson gson = new Gson();
            pasajeros = gson.fromJson(reader, listType);
        } catch (IOException e) {

        }

        if (pasajeros == null) {
            pasajeros = new ArrayList<>();
        }

        pasajeros.add(nuevo_pasajero);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String clientes_json = gson.toJson(pasajeros);

        try(FileWriter file = new FileWriter("src/resources/pasajeros.json")) {
            file.write(clientes_json);
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Pasajero consultar_credenciales(String dni, String contrasena){
        List<Pasajero> pasajeros = null;

        try (Reader reader = new FileReader("src/resources/pasajeros.json")) {
            Type listType = new TypeToken<ArrayList<Pasajero>>(){}.getType();
            Gson gson = new Gson();
            pasajeros = gson.fromJson(reader, listType);
        } catch (IOException e) {

        }

        if (pasajeros == null) {
            pasajeros = new ArrayList<>();
        }

        if(contrasena.equals("")){
            for(Pasajero pasajero : pasajeros){
                if(pasajero.get_dni().equals(dni)){
                    return pasajero;
                }
            }
            return null;
        }

        for (Pasajero pasajero : pasajeros){
            if(pasajero.get_dni().equals(dni) && pasajero.get_contrasena().equals(contrasena)){
                return pasajero;
            }
        }
        return null;
    }

    public boolean editar_cliente(Pasajero pasajero) {
        List<Pasajero> pasajeros = null;

        try (Reader reader = new FileReader("src/resources/pasajeros.json")) {
            Type listType = new TypeToken<ArrayList<Pasajero>>() {}.getType();
            Gson gson = new Gson();
            pasajeros = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (pasajeros == null) {
            return false;
        }

        boolean encontrado = false;
        for (int i = 0; i < pasajeros.size(); i++) {
            if (pasajeros.get(i).get_dni().equals(pasajero.get_dni())) {
                pasajeros.set(i, pasajero);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            return false;
        }

        try (Writer writer = new FileWriter("src/resources/pasajeros.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(pasajeros, writer);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

}
