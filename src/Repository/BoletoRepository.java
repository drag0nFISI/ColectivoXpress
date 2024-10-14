package Repository;

import Models.Boleto;
import Models.Conductor;
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

public class BoletoRepository {

    public boolean guardar_boleto(Boleto boleto){
        List<Boleto> boletos = null;

        try (Reader reader = new FileReader("src/resources/boletos.json")) {
            Type listType = new TypeToken<ArrayList<Boleto>>(){}.getType();
            Gson gson = new Gson();
            boletos = gson.fromJson(reader, listType);
        } catch (IOException e) {

        }

        if (boletos == null) {
            boletos = new ArrayList<>();
        }

        boletos.add(boleto);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String clientes_json = gson.toJson(boletos);

        try(FileWriter file = new FileWriter("src/resources/boletos.json")) {
            file.write(clientes_json);
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean eliminar_boleto(String id_boleto){
        List<Boleto> boletos = null;

        try (Reader reader = new FileReader("src/resources/boletos.json")) {
            Type listType = new TypeToken<ArrayList<Boleto>>(){}.getType();
            Gson gson = new Gson();
            boletos = gson.fromJson(reader, listType);
        } catch (IOException e) {

        }

        if (boletos == null) {
            return false;
        }

        for(Boleto boleto:boletos){
            if(boleto.get_id().equals(id_boleto)){
                boletos.remove(boleto);
                break;
            }
        }

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String clientes_json = gson.toJson(boletos);

        try(FileWriter file = new FileWriter("src/resources/boletos.json")) {
            file.write(clientes_json);
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Boleto buscar_boleto(String id_boleto){
        List<Boleto> boletos = null;

        try (Reader reader = new FileReader("src/resources/boletos.json")) {
            Type listType = new TypeToken<ArrayList<Boleto>>(){}.getType();
            Gson gson = new Gson();
            boletos = gson.fromJson(reader, listType);
        } catch (IOException e) {

        }

        if (boletos == null) {
            return null;
        }

        for(Boleto boleto:boletos){
            if(boleto.get_id().equals(id_boleto)){
                return boleto;
            }
        }
        return null;
    }

    public List<Boleto> obtener_boletos(){
        List<Boleto> boletos = null;

        try (Reader reader = new FileReader("src/resources/boletos.json")) {
            Type listType = new TypeToken<ArrayList<Boleto>>(){}.getType();
            Gson gson = new Gson();
            boletos = gson.fromJson(reader, listType);
        } catch (IOException e) {

        }

        if (boletos == null) {
            return null;
        }

        return boletos;
    }
}

