package app.my.mensaje_app_a7;

import android.os.AsyncTask;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

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
import java.util.logging.Logger;

/**
 * Esta aplicación requiere Android 6.0 para arriba
 */
public class MainActivity extends AppCompatActivity
{
    private EditText numero;
    private EditText mensaje;
    // private DatabaseHandler db;
    private String base_url = "http://192.168.1.104:8000/";
    private List<Integer> list_ids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = (EditText) findViewById(R.id.et_numero);
        mensaje = (EditText) findViewById(R.id.et_mensaje);
        // db = new DatabaseHandler(getApplicationContext());

        new Consultar().execute();
    }

    public void enviarSms(View view)
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(numero.getText().toString(), null, mensaje.getText().toString(), null, null);
        numero.getText().clear();
        mensaje.getText().clear();
        Toast.makeText(this, "SMS enviado", Toast.LENGTH_LONG).show();
    }

    public void rellenarSms(View view)
    {
        // Actividad ultima_actividad = db.getUltimaActividad();
        // numero.setText("0" + ultima_actividad.getTelefono());
        // String indicaciones = ultima_actividad.getResponsable() + " tienes asignada la actividad " + ultima_actividad.getActividad() + " en el área de " + ultima_actividad.getArea_actividad() + ", hasta la fecha " + ultima_actividad.getFecha_final() + " y consistirá en " + ultima_actividad.getResumen_actividad() + ". " + "Tu supervisor será " + ultima_actividad.getSupervisor() + ". " + "Favor de revisar el sistema para más detalles.";
        // mensaje.setText(indicaciones);
    }

    public void obtenerMensajesDesdeBD()
    {
        /* Video: https://www.youtube.com/watch?v=u0Rbi69ZA0U */
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url;
        HttpURLConnection conn = null;

        try
        {
            url = new URL(base_url);
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
                String texto = "";

                for (int i = 0; i < jsonArr.length(); i++)
                {
                    JSONObject jsonObject = jsonArr.getJSONObject(i);

                    list_ids.add(jsonObject.optInt("id"));

                    texto += "ID: " + jsonObject.optString("id") + " ";
                    texto += "EMPLEADO: " + jsonObject.optString("nombreEmpleado") + " ";
                    texto += "TELEFONO: " + jsonObject.optString("numeroTelefono") + " ";
                    texto += "TAREA: " + jsonObject.optString("nombreTarea") + " ";
                    texto += "\n";
                    mensaje.setText(texto);
                    Log.i("AVEL", texto);
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

    public void actualizarMensajesABD()
    {
        URL url;
        HttpURLConnection conn = null;
        String update_url = construirURLActualizacion();

        try
        {
            url = new URL(update_url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("PUT");
            conn.connect();

            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK)
            {
                System.out.println("HECHO");
            }
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

    public String construirURLActualizacion()
    {
        StringBuilder update_url = new StringBuilder(base_url + "/update/ids/");
        String id_format = "id[]=";

        for (int i = 0; i < list_ids.size(); i++)
        {
            // Si es la primera iteracion solo se agrega el simbolo "?" (signo de
            // interrogacion)
            if (i == 0) update_url.append("?");

            update_url.append(id_format);
            update_url.append(list_ids.get(i));

            // Si es la ultima iteracion se omite el simbolo "&" al final
            if (i + 1 == list_ids.size()) break;

            // De la segunda iteracion a la penultima se agrega el simbolo "&"
            update_url.append("&");
        }

        return update_url.toString();
    }

    private class Consultar extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            obtenerMensajesDesdeBD();
        }

        @Override
        protected Void doInBackground(Void... voids)
        {

            try
            {
                Thread.sleep(10000);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid)
        {
            list_ids.clear();
            new Consultar().execute();
        }
    }
}
