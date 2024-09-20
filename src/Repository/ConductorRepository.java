package Repository;

import Models.Conductor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ConductorRepository {

    public boolean guardar_conductor(Conductor nuevo_conductor){

        List<Conductor> conductores = null;

        try (Reader reader = new FileReader("src/resources/conductores.json")) {
            Type listType = new TypeToken<ArrayList<Conductor>>(){}.getType();
            Gson gson = new Gson();
            conductores = gson.fromJson(reader, listType);
        } catch (IOException e) {

        }

        if (conductores == null) {
            conductores = new ArrayList<>();
        }

        conductores.add(nuevo_conductor);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String clientes_json = gson.toJson(conductores);
        System.out.println(clientes_json);

        try(FileWriter file = new FileWriter("src/resources/conductores.json")) {
            file.write(clientes_json);
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Conductor consultar_credenciales(String dni, String contrasena){
        List<Conductor> conductores = null;

        try (Reader reader = new FileReader("src/resources/conductores.json")) {
            Type listType = new TypeToken<ArrayList<Conductor>>(){}.getType();
            Gson gson = new Gson();
            conductores = gson.fromJson(reader, listType);
        } catch (IOException e) {

        }

        if (conductores == null) {
            conductores = new ArrayList<>();
        }

        if(contrasena.equals("")){
            for(Conductor conductor : conductores){
                if(conductor.get_dni().equals(dni)){
                    return conductor;
                }
            }
            return null;
        }

        for (Conductor conductor : conductores){
            if(conductor.get_dni().equals(dni) && conductor.get_contrasena().equals(contrasena)){
                return conductor;
            }
        }
        return null;
    }


    public boolean editar_conductor(Conductor conductor) {
        List<Conductor> conductores = null;

        try (Reader reader = new FileReader("src/resources/conductores.json")) {
            Type listType = new TypeToken<ArrayList<Conductor>>() {}.getType();
            Gson gson = new Gson();
            conductores = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (conductores == null) {
            return false;
        }

        boolean encontrado = false;
        for (int i = 0; i < conductores.size(); i++) {
            if (conductores.get(i).get_dni().equals(conductor.get_dni())) {
                conductores.set(i, conductor);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            return false;
        }

        try (Writer writer = new FileWriter("src/resources/conductores.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(conductores, writer);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public boolean eliminar_conductor(String dni){
        List<Conductor> conductores = null;

        try (Reader reader = new FileReader("src/resources/conductores.json")) {
            Type listType = new TypeToken<ArrayList<Conductor>>() {}.getType();
            Gson gson = new Gson();
            conductores = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        if (conductores == null) {
            return false;
        }

        boolean encontrado = false;
        for (int i = 0; i < conductores.size(); i++) {
            if (conductores.get(i).get_dni().equals(dni)) {
                conductores.remove(i);
                encontrado = true;
                break;
            }
        }

        if (!encontrado) {
            return false;
        }

        try (Writer writer = new FileWriter("src/resources/conductores.json")) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(conductores, writer);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


}
