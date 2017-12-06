package app.my.mensaje_app_a7;

public class Actividad
{
    private int id;
    private String responsable;
    private String telefono;
    private String actividad;
    private String area_actividad;
    private String descripcion_actividad;
    private String fecha_final;
    private String resumen_actividad;
    private String supervisor;

    public Actividad()
    {

    }

    public Actividad(int id, String responsable, String telefono, String actividad, String area_actividad, String descripcion_actividad, String fecha_final, String resumen_actividad, String supervisor)
    {
        this.id = id;
        this.responsable = responsable;
        this.telefono = telefono;
        this.actividad = actividad;
        this.area_actividad = area_actividad;
        this.descripcion_actividad = descripcion_actividad;
        this.fecha_final = fecha_final;
        this.resumen_actividad = resumen_actividad;
        this.supervisor = supervisor;
    }

    public Actividad(String responsable, String telefono, String actividad, String area_actividad, String descripcion_actividad, String fecha_final, String resumen_actividad, String supervisor)
    {
        this.responsable = responsable;
        this.telefono = telefono;
        this.actividad = actividad;
        this.area_actividad = area_actividad;
        this.descripcion_actividad = descripcion_actividad;
        this.fecha_final = fecha_final;
        this.resumen_actividad = resumen_actividad;
        this.supervisor = supervisor;
    }

    public int getId() {
        return id;
    }

    public String getResponsable() {
        return responsable;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getActividad() {
        return actividad;
    }

    public String getArea_actividad() {
        return area_actividad;
    }

    public String getDescripcion_actividad() {
        return descripcion_actividad;
    }

    public String getFecha_final() {
        return fecha_final;
    }

    public String getResumen_actividad() {
        return resumen_actividad;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public void setArea_actividad(String area_actividad) {
        this.area_actividad = area_actividad;
    }

    public void setDescripcion_actividad(String descripcion_actividad) {
        this.descripcion_actividad = descripcion_actividad;
    }

    public void setFecha_final(String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public void setResumen_actividad(String resumen_actividad) {
        this.resumen_actividad = resumen_actividad;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }
}
