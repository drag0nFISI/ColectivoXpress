package Repository;
import Models.Conductor;
import Models.Viaje;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ViajeRepository {

    public boolean guardar_viaje(Viaje nuevo_viaje){
        List<Viaje> viajes = null;

        try (Reader reader = new FileReader("src/resources/viajes.json")) {
            Type listType = new TypeToken<ArrayList<Viaje>>(){}.getType();
            Gson gson = new Gson();
            viajes = gson.fromJson(reader, listType);
        } catch (IOException e) {

        }

        if (viajes == null) {
            viajes = new ArrayList<>();
        }

        if(viajes.contains(nuevo_viaje)){
            return false;
        }
        viajes.add(nuevo_viaje);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String viajes_json = gson.toJson(viajes);

        try(FileWriter file = new FileWriter("src/resources/viajes.json")) {
            file.write(viajes_json);
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean editar_viaje(Viaje viaje) {
        List<Viaje> viajes = null;

        try (Reader reader = new FileReader("src/resources/viajes.json")) {
            Type listType = new TypeToken<ArrayList<Viaje>>() {}.getType();
            Gson gson = new Gson();
            viajes = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (viajes == null) {
            return false;
        }

        boolean encontrado = false;
        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).get_id().equals(viaje.get_id())) {
                viajes.set(i, viaje);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            return false;
        }

        try (Writer writer = new FileWriter("src/resources/viajes.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(viajes, writer);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public Viaje obtener_viaje(String id){
        List<Viaje> viajes = null;

        try (Reader reader = new FileReader("src/resources/viajes.json")) {
            Type listType = new TypeToken<ArrayList<Viaje>>() {}.getType();
            Gson gson = new Gson();
            viajes = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (viajes == null) {
            return null;
        }

        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).get_id().equals(id)) {
                return viajes.get(i);
            }
        }

        return null;
    }

    public List<Viaje> obtener_viajes(){
        List<Viaje> viajes = null;

        try (Reader reader = new FileReader("src/resources/viajes.json")) {
            Type listType = new TypeToken<ArrayList<Viaje>>() {}.getType();
            Gson gson = new Gson();
            viajes = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        if (viajes == null) {
            return null;
        }

        return viajes;
    }

}
