package app.my.mensaje_app_a7;

/**
 * Esa clase almacena los datos para enviar mensaje a la persona
 * Mensaje: "nombreEmpleado" usted tiene asignada la tarea "nombreTarea" para la fecha "fechaInicioTarea". | SPF
 */
public class DatosMensaje
{
    private String id;
    private String numeroTelefono;
    private String nombreEmpleado;
    private String nombretarea;
    private String fechaInicioTarea;
    private String mensajeTexto;

    DatosMensaje(String id, String numeroTelefono, String nombreEmpleado, String nombretarea, String fechaInicioTarea)
    {
        this.id = id;
        this.numeroTelefono = numeroTelefono;
        this.nombreEmpleado = nombreEmpleado;
        this.nombretarea = nombretarea;
        this.fechaInicioTarea = fechaInicioTarea;
        this.mensajeTexto = String.format("%s usted tiene asignada la tarea %s para la fecha %s. | SPF", nombreEmpleado, nombretarea, fechaInicioTarea);
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

    public String getFechaInicioTarea()
    {
        return fechaInicioTarea;
    }

    public void setFechaInicioTarea(String fechaInicioTarea)
    {
        this.fechaInicioTarea = fechaInicioTarea;
    }

    public String getMensajeTexto()
    {
        return mensajeTexto;
    }
}
