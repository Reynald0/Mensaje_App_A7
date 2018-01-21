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
    private String mensajeTexto;

    DatosMensaje(String id, String numeroTelefono, String nombreEmpleado, String nombretarea, String fecha)
    {
        this.id = id;
        this.numeroTelefono = numeroTelefono;
        this.nombreEmpleado = nombreEmpleado;
        this.nombretarea = nombretarea;
        this.fecha = fecha;
        this.mensajeTexto = String.format("%s usted tiene asignada la tarea %s para la fecha %s. | SPF", nombreEmpleado, nombretarea, fecha);
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getNumeroTelefono()
    {
        return numeroTelefono;
    }

    public void setNumeroTelefono(String numeroTelefono)
    {
        this.numeroTelefono = numeroTelefono;
    }

    public String getNombreEmpleado()
    {
        return nombreEmpleado;
    }

    public void setNombreEmpleado(String nombreEmpleado)
    {
        this.nombreEmpleado = nombreEmpleado;
    }

    public String getNombretarea()
    {
        return nombretarea;
    }

    public void setNombretarea(String nombretarea)
    {
        this.nombretarea = nombretarea;
    }

    public String getFecha()
    {
        return fecha;
    }

    public void setFecha(String fecha)
    {
        this.fecha = fecha;
    }

    public String getMensajeTexto()
    {
        return mensajeTexto;
    }
}
