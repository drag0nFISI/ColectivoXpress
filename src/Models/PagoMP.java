package Models;
import com.google.gson.Gson;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.Payer;
import okhttp3.*;

import java.io.IOException;
import java.util.List;

public class PagoMP {

    public static final String ACCESS_TOKEN = "APP_USR-7844775058804207-101721-0b3cf4153a9b280de84abf7a232b2800-1628474064";

    public static void init() throws MPConfException {
        MercadoPago.SDK.setAccessToken(ACCESS_TOKEN);
    }

    public static Preference crearPreferencia(String titulo, String descripcion, double precio, String correo) throws MPException {
        Preference preference = new Preference();

        Item item = new Item();
        item.setTitle(titulo)
                .setQuantity(1)
                .setUnitPrice((float) precio)
                .setDescription(descripcion);

        preference.appendItem(item);

        Payer payer = new Payer();
        payer.setEmail(correo);
        preference.setPayer(payer);

        preference.setExternalReference("BOLETO-"+System.currentTimeMillis());

        preference.save();
        return preference;
    }

    public static void redirigirAWeb(Preference preference) {
        String linkDePago = preference.getInitPoint();

        try {
            java.awt.Desktop.getDesktop().browse(new java.net.URI(linkDePago));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //---------------------------- CLASES PARA MAPIAR LA RESPUESTA JSON ----------------------------//

    class PagoResponse {
        List<Pago> results;
    }

    class Pago {
        String id;
        String status;
    }

    public static boolean verificarEstadoPago(String externalReference) {
        OkHttpClient client = new OkHttpClient();

        String url = "https://api.mercadopago.com/v1/payments/search?external_reference=" + externalReference;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("Authorization", "Bearer " + ACCESS_TOKEN)
                .build();

        try {
            // Hacer la llamada de forma síncrona
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return procesarRespuesta(responseBody); // Retorna si el pago fue exitoso o no
            } else {
                System.out.println("Error en la consulta del pago: " + response.message());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }

    private static boolean procesarRespuesta(String responseBody) {
        Gson gson = new Gson();
        PagoResponse pagoResponse = gson.fromJson(responseBody, PagoResponse.class);

        if (pagoResponse != null && pagoResponse.results != null && !pagoResponse.results.isEmpty()) {
            Pago pago = pagoResponse.results.get(0);
            System.out.println("Estado del pago: " + pago.status);
            System.out.println("ID del pago: " + pago.id);

            if ("approved".equals(pago.status)) {
                System.out.println("El pago fue aprobado.");
                return true;
            } else {
                System.out.println("El pago no fue aprobado o está pendiente.");
                return false;
            }
        } else {
            System.out.println("No se encontró ningún pago con la referencia externa proporcionada.");
            return false;
        }
    }

}

