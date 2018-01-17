package app.my.mensaje_app_a7;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper
{
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mensajeria_db";
    private static final String TABLE_NAME = "actividades";

    private static final String KEY_ID = "id";
    private static final String KEY_RESPONSABLE = "responsable";
    private static final String KEY_TELEFONO = "telefono";
    private static final String KEY_ACTIVIDAD = "actividad";
    private static final String KEY_AREA_ACTIVIDAD = "area_actividad";
    private static final String KEY_DESCRIPCION_ACTIVIDAD = "descripcion_actividad";
    private static final String KEY_FECHA_FINAL = "fecha_final";
    private static final String KEY_RESUMEN_ACTIVIDAD = "resumen_actividad";
    private static final String KEY_SUPERVISOR = "supervisor";

    public DatabaseHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String tabla_puntaje = "CREATE TABLE " + TABLE_NAME + "("
                + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + KEY_RESPONSABLE + " TEXT,"
                + KEY_TELEFONO + " TEXT,"
                + KEY_ACTIVIDAD + " TEXT,"
                + KEY_AREA_ACTIVIDAD + " TEXT,"
                + KEY_DESCRIPCION_ACTIVIDAD + " TEXT,"
                + KEY_FECHA_FINAL + " TEXT,"
                + KEY_RESUMEN_ACTIVIDAD + " TEXT,"
                + KEY_SUPERVISOR + " TEXT" + ");";
        db.execSQL(tabla_puntaje);

        String registros = "INSERT INTO "+ TABLE_NAME + " " +
                "(\"id\", \"responsable\", \"telefono\", \"actividad\", \"area_actividad\", \"descripcion_actividad\", \"fecha_final\", \"resumen_actividad\", \"supervisor\", \"ROWID\") " +
                "VALUES (1, 'Carlos Escobar Vázquez', 0449935901635, 'Desarrollo APP', 'SPyF', 'Crear una APP para Android', '10/12/2017', 'Hacer una APP para Android usando Java', 'Isaac Serrano', 1);\n";
        db.execSQL(registros);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public Actividad getUltimaActividad()
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME, new String[] { KEY_ID,
                        KEY_RESPONSABLE, KEY_TELEFONO, KEY_ACTIVIDAD, KEY_AREA_ACTIVIDAD, KEY_DESCRIPCION_ACTIVIDAD,
                        KEY_FECHA_FINAL, KEY_RESUMEN_ACTIVIDAD, KEY_SUPERVISOR}, null,
                null, null, null, ""+KEY_ID, "1");
        if (cursor != null)
            cursor.moveToFirst();

        Actividad actividad = new Actividad(cursor.getInt(0),
                                            cursor.getString(1),
                                            cursor.getString(2),
                                            cursor.getString(3),
                                            cursor.getString(4),
                                            cursor.getString(5),
                                            cursor.getString(6),
                                            cursor.getString(7),
                                            cursor.getString(8));

        return actividad;
    }

}
