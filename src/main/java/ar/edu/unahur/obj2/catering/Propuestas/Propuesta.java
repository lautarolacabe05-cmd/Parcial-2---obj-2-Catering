package ar.edu.unahur.obj2.catering.Propuestas;

public interface Propuesta {
    double getPrecio();
    String getNombre();
    int getCantidadServicios();
    boolean tieneServicio(String nombreServicio);
}
