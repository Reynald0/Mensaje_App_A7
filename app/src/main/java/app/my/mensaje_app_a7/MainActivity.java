package app.my.mensaje_app_a7;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

/** Esta aplicación requiere Android 6.0 para arriba */
public class MainActivity extends AppCompatActivity {

    private EditText numero;
    private EditText mensaje;
    private DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        numero = (EditText) findViewById(R.id.et_numero);
        mensaje = (EditText) findViewById(R.id.et_mensaje);
        db = new DatabaseHandler(getApplicationContext());
    }

    public void enviarSms(View view)
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(numero.getText().toString(), null, mensaje.getText().toString(),null,null);
        numero.getText().clear();
        mensaje.getText().clear();
        Toast.makeText(this, "SMS enviado", Toast.LENGTH_LONG).show();
    }

    public void rellenarSms(View view)
    {
        Actividad ultima_actividad = db.getUltimaActividad();
        numero.setText(ultima_actividad.getTelefono());
        String indicaciones = ultima_actividad.getResponsable() + " tienes asignada la actividad " + ultima_actividad.getActividad() +
                " en el área de " + ultima_actividad.getArea_actividad() +", hasta la fecha " + ultima_actividad.getFecha_final() +
                " y consistirá en " + ultima_actividad.getResumen_actividad() + ". " +
                "Tu supervisor será " + ultima_actividad.getSupervisor() + ". " +
                "Favor de revisar el sistema para más detalles.";
        mensaje.setText(indicaciones);
    }

}
