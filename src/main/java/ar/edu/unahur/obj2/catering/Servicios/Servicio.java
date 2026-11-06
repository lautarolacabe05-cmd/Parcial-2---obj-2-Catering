package ar.edu.unahur.obj2.catering.Servicios;

import ar.edu.unahur.obj2.catering.Excepciones.PrecioNegativoException;
import ar.edu.unahur.obj2.catering.Propuestas.Propuesta;

public class Servicio implements Propuesta {
    private final String nombre;
    private double precio;

    public Servicio(String nombre, double precio) {
        this.nombre = nombre;
        actualizarPrecio(precio);
    }

    public final void actualizarPrecio(double nuevoPrecio) {
        if (nuevoPrecio < 0) {
            throw new PrecioNegativoException("El precio no puede ser negativo");
        }
        this.precio = nuevoPrecio;
    }

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public double getPrecio() {
        return precio;
    }

    @Override
    public int getCantidadServicios() {
        return 1;
    }

    @Override
    public boolean tieneServicio(String nombreServicio) {
        return nombre.equals(nombreServicio);
    }
}
