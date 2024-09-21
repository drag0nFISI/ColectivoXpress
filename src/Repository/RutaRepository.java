package Repository;

import Models.Ruta;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class RutaRepository {

    public boolean guardar_ruta(Ruta nueva_ruta){

        Ruta consulta_ruta = buscar_ruta(nueva_ruta.origen, nueva_ruta.destino);
        if(consulta_ruta!=null){
            return false;
        }

        List<Ruta> rutas = null;

        try (Reader reader = new FileReader("src/resources/rutas.json")) {
            Type listType = new TypeToken<ArrayList<Ruta>>(){}.getType();
            Gson gson = new Gson();
            rutas = gson.fromJson(reader, listType);
        } catch (IOException e) {

        }

        if (rutas == null) {
            rutas = new ArrayList<>();
        }

        rutas.add(nueva_ruta);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String clientes_json = gson.toJson(rutas);
        System.out.println(clientes_json);

        try(FileWriter file = new FileWriter("src/resources/rutas.json")) {
            file.write(clientes_json);
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean eliminar_ruta(String origen, String destino){
        List<Ruta> rutas = null;

        try (Reader reader = new FileReader("src/resources/rutas.json")) {
            Type listType = new TypeToken<ArrayList<Ruta>>(){}.getType();
            Gson gson = new Gson();
            rutas = gson.fromJson(reader, listType);
        } catch (IOException e) {
        }

        if (rutas == null) {
            return false;
        }

        boolean encontrado = false;
        for (int i = 0; i < rutas.size(); i++) {
            if (rutas.get(i).get_origen().equals(origen) && rutas.get(i).get_destino().equals(destino)) {
                rutas.remove(i);
                encontrado = true;
                break;
            }
        }

        if(!encontrado){
            return false;
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String clientes_json = gson.toJson(rutas);
        System.out.println(clientes_json);

        try(FileWriter file = new FileWriter("src/resources/rutas.json")) {
            file.write(clientes_json);
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public Ruta buscar_ruta(String origen, String destino){
        List<Ruta> rutas = null;

        try (Reader reader = new FileReader("src/resources/rutas.json")) {
            Type listType = new TypeToken<ArrayList<Ruta>>(){}.getType();
            Gson gson = new Gson();
            rutas = gson.fromJson(reader, listType);
        } catch (IOException e) {
        }

        if (rutas == null) {
            return null;
        }

        for(Ruta ruta:rutas){
            if(ruta.get_origen().equals(origen) && ruta.get_destino().equals(destino)){
                System.out.println("Se encontro la ruta");
                return ruta;
            }
        }

        System.out.println("no se encontro la ruta");
        return null;
    }

    public List<Ruta> obtener_rutas(){
        List<Ruta> rutas = null;

        try (Reader reader = new FileReader("src/resources/rutas.json")) {
            Type listType = new TypeToken<ArrayList<Ruta>>(){}.getType();
            Gson gson = new Gson();
            rutas = gson.fromJson(reader, listType);
        } catch (IOException e) {
            return null;
        }

        return rutas;
    }
}
