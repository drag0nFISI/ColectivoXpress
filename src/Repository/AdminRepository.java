package Repository;

import Models.Admin;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AdminRepository {

    public boolean guardar_admin(Admin nuevo_admin){

        List<Admin> admins = null;

        try (Reader reader = new FileReader("src/resources/admins.json")) {
            Type listType = new TypeToken<ArrayList<Admin>>(){}.getType();
            Gson gson = new Gson();
            admins = gson.fromJson(reader, listType);
        } catch (IOException e) {

        }

        if (admins == null) {
            admins = new ArrayList<>();
        }

        admins.add(nuevo_admin);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String clientes_json = gson.toJson(admins);

        try(FileWriter file = new FileWriter("src/resources/admins.json")) {
            file.write(clientes_json);
            return true;
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Admin consultar_credenciales(String codigo, String contrasena){
        List<Admin> admins = null;

        try (Reader reader = new FileReader("src/resources/admins.json")) {
            Type listType = new TypeToken<ArrayList<Admin>>(){}.getType();
            Gson gson = new Gson();
            admins = gson.fromJson(reader, listType);
        } catch (IOException e) {

        }

        if (admins == null) {
            admins = new ArrayList<>();
        }

        if(contrasena.equals("")){
            for(Admin admin : admins){
                if(admin.get_codigo().equals(codigo)){
                    return admin;
                }
            }
            return null;
        }

        for (Admin admin : admins){
            if(admin.get_codigo().equals(codigo) && admin.get_contrasena().equals(contrasena)){
                return admin;
            }
        }
        return null;
    }

    public HashMap<String, Object> obtener_configuraciones(){
        Gson gson = new Gson();
        Type tipoMapa = new TypeToken<HashMap<String, Object>>(){}.getType();
        HashMap<String, Object> configuraciones;

        try (FileReader reader = new FileReader("src/resources/configuraciones.json")) {
            configuraciones = gson.fromJson(reader, tipoMapa);

            return configuraciones;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean guardar_configuraciones(HashMap<String, Object> configuraciones){
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String jsonString = gson.toJson(configuraciones);

        try (FileWriter writer = new FileWriter("src/resources/configuraciones.json")) {
            writer.write(jsonString);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

}
