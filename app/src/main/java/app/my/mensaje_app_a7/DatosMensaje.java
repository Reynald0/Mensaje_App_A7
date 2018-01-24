package app.my.mensaje_app_a7;

/**
 * Esa clase almacena los datos para enviar mensaje a la persona
 * Mensaje: "nombreEmpleado" usted tiene asignada la tarea "nombreTarea" para la fecha "fecha". | SPF
 */
public class DatosMensaje
{
    private String id;
    private String numeroTelefono;
    private String nombreEmpleado;
    private String nombretarea;
    private String fecha;
    private String hora;
    private String mensajeTexto;


    DatosMensaje(String id, String numeroTelefono, String nombreEmpleado, String nombretarea, String fecha, String hora)
    {
        this.id = id;
        this.numeroTelefono = numeroTelefono;
        this.nombreEmpleado = nombreEmpleado;
        this.nombretarea = nombretarea;
        this.fecha = fecha;
        this.hora = hora;
        this.mensajeTexto = String.format("%s usted tiene asignada la tarea %s para la fecha %s a las %s. | SPF",
                nombreEmpleado, nombretarea, fecha, hora);
    }

    public String getId()
    {
        return id;
    }

    public String getNumeroTelefono()
    {
        return numeroTelefono;
    }

    public String getMensajeTexto()
    {
        return mensajeTexto;
    }
}
