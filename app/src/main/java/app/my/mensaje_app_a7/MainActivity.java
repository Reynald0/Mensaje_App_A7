package app.my.mensaje_app_a7;

import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
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
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Esta aplicación requiere Android 6.0 para arriba
 */
public class MainActivity extends AppCompatActivity
{
    private EditText numero;
    private EditText mensaje;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = (EditText) findViewById(R.id.et_numero);
        mensaje = (EditText) findViewById(R.id.et_mensaje);
        db = new DatabaseHandler(getApplicationContext());
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
        Actividad ultima_actividad = db.getUltimaActividad();
        numero.setText("0" + ultima_actividad.getTelefono());
        String indicaciones = ultima_actividad.getResponsable() + " tienes asignada la actividad " + ultima_actividad.getActividad() + " en el área de " + ultima_actividad.getArea_actividad() + ", hasta la fecha " + ultima_actividad.getFecha_final() + " y consistirá en " + ultima_actividad.getResumen_actividad() + ". " + "Tu supervisor será " + ultima_actividad.getSupervisor() + ". " + "Favor de revisar el sistema para más detalles.";
        mensaje.setText(indicaciones);
    }

    public void obtenerMensajesDesdeBD(View view)
    {
        /* Video: https://www.youtube.com/watch?v=u0Rbi69ZA0U */
        String base_url = "http://192.168.1.104:8000/";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url;
        HttpURLConnection conn;

        try
        {
            url = new URL(base_url);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
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
                texto += "ID: " + jsonObject.optString("id") + " ";
                texto += "TELEFONO: " + jsonObject.optString("telefono") + " ";
                texto += "EMPLEADO: " + jsonObject.optString("empleado") + " ";
                texto += "\n";
                mensaje.setText(texto);
            }
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
