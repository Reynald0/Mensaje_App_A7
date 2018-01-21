package app.my.mensaje_app_a7;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Esta aplicación requiere Android 6.0 para arriba
 */
public class MainActivity extends AppCompatActivity
{
    private static EditText mensaje;
    // private DatabaseHandler db;
    private static String baseUrl = "http://192.168.1.104:8000/";
    private static List<DatosMensaje> ListaDatosObtenidos = new ArrayList<>();
    private static int DELAY = 60000 * 5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mensaje = (EditText) findViewById(R.id.et_mensaje);
        // db = new DatabaseHandler(getApplicationContext());
        mensaje.setFocusable(false);
        mensaje.setText("Iniciando...");
        new Consultar().execute();
    }

    public static void enviarSms(String numeroTelefono, String mensaje)
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(numeroTelefono, null, mensaje, null, null);
    }

    public void rellenarSms(View view)
    {
        // Actividad ultima_actividad = db.getUltimaActividad();
        // numero.setText("0" + ultima_actividad.getTelefono());
        // String indicaciones = ultima_actividad.getResponsable() + " tienes asignada la actividad " + ultima_actividad.getActividad() + " en el área de " + ultima_actividad.getArea_actividad() + ", hasta la fecha " + ultima_actividad.getFecha_final() + " y consistirá en " + ultima_actividad.getResumen_actividad() + ". " + "Tu supervisor será " + ultima_actividad.getSupervisor() + ". " + "Favor de revisar el sistema para más detalles.";
        // mensaje.setText(indicaciones);
    }

    public static void obtenerMensajesDesdeBD()
    {
        /* Video: https://www.youtube.com/watch?v=u0Rbi69ZA0U */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url;
        HttpURLConnection conn = null;

        try
        {
            url = new URL(baseUrl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();
                String json;

                while ((inputLine = in.readLine()) != null)
                {
                    response.append(inputLine);
                }

                json = response.toString();
                JSONArray jsonArr;
                jsonArr = new JSONArray(json);

                for (int i = 0; i < jsonArr.length(); i++)
                {
                    JSONObject jsonObject = jsonArr.getJSONObject(i);
                    DatosMensaje data = new DatosMensaje(jsonObject.optString("id"),
                                                         jsonObject.optString("numeroTelefono"),
                                                         jsonObject.optString("nombreEmpleado"),
                                                         jsonObject.optString("nombreTarea"),
                                                         jsonObject.optString("horaInicio"));

                    ListaDatosObtenidos.add(data);
                }
            }
        }
        catch (IOException | JSONException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                conn.disconnect();
            }
        }
    }

    /*
     * Este metodo envia por medio de PUT los ID de los mensajes que han sido enviados
     */
    public static void actualizarMensajesEnBD()
    {
        URL url;
        HttpURLConnection conn = null;
        String update_url = construirURLActualizacion();

        try
        {
            url = new URL(update_url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.connect();

            // Si la respuesta de la conexion es exitosa (codigo 200)
            if (conn.getResponseCode() == 200)
                mensaje.append("\n Mensajes actualizados en base de datos");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (conn != null)
            {
                conn.disconnect();
            }
        }
    }

    public static String construirURLActualizacion()
    {
        StringBuilder updateUrl = new StringBuilder(baseUrl + "update/ids/");
        String idFormat = "id[]=";

        for (int i = 0; i < ListaDatosObtenidos.size(); i++)
        {
            // Si es la primera iteracion solo se agrega el simbolo "?" (signo de
            // interrogacion)
            if (i == 0) updateUrl.append("?");

            updateUrl.append(idFormat);
            DatosMensaje data = ListaDatosObtenidos.get(i);
            updateUrl.append(data.getId());

            // Si es la ultima iteracion se omite el simbolo "&" al final
            if (i + 1 == ListaDatosObtenidos.size()) break;

            // De la segunda iteracion a la penultima se agrega el simbolo "&"
            updateUrl.append("&");
        }

        return updateUrl.toString();
    }

    private static class Consultar extends AsyncTask<Void, DatosMensaje, Void>
    {
        @Override
        protected void onPreExecute()
        {
            obtenerMensajesDesdeBD();
        }

        @Override
        protected Void doInBackground(Void... voids)
        {
            // try
            // {
                // Esperar cierta cantidad de milisegundos
            //    Thread.sleep(DELAY);
            // }
            // catch (InterruptedException e)
            // {
            //    e.printStackTrace();
            // }
            for(DatosMensaje data : ListaDatosObtenidos)
            {
                // Si existe numero de telefono
                // if(data.getNumeroTelefono() != null)
                //    publishProgress(data);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(DatosMensaje... values)
        {
            // DatosMensaje data = values[0];
            // enviarSms(data.getNumeroTelefono(), data.getMensajeTexto());
        }

        @Override
        protected void onPostExecute(Void aVoid)
        {
            enviarSms("0449934428441", "texto de prueba");
            // Si la lista de datos obtenidos NO es vacia, es decir, tiene registros
            if(!ListaDatosObtenidos.isEmpty())
            {
                actualizarMensajesEnBD();
                ListaDatosObtenidos.clear();
            }
            mensaje.append("\nProxima ronda en: " + DELAY / 1000 + " segundos");
            // Al terminar se manda llamar nuevamente la tarea, de esta forma se hace un ciclo infinito
            // new Consultar().execute();

        }
    }
}
