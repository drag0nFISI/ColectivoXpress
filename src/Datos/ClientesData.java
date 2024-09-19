package Datos;
import Modelos.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

//ACA VA A ESTAR LA CONEXION A LA PSEUDO BASE DE DATOS / JSON / XML / CSV

public class ClientesData {
    public void guardar_cliente(Cliente nuevo_cliente){

        List<Cliente> clientes = null;

        try (Reader reader = new FileReader("src/resources/datos_prueba.json")) {
            Type listType = new TypeToken<ArrayList<Cliente>>(){}.getType();
            Gson gson = new Gson();
            clientes = gson.fromJson(reader, listType);
        } catch (IOException e) {

        }

        if (clientes == null) {
            clientes = new ArrayList<>();
        }

        clientes.add(nuevo_cliente);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String clientes_json = gson.toJson(clientes);
        System.out.println(clientes_json);

        try(FileWriter file = new FileWriter("src/resources/datos_prueba.json")) {
            file.write(clientes_json);
            System.out.println("Datos guardados en datos_prueba.json");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
